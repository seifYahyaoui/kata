package com.nexeo.kata.services.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.model.OperationHistory;
import com.nexeo.kata.services.IOperationsHistoryService;
import com.nexeo.kata.services.dao.OperationHistoryRepository;

/**
 *
 * @author yahyaoui
 *
 */
@Service
public class OperationsHistoryServiceImpl implements IOperationsHistoryService {

	@Autowired
	private OperationHistoryRepository historyRepository;

	public List<OperationHistory> getHistory(Long accountID) throws InvalidAccountException {
		 List<OperationHistory> findByAccountAndOperation = historyRepository.findByAccountID(accountID);
		 Comparator<OperationHistory> comparator = (h1, h2) -> h1.getOperationDateTime().isBefore(h2.getOperationDateTime())? 0 : 1;
		 Collections.sort(findByAccountAndOperation, comparator);
		 return findByAccountAndOperation;
	}
}
