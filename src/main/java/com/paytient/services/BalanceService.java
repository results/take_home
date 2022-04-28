package com.paytient.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytient.entities.Balance;
import com.paytient.entities.User;
import com.paytient.repositories.BalanceRepository;

@Service
@Transactional
public class BalanceService {

	@Autowired
	private BalanceRepository balanceRepository;
	
	/**
	 * Finds balance by user
	 * @param user User to find balance for
	 * @return balance associated with user
	 */
	public Balance getBalanceByUser(User user) {
		if(user != null) {
			Optional<Balance> balanceOptional = balanceRepository.findByUser(user);
			if(balanceOptional.isPresent()) {
				return balanceOptional.get();
			}
		}
		return null;
	}
	
	/**
	 * Finds next payment date
	 * @param days days from today
	 * @param includeWeekends Should include weekend days in next date calculation
	 * @return new due date
	 */
	public Date calculateNextDueDate(int days, boolean includeWeekends) {
	    LocalDate result = LocalDate.now();
	    if(includeWeekends) {
	    	result.plusDays(days);
			return Date.from(result.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());	
	    } else {
		    int addedDays = 0;
		    while (addedDays < days) {
		        result = result.plusDays(1);
		        if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
		            ++addedDays;
		        }
		    }
			return Date.from(result.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	    }
	}
	
	/**
	 * Applies payment to balance
	 * @param balance balance to update
	 * @param payment payment Amount
	 * @return payment successful
	 */
	public boolean applyPayment(Balance balance, BigDecimal payment) {
		if(balance != null && payment.compareTo(BigDecimal.ZERO) > 0) {
			balance.setPreviousBalance(balance.getCurrentBalance().setScale(2, RoundingMode.HALF_EVEN));
			balance.setCurrentBalance(balance.getCurrentBalance().subtract(payment).setScale(2, RoundingMode.HALF_EVEN));//has the option to put us negative, but we will leave for now (not in spec)
			balance.setDueDate(calculateNextDueDate(15, false));
			balance.setLastPaymentDate(new Date());
			return true;
		}
		return false;
	}
	
}
