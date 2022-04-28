package com.paytient.constants;

/**
 * Transaction status response
 * @author John Fink
 *
 */
public enum TransactionStatus {
	
	SUCCESS("Payment was applied successfully."),
	ERROR("There was an eror with the payment."),
	REJECTED("Payment was rejected. Not successful.");
	
	private final String statusText;
	
	private TransactionStatus(String statusText) {
		this.statusText = statusText;
	}
	
	@Override 
	public String toString() { 
	    return this.statusText; 
	}
}
