import java.util.Random;

/**
 * Multiple Producers and Consumers using a Semaphore
 */
public class ProducerConsumer {
	public static void main(String[] args) {
		MySemaphore semaphore = new MySemaphore();
		Producer producer = new Producer(semaphore);
		Consumer consumer = new Consumer(semaphore);
		Runnable r1 = producer;
		Runnable r2 = consumer;

		Thread t1 = new Thread(r1, "Producer 1");
		Thread t2 = new Thread(r2, "Consumer 1");
		Thread t3 = new Thread(r1, "Producer 2");
		Thread t4 = new Thread(r2, "Consumer 2");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class MySemaphore {

	int ctr = 0;

	public synchronized void produce() {
		ctr++;
		System.out.println("Produced by " + Thread.currentThread().getName() + ", ctr = " + ctr);
		notifyAll();
	}

	public synchronized void consume() {
		while (ctr == 0) {
			try {
				System.out.println("Blocked consumer = " + Thread.currentThread().getName());
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		ctr--;
		System.out.println("Consumed by " + Thread.currentThread().getName() + ", ctr = " + ctr);
	}
}

class Producer implements Runnable {

	MySemaphore semaphore;

	Producer(MySemaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		Random r = new Random();
		for (int i = 1; i < 10; i++) {
			int waitTime = r.nextInt(2000);
			synchronized (this) {
				try {
					wait(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			semaphore.produce();
		}
	}
}

class Consumer implements Runnable {

	MySemaphore semaphore;

	Consumer(MySemaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		Random r = new Random();
		for (int i = 1; i < 10; i++) {
			int waitTime = r.nextInt(3000);
			synchronized (this) {
				try {
					wait(waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semaphore.consume();
			}
		}
	}
}
