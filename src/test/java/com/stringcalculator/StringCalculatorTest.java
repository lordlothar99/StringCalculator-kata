package com.stringcalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {

	private StringCalculator calculator;

	@Before
	public void setup() {
		calculator = new StringCalculator();
	}

	@Test
	public void should_return_0_when_empty_string_sent() {
		assertEquals(0, calculator.add(""));
	}

	@Test
	public void should_return_input_as_an_integer_when_single_number_sent() {
		assertEquals(9, calculator.add("9"));
	}

	@Test
	public void should_return_sum_when_two_numbers_sent() {
		assertEquals(14, calculator.add("9,5"));
	}

	@Test
	public void should_return_sum_when_more_than_two_numbers_sent() {
		assertEquals(37, calculator.add("9,5,8,9,6"));
	}

	@Test
	public void should_return_sum_when_new_line_separator() {
		assertEquals(6, calculator.add("1\n2,3"));
	}

	@Test
	public void should_return_sum_when_defined_separator() {
		assertEquals(6, calculator.add("//;\n1;2;3"));
	}

	@Test
	public void should_throw_an_error_when_sum_negative_number() {
		try {
			calculator.add("1,-2,3");
			fail();
		} catch (NegativeNotAllowedException e) {
			assertEquals("Negatives not allowed : -2", e.getMessage());
		}
	}

	@Test
	public void should_throw_an_error_when_sum_several_negative_numbers() {
		try {
			calculator.add("1,-2,-3");
			fail();
		} catch (NegativeNotAllowedException e) {
			assertEquals("Negatives not allowed : -2, -3", e.getMessage());
		}
	}
}
