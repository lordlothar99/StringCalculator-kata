package com.stringcalculator;

import static com.stringcalculator.AdditionBuilder.buildAddition;

public class StringCalculator {

	private static final int IGNORE_LIMIT = 1000;

	public int add(String additionString) {
		Addition addition = buildAddition(additionString);
		addition.assertNoNegativeNumbers();
		addition.ignoreNumbersOver(IGNORE_LIMIT);
		return addition.execute();
	}

}
