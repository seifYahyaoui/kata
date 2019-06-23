package com.nexeo.kata.services;

import java.util.List;

import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.model.OperationHistory;
/**
 *
 * @author yahyaoui
 *
 */
public interface IOperationsHistoryService {
	List<OperationHistory> getHistory(Long accountID) throws InvalidAccountException;
}
