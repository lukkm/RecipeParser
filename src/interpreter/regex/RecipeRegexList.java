package interpreter.regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class RecipeRegexList {
	
	private List<Pattern> startingPatterns = new LinkedList<Pattern>();
	private List<List<Pattern>> simultaneousFilterPatterns = new LinkedList<List<Pattern>>();
	private List<Pattern> filterPatterns = new LinkedList<Pattern>();
	private List<Pattern> excludePatterns = new LinkedList<Pattern>();
	
	public void addFilterPattern(Pattern pattern) {
		filterPatterns.add(pattern);
	}
	
	public void addStartingPattern(Pattern pattern) {
		startingPatterns.add(pattern);
	}
	
	public void addSimultaneousFilterPatterns(List<Pattern> patterns) {
		simultaneousFilterPatterns.add(patterns);
	}
	
	public void addExcludingPattern(Pattern pattern) {
		excludePatterns.add(pattern);
	}
	
	public List<String> processText(String text) {
		if (startingPatterns.isEmpty()) {
			return new LinkedList<String>();
		}
		List<String> finalMatches = RecipeRegexUtils.matchingPieces(text, startingPatterns);
		return processList(finalMatches);
	}
	
	public List<String> processList(List<String> list) {
		List<String> finalMatches = list;
		for (List<Pattern> lp : simultaneousFilterPatterns) {
			finalMatches = RecipeRegexUtils.matchingSimultaneousSubList(finalMatches, lp);
		}
		for (Pattern p : filterPatterns) {
			finalMatches = RecipeRegexUtils.matchingSubList(list, p);
		}
		for (Pattern p : excludePatterns) {
			finalMatches = RecipeRegexUtils.excludingSubList(finalMatches, p);
		}
		return finalMatches;
	}
			
}
