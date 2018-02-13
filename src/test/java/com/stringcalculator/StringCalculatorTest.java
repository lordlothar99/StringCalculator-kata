package com.stringcalculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {

    private StringCalculator calculator;
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        calculator = new StringCalculator();
    }

    @Test
    public void should_return_0_when_empty_string_sent() {
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void should_return_input_as_an_integer_when_single_number_sent() {
        assertThat(calculator.add("9")).isEqualTo(9);
    }

    @Test
    public void should_return_sum_when_two_numbers_sent() {
        assertThat(calculator.add("9,5")).isEqualTo(14);
    }

    @Test
    public void should_return_sum_when_more_than_two_numbers_sent() {
        assertThat(calculator.add("9,5,8,9,6")).isEqualTo(37);
    }

    @Test
    public void should_return_sum_when_new_line_separator() {
        assertThat(calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void should_return_sum_when_using_specific_separator() {
        assertThat(calculator.add("//;\n1;2;3")).isEqualTo(6);
    }

    @Test
    public void should_throw_an_error_when_sum_negative_number() {
        exception.expect(NegativeNotAllowedException.class);
        exception.expectMessage("Negatives not allowed : -2");

        calculator.add("1,-2,3");
    }

    @Test
    public void should_throw_an_error_when_sum_several_negative_numbers() {
        exception.expect(NegativeNotAllowedException.class);
        exception.expectMessage("Negatives not allowed : -2, -3");

        calculator.add("1,-2,-3");
    }

    @Test
    public void should_ignore_numbers_over_1000_when_sum_with_such_numbers() {
        assertThat(calculator.add("2,1001")).isEqualTo(2);
    }

    @Test
    public void should_return_sum_when_using_separator_with_several_characters() {
        assertThat(calculator.add("//[***]\n1***2***3")).isEqualTo(6);
    }
}
