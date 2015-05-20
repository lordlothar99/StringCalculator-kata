package com.stringcalculator;

import static com.stringcalculator.StringUtils.after;
import static com.stringcalculator.StringUtils.asInt;
import static com.stringcalculator.StringUtils.before;
import static com.stringcalculator.StringUtils.between;

public class StringCalculator {

	private static final String SEPARATOR_STOP = "\n";
	private static final String SEPARATOR_START = "//";
	private static final String[] SEPARATORS = new String[] { ",", SEPARATOR_STOP };

	public int add(String operation) {

		String[] separators = getSeparators(operation);

		String sumString = getSumString(operation);

		return add(sumString, separators);
	}

	private String[] getSeparators(String operation) {
		return separatorSpecified(operation) ? new String[] { between(operation, SEPARATOR_START, SEPARATOR_STOP) }
				: SEPARATORS;
	}

	private String getSumString(String operation) {
		return separatorSpecified(operation) ? after(operation, SEPARATOR_STOP) : operation;
	}

	private int add(String sumString, String[] separators) {
		int separatorIndex = nextSeparatorIndex(sumString, separators);
		int sum = asInt(before(sumString, separatorIndex));
		if (sum < 0) {
			throw new NegativeNotAllowedException(sum);
		}

		if (separatorIndex < sumString.length()) {
			sum += add(after(sumString, separatorIndex), separators);
		}

		return sum;
	}

	private boolean separatorSpecified(String string) {
		return string.startsWith(SEPARATOR_START);
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

}
