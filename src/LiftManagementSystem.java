import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LiftManagementSystem {
	
	public static void main(String[] args) throws InterruptedException {
		Lift lift1 = new Lift(1,0);
		Lift lift2 = new Lift(2,3);
		LiftController lc = new LiftController();
		lc.addLift(lift1);
		lc.addLift(lift2);
		Passanger p1 = new Passanger(1, 3, 6, lc);
		Passanger p2 = new Passanger(2, 7, 4, lc); 
		Passanger p3 = new Passanger(3, 4, 5, lc);
		p1.pressUp();
		p2.pressDown();
		p3.pressUp();
		Thread.sleep(7000);
		Passanger p4 = new Passanger(4, 5, 1, lc);
		p4.pressDown();
	}
	
}

enum LiftState{
	UP,
	DOWN,
	IDLE,
	OPEN;
}

class Lift implements Runnable{
	private volatile int liftId;
	private volatile LiftState state;
	private volatile List<Integer> floorsToVisit;
	private volatile int curFloor;
	
	public Lift(int liftId, int curFloor){
		this.liftId = liftId;
		this.curFloor = curFloor;
		state = LiftState.IDLE;
		floorsToVisit = new LinkedList<Integer>();
	}
	
	public void addFloorsToVisit(int from, int to){
		floorsToVisit.add(from);
		floorsToVisit.add(to);
		//Collections.sort(floorsToVisit);    //TODO: this needs more work, weather to add nos in bw or in order
	}
	
	private void displayFloorsToVisit(){
		System.out.print("Lift No. " + liftId + " to visit floors : ");
		for(int floor: floorsToVisit){
			System.out.print(floor + " ");
		}
		System.out.println();
	}
	
	@Override
	public void run(){
		try{
			while(!floorsToVisit.isEmpty()){
				if(curFloor == floorsToVisit.get(0)){
					System.out.println("Lift No. " + liftId + " opened on floor no. " + curFloor);
					Thread.sleep(1000);
					floorsToVisit.remove(0);					
				}else{
					if(curFloor < floorsToVisit.get(0)){
						state = LiftState.UP;
						curFloor++;
						System.out.println("Lift No. " + liftId + " moved up to " + curFloor);
					}
					if(curFloor > floorsToVisit.get(0)){
						state = LiftState.DOWN;
						curFloor--;
						System.out.println("Lift No. " + liftId + " down up to " + curFloor);
					}
				}
					
				Thread.sleep(1000);
			}
			if(floorsToVisit.isEmpty()){
				state = LiftState.IDLE;
				System.out.println("Lift No. " + liftId + " is now idle at " + curFloor);
			}
		}
		catch(Exception ex){
			
		}
		
	}

	public LiftState getState() {
		return state;
	}

	public void setState(LiftState state) {
		this.state = state;
	}

	public List<Integer> getFloorsToVisit() {
		return floorsToVisit;
	}

	public int getCurFloor() {
		return curFloor;
	}

	public void setCurFloor(int curFloor) {
		this.curFloor = curFloor;
	}
	
	public int getLiftId() {
		return liftId;
	}

	public void setLiftId(int liftId) {
		this.liftId = liftId;
	}

	@Override
	public String toString() {
		return "Lift : "+liftId + ", state = "+state;
	}
}

class Passanger{
	int id;
	int curFloor;
	int destFloor;
	private LiftController liftController;
	
	public Passanger(int id, int curFloor, int destFloor, LiftController liftController){
		this.id = id;
		this.curFloor = curFloor;
		this.destFloor = destFloor;
		this.liftController = liftController;
	}
	
	public void pressUp(){
		Lift lift = liftController.requestUp(this);
		System.out.println("Passenger. "+id+" assigned to Lift."+lift.getLiftId());
	}
	
	public void pressDown(){
		Lift lift = liftController.requestDown(this);
		System.out.println("Passenger. "+id+" assigned to Lift."+lift.getLiftId());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurFloor() {
		return curFloor;
	}

	public void setCurFloor(int curFloor) {
		this.curFloor = curFloor;
	}

	public int getDestFloor() {
		return destFloor;
	}

	public void setDestFloor(int destFloor) {
		this.destFloor = destFloor;
	}
	
}

class LiftController{
	private Executor executor;
	private List<Lift> lifts;
	
	public LiftController(){
		lifts = new ArrayList<Lift>();
		executor = Executors.newCachedThreadPool(); //no of thread should be equal to no of lifts available
	}
	
	public void addLift(Lift lift){
		lifts.add(lift);
	}
	
	/**
	 * Logic for assigning the best lift (in sequential order):
	 * 1. If lift is idle and on same floor.
	 * 2. If a lift is moving to this floor as last stop
	 * 3. If a lift is idle on some other floor
	 * 4. If a lift is moving towards this floor (can be enhanced for loading factor)
	 */
	public Lift requestUp(Passanger p){
		Lift lift = idleOnCurFLoor(p.getCurFloor(),p.getDestFloor());
		if(lift == null){
			lift = stoppingOnCurFLoor(p.getCurFloor(),p.getDestFloor());
		}
		if(lift == null){
			lift = idleNotOnCurFLoor(p.getCurFloor(),p.getDestFloor());
		}
		if(lift == null){
			lift = movingUptoCurFLoor(p.getCurFloor(),p.getDestFloor());
		}
		if(lift!=null){
			if(lift.getState() == LiftState.IDLE){
				executor.execute(lift);
			}
		}
		return lift;
	}
	
	public Lift requestDown(Passanger p){
		Lift lift = idleOnCurFLoor(p.getCurFloor(),p.getDestFloor());
		if(lift == null){
			lift = stoppingOnCurFLoor(p.getCurFloor(),p.getDestFloor());
		}
		if(lift == null){
			lift = idleNotOnCurFLoor(p.getCurFloor(),p.getDestFloor());
		}
		if(lift == null){
			lift = movingDowntoCurFLoor(p.getCurFloor(),p.getDestFloor());
		}
		if(lift!=null){
			if(lift.getState() == LiftState.IDLE){
				executor.execute(lift);
			}
		}
		return lift;
	}
	
	private Lift idleOnCurFLoor(int curFloor, int destFloor){
		for(Lift lift : lifts){
			if((lift.getState() == LiftState.IDLE) && lift.getCurFloor() == curFloor){
				lift.addFloorsToVisit(curFloor,destFloor);
				return lift;
			}
		}
		return null;
	}
	
	private Lift stoppingOnCurFLoor(int curFloor, int destFloor){
		for(Lift lift : lifts){
			if((lift.getState() == LiftState.UP || lift.getState() == LiftState.DOWN) && 
					lift.getFloorsToVisit().get(lift.getFloorsToVisit().size()-1) == curFloor){
				lift.addFloorsToVisit(curFloor,destFloor);
				return lift;
			}
		}
		return null;
	}
	
	private Lift movingUptoCurFLoor(int curFloor, int destFloor){
		for(Lift lift : lifts){
			if((lift.getState() == LiftState.UP) && lift.getCurFloor() < curFloor){
				lift.addFloorsToVisit(curFloor,destFloor);
				return lift;
			}
		}
		return null;
	}
	
	private Lift movingDowntoCurFLoor(int curFloor, int destFloor){
		for(Lift lift : lifts){
			if((lift.getState() == LiftState.DOWN) && lift.getCurFloor() > curFloor){
				lift.addFloorsToVisit(curFloor,destFloor);
				return lift;
			}
		}
		return null;
	}
	
	private Lift idleNotOnCurFLoor(int curFloor, int destFloor){
		for(Lift lift : lifts){
			if((lift.getState() == LiftState.IDLE) && lift.getCurFloor() != curFloor){
				lift.addFloorsToVisit(curFloor,destFloor);
				return lift;
			}
		}
		return null;
	}
	
}
