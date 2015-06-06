package com.devops.data;

public enum DeclineReason {
	AMOUNT_TOO_HIGH ("Amount too high"),
	CURRENCY_NOT_SUPPORTED ("Currency no supported"),
	SENDER_BLACKLISTED("Sender is blacklisted"),
	COUNTERPARTY_BLACKLISTED("Counterparty is blacklisted"),
	EMPTY_TXN_DATA ("Transaction data is empty"),
	SYSTEM_UNAVAILABLE ("System is unavailable");
	
	private final String reason;
	
	DeclineReason(String reason) {
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}
}
