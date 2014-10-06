package interpreter.regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

enum Types {
	QUANTITY,
	INGREDIENT
}

public class RecipeRegexList {
	
	private List<Pattern> patterns = new LinkedList<Pattern>();
	
	public void addPattern(Pattern pattern) {
		patterns.add(pattern);
	}
	
	public List<String> processText(String text) {
		if (patterns.isEmpty()) {
			return new LinkedList<String>();
		}
		List<String> finalMatches = RecipeRegexUtils.matchingPieces(text, patterns.get(0));
		for (int i = 1; i < patterns.size(); i++) {
			finalMatches = RecipeRegexUtils.matchingSubList(finalMatches, patterns.get(i));
		}
		return finalMatches;
	}
	
}
