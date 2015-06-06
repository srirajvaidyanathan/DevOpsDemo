package com.devops.data;

public class Money {
	
	//Amount in cents
	private Long amount;
	private String currency;
	
	public Money(Long amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	

}
