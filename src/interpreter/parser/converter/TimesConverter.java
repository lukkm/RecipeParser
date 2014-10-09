package interpreter.parser.converter;

import interpreter.model.RecipeTime;

public class TimesConverter extends StringConverter<RecipeTime> {

	private String timesNumberPattern = ".*[0-9]+.*";
	private String timesUnitPattern = ".*(min(s|utes|ute)|sec(s|ond|onds)|hour(s)?|hs).*";
	
	@Override
	public RecipeTime convert(String timesString) {
		RecipeTime recipeTime = new RecipeTime();
		String[] words = timesString.split(" ");
		for (String s : words) {
			if (s.matches(timesNumberPattern)) {
				s = s.replaceAll("[^0-9]", "").trim();
				recipeTime.setQuantity(Double.valueOf(s));
			} else if (s.matches(writtenNumberPattern)) {
				s = s.trim();
				if (writtenNumbersMapping.get(s) != null) {
					recipeTime.setQuantity(writtenNumbersMapping.get(s));
				}
			} else if (s.matches(timesUnitPattern)) {
				recipeTime.setUnit(s);
			}
		}
		return recipeTime;
	}
	
	

}
