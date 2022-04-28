package com.paytient.services;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytient.constants.PaymentAdjustmentCode;
import com.paytient.entities.PaymentAdjustment;
import com.paytient.repositories.PaymentAdjustmentRepository;

@Service
@Transactional
public class PaymentAdjustmentService {
	
	@Autowired
	private PaymentAdjustmentRepository adjustmentRepository;
	
	/**
	 * Finds most appropriate adjustment 
	 * 	* Filters codes by threshold, finds the highest treshold
	 * @param adjustmentCode Code to find for adjustments
	 * @param paymentAmount The amount of the payment
	 * @return Optional<PaymentAdjustment> adjustment
	 */
	public Optional<PaymentAdjustment> findMatchingAdjustment(PaymentAdjustmentCode adjustmentCode, BigDecimal paymentAmount) {
		return adjustmentRepository.findByAdjustmentCode(adjustmentCode.toString()).stream()
				.filter(adj -> adj.getAdjustmentThreshold().compareTo(paymentAmount) < 1)
				.max(Comparator.comparing(PaymentAdjustment::getAdjustmentThreshold));
	}
	
	/**
	 * Fill with matches. Would make endpoint/service method for this in real world
	 */
	public void createMatchAdjustments() {
		PaymentAdjustment adjust1 = new PaymentAdjustment(new BigDecimal(1.0), new BigDecimal(0.00), "OTP", "Match: 1% of payment for one-time payments", null);
		PaymentAdjustment adjust3 = new PaymentAdjustment(new BigDecimal(3.0), new BigDecimal(10.00), "OTP", "Match: 3% of payment for one-time payments", null);
		PaymentAdjustment adjust5 = new PaymentAdjustment(new BigDecimal(5.0), new BigDecimal(50.00), "OTP", "Match: 5% of payment for one-time payments", null);
		adjustmentRepository.save(adjust1);
		adjustmentRepository.save(adjust3);
		adjustmentRepository.save(adjust5);
	}

}
