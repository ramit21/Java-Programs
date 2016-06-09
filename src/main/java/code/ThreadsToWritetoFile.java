package code;

import java.io.*;

//Three threads having different values, all should write to a file in a sequential order (ie 123456789)
public class ThreadsToWritetoFile {
	
	public static void main(String[] args) {
		MyFileWriter obj = new MyFileWriter();
		NumberHolder obj1 = new NumberHolder();
		obj1.setMyFileWriter(obj);
		Thread t1 = new Thread(obj1, "T1");
		Thread t2 = new Thread(obj1, "T2");
		Thread t3 = new Thread(obj1, "T3");
		t1.start();
		t2.start();
		t3.start();
		// make main wait till all the threads hv executed
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {

		}

		obj.writeToFile();
		System.out.println(" final array = " + obj.finalArr[6]);
	}
}

class NumberHolder implements Runnable {
	int arr[];
	MyFileWriter obj;
	int arr1[] = { 1, 4, 7 };
	int arr2[] = { 2, 5, 8 };
	int arr3[] = { 3, 6, 9 };

	public void setMyFileWriter(MyFileWriter obj) {
		this.obj = obj;
	}

	public void run() {

		int arr[] = { 0 };
		String threadName = Thread.currentThread().getName();
		if ("T1".equalsIgnoreCase(threadName)) {
			arr = arr1;
		}
		if ("T2".equalsIgnoreCase(threadName)) {
			arr = arr2;
		}
		if ("T3".equalsIgnoreCase(threadName)) {
			arr = arr3;
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.println("thread: " + threadName + ", data = " + arr[i]);
			obj.insert(arr[i]);
		}
	}
}

class MyFileWriter {

	int finalArr[] = new int[10];
	int curIndex = 0;

	public synchronized void insert(int no) {

		while (finalArr[curIndex] != (no - 1)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finalArr[++curIndex] = no;
		notifyAll();
	}

	public void writeToFile() {
		File file = new File("src/main/resources/ThreadOutputFile.txt");
		try {
			FileWriter fs = new FileWriter(file);
			for (int i = 1; i < 10; i++) {
				fs.write("" + finalArr[i]); // prepending a blank to ints is imp
			}
			fs.close();
		} catch (IOException e) {

		}
	}

}
