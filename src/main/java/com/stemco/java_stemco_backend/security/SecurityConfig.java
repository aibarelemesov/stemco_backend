package com.stemco.java_stemco_backend.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private FirebaseIdTokenValidationFilter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilterAt(filter, BasicAuthenticationFilter.class)
				.authorizeRequests()
				.anyRequest()
				.permitAll()
				.and()
				.cors();
	}
}
