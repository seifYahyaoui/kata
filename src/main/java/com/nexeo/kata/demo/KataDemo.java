package com.nexeo.kata.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nexeo.kata.checked.exception.InsufficientBalanceException;
import com.nexeo.kata.checked.exception.InvalidAccountException;
import com.nexeo.kata.checked.exception.InvalidRequestedMoney;
import com.nexeo.kata.configuration.Config;
import com.nexeo.kata.services.IDepositAccountService;
import com.nexeo.kata.services.IOperationsHistoryService;
/**
 * 
 * @author yahyaoui
 *
 */
public class KataDemo {

	public static void main(String[] args) throws InsufficientBalanceException, InvalidAccountException, InvalidRequestedMoney {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		IDepositAccountService bean = applicationContext.getBean(IDepositAccountService.class);
		System.out.println(bean.depositMoney(0, null));
	}
}
