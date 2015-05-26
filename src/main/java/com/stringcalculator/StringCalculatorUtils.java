package com.stringcalculator;

public class StringCalculatorUtils {

	public static String after(String string, String separator) {
		int startIndex = string.indexOf(separator);
		startIndex = startIndex == -1 ? 0 : startIndex + separator.length();
		return string.substring(startIndex);
	}

	public static String before(String string, String separator) {
		int stopIndex = string.indexOf(separator);
		stopIndex = stopIndex == -1 ? string.length() : stopIndex;
		return string.substring(0, stopIndex);
	}

	public static String between(String string, String start, String stop) {
		return before(after(string, start), stop);
	}

	public static int asInt(String number) {
		return number.isEmpty() ? 0 : Integer.parseInt(number);
	}

	public static boolean isNotEmpty(String negativeNumbers) {
		return !"".equals(negativeNumbers);
	}

	public static boolean isNegative(int number) {
		return number < 0;
	}

}
