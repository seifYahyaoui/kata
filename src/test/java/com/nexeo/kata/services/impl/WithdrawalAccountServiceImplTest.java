package com.nexeo.kata.services.impl;

import static org.junit.Assert.assertEquals;

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
import com.nexeo.kata.services.IDepositAccountService;
import com.nexeo.kata.services.IWithdrawalAccountService;
/**
 * 
 * @author yahyaoui
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class WithdrawalAccountServiceImplTest {

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

	@Test(expected = InvalidRequestedMoney.class)
	public final void whenWithdrawalNegativeMoneyThenExceptionIsThrown()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		this.withdrawalAccountService.withdrawalMoney(-5, 1l);
	}

	@Test(expected = InvalidAccountException.class)
	public final void whenWithdrawalWithInvalidAccountThenExceptionIsThrown()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		// Seif: TODO The rib represent the account not the database table ID
		this.withdrawalAccountService.withdrawalMoney(5, -100l);
	}

	@Test(expected= InsufficientBalanceException.class)
	public final void whenWithdrawalWithValidAccountAndValidMoneyButAnEmptyAccountThenExceptionIsThrown()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		assertEquals(this.withdrawalAccountService.withdrawalMoney(5, account.getId()), true);
	}
	
	@Test
	public final void whenWithdrawalWithValidAccountAndValidMoneyWithAccountThatHoldTheRequestedMoneyThenTestShouldPass()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		accountService.depositMoney(10, account.getId());
		assertEquals(this.withdrawalAccountService.withdrawalMoney(5, account.getId()), true);
		double ammount = this.accountService.findByAccountID(account.getId()).getBalance();
		assertEquals(10-5, ammount, 0);
	}
}

