package com.devops.main;

import com.devops.data.DataSet;
import com.devops.data.Money;
import com.devops.data.TransactionResult;
import com.devops.transaction.ProcessTransactionUtil;

public class DevOpsMain {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Initialize arguments to utils
		DataSet transactionData = initializeData();
		
		//Process Transcation
		TransactionResult result = ProcessTransactionUtil.processTransaction(transactionData);
		
		//Display in console
		System.out.println("Transaction is : " + (result != null && result.getResult() ? "Approved" : "Declined: " + result.getReason().getReason())); 

	}
	
	public static DataSet initializeData() {
		DataSet data = new DataSet();
		data.setSenderName("Sender");
		data.setCounterpartyName("Counterparty");
		data.setAmount(new Money(300L, "USD"));
		data.setNotes("Sending money for dinner");
		return data;
	}

}
