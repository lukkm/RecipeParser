package interpreter.parser;

import interpreter.model.Ingredient;
import interpreter.regex.RecipeRegexList;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class RecipeParser {

	private String recipeText;
	
	private static RecipeRegexList servingsParser = new RecipeRegexList();
	private static RecipeRegexList listItemsParser = new RecipeRegexList();
	
	private static Pattern MONTHS = Pattern.compile(".*\\b(jan(uary)?|feb(ruary)?|mar(ch)?|apr(il)?|may|jun(e)?|jul(y)?|aug(ust)?|sep(tember)?|oct(ober)?|nov(ember)?|dec(ember)?)\\b.*");
	private static Pattern NUMBER = Pattern.compile("[0-9]+(\\s[a-z0-9,.;]+){0,8}");
	private static Pattern NUMBER_WITH_WORDS = Pattern.compile(".*\\b(a|an|one|two|three|four|five|six|seven|eight|nine|ten)\\b.*");
	
	static {
		// Find all the numbers with the, at most, eight words after them 
		servingsParser.addStartingPattern(NUMBER);
		
		// Find all pieces with numbers
		List<Pattern> numberPatternList = new LinkedList<Pattern>();
		numberPatternList.add(NUMBER);
		numberPatternList.add(NUMBER_WITH_WORDS);
		listItemsParser.addSimultaneousFilterPatterns(numberPatternList);
		
		// Find dates to discard comments
		listItemsParser.addExcludingPattern(MONTHS);
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
	
	public List<String> removeThisMethod(String text) {
		return servingsParser.processText(text);
	}
	
	public List<String> parseListItems(List<String> items) {
		return listItemsParser.processList(items);
	}
	
}
