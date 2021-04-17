package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Read a file using Java 8 syntax (NIO package), and print the words that start with capital letter.
 * Below code is faster than traditional O(n^2) BufferedReader approach.
 */
public class Java8FileReader {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/main/resources/paragraphs.txt");
		Stream<String> lines = Files.lines(path);
		String data = lines.collect(Collectors.joining("\n"));
		
		Pattern p = Pattern.compile("[A-Z][a-z]*");
		Matcher m = p.matcher(data);

		while(m.find()){
		    System.out.println(data.substring(m.start(), m.end()));
		}
		
		
	}
}
