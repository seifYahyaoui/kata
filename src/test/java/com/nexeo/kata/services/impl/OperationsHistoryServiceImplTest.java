package com.nexeo.kata.services.impl;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

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
import com.nexeo.kata.model.OperationHistory;
import com.nexeo.kata.services.IDepositAccountService;
import com.nexeo.kata.services.IOperationsHistoryService;
import com.nexeo.kata.services.IWithdrawalAccountService;
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
	private IWithdrawalAccountService withdrawalAccountService;
	@Autowired
	private IDepositAccountService accountService;

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
		Collection<OperationHistory> historyOfDepositOperation = this.historyService.getHistoryOfDepositOperation(account.getId());
		assertEquals(1,historyOfDepositOperation.size());
	}

	@Test
	public final void whenWithdrawalWithValidAccountAndValidMoneyWithAccountThatHoldTheRequestedMoneyThenHistoryWithdrawalHistoryIsNotEmpty()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		historyService.deleteHistory(account.getId());
		accountService.depositMoney(10, account.getId());
		this.withdrawalAccountService.withdrawalMoney(5, account.getId());
		Collection<OperationHistory> historyOfDepositOperation = this.historyService.getHistoryOfWithdrawalOperation(account.getId());
		assertEquals(1, historyOfDepositOperation.size());
	}
}
