package interpreter.parser.converter;

import java.util.HashMap;
import java.util.Map;

public abstract class StringConverter<T> {

	protected String writtenNumberPattern = "(one|two|three|four|five|six|seven|eight|nine|ten)";
	
	protected Map<String, Integer> writtenNumbersMapping = new HashMap<String, Integer>();
	
	public StringConverter() {
		writtenNumbersMapping.put("one", 1);
		writtenNumbersMapping.put("two", 2);
		writtenNumbersMapping.put("three", 3);
		writtenNumbersMapping.put("four", 4);
		writtenNumbersMapping.put("five", 5);
		writtenNumbersMapping.put("six", 6);
		writtenNumbersMapping.put("seven", 7);
		writtenNumbersMapping.put("eight", 8);
		writtenNumbersMapping.put("nine", 9);
		writtenNumbersMapping.put("ten", 10);
	}
	
	public abstract T convert(String s);
	
}
