package com.paytient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytient.repositories.PaymentMethodRepository;

@Service
@Transactional
public class PaymentMethodService {

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	
}
