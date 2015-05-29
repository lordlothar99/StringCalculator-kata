package com.stringcalculator;

import static com.stringcalculator.StringCalculatorUtils.after;
import static com.stringcalculator.StringCalculatorUtils.asInt;
import static com.stringcalculator.StringCalculatorUtils.before;
import static com.stringcalculator.StringCalculatorUtils.between;

import java.util.ArrayList;
import java.util.List;

public class AdditionBuilder {

	private static final String BRACE_CLOSE = "]";
	private static final String BRACE_OPEN = "[";
	private static final String SEPARATOR_STOP = "\n";
	private static final String SEPARATOR_START = "//";
	private static final String[] SEPARATORS = new String[] { ",", SEPARATOR_STOP };
	private static final String UNDEFINED = null;
	private final String additionString;

	public AdditionBuilder(String additionString) {
		this.additionString = additionString;
	}

	public static Addition buildAddition(String additionString) {
		return new AdditionBuilder(additionString).buildAddition();
	}

	private Addition buildAddition() {
		String[] separators = extractSeparators(additionString);
		String numbersString = extractNumbersString(additionString);
		List<Integer> numbers = extractNumbers(numbersString, separators);
		return new Addition(numbers);
	}

	private String extractNumbersString(String additionString) {
		return separatorSpecified(additionString) ? after(additionString, SEPARATOR_STOP) : additionString;
	}

	private boolean separatorSpecified(String string) {
		return string.startsWith(SEPARATOR_START);
	}

	private String[] extractSeparators(String additionString) {
		if (!separatorSpecified(additionString)) {
			return SEPARATORS;
		}

		String separators = between(additionString, SEPARATOR_START, SEPARATOR_STOP);
		separators = between(separators, BRACE_OPEN, BRACE_CLOSE);
		return new String[] { separators };
	}

	private List<Integer> extractNumbers(String numbersString, String[] separators) {
		String[] split = splitOnNextSeparator(numbersString, separators);
		int number = asInt(split[0]);
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(number);

		if (split[1] != UNDEFINED) {
			numbers.addAll(extractNumbers(split[1], separators));
		}
		return numbers;
	}

	private String[] splitOnNextSeparator(String numbersString, String[] separators) {

		String separator = nextSeparator(numbersString, separators);
		String[] split = new String[2];
		if (separator == UNDEFINED) {
			split[0] = numbersString;
			split[1] = UNDEFINED;
		} else {
			split[0] = before(numbersString, separator);
			split[1] = after(numbersString, separator);
		}

		return split;
	}

	private String nextSeparator(String numbersString, String[] separators) {
		int separatorIndex = numbersString.length();
		String nextSeparator = UNDEFINED;
		for (String separator : separators) {
			int nextSeparatorIndex = numbersString.indexOf(separator);
			if (separatorFound(nextSeparatorIndex)) {
				separatorIndex = Math.min(separatorIndex, nextSeparatorIndex);
				nextSeparator = separator;
			}
		}
		return nextSeparator;
	}

	private boolean separatorFound(int separatorIndex) {
		return separatorIndex != -1;
	}

}
