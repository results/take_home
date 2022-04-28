package com.paytient.security;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.paytient.entities.AuthToken;
import com.paytient.services.AuthTokenService;


public class AuthTokenFilter implements Filter {

	private static final String HEADER_NAME = "Authorization";

	@Autowired
	private AuthTokenService authService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			AuthToken token = getAuthTokenFromRequest((HttpServletRequest) request);
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			if (token != null) {
				if (!authService.isTokenExpired(token)) {
					AuthTokenAuthentication authentication = new AuthTokenAuthentication(token.getToken(),AuthorityUtils.NO_AUTHORITIES);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					System.err.println("bad");
					httpResponse.setStatus(401);
					httpResponse.getWriter().write("Authorization token is expired. Expired at: " + token.getExpiresAt());
					return;
				}
			} else {
				System.err.println("bad");
				httpResponse.setStatus(401);
				httpResponse.getWriter().write("Authorization token is not valid or missing.");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private AuthToken getAuthTokenFromRequest(HttpServletRequest request) {
		String receivedToken = request.getHeader(HEADER_NAME);
		if (receivedToken != null) {
			UUID token = UUID.fromString(receivedToken.trim());
			if (token != null) {
				return authService.getAuthByToken(token);
			}
		}
		return null;
	}

}
