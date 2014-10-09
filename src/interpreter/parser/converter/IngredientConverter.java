package interpreter.parser.converter;

import interpreter.model.Ingredient;

public class IngredientConverter extends StringConverter<Ingredient> {

	private String numberPattern = "([0-9]+\\.[0-9]*)|([0-9]*\\.[0-9]+)|([0-9]+)";
	private String fractionPattern = "[0-9]+\\/[1-9]{1}[0-9]*";	
	
	private String measures = ".*(tbsp|spoon|cup|pint|inch|part|slice|dash|ounc|gram|kilo|pound|quart|liter|gallon|cubic|cm|centimeter|meter|oz|cubic|cc|lb).*";
	
	@Override
	public Ingredient convert(String ingredientString) {
		Ingredient ing = new Ingredient();
		String[] words = ingredientString.split(" ");
		String amount = null;
		for (String s : words) {
			if (s.matches(numberPattern)) {
				s = s.replaceAll("[^0-9]", "").trim();
				ing.setAmount(Integer.valueOf(s));
				amount = s;
			} else if (s.matches(writtenNumberPattern)) {
				ing.setAmount(writtenNumbersMapping.get(s));
				amount = s;
			} else if (s.matches(fractionPattern)) {
				String[] splittedFraction = s.split("\\/");
				ing.setAmount((double)Integer.valueOf(splittedFraction[0]) / Integer.valueOf(splittedFraction[1]));
				amount = s;
			} else if (s.matches(measures)) {
				ing.setUnit(s);
			}
		}
		
		if (ingredientString.contains("of")) {
			ing.setName(ingredientString.substring(ingredientString.indexOf("of") + 2));
		} else {
			String cleanIngredient = ingredientString;
			if (amount != null) {
				cleanIngredient = cleanIngredient.replace(amount, "").trim();
			}
			if (ing.getUnit() != null) {
				cleanIngredient = cleanIngredient.replace(ing.getUnit(), "").trim();
			}
			ing.setName(cleanIngredient);
		}
		
		return ing;
	}

}
