package code;


/**
 * Given 3 threads that have some data array each. WAP to print the data elements sequentially.
 * ie. for data as given below, Thread1 writes a, then Thread2 writes b, then Thread3 writes c, Thread 1 writes d and so on
 */
public class SequentialThreads {

	public static void main(String[] args) {
		BufferController bufController = new BufferController();
		BufferProducer r1 = new BufferProducer(1, 1000, new String[] { "a", "d", "g" }, bufController);
		BufferProducer r2 = new BufferProducer(2, 5000, new String[] { "b", "e", "h" }, bufController);
		BufferProducer r3 = new BufferProducer(3, 3000, new String[] { "c", "f", "i" }, bufController);

		r1.setAlive(true);
		r1.setSuccessor(r2);
		r2.setSuccessor(r3);
		r3.setSuccessor(r1);

		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);

		t1.start();
		t2.start();
		t3.start();

	}
}

class BufferProducer implements Runnable {
	private final int threadId;
	private BufferProducer successor;
	private int curCounter;
	private final String[] data;
	private boolean isAlive;
	private final int waitTime;
	private final BufferController bufcontroller;

	public BufferProducer(int threadId, int waitTime, String[] data, BufferController bufcontroller) {
		this.threadId = threadId;
		this.data = data;
		this.waitTime = waitTime;
		this.bufcontroller = bufcontroller;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < data.length; i++) {
				Thread.sleep(waitTime);
				bufcontroller.produce(this, data[i]);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public BufferProducer getSuccessor() {
		return successor;
	}

	public void setSuccessor(BufferProducer successor) {
		this.successor = successor;
	}

	public int getCurCounter() {
		return curCounter;
	}

	public void setCurCounter(int curCounter) {
		this.curCounter = curCounter;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public String[] getData() {
		return data;
	}

	public BufferController getBufcontroller() {
		return bufcontroller;
	}

	public int getThreadId() {
		return threadId;
	}

}

class BufferController {

	public synchronized void produce(BufferProducer producer, String data) throws InterruptedException {
		while (!producer.isAlive()) {
			wait();
		}
		System.out.println("Thread " + producer.getThreadId() + " prints => " + data);
		producer.setAlive(false);
		producer.getSuccessor().setAlive(true);
		notifyAll();
	}
}
