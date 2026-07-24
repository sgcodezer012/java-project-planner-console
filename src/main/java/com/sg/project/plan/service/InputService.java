package com.sg.project.plan.service;

import java.util.Scanner;

/**
 * Utility class service to read user input for Text and Number
 * @author sydney.garcia
 */
public class InputService {
	
	static final String MESSAGE_INVALID_NUMBER = "Only numbers[1-9] is allowed.";
	static final String MESSAGE_INVALID_STRING = "Only numbers, letters and space are allowed.";
	static final String MESSAGE_INPUT_VALUE = "\t Enter value here:";
	static final String RGX_LETTER_SPACE_ONLY_VALUE = "[0-9a-zA-Z ]+";
	static final int ZERO = 0;
	
	/**
	 * To read and validate user input for number only
	 * @param scanner
	 * @param message
	 * @return
	 */
	public static int readNumber(Scanner scanner, String message) {
		
		System.out.println(message);
		System.out.print(MESSAGE_INPUT_VALUE);
		
		while(!scanner.hasNextInt()) {
			System.out.println(MESSAGE_INVALID_NUMBER);
			System.out.println(message);
			System.out.print(MESSAGE_INPUT_VALUE);
			scanner.next();
		}
		int value = scanner.nextInt();
		scanner.nextLine();
		
		return value;
	}
	
	/**
	 * To read and validate user input for number, text, space only
	 * @param scanner
	 * @param message
	 * @return
	 */
	public static String readText(Scanner scanner, String message) {
		System.out.println(message);
		System.out.print(MESSAGE_INPUT_VALUE);

		while(!scanner.hasNext(RGX_LETTER_SPACE_ONLY_VALUE)) {
			System.out.println(MESSAGE_INVALID_STRING);
			System.out.println(message);
			System.out.print(MESSAGE_INPUT_VALUE);
			scanner.nextLine();
		}
		
		return scanner.nextLine();
	}
	
	/**
	 * Use to check the project, and task count
	 * @param count
	 * @return
	 */
	public static boolean isZeroValue(Integer count) {
		return count == ZERO;
	}
}
