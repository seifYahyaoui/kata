package com.nexeo.kata.services;

import com.nexeo.kata.checked.exception.InsufficientBalanceException;
import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.checked.exception.InvalidRequestedMoney;
/**
 * 
 * @author yahyaoui
 *
 */
public interface IWithdrawalAccountService {
	boolean withdrawalMoney(double money, Long accountID)
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney;
}
