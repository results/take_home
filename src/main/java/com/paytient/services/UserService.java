package com.paytient.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytient.constants.PaymentType;
import com.paytient.entities.AuthToken;
import com.paytient.entities.PaymentMethod;
import com.paytient.entities.User;
import com.paytient.repositories.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * Filler data for db. Endpoints/services in real world
	 */
	public void fillerData() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, 15);
		User user = new User();
		AuthToken token = new AuthToken(user, UUID.randomUUID(), cal.getTime());
		PaymentMethod paymentMethod = new PaymentMethod(user, PaymentType.BANK_DRAFT, "123456789455", cal.getTime());
		user.getBalance().setCurrentBalance(new BigDecimal(500.00));
		user.setSession(token);
		user.getPaymentMethods().add(paymentMethod);
		user.setActive(true);
		user.setFirstName("man");
		user.setLastName("dog");
		userRepository.save(user);
	}

}
