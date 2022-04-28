package com.paytient.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;

@Transient
public class AuthTokenAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 322586294646118850L;
	
	private UUID token;
    
    public AuthTokenAuthentication(UUID token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}