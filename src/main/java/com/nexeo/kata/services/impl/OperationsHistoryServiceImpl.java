package com.nexeo.kata.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.model.Operation;
import com.nexeo.kata.model.OperationHistory;
import com.nexeo.kata.services.IOperationsHistoryService;
import com.nexeo.kata.services.dao.OperationHistoryRepository;
/**
 * 
 * @author yahyaoui
 *
 */
@Service
public class OperationsHistoryServiceImpl implements IOperationsHistoryService{

	@Autowired
	private OperationHistoryRepository historyRepository;
	
	public Collection<OperationHistory> getHistoryOfDepositOperation(Long accountID) throws InvalidAccountException {
		// TODO Auto-generated method stub
		return historyRepository.findByAccountAndOperation(accountID, Operation.Deposit);
	}

	public Collection<OperationHistory> getHistoryOfWithdrawalOperation(Long accountID) throws InvalidAccountException {
		// TODO Auto-generated method stub
		return historyRepository.findByAccountAndOperation(accountID, Operation.Withdrawal);
	}

	public void deleteHistory(Long accountId) {
		// TODO Auto-generated method stub
		historyRepository.deleteAll();
	}

}
