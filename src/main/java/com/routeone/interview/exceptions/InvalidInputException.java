package com.routeone.interview.exceptions;

/**
 * Handles input items exceptions
 */
public class InvalidInputException extends Exception {

	
	private static final long serialVersionUID = 4658350613214402399L;

	public InvalidInputException(String string) {
		System.out.println(string);
	}

}
