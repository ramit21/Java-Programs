import java.util.Map;

// Run with VM paramter -XX:+PrintGC to see GC activiy and
// -XX:+HeapDumpOnOutOfMemoryError To print heap dump (hprof file) in case of heap dump out of memory
public class OutOfMemory {

	public OutOfMemory() {
		//Special function to catch throwables that were not caught anywhere, and were on the way to the JVM
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(final Thread t, final Throwable e) {
				System.out.println("CAUGHT: " + e.getMessage());
				Map<Thread, StackTraceElement[]> stackTraces = Thread.getAllStackTraces();
				for(Thread thread : stackTraces.keySet()){
					System.out.println("Trace information for the thread " + thread.getId()+ ", "+ thread.getName());
					   StackTraceElement[] trace = stackTraces.get(thread);

					   for(int i=0; i < trace.length; i++){
					    System.out.println(trace[i]);
					   }
					   System.out.println();
				}				
			}
		});
	}

	public static void main(String[] args) throws Exception {
		OutOfMemory memoryTest = new OutOfMemory();
		// int i = 4/0;
		memoryTest.generateOOM();
	}

	public void generateOOM() throws Exception {
		int iteratorValue = 20;
		System.out.println("\n=================> OOM test started..\n");
		for (int outerIterator = 1; outerIterator < 20; outerIterator++) {
			System.out.println("Iteration " + outerIterator + " Free Mem: " + Runtime.getRuntime().freeMemory());
			int loop1 = 2;
			int[] memoryFillIntVar = new int[iteratorValue];
			// fill memoryFillIntVar array in loop..
			do {
				memoryFillIntVar[loop1] = 0;
				loop1--;
			} while (loop1 > 0);
			iteratorValue = iteratorValue * 5;
			System.out.println("\nRequired Memory for next loop: " + iteratorValue);
			Thread.sleep(1000);
		}
	}
}
