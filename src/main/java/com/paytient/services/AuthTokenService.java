package com.paytient.services;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paytient.entities.AuthToken;
import com.paytient.entities.User;
import com.paytient.repositories.AuthTokenRepository;

@Service
@Transactional
public class AuthTokenService {
	
	@Autowired
	private AuthTokenRepository tokenRepository;
	
	public boolean isTokenExpired(AuthToken token) {
		Calendar cal = Calendar.getInstance();
		if(token != null) {
			return cal.getTime().after(token.getExpiresAt());
		}
		return true;
	}
	
	public AuthToken getAuthByToken(UUID token) {
		if(token != null) {
			Optional<AuthToken> tokenOptional = tokenRepository.findByToken(token);
			if(tokenOptional.isPresent()) {
				return tokenOptional.get();
			}
		}
		return null;
	}

	public User getUserFromToken(UUID token) {
		if(token != null) {
			Optional<AuthToken> tokenOptional = tokenRepository.findByToken(token);
			if(tokenOptional.isPresent()) {
				AuthToken auth = tokenOptional.get();
				Calendar cal = Calendar.getInstance();
				if(cal.getTime().before(auth.getExpiresAt())) {
					return auth.getUser();
				}
			}
		}
		return null;
	}

}
