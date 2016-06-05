import java.util.ArrayList;
import java.util.List;

/**
 * Ques: 2 Threads, 1 produce Hello, other produces World 5 times each. Print Hello World 5 times.
 * Perfectly structured code for Producer consumer types of problems.
 * Producer consuners are Runnables that contain a buffer, that call produce and consume functions on the buffer.
 * Buffer functions are synchronized on its 'this'
 */

public class HelloWorldProducer {
	
	public static String HELLO = "Hello";
	public static String WORLD = "World";
	
	public static void main(String[] args) throws InterruptedException {
		Buffer buf = new Buffer();
		StringProducer helloProducer = new StringProducer("T1", buf, 1000, HelloWorldProducer.HELLO);
		StringProducer worldProducer = new StringProducer("T2", buf, 500, HelloWorldProducer.WORLD);
		StringConsumer consumer = new StringConsumer("T3", buf, 10);
		
		new Thread(helloProducer).start();
		new Thread(worldProducer).start();
		new Thread(consumer).start();
	}
}

class Buffer{
	List<String> helloList;
	List<String> worldList;
	
	public Buffer(){
		helloList = new ArrayList<String>();
		worldList = new ArrayList<String>();
	}
	
	public synchronized void addValue(String str){
		if(HelloWorldProducer.HELLO.equals(str)){
			helloList.add(str);
		}else if(HelloWorldProducer.WORLD.equals(str)){
			worldList.add(str);
		}
		notify();
	}
	
	public synchronized void consume(){
		while(helloList.isEmpty() || worldList.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(helloList.remove(0) + " " + worldList.remove(0));
	}
}

class StringProducer implements Runnable{
	
	private String threadId;
	private Buffer buf;
	private String value;
	private int sleepTime;
	
	public StringProducer(String threadId, Buffer buf, int sleepTime, String value){
		this.threadId = threadId;
		this.buf = buf;
		this.sleepTime = sleepTime;
		this.value = value;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++){
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread "+ threadId + " generating value " + value);
			buf.addValue(value);
		}
	}
}

class StringConsumer implements Runnable{
	
	private String threadId;
	private Buffer buf;
	private int sleepTime;
	
	public StringConsumer(String threadId, Buffer buf, int sleepTime){
		this.threadId = threadId;
		this.buf = buf;
		this.sleepTime = sleepTime;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++){
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buf.consume();
			System.out.println("Thread "+ threadId + " consumed values.");
		}
	}
}
