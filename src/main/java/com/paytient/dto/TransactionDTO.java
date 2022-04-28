package com.paytient.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.UUID;

import com.paytient.constants.PaymentType;
import com.paytient.constants.TransactionStatus;
import com.paytient.entities.Balance;
import com.paytient.entities.Transaction;

public class TransactionDTO {
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		
	private UUID transactionUUID;	
	private String paymentDate;
	private PaymentType paymentType;
	private String paymentAccountNumber;
	private BigDecimal paymentAmount;
	private BigDecimal adjustmentAmount;
	private String adjustmentDescription;
	private BigDecimal totalPaymentAmount;	
	private BigDecimal remainingBalance;
	private String nextDueDate;
	private String status;
	
	public TransactionDTO(TransactionStatus status) {
		this.status = status.toString();
	}
	
	public TransactionDTO(Balance balance, Transaction transaction) {
		this.transactionUUID = transaction.getTransactionUUID();
		this.paymentDate = formatter.format(balance.getLastPaymentDate());
		this.paymentType = transaction.getPaymentMethod().getPaymentType();
		String accountNumbers = transaction.getPaymentMethod().getAccountNumber();
		if(accountNumbers.length() > 4) {
			this.paymentAccountNumber = "*"+accountNumbers.substring(accountNumbers.length()-4, accountNumbers.length());
		}
		this.paymentAmount = transaction.getTransactionAmount();
		this.adjustmentAmount = transaction.getAdjustmentAmount();
		this.adjustmentDescription = transaction.getPaymentAdjustment().getDescription();
		this.totalPaymentAmount = this.paymentAmount.add(this.adjustmentAmount);
		this.remainingBalance = balance.getCurrentBalance();
		this.nextDueDate = formatter.format(balance.getDueDate());
		this.status = TransactionStatus.SUCCESS.toString();
	}
	
	public UUID getTransactionUUID() {
		return transactionUUID;
	}
	public void setTransactionUUID(UUID transactionUUID) {
		this.transactionUUID = transactionUUID;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public BigDecimal getAdjustmentAmount() {
		return adjustmentAmount;
	}
	public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}
	public String getAdjustmentDescription() {
		return adjustmentDescription;
	}
	public void setAdjustmentDescription(String adjustmentDescription) {
		this.adjustmentDescription = adjustmentDescription;
	}
	public BigDecimal getTotalPaymentAmount() {
		return totalPaymentAmount;
	}
	public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
		this.totalPaymentAmount = totalPaymentAmount;
	}
	public BigDecimal getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(BigDecimal remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	public String getNextDueDate() {
		return nextDueDate;
	}
	public void setNextDueDate(String nextDueDate) {
		this.nextDueDate = nextDueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status.toString();
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentAccountNumber() {
		return paymentAccountNumber;
	}

	public void setPaymentAccountNumber(String paymentAccountNumber) {
		this.paymentAccountNumber = paymentAccountNumber;
	}

	
}
