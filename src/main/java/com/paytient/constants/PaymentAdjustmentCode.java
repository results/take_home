package com.paytient.constants;

/**
 * Internal codes we can use to adjust payments. adjustment-code in db
 * @author John Fink
 *
 */
public enum PaymentAdjustmentCode {

	ONE_TIME_PAYMENT("OTP");//one time payment match adjustment
	
	private final String code;
	
	private PaymentAdjustmentCode(String code) {
		this.code = code;
	}
	
	@Override 
    public String toString() {
        return this.code;
    }
}
