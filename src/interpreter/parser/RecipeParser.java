package interpreter.parser;

import interpreter.model.Ingredient;
import interpreter.regex.RecipeRegexList;

import java.util.List;
import java.util.regex.Pattern;

public class RecipeParser {

	private String recipeText;
	
	private static RecipeRegexList servingsParser = new RecipeRegexList();
	
	static {
		// Find all the numbers with five or less words surrounding them 
		servingsParser.addPattern(Pattern.compile(
				"([a-zA-Z0-9]+\\s){0,5}[0-9]+(\\s[a-zA-Z0-9]+){0,5}"));
	}
	
	public RecipeParser(String recipeText) {
		this.recipeText = recipeText;
	}
	
	public String getFullText() {
		return recipeText;
	}

	public List<Ingredient> getIngredients() {
		return null;
	}
	
	public int getServings() {
		return 0;
	}
	
	public List<String> removeThisMethod(String string) {
		return servingsParser.processText(string);
	}
	
}
