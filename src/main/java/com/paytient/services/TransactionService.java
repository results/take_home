package com.paytient.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytient.constants.PaymentAdjustmentCode;
import com.paytient.constants.TransactionStatus;
import com.paytient.dto.TransactionDTO;
import com.paytient.entities.Balance;
import com.paytient.entities.PaymentAdjustment;
import com.paytient.entities.PaymentMethod;
import com.paytient.entities.Transaction;
import com.paytient.entities.User;
import com.paytient.repositories.TransactionRepository;

@Service
@Transactional
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private PaymentAdjustmentService adjustmentService;
	
	@Autowired
	private BalanceService balanceService;
		
	public TransactionDTO createOneTimePayment(User user, PaymentMethod paymentMethod, BigDecimal paymentAmount) {
		BigDecimal startingBalance = null;
		BigDecimal endingBalance = null;
		PaymentAdjustment adjustment = null;
		BigDecimal adjustmentAmount = new BigDecimal(0.00);
		if(user != null && paymentMethod != null && paymentAmount.compareTo(BigDecimal.ZERO) > 0) {
			Balance balance = user.getBalance();
			startingBalance = balance.getCurrentBalance();
			Optional<PaymentAdjustment> adjustmentOptional = adjustmentService.findMatchingAdjustment(PaymentAdjustmentCode.ONE_TIME_PAYMENT, paymentAmount);
			if(adjustmentOptional.isPresent()) {
				adjustment = adjustmentOptional.get();
				adjustmentAmount = paymentAmount.multiply(adjustment.getAdjustmentPercent()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN);
				endingBalance = startingBalance.subtract(paymentAmount).subtract(adjustmentAmount);
			} else {
				endingBalance = startingBalance.subtract(paymentAmount);
			}
			Transaction transaction = new Transaction(user, startingBalance, paymentAmount, adjustment, adjustmentAmount, endingBalance, paymentMethod);
			if(transaction != null) {
				if(balanceService.applyPayment(balance, paymentAmount)) {
					user.getTransactions().add(transaction);
					return new TransactionDTO(balance, transaction);
				}
			}
		}
		return new TransactionDTO(TransactionStatus.ERROR);
	}
	
	
}
