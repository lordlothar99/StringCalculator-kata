package com.stringcalculator;

import static com.stringcalculator.StringUtils.after;
import static com.stringcalculator.StringUtils.between;

public class StringCalculator {

	private static final String SEPARATOR_STOP = "\n";
	private static final String SEPARATOR_START = "//";
	private static final String[] SEPARATORS = new String[] { ",", SEPARATOR_STOP };

	public int add(String additionString) {

		Addition addition = buildAddition(additionString);
		addition.assertNoNegativeNumbers();
		return addition.execute();
	}

	private Addition buildAddition(String additionString) {

		String[] separators = extractSeparators(additionString);

		String sumString = extractSumString(additionString);

		Addition addition = new Addition(sumString, separators);

		return addition;
	}

	private String[] extractSeparators(String operation) {
		return separatorSpecified(operation) ? new String[] { between(operation, SEPARATOR_START, SEPARATOR_STOP) }
				: SEPARATORS;
	}

	private String extractSumString(String operation) {
		return separatorSpecified(operation) ? after(operation, SEPARATOR_STOP) : operation;
	}

	private boolean separatorSpecified(String string) {
		return string.startsWith(SEPARATOR_START);
	}

}
