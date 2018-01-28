package com.nexeo.kata.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operation_history")
/**
 * 
 * @author yahyaoui
 *
 */
public class OperationHistory extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name="operation")
	@Enumerated(EnumType.STRING)
	private Operation operation = Operation.Default;
	@Column(name = "operation_dateTime")
	private LocalDateTime operationDateTime;
	@Column(name = "ammount")
	private double ammount;
	@Column(name = "balance")
	private double balance;

	@OneToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public LocalDateTime getOperationDateTime() {
		return operationDateTime;
	}

	public void setOperationDateTime(LocalDateTime operationDateTime) {
		this.operationDateTime = operationDateTime;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
