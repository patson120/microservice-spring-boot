package com.plinepay.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.plinepay.authservice.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf()
				.disable()
				.authorizeHttpRequests()
				.requestMatchers("/auth/login", "/auth/register")
				.permitAll()
				// .and()
				// .authorizeHttpRequests()
				// .requestMatchers("/**")
				// .permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authenticationProvider(authenticationProvider());
		
		http.addFilterBefore(jwtRequestFilter,
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
		return authConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
