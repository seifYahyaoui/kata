package com.nexeo.kata.demo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.nexeo.kata.configuration.Config;
import com.nexeo.kata.services.IDepositAccountService;
import com.nexeo.kata.services.IOperationsHistoryService;
import com.nexeo.kata.services.IWithdrawalAccountService;
import com.nexeo.kata.services.impl.DepositAccountServiceImpl;
import com.nexeo.kata.services.impl.OperationsHistoryServiceImpl;
import com.nexeo.kata.services.impl.WithdrawalAccountServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class, loader = AnnotationConfigContextLoader.class)
public class KataDemoTest {

	@Autowired
	private IDepositAccountService accountService ;
	@Autowired
	private IOperationsHistoryService historyService;
	@Autowired
	private IWithdrawalAccountService withdrawalAccountService;

	@Test
	public final void test() {
		assertEquals(1, 1);
	}

	@Test
	public final void test1() {
		assertEquals(1, 2);
	}
}
