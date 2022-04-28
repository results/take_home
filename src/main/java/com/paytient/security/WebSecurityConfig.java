package com.paytient.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.HeaderWriterFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthTokenFilter filter() {
		return new AuthTokenFilter();
	}
	
	/**
	 * What pages we can ignore the missing token for
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/create");
	}
	
	@Bean
	public FilterRegistrationBean<AuthTokenFilter> authTokenFilterBean() {
	    FilterRegistrationBean<AuthTokenFilter> registrationBean = new FilterRegistrationBean<AuthTokenFilter>();
	    registrationBean.setFilter(filter());
	    registrationBean.setEnabled(false);
	    return registrationBean;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.anonymous().disable().csrf().disable()
			.addFilterBefore(authTokenFilterBean().getFilter(), HeaderWriterFilter.class)
			.authorizeRequests().anyRequest()
			.fullyAuthenticated().and()
			.formLogin().disable();
	}
}