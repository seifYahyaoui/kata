package com.nexeo.kata.checked.exception;
/**
 * 
 * @author yahyaoui
 *
 */
public class InsufficientBalanceException extends Exception{

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
		super("Your account is not holding the requested ammount of money");
	}
}
