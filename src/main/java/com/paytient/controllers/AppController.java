package com.paytient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paytient.services.PaymentAdjustmentService;
import com.paytient.services.UserService;

@RestController
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentAdjustmentService adj;

	/**
	 * Just did this to create some data in db user, payment, matches, token etc
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createUser() {
		userService.fillerData();
		adj.createMatchAdjustments();
		return new ResponseEntity<String>("created", HttpStatus.CREATED);
	}
	
	
}
