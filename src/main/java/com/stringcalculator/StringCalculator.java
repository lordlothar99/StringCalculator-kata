package com.stringcalculator;

import static com.stringcalculator.AdditionBuilder.buildAddition;

public class StringCalculator {

	public int add(String additionString) {
		Addition addition = buildAddition(additionString);
		addition.assertNoNegativeNumbers();
		return addition.execute();
	}

}
