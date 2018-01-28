package com.nexeo.kata.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexeo.kata.checked.exception.InsufficientBalanceException;
import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.checked.exception.InvalidRequestedMoney;
import com.nexeo.kata.model.Account;
import com.nexeo.kata.model.Operation;
import com.nexeo.kata.model.OperationHistory;
import com.nexeo.kata.services.IWithdrawalAccountService;
import com.nexeo.kata.services.dao.AccountRepository;
import com.nexeo.kata.services.dao.OperationHistoryRepository;
/**
 * 
 * @author yahyaoui
 *
 */
@Service
public class WithdrawalAccountServiceImpl implements IWithdrawalAccountService{

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OperationHistoryRepository historyRepository;
	public boolean withdrawalMoney(double money, Long accountID)
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
			if(money <= 0){
				throw new InvalidRequestedMoney();
			}
			if(this.accountRepository.findOne(accountID)==null){
				throw new InvalidAccountException();
			}
			Account account = this.accountRepository.findOne(accountID);
			double ammount = account.getBalance();
			double res = ammount - money;
			if(res < 0){
				throw new InsufficientBalanceException();
			}
			account.setBalance(res);
			this.accountRepository.save(account);
			// Seif TODO use spring AOP instead hard coding the logging actions!!
			saveWithdrawalHistory(account, money);
			return true;
	}
	
	private void saveWithdrawalHistory(Account account, double ammount){ 
		OperationHistory operationHistory = new OperationHistory();
		operationHistory.setOperation(Operation.Withdrawal);
		operationHistory.setOperationDateTime(LocalDateTime.now());
		operationHistory.setAmmount(ammount);
		operationHistory.setAccount(account);
		historyRepository.save(operationHistory);
	}

}
