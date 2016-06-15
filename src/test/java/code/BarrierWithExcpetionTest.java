package code;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BarrierWithExcpetionTest {

	private CyclicBarrier cb;
	private ExecutorService executor;

	@Before
	public void setup() {
		cb = new CyclicBarrier(6, new BarrierCallBack());
		executor = Executors.newCachedThreadPool();
	}

	@Test
	public void testBarrierException() {

		Callable<Integer> thread1 = new Divider(1, 1, 1000, cb);
		Callable<Integer> thread2 = new Divider(2, 10, 2000, cb);
		Callable<Integer> thread3 = new Divider(3, 20, 1500, cb);
		Callable<Integer> thread4 = new Divider(4, 0, 500, cb);
		Callable<Integer> thread5 = new Divider(5, 40, 10, cb);
		Callable<Integer> thread6 = new Divider(6, 50, 1500, cb);

		Future<Integer> task1 = executor.submit(thread1);
		Future<Integer> task2 = executor.submit(thread2);
		Future<Integer> task3 = executor.submit(thread3);
		Future<Integer> task4 = executor.submit(thread4);
		Future<Integer> task5 = executor.submit(thread5);
		Future<Integer> task6 = executor.submit(thread6);

		int resultArr[] = new int[6];
		try {
			resultArr[0] = task1.get();
			resultArr[1] = task2.get();
			resultArr[2] = task3.get();
			resultArr[3] = task4.get();
			resultArr[4] = task5.get();
			resultArr[5] = task6.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals("Mismatch from task1", resultArr[0], 100);
		Assert.assertEquals("Mismatch from task2", resultArr[1], 10);
		Assert.assertEquals("Mismatch from task3", resultArr[2], 5);
		Assert.assertEquals("Mismatch from task4", resultArr[3], -1);
		Assert.assertEquals("Mismatch from task5", resultArr[4], 2);
		Assert.assertEquals("Mismatch from task6", resultArr[5], 2);
	}

	@After
	public void tearDown() {
		executor.shutdown();
	}
}
