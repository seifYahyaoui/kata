package com.nexeo.kata.services.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.nexeo.kata.checked.exception.InsufficientBalanceException;
import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.checked.exception.InvalidRequestedMoney;
import com.nexeo.kata.configuration.Config;
import com.nexeo.kata.model.Account;
import com.nexeo.kata.model.Client;
import com.nexeo.kata.model.Operation;
import com.nexeo.kata.model.OperationHistory;
import com.nexeo.kata.services.IAccountService;
import com.nexeo.kata.services.IOperationsHistoryService;
/**
 * 
 * @author yahyaoui
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class OperationsHistoryServiceImplTest {
	@Autowired
	private IOperationsHistoryService historyService;
	@Autowired
	private IAccountService accountService;

	// The valid account that will be used all along the tests
	private static final Account account = new Account();

	// Fill the in-memory database
	@Before
	public void init() {

		account.setBalance(0);
		Client client = new Client();
		client.setAddress("Nexeo Paris");
		client.setFirstName("Seif Eddine");
		client.setLastName("Yahyaoui");
		client.setRib("0000");

		account.setClient(client);
		Account acc = this.accountService.saveAccount(account);
		account.setId(acc.getId());
	}

	@Test
	public final void whenDepositWithValidAccountAndValidMoneyThenHistoryDepositHistoryIsNotEmpty()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		historyService.deleteHistory(account.getId());
		this.accountService.depositMoney(5, account.getId());
		this.accountService.depositMoney(5, account.getId());

		Collection<OperationHistory> historyOfDepositOperation = this.historyService.getHistory(account.getId());
		historyOfDepositOperation.stream().forEach(h -> assertEquals(Operation.Deposit, h.getOperation()));
		assertEquals(2,historyOfDepositOperation.size());
	}
	
	@Test
	public final void historyMustBeOrderedByOperationTime()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		historyService.deleteHistory(account.getId());
		this.accountService.depositMoney(5, account.getId());
		this.accountService.depositMoney(5, account.getId());
		this.accountService.withdrawalMoney(5, account.getId());

		List<OperationHistory> historyOfDepositOperation = this.historyService.getHistory(account.getId());
		
		Comparator<OperationHistory> comparator = (h1, h2) -> h1.getOperationDateTime().isBefore(h2.getOperationDateTime()) ? 0 : 1;
		
		List<OperationHistory> newList = new ArrayList<>(historyOfDepositOperation);
		
		Collections.sort(newList, comparator);
		
		assertEquals(true,historyOfDepositOperation.equals(newList));
		
	}

	@Test
	public final void whenWithdrawalWithValidAccountAndValidMoneyWithAccountThatHoldTheRequestedMoneyThenHistoryIsNotEmpty()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		historyService.deleteHistory(account.getId());
		accountService.depositMoney(10, account.getId());
		this.accountService.withdrawalMoney(5, account.getId());
		Collection<OperationHistory> historyOfDepositOperation = this.historyService.getHistory(account.getId());

		assertEquals(2, historyOfDepositOperation.size());
	}
}
