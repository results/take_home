package com.paytient.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.paytient.entities")
@EnableJpaRepositories(basePackages = "com.paytient.repositories")
@ComponentScan({"com.paytient.controllers","com.paytient.services","com.paytient.security"})
public class PaytientApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(PaytientApplication.class, args);
	}
	
}
