package com.stringcalculator;

import static com.stringcalculator.StringCalculatorUtils.after;
import static com.stringcalculator.StringCalculatorUtils.asInt;
import static com.stringcalculator.StringCalculatorUtils.before;
import static com.stringcalculator.StringCalculatorUtils.between;

import java.util.ArrayList;
import java.util.List;

public class AdditionBuilder {

	private static final String SEPARATOR_STOP = "\n";
	private static final String SEPARATOR_START = "//";
	private static final String[] SEPARATORS = new String[] { ",", SEPARATOR_STOP };

	public static Addition buildAddition(String additionString) {
		String[] separators = extractSeparators(additionString);
		String sumString = extractSumString(additionString);
		List<Integer> numbers = extractNumbers(sumString, separators);
		return new Addition(numbers);
	}

	private static String extractSumString(String additionString) {
		return separatorSpecified(additionString) ? after(additionString, SEPARATOR_STOP) : additionString;
	}

	private static boolean separatorSpecified(String string) {
		return string.startsWith(SEPARATOR_START);
	}

	private static String[] extractSeparators(String operation) {
		return separatorSpecified(operation) ? new String[] { between(operation, SEPARATOR_START, SEPARATOR_STOP) }
				: SEPARATORS;
	}

	private static List<Integer> extractNumbers(String sumString, String[] separators) {
		int separatorIndex = nextSeparatorIndex(sumString, separators);
		int number = asInt(before(sumString, separatorIndex));

		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(number);

		if (separatorIndex < sumString.length()) {
			numbers.addAll(extractNumbers(after(sumString, separatorIndex), separators));
		}
		return numbers;
	}

	private static boolean separatorFound(int separatorIndex) {
		return separatorIndex != -1;
	}

	private static int nextSeparatorIndex(String sumString, String[] separators) {
		int separatorIndex = sumString.length();
		for (String separator : separators) {
			int nextSeparatorIndex = sumString.indexOf(separator);
			if (separatorFound(nextSeparatorIndex)) {
				separatorIndex = Math.min(separatorIndex, nextSeparatorIndex);
			}
		}
		return separatorIndex;
	}

}
