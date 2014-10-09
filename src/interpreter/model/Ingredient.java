package interpreter.model;

public class Ingredient {

	private String name;
	private double amount;
	private String unit;

	private double epsilon = 0.01;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Ingredient)) {
			return false;
		}
		Ingredient other = (Ingredient) obj;
		if (Math.abs(other.getAmount() - amount) > epsilon) {
			return false;
		}
		return (other.getUnit().contains(unit) || unit
				.contains(other.getUnit()))
				&& (other.getName().contains(name) || name.contains(other
						.getName()));
	}

}
