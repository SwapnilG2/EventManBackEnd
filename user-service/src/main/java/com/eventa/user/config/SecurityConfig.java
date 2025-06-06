package com.eventa.user.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


	private final JwtAuthFilter jwtAuthFilter;

//	@Bean
//	public UserDetailsService userDetailsService() {
//		return email -> userRepository.findByEmail(email)
//				.map(user -> User.withUsername(user.getEmail()).password(user.getPassword())
//						.roles(user.getRole().name()).build())
//				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> csrf
						.disable())
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/users/login", "/users/register", "/swagger-ui.html",
								"/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/v3/api-docs").permitAll().anyRequest().authenticated())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
