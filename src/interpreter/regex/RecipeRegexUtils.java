package interpreter.regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeRegexUtils {

	public static List<String> matchingPieces(String text, Pattern pattern) {
		List<String> matchResults = new LinkedList<String>();
		Matcher m = pattern.matcher(text);
		while(m.find()) {
			matchResults.add(m.group());
		}
		return matchResults;
	}
	
	public static List<String> matchingSubList(List<String> parts, Pattern pattern) {
		List<String> matchResults = new LinkedList<String>();
		Matcher m;
		for (String s : parts) {
			m = pattern.matcher(s);
			if (m.matches()) {
				matchResults.add(s);
			}
		}
		return matchResults;
	}
	
}
