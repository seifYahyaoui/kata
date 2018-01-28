package com.nexeo.kata.checked.exception;
/**
 * 
 * @author yahyaoui
 *
 */
public class InvalidRequestedMoney extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidRequestedMoney(){
		super("The requested/versed money is invalid");
	}
}
