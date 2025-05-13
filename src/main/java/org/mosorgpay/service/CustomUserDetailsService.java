package org.mosorgpay.service;

import org.mosorgpay.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import org.mosorgpay.model.Employee;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	
	private final EmployeeRepository employeeRepository;
	private final Logger logger = Logger.getLogger(" from the user Details ");
	
	
	
	public CustomUserDetailsService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee employee = employeeRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException(" User does'nt esxist"));
		
		logger.info(employee.getFirstName());
		logger.info(employee.getPassword());
		
		return new User(employee.getEmailAddress(), employee.getPassword(), List.of(new SimpleGrantedAuthority("worker")));
	}

}
