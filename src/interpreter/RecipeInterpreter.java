package interpreter;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RecipeInterpreter {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new IllegalArgumentException("Usage: java -jar "
					+ "RecipeInterpreter.jar <input_file> <output_file>");
		}
		
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		
		if (!inputFile.exists()) { 
			throw new IllegalArgumentException("Input file does not exist!");
		}
		
		if (outputFile.exists()) { 
			throw new IllegalArgumentException("Output file already exists!.");
		}
		
		Document doc = Jsoup.parse(inputFile, "UTF-8");
		String rawText = doc.text();
	}
}
