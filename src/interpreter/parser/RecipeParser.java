package interpreter.parser;

import interpreter.model.Ingredient;
import interpreter.regex.RecipeRegexList;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class RecipeParser {

	private String recipeText;
	
	public static final RecipeRegexList SERVINGS_PARSER = new RecipeRegexList();
	public static final RecipeRegexList TIMES_PARSER = new RecipeRegexList();
	public static final RecipeRegexList LIST_ITEMS_INGREDIENTS_PARSER = new RecipeRegexList();
	public static final RecipeRegexList STRING_ITEMS_INGREDIENTS_PARSER = new RecipeRegexList();
	
	private static Pattern MONTHS = Pattern.compile(".*\\b(jan(uary)?|feb(ruary)?|mar(ch)?|apr(il)?|may|jun(e)?|jul(y)?|aug(ust)?|sep(tember)?|oct(ober)?|nov(ember)?|dec(ember)?)\\b.*");
	
	private static Pattern NUMBER = Pattern.compile("([0-9]+\\/[1-9]{1}[0-9]*|([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+))(\\s[a-z,.;:]+){0,5}");
	private static Pattern NUMBER_WITH_WORDS = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|ten)(\\s[a-z0-9,.;:]+){0,5}");
	
	private static Pattern NUMBER_BEFORE = Pattern.compile("([a-z,.;:]+\\s){0,5}([0-9]+\\/[1-9]{1}[0-9]*|([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+))");
	private static Pattern NUMBER_WITH_WORDS_BEFORE = Pattern.compile("([a-z,.;:]+\\s){0,5}(one|two|three|four|five|six|seven|eight|nine|ten)");
	
	private static Pattern NUMERICAL_EXPRESSION = Pattern.compile("(a|an)(\\s[a-z0-9,.;:]+){0,5}");
	
	private static Pattern REFERENT_MEASURES = Pattern.compile(".*(stick|tbsp|spoon|cup|pint|inch|can).*");
	private static Pattern RELATIVE_MEASURES = Pattern.compile(".*\\b(part(s)?|slice(s)?|dash(es)?)\\b.*");
	private static Pattern METRIC_REFERENT_MEASURES = Pattern.compile(".*(ounc|gram|kilo|pound|quart|liter|gallon|cubic|cm|centimeter|meter|oz|cubic|cc|lb|lb\\.|oz\\.|ml\\.|tsp\\.|tbsp\\.).*");
	
	private static Pattern TIME_MEASURES = Pattern.compile(".*\\b(min(s|ute|utes)|sec(s|ond|onds)|hour(s)?|hs)\\b.*");
	
	private static Pattern SERVING_REFERENCES = Pattern.compile(".*(serves|servi|people|person).*");
	
	static {
		List<Pattern> numberPatternList = new LinkedList<Pattern>();
		numberPatternList.add(NUMBER);
		numberPatternList.add(NUMBER_WITH_WORDS);
		numberPatternList.add(NUMERICAL_EXPRESSION);

		List<Pattern> measurementsPatternList = new LinkedList<Pattern>();
		measurementsPatternList.add(REFERENT_MEASURES);
		measurementsPatternList.add(METRIC_REFERENT_MEASURES);
		measurementsPatternList.add(RELATIVE_MEASURES);
		
		// Find all the numbers with their surrounding words 
		SERVINGS_PARSER.addStartingPattern(NUMBER);
		SERVINGS_PARSER.addStartingPattern(NUMBER_WITH_WORDS);
		SERVINGS_PARSER.addStartingPattern(NUMBER_BEFORE);
		SERVINGS_PARSER.addStartingPattern(NUMBER_WITH_WORDS_BEFORE);
		
		// Find keywords for serving patterns
		SERVINGS_PARSER.addFilterPattern(SERVING_REFERENCES);
		
		// Find all the numbers with their surrounding words 
		TIMES_PARSER.addStartingPattern(NUMBER);
		TIMES_PARSER.addStartingPattern(NUMBER_WITH_WORDS);
		
		// Find keywords for time patterns
		TIMES_PARSER.addFilterPattern(TIME_MEASURES);
		
		for (Pattern p : numberPatternList) {
			STRING_ITEMS_INGREDIENTS_PARSER.addStartingPattern(p);
		}
		
		// Once we have that, filter the ones with a known measurement
		STRING_ITEMS_INGREDIENTS_PARSER.addSimultaneousFilterPatterns(measurementsPatternList);
				
		// Find dates to discard comments
		STRING_ITEMS_INGREDIENTS_PARSER.addExcludingPattern(MONTHS);
		
		// Find all pieces with numbers
		LIST_ITEMS_INGREDIENTS_PARSER.addSimultaneousFilterPatterns(numberPatternList);
		// Once we have that, filter the ones with a known measurement
		LIST_ITEMS_INGREDIENTS_PARSER.addSimultaneousFilterPatterns(measurementsPatternList);
		
		// Find dates to discard comments
		LIST_ITEMS_INGREDIENTS_PARSER.addExcludingPattern(MONTHS);
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
	
	public List<String> parseText(String text, RecipeRegexList listParser) {
		return listParser.processText(text);
	}
	
	public List<String> parseListItems(List<String> items, RecipeRegexList listParser) {
		return listParser.processList(items);
	}
	
}
