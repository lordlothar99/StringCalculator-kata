package com.stringcalculator;

public class NegativeNotAllowedException extends RuntimeException {

	public NegativeNotAllowedException(int number) {
		super("Negatives not allowed : " + number);
	}
}
