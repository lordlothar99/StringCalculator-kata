package com.stringcalculator;

public class StringCalculator {

	private static final String SEPARATOR_STOP = "\n";
	private static final String SEPARATOR_START = "//";
	private static final String[] SEPARATORS = new String[] { ",", SEPARATOR_STOP };

	public int add(String operation) {

		String[] separators = getSeparators(operation);

		String sumString = getSumString(operation);

		return add(sumString, separators);
	}

	private String getSumString(String operation) {
		return separatorSpecified(operation) ? after(operation, SEPARATOR_STOP) : operation;
	}

	private int add(String sumString, String[] separators) {
		int separatorIndex = nextSeparatorIndex(sumString, separators);
		int sum = asInt(before(sumString, separatorIndex));

		if (separatorIndex < sumString.length()) {
			sum += add(after(sumString, separatorIndex), separators);
		}

		return sum;
	}

	private String[] getSeparators(String operation) {
		if (separatorSpecified(operation)) {
			return new String[] { between(operation, SEPARATOR_START, SEPARATOR_STOP) };
		} else {
			return SEPARATORS;
		}
	}

	private boolean separatorSpecified(String string) {
		return string.startsWith(SEPARATOR_START);
	}

	private String after(String string, String separator) {
		return after(string, string.indexOf(separator));
	}

	private String after(String string, int index) {
		return string.substring(index + 1);
	}

	private String before(String string, int index) {
		return string.substring(0, index);
	}

	private String between(String string, String start, String stop) {
		return string.substring(string.indexOf(start) + start.length(), string.indexOf(stop));
	}

	private boolean separatorFound(int separatorIndex) {
		return separatorIndex != -1;
	}

	private int nextSeparatorIndex(String sumString, String[] separators) {
		int separatorIndex = sumString.length();
		for (String separator : separators) {
			int nextSeparatorIndex = sumString.indexOf(separator);
			if (separatorFound(nextSeparatorIndex)) {
				separatorIndex = Math.min(separatorIndex, nextSeparatorIndex);
			}
		}
		return separatorIndex;
	}

	private static int asInt(String number) {
		return number.isEmpty() ? 0 : Integer.parseInt(number);
	}

}
