package com.stringcalculator;

public class NegativeNotAllowedException extends RuntimeException {

	public NegativeNotAllowedException() {
		super("Negatives not allowed");
	}
}
