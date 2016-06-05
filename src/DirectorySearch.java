import java.io.File;

/**
 * Directory search
 *
 */
public class DirectorySearch {

	public static void findFile(File file, String fileName) {
		File[] fileList = file.listFiles();
		if (fileList != null) {
			
			for (File curFile : fileList) {
				
				if (curFile.isDirectory()) {
					System.out.println("Lokking into "+curFile.getName());
					findFile(curFile, fileName);
				} else if (curFile.getName().equalsIgnoreCase(fileName)) {
					System.out.println(file.getParent());
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		findFile(new File("C:\\"), "temp.txt");
	}
}
