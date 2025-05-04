package com.eventa.booking.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsServiceConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {

			return org.springframework.security.core.userdetails.User.withUsername(username).password("")
					.authorities("ROLE_USER").build();
		};
	}
}