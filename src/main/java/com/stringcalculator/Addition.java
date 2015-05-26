package com.stringcalculator;

import static com.stringcalculator.StringCalculatorUtils.isNegative;
import static com.stringcalculator.StringCalculatorUtils.isNotEmpty;

import java.util.List;

public class Addition {

	private final List<Integer> numbers;

	public Addition(List<Integer> numbers) {
		super();
		this.numbers = numbers;
	}

	public int execute() {
		int sum = 0;
		for (Integer number : numbers) {
			sum += number;
		}
		return sum;
	}

	public void assertNoNegativeNumbers() {
		String negativeNumbers = getNegativeNumbers();
		if (isNotEmpty(negativeNumbers)) {
			throw new NegativeNotAllowedException(negativeNumbers);
		}
	}

	private String getNegativeNumbers() {
		String negativeNumbers = "";
		String sep = "";
		for (Integer number : numbers) {
			if (isNegative(number)) {
				negativeNumbers += sep + number;
				sep = ", ";
			}
		}
		return negativeNumbers;
	}
}
