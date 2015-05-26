package com.stringcalculator;


public class NegativeNotAllowedException extends RuntimeException {

	public NegativeNotAllowedException(String negativeNumbers) {
		super("Negatives not allowed : " + negativeNumbers);
	}

}
