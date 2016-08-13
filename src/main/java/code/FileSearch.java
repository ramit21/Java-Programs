package code;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Directory search (using sequential and parallel search) File name can be a
 * regex If parallal search doesn't complete in given time, then time out
 * 
 */
public class FileSearch {

	private volatile String result;

	private void sequentialSearch(File file, String fileRegex) {
		File[] fileList = file.listFiles();
		if (fileList != null) {
			for (File curFile : fileList) {
				if (curFile.isDirectory()) {
					System.out.println("Looking into " + curFile.getName());
					sequentialSearch(curFile, fileRegex);
				} else if (curFile.getName().matches(fileRegex)) {
					System.out.println(">>>>> Found " + curFile.getName() + " in directory " + curFile.getParent());
					break;
				}
			}
		}
	}

	private void parallelSearch(File file, String fileRegex) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		Future<?> task = executorService.submit(this.new FileSearchCallable(file, fileRegex, executorService));
		try {
			task.get(10, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			System.out.println("EXCEPTION = " + e.getMessage());
		} finally {
			executorService.shutdownNow();
		}

		if (result != null) {
			System.out.println(">>> File Found in parent === " + result);
		} else {
			System.out.println(">>> File not found");
		}
	}

	public static void main(String[] args) {
		FileSearch fileSearchObj = new FileSearch();
		String parentDirectory = "C:\\tv series";
		String regex = "(.*).pdf";
		// Sequential search
		long startTime = System.currentTimeMillis();
		fileSearchObj.sequentialSearch(new File(parentDirectory), regex);
		long endTime = System.currentTimeMillis();
		System.out.println("Sequential search completed in " + (endTime - startTime));

		// Parallal search
		startTime = System.currentTimeMillis();
		fileSearchObj.parallelSearch(new File(parentDirectory), regex);
		endTime = System.currentTimeMillis();
		System.out.println("Parallel search completed in " + (endTime - startTime));

	}

	class FileSearchCallable implements Callable<String> {

		private final File file;
		private final String fileRegex;
		private final ExecutorService executorService;

		public FileSearchCallable(File file, String fileRegex, ExecutorService executorService) {
			this.file = file;
			this.fileRegex = fileRegex;
			this.executorService = executorService;
		}

		@Override
		public String call() {
			System.out.println("Checking " + file.getName());
			File[] fileList = file.listFiles();
			if (fileList != null) {
				List<Callable<String>> taskList = new ArrayList<>();
				for (File curFile : fileList) {
					if (!curFile.isDirectory() && result == null && curFile.getName().matches(fileRegex)) {
						result = curFile.getParent();
						return result;
					} else if (result == null && curFile.isDirectory()) {
						taskList.add(new FileSearchCallable(curFile, fileRegex, executorService));
					}
					try {
						executorService.invokeAll(taskList);
					} catch (Exception e) {
						System.out.println("Exception = " + e.getMessage());
					}
				}
			}
			return null;
		}
	}

}
