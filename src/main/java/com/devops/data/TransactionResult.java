package com.devops.data;

public class TransactionResult {
	
	private Boolean result;
	private DeclineReason reason;
	
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public DeclineReason getReason() {
		return reason;
	}
	public void setReason(DeclineReason reason) {
		this.reason = reason;
	}
}
