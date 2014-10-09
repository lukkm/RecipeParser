package interpreter.model;

import interpreter.utils.UnitConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.sun.tools.javac.util.Pair;

public class Recipe {

	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	private int servings = 0;
	private RecipeTime time;

	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void addIngredient(Ingredient i) {
		ingredients.add(i);
	}

	public void addAllIngredients(Collection<Ingredient> i) {
		ingredients.addAll(i);
	}

	public int getServings() {
		return servings;
	}

	public RecipeTime getTime() {
		return time;
	}

	public void setServings(int servingNumber) {
		this.servings = servingNumber;
	}

	public void setTime(RecipeTime time) {
		this.time = time;
	}

	public void setTime(double quantity, String unit) {
		if (time == null) {
			time = new RecipeTime();
		}
		this.time.setQuantity(quantity);
		this.time.setUnit(unit);
	}

	private String generateIngredientsString() {
		StringBuffer ings = new StringBuffer();
		ings.append("ingredients ");
		for (Ingredient ing : ingredients) {
			ings.append(ing.getName() + ", ");
		}
		return ings.toString();
	}

	private String generateOrigQuantityString() {
		StringBuffer ings = new StringBuffer();
		ings.append("orig_quantity ");
		for (Ingredient ing : ingredients) {
			ings.append(ing.getAmount() + ", ");
		}
		return ings.toString();
	}

	private String generateMetricQuantityString() {
		StringBuffer ings = new StringBuffer();
		ings.append("metric_quantity ");
		for (Ingredient ing : ingredients) {
			Pair<Double, String> metricAmount = UnitConverter.convertUnit(
					ing.getAmount(), ing.getUnit());
			if (metricAmount != null) {				
				ings.append(metricAmount.fst + ", ");
			} else {
				ings.append("None, ");
			}
		}
		return ings.toString();
	}

	private String generateOrigUnitString() {
		StringBuffer ings = new StringBuffer();
		ings.append("orig_unit ");
		for (Ingredient ing : ingredients) {
			if (ing.getUnit() != null) {
				ings.append(ing.getUnit() + ", ");
			} else {
				ings.append("None, ");
			}
		}
		return ings.toString();
	}
	
	private String generateMetricUnitString() {
		StringBuffer ings = new StringBuffer();
		ings.append("metric_unit ");
		for (Ingredient ing : ingredients) {
			Pair<Double, String> metricAmount = UnitConverter.convertUnit(
					ing.getAmount(), ing.getUnit());
			if (metricAmount != null) {				
				ings.append(metricAmount.snd + ", ");
			} else {
				ings.append("None, ");
			}
		}
		return ings.toString();
	}

	public void writeTo(File outputFile) throws FileNotFoundException,
			UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
		writer.println(generateIngredientsString());
		writer.println(generateOrigQuantityString());
		writer.println(generateOrigUnitString());
		writer.println(generateMetricQuantityString());
		writer.println(generateMetricUnitString());
		writer.println("nb_servings " + getServings());
		writer.println("total_time "
				+ getTime().getQuantity()
				+ " "
				+ ((getTime().getUnit() != null) ? getTime().getUnit() : "None"));
		writer.close();
	}
}
