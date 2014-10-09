package interpreter.utils;

import java.util.HashMap;
import java.util.Map;

import com.sun.tools.javac.util.Pair;

public class UnitConverter {

	private static Map<String, Pair<Integer, String>> unitChanges = new HashMap<String, Pair<Integer, String>>();

	static {
		unitChanges.put("teaspoon", new Pair<Integer, String>(5, "ml"));
		unitChanges.put("tablespoon", new Pair<Integer, String>(15, "ml"));
		unitChanges.put("cup", new Pair<Integer, String>(240, "ml"));
		unitChanges.put("ounce", new Pair<Integer, String>(30, "ml"));
		unitChanges.put("pint", new Pair<Integer, String>(480, "ml"));
		unitChanges.put("quart", new Pair<Integer, String>(960, "ml"));
		unitChanges.put("gallon", new Pair<Integer, String>(3840, "ml"));

		unitChanges.put("teaspoons", new Pair<Integer, String>(5, "ml"));
		unitChanges.put("tablespoons", new Pair<Integer, String>(15, "ml"));
		unitChanges.put("cups", new Pair<Integer, String>(240, "ml"));
		unitChanges.put("ounces", new Pair<Integer, String>(30, "ml"));
		unitChanges.put("pints", new Pair<Integer, String>(480, "ml"));
		unitChanges.put("quarts", new Pair<Integer, String>(960, "ml"));
		unitChanges.put("gallons", new Pair<Integer, String>(3840, "ml"));
	}

	public static Pair<Double, String> convertUnit(Double amount, String unit) {
		if (unitChanges.containsKey(unit)) {
			return new Pair<Double, String>(amount * unitChanges.get(unit).fst,
					unitChanges.get(unit).snd);
		} else {
			return null;
		}
	}

}
