package interpreter.model;

import java.util.LinkedList;
import java.util.List;

public class Recipe {

	private List<Ingredient> ingredients = new LinkedList<Ingredient>();
	private int servings = 0;
	private RecipeTime time;
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void addIngredient(Ingredient i) {
		ingredients.add(i);
	}
	
	public void addAllIngredients(List<Ingredient> i) {
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
	
}
