package com.nexeo.kata.services;

import java.util.Collection;

import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.model.OperationHistory;
/**
 * 
 * @author yahyaoui
 *
 */
public interface IOperationsHistoryService {
	Collection<OperationHistory> getHistoryOfDepositOperation(Long accountID) throws InvalidAccountException;
	
	Collection<OperationHistory> getHistoryOfWithdrawalOperation(Long accountID)throws InvalidAccountException;
	
	void deleteHistory(Long accountId);
}
