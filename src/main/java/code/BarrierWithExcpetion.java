package code;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * Use of Callable, Barrier timeout, and Exception handling when 1 of the thread throws exception
 *
 */
public class BarrierWithExcpetion {
	

	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(6,new BarrierCallBack());
		
		Callable<Integer> thread1 = new Divider(1, 1, 1000, cb);
		Callable<Integer> thread2 = new Divider(2, 10, 2000, cb);
		Callable<Integer> thread3 = new Divider(3, 20, 1500, cb);
		Callable<Integer> thread4 = new Divider(4, 0, 500, cb);
		Callable<Integer> thread5 = new Divider(5, 40, 10, cb);
		Callable<Integer> thread6 = new Divider(6, 50, 1500, cb);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> task1 =  executor.submit(thread1);
		Future<Integer> task2 =  executor.submit(thread2);
		Future<Integer> task3 =  executor.submit(thread3);
		Future<Integer> task4 =  executor.submit(thread4);
		Future<Integer> task5 =  executor.submit(thread5);
		Future<Integer> task6 =  executor.submit(thread6);
		
		try {
			System.out.println("From main, computed values: " + task1.get() + ", " + task2.get()
					+ ", " + task3.get() + ", " + task4.get() + ", " + task5.get() + ", " + task6.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
	
 
}

class Divider implements Callable<Integer>{
	
	final int id;
	final int denom;
	final int sleepTime;
	final CyclicBarrier cb;
	
	public Divider(int id, int denom, int sleepTime, CyclicBarrier cb){
		this.id = id;
		this.denom = denom;
		this.sleepTime = sleepTime;
		this.cb = cb;
	}
	
	@Override
	public Integer call() throws Exception {
		System.out.println("Inside Thread : " + id);
		Thread.sleep(denom);
		int result = -1;
		try{
			result = 100/denom;
		}
		catch(Exception e){
			System.out.println("Divide by 0 Exception by thread :"+id);
		}
		finally{
			cb.await(5,TimeUnit.SECONDS);
		}
		
		//Try below without try catch as above finally to see the Barrier exception
		//cb.await(5,TimeUnit.SECONDS);
		System.out.println("Executed Thread : " + id);
		return result;
	}
}

class BarrierCallBack implements Runnable{
	public void run() {
		System.out.println(" YoHooooo Barrier completed execution");
	}
}
