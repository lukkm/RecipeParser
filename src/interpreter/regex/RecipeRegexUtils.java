package interpreter.regex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeRegexUtils {

	public static List<String> matchingPieces(String text, Pattern pattern) {
		List<String> matchResults = new LinkedList<String>();
		Matcher m = pattern.matcher(text);
		while (m.find()) {
			matchResults.add(m.group());
		}
		return matchResults;
	}

	public static List<String> matchingPieces(String text,
			List<Pattern> patterns) {
		List<String> matchResults = new LinkedList<String>();
		for (Pattern p : patterns) {
			matchResults.addAll(matchingPieces(text, p));
		}
		return matchResults;
	}

	public static List<String> matchingSubList(List<String> parts,
			Pattern pattern) {
		return processingSubListMatch(parts, pattern, true);
	}

	public static List<String> matchingSimultaneousSubList(List<String> parts,
			List<Pattern> patterns) {
		Set<String> resultSet = new HashSet<String>();
		for (Pattern p : patterns) {
			resultSet.addAll(matchingSubList(parts, p));
		}
		return new LinkedList<String>(resultSet);
	}

	public static List<String> excludingSubList(List<String> parts,
			Pattern pattern) {
		return processingSubListMatch(parts, pattern, false);
	}

	private static List<String> processingSubListMatch(List<String> parts,
			Pattern pattern, boolean exclude) {
		List<String> matchResults = new LinkedList<String>();
		Matcher m;
		for (String s : parts) {
			m = pattern.matcher(s);
			if (m.matches() == exclude) {
				matchResults.add(s);
			}
		}
		return matchResults;
	}

}
