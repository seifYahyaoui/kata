package com.nexeo.kata.checked.exception;
/**
 * 
 * @author yahyaoui
 *
 */
public class InvalidAccountException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidAccountException() {
		super("The account is not valid");
	}

}
