package interpreter;

import interpreter.model.Ingredient;
import interpreter.model.Recipe;
import interpreter.parser.HTMLParser;
import interpreter.parser.RecipeParser;
import interpreter.parser.converter.IngredientConverter;
import interpreter.parser.converter.ServingsConverter;
import interpreter.parser.converter.TimesConverter;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RecipeInterpreter {

	public static IngredientConverter ingConverter = new IngredientConverter();
	public static ServingsConverter servingsConverter = new ServingsConverter();
	public static TimesConverter timesConverter = new TimesConverter();

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

		/*
		 * if (outputFile.exists()) { throw new
		 * IllegalArgumentException("Output file already exists!."); }
		 */

		HTMLParser parser = new HTMLParser(inputFile);
		RecipeParser recipeParser = new RecipeParser(parser.getText());
		Recipe recipe = new Recipe();

		// Parse the servings, use the first one (it's most of the times the
		// correct one)
		List<String> servingsList = recipeParser.parseText(
				recipeParser.getFullText(), RecipeParser.SERVINGS_PARSER);
		if (!servingsList.isEmpty()) {
			recipe.setServings(servingsConverter.convert(servingsList.get(0)));
		}

		// Parse all the ingredients using the lists
		for (String s : recipeParser.parseListItems(parser.getListedItems(),
				RecipeParser.LIST_ITEMS_INGREDIENTS_PARSER)) {
			Ingredient i = ingConverter.convert(s);
			if (!i.getName().trim().isEmpty()) {				
				recipe.addIngredient(i);
			}
		}

		// Parse all the ingredients in the whole text
		for (String s : recipeParser.parseText(recipeParser.getFullText(),
				RecipeParser.STRING_ITEMS_INGREDIENTS_PARSER)) {
			Ingredient i = ingConverter.convert(s);
			if (!i.getName().trim().isEmpty()) {				
				recipe.addIngredient(i);
			}
		}

		// Parse all the ingredients in the whole text
		for (String s : recipeParser.parseText(recipeParser.getFullText(),
				RecipeParser.TIMES_PARSER)) {
			recipe.setTime(timesConverter.convert(s));
		}

		recipe.writeTo(outputFile);
		System.out.println("Finished writing recipe!");
	}
}
