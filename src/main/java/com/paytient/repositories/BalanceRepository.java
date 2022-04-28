package com.paytient.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paytient.entities.Balance;
import com.paytient.entities.User;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

	Optional<Balance> findByUser(User user);
	
}
