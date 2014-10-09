package interpreter.parser.converter;

public class ServingsConverter extends StringConverter<Integer> {

	private String servingsNumberPattern = ".*[0-9]+.*";

	@Override
	public Integer convert(String servingsString) {
		String[] words = servingsString.split(" ");
		for (String s : words) {
			if (s.matches(servingsNumberPattern)) {
				s = s.replaceAll("[^0-9]", "").trim();
				return Integer.valueOf(s);
			} else if (s.matches(writtenNumberPattern)) {
				return writtenNumbersMapping.get(s);
			}
		}
		return 0;
	}
	
}
