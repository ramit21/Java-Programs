package code;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Read from multiple files concurrently and write to a single file
 */
public class FileWritingUsingBarrier {
	
	public static void main(String[] args) {
		//All threads to share string buffer for output and the cyclic bariier
		File file1 = new File("src/main/resources/storyData1.txt");
		File file2 = new File("src/main/resources/storyData2.txt");
		File file3 = new File("src/main/resources/storyData3.txt");
		
		File file4 = new File("src/main/resources/output/storyDataOutput.txt");
		
		StringBuffer buf = new StringBuffer();
		ExecutorService executor = Executors.newFixedThreadPool(3);
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
		finally{
			executor.shutdown();
		}
	}
}

class FileReaderBarrier implements Runnable{
	
	private int id;
	private BufferedReader reader;
	private StringBuffer buf;
	private CyclicBarrier barrier;
	
	public FileReaderBarrier(int id, BufferedReader reader, StringBuffer buf, CyclicBarrier barrier){
		this.id = id;
		this.reader= reader;
		this.buf = buf;
		this.barrier = barrier;
	}
	
	@Override
	public void run() {
		String strLine = null;
		try {
			while ((strLine = reader.readLine()) != null) {
				buf.append(strLine + "\n");
				System.out.println("Writing thread: " + id);
				Thread.sleep(200);
			}
			System.out.println("Thread done writing: " + id);
			reader.close();
			barrier.await();
			System.out.println("Thread "+id+" done waiting at barrier !!");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}

class FileWriterBarrier implements Runnable{
	
	private BufferedWriter bufferedWriter;
	private StringBuffer buf;
	
	public FileWriterBarrier(BufferedWriter bufferedWriter, StringBuffer buf) {
		this.bufferedWriter = bufferedWriter;
		this.buf = buf;
	}

	@Override
	public void run() {
		System.out.println("Barrier executed succesfully. ");
		try {
			bufferedWriter.write(buf.toString());
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}