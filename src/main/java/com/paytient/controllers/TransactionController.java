package com.paytient.controllers;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paytient.dto.TransactionDTO;
import com.paytient.entities.PaymentMethod;
import com.paytient.entities.User;
import com.paytient.services.AuthTokenService;
import com.paytient.services.TransactionService;

@RestController
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	private AuthTokenService authService;
	
	@Autowired
	private TransactionService transactionService;

	@PostMapping("/one-time-payment")
	public ResponseEntity<?> createUser(@RequestHeader UUID Authorization, @RequestParam UUID paymentUUID, @RequestParam BigDecimal paymentAmount) {
		User user = authService.getUserFromToken(Authorization);//auth token is verfied already from security
		if(user == null) {
			return new ResponseEntity<String>("A user could not be found for that authorization token. Please verify it is accured or non-expired.", HttpStatus.BAD_REQUEST);
		}
		Optional<PaymentMethod> paymentOptional = user.getPaymentMethods().stream().filter(method -> method.getPaymentUUID().compareTo(paymentUUID) == 0).findFirst();
		if(!paymentOptional.isPresent()) {
			return new ResponseEntity<String>("A payment method UUID associated with this user must be provided to process this payment.", HttpStatus.BAD_REQUEST);	
		}
		PaymentMethod paymentMethod = paymentOptional.get();
		if(paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
			return new ResponseEntity<String>("Payment amount must be greater than $0.00 to be processed.", HttpStatus.BAD_REQUEST);	
		}
		TransactionDTO transactionResponse = transactionService.createOneTimePayment(user,paymentMethod,paymentAmount);
		return new ResponseEntity<TransactionDTO>(transactionResponse, HttpStatus.OK);
	}
	
}
