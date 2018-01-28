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
/**
 * 
 * @author yahyaoui
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class DepositAccountServiceImplTest {
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
	public final void whenDepositNegativeMoneyThenExceptionIsThrown()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		this.accountService.depositMoney(-5, 1l);
	}

	@Test(expected = InvalidAccountException.class)
	public final void whenDepositWithInvalidAccountThenExceptionIsThrown()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		// Seif: TODO The rib represent the account not the database table ID
		this.accountService.depositMoney(5, -100l);
	}

	@Test
	public final void whenDepositWithValidAccountAndValidMoneyTestShouldPass()
			throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		final double money = 5;

		assertEquals(this.accountService.depositMoney(5, account.getId()), true);
		double ammount = this.accountService.findByAccountID(account.getId()).getBalance();
		// the account is empty then after adding the 5$ the account must have
		// 5$ after the operation is finished
		assertEquals(money, ammount, 0);
	}
}
