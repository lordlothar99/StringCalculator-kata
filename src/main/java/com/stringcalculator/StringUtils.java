package com.stringcalculator;

public class StringUtils {

	public static String after(String string, String separator) {
		return after(string, string.indexOf(separator));
	}

	public static String after(String string, int index) {
		return string.substring(index + 1);
	}

	public static String before(String string, int index) {
		return string.substring(0, index);
	}

	public static String between(String string, String start, String stop) {
		return string.substring(string.indexOf(start) + start.length(), string.indexOf(stop));
	}

	public static int asInt(String number) {
		return number.isEmpty() ? 0 : Integer.parseInt(number);
	}

}
