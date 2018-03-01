package com.nexeo.kata.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 
 * @author yahyaoui
 *
 */
@Entity
@Table(name = "account")
public class Account extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="client_id")
	private Client client;
	@Column(name = "ammount")
	private double balance = 0;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double ammount) {
		this.balance = ammount;
	}

}
