package com.paytient.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paytient.entities.AuthToken;
import com.paytient.entities.User;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
	
	Optional<AuthToken> findByToken(UUID token);
	
	Optional<User> findUserByToken(UUID token);

}

