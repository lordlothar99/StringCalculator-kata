package com.stringcalculator;

import static com.stringcalculator.StringUtils.after;
import static com.stringcalculator.StringUtils.asInt;
import static com.stringcalculator.StringUtils.before;

import java.util.ArrayList;
import java.util.List;

public class Addition {

	private final List<Integer> numbers = new ArrayList<Integer>();

	public Addition(String sumString, String[] separators) {
		super();
		extractNumbers(sumString, separators);
	}

	private void extractNumbers(String sumString, String[] separators) {
		int separatorIndex = nextSeparatorIndex(sumString, separators);
		int number = asInt(before(sumString, separatorIndex));

		numbers.add(number);

		if (separatorIndex < sumString.length()) {
			extractNumbers(after(sumString, separatorIndex), separators);
		}
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

	private boolean isNegative(Integer number) {
		return number < 0;
	}

	public int execute() {
		int sum = 0;
		for (Integer number : numbers) {
			sum += number;
		}
		return sum;
	}

	public void assertNoNegativeNumbers() {
		String negativeNumbers = "";
		String sep = "";
		for (Integer number : numbers) {
			if (isNegative(number)) {
				negativeNumbers += sep + number;
				sep = ", ";
			}
		}

		if (isNotEmpty(negativeNumbers)) {
			throw new NegativeNotAllowedException(negativeNumbers);
		}
	}

	private boolean isNotEmpty(String negativeNumbers) {
		return !"".equals(negativeNumbers);
	}
}
