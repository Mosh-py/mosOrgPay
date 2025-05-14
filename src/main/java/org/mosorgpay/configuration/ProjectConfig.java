package org.mosorgpay.configuration;

import org.mosorgpay.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

	
	private final CustomUserDetailsService customUserDetailsService;
	
	public ProjectConfig(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}  
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.userDetailsService(customUserDetailsService);
		http.formLogin(c->c.loginPage("/employeeLogin").defaultSuccessUrl("/dashboard"));
		http.csrf(c->{
			try {
				c.disable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}); 
		
		return http.build();	
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}
	
	
	
	
}
