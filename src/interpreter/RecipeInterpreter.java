package interpreter;

import interpreter.model.Recipe;
import interpreter.parser.HTMLParser;
import interpreter.parser.RecipeParser;

import java.io.File;
import java.io.IOException;

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
		
		HTMLParser parser = new HTMLParser(inputFile);
		RecipeParser recipeParser = new RecipeParser(parser.getText());
		
		Recipe mainRecipe = new Recipe();
		//mainRecipe.addAllIngredients(recipeParser.getIngredients());
		//mainRecipe.setServings(recipeParser.getServings());
		
		System.out.println(recipeParser.getFullText());
		System.out.println("");
		
		for (String s : recipeParser.removeThisMethod(recipeParser.getFullText())) {
			System.out.println(s);
		}
		
	}
}
