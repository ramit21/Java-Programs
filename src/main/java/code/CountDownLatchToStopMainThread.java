package code;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchToStopMainThread {
	
	public static void main(String[] args) {
		
		final CountDownLatch cd = new CountDownLatch(3);
		
		ExecutorService ex = Executors.newCachedThreadPool();
		//start 3 threads
		for(int i= 0; i<3; i++){
			
			ex.execute(new Runnable() {
				
				@Override
				public void run() {
					String name = Thread.currentThread().getName();
					System.out.println("Starting thread "+ name);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					cd.countDown();
					System.out.println("Finished thread "+ name);
					
				}
			});
		}
		//This await along with countdown in above threads will make main method to wait for all threads to execute first
		try {
			cd.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ex.shutdown();
		System.err.println("Main thread finishes and countlatch count is: "+ cd.getCount());
	}

}
