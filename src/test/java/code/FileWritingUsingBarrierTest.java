package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileWritingUsingBarrierTest {
	
	private ExecutorService executor;
	private File file1;
	private File file2;
	private File file3;
	private File file4;

	@Before
	public void setup() {
		executor = Executors.newFixedThreadPool(3);
		file1 = new File("src/test/resources/storyData1.txt");
		file2 = new File("src/test/resources/storyData2.txt");
		file3 = new File("src/test/resources/storyData3.txt");
		file4 = new File("src/test/resources/output/storyDataOutput.txt");
	}

	@Test
	public void barrierFileWriteTest(){
		StringBuffer buf = new StringBuffer();
		try {
			FileWriterBarrier writer = new FileWriterBarrier(new BufferedWriter(new FileWriter(file4)), buf);
			
			CyclicBarrier barrier = new CyclicBarrier(3, writer);
			FileReaderBarrier reader1 = new FileReaderBarrier(1, new BufferedReader(new FileReader(file1)), buf, barrier);
			FileReaderBarrier reader2 = new FileReaderBarrier(2, new BufferedReader(new FileReader(file2)), buf, barrier);
			FileReaderBarrier reader3 = new FileReaderBarrier(3, new BufferedReader(new FileReader(file3)), buf, barrier);	
			
			executor.execute(reader1);
			executor.execute(reader2);
			executor.execute(reader3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@After
	public void tearDown() {
		executor.shutdown();
	}
}
