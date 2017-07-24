package com.routeone.interview.exceptions;

/**
 * Handles exceptions on inventory file operations  
 */
public class InvalidFileException extends Exception{
	
	private static final long serialVersionUID = -1224916960727277020L;

	public InvalidFileException(String exceptionMessage)
	{
		System.out.println(exceptionMessage);
	}
}
