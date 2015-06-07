package com.devops.transaction;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.devops.data.DataSet;
import com.devops.data.DeclineReason;
import com.devops.data.Money;
import com.devops.data.TransactionResult;

public class ProcessTransactionResultTest {
	
	@Test
	public void testEmptyCurrency() {
		DataSet data = initData();
		data.setAmount(new Money(100L, null));
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.EMPTY_TXN_DATA, result.getReason());
	}
	
	@Test
	public void testEmptyAmount() {
		DataSet data = initData();
		data.setAmount(new Money(null, "USD"));
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.EMPTY_TXN_DATA, result.getReason());
	}
	
	@Test
	public void testEmptySenderName() {
		DataSet data = initData();
		data.setSenderName(null);
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.EMPTY_TXN_DATA, result.getReason());
	}
	
	@Test
	public void testEmptyCounterpartyName() {
		DataSet data = initData();
		data.setCounterpartyName(null);
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.EMPTY_TXN_DATA, result.getReason());
	}
	
	@Test
	public void testEmptyData() {
		TransactionResult result = ProcessTransactionUtil.processTransaction(null);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.EMPTY_TXN_DATA, result.getReason());
	}
	
	@Test
	public void testHighAmountDecline() {
		DataSet data = initData();
		data.setAmount(new Money(1200L, "USD"));
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.AMOUNT_TOO_HIGH, result.getReason());
	}
	
	@Test
	public void testUnsupportedCurrencyDecline() {
		DataSet data = initData();
		data.setAmount(new Money(1000L, "EUR"));
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.CURRENCY_NOT_SUPPORTED, result.getReason());
	}
	
	@Test
	public void testSenderBlacklistDecline() {
		DataSet data = initData();
		data.setSenderName("badSender");
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.SENDER_BLACKLISTED, result.getReason());
	}
	
	@Test
	public void testCounterpartyBlacklistDecline() {
		DataSet data = initData();
		data.setCounterpartyName("badCounterparty");
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.FALSE, result.getResult());
		Assert.assertEquals(DeclineReason.COUNTERPARTY_BLACKLISTED, result.getReason());
	}
	
	@Test
	public void testHappyPath() {
		DataSet data = initData();
		TransactionResult result = ProcessTransactionUtil.processTransaction(data);
		Assert.assertEquals(Boolean.TRUE, result.getResult());
	}
	
	private DataSet initData() {
		DataSet data = new DataSet();
		data.setSenderName("sender");
		data.setCounterpartyName("counterparty");
		data.setAmount(new Money(100L, "USD"));
		data.setNotes("notes");
		return data;
	}

}
