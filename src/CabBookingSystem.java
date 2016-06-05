import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Taxi booking system using a BlockingQueue
 *
 */
public class CabBookingSystem {

	public static void main(String[] args) {
		//BlockingQueue's take and put methods block in case of full/empty queue. 
		//Other queue methods like add() throw exception if full.
		BlockingQueue<CabRequest> requestQueue = new LinkedBlockingDeque<CabRequest>(4);
		Taxi taxi1 = new Taxi(1, requestQueue);
		Taxi taxi2 = new Taxi(2, requestQueue);
		Taxi taxi3 = new Taxi(3, requestQueue);

		TaxiController tc = new TaxiController();
		tc.addTaxi(taxi1);
		tc.addTaxi(taxi2);
		tc.addTaxi(taxi3);

		tc.startAllTaxis();

		try {
			CabRequest req1 = new CabRequest(1, 0, 5);
			CabRequest req2 = new CabRequest(2, 6, 3);
			CabRequest req3 = new CabRequest(3, 5, 8);
			requestQueue.put(req1);
			requestQueue.put(req2);
			requestQueue.put(req3);

			CabRequest req4 = new CabRequest(4, 6, 9);
			CabRequest req5 = new CabRequest(5, 2, 6);
			CabRequest req6 = new CabRequest(6, 5, 0);

			requestQueue.put(req4);
			requestQueue.put(req5);
			requestQueue.put(req6);

			CabRequest req7 = new CabRequest(7, 1, 2);
			CabRequest req8 = new CabRequest(8, 5, 3);

			requestQueue.put(req7);
			requestQueue.put(req8);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class TaxiController {
	private List<Taxi> taxiList = new ArrayList<Taxi>();

	public void addTaxi(Taxi taxi) {
		taxiList.add(taxi);
	}

	public void startAllTaxis() {
		for (Taxi taxi : taxiList) {
			taxi.setStopped(false);
			Thread t = new Thread(taxi);
			t.start();
		}
	}

	public void stopAllTaxis() {
		for (Taxi taxi : taxiList) {
			taxi.setStopped(true);
		}
	}
}

class Taxi implements Runnable {

	int taxiId;
	BlockingQueue<CabRequest> queue;
	public boolean isStopped = false;
	int curStop;

	public Taxi(int taxiId, BlockingQueue<CabRequest> queue) {
		this.taxiId = taxiId;
		this.queue = queue;
		curStop = 0;
	}

	public void setTaxiId(int taxiId) {
		this.taxiId = taxiId;
	}

	public void setQueue(BlockingQueue<CabRequest> queue) {
		this.queue = queue;
	}

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}

	public int getTaxiId() {
		return taxiId;
	}

	public BlockingQueue<CabRequest> getQueue() {
		return queue;
	}

	public boolean isStopped() {
		return isStopped;
	}

	@Override
	public void run() {
		while (!isStopped) {
			try {
				if (!queue.isEmpty()) {
					CabRequest cabRequest = queue.take();
					int fare = cabRequest.getFare();
					Thread.sleep(100 * fare);   // wait as per the distance

					curStop = cabRequest.getDest();
					System.out.println("Cab request." + cabRequest.getId() + " serviced by cab no." + taxiId + " for fare = " + fare);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//This class can also be named person
class CabRequest {
	int id;
	int source;
	int dest;

	public CabRequest(int id, int source, int dest) {
		this.id = id;
		this.source = source;
		this.dest = dest;
	}

	public int getFare() {
		return Math.abs(dest - source) * 10;
	}

	public int getId() {
		return id;
	}

	public int getSource() {
		return source;
	}

	public int getDest() {
		return dest;
	}

}