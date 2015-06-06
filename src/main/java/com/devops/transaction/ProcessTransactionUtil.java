package com.devops.transaction;

import com.devops.data.DataSet;
import com.devops.data.DeclineReason;
import com.devops.data.TransactionResult;

public class ProcessTransactionUtil {
	
	public static TransactionResult processTransaction(DataSet transactionData) {
		TransactionResult transactionResult = new TransactionResult();
		
		if (transactionData == null || transactionData.getAmount() == null || transactionData.getAmount().getAmount() == null 
				|| transactionData.getAmount().getCurrency() == null || transactionData.getSenderName() == null || transactionData.getCounterpartyName() == null) {
			transactionResult.setResult(false);
			transactionResult.setReason(DeclineReason.EMPTY_TXN_DATA);
		} else if (!transactionData.getAmount().getCurrency().equals("USD")) {
			transactionResult.setResult(false);
			transactionResult.setReason(DeclineReason.CURRENCY_NOT_SUPPORTED);
		} else if (transactionData.getAmount().getAmount() > 1000L) {
			transactionResult.setResult(false);
			transactionResult.setReason(DeclineReason.AMOUNT_TOO_HIGH);
		} else if (transactionData.getSenderName().equals("badSender")) {
			transactionResult.setResult(false);
			transactionResult.setReason(DeclineReason.SENDER_BLACKLISTED);
		} else if (transactionData.getCounterpartyName().equals("badCounterparty")) {
			transactionResult.setResult(false);
			transactionResult.setReason(DeclineReason.COUNTERPARTY_BLACKLISTED);
		} else {
			transactionResult.setResult(true);
		}
		return transactionResult;
	}
	

}
