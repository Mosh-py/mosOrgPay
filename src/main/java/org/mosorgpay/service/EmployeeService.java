package org.mosorgpay.service;

import java.util.Optional;

import org.mosorgpay.dto.EmployeeDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.repository.EmployeeRepository;
import org.mosorgpay.repository.OrganisationRepository;
import org.mosorgpay.serviceInterface.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.stereotype.Service
public class EmployeeService implements Service {

	private final EmployeeRepository repository;
	private final OrganisationRepository organisationRepository;
	
	
	private final PasswordEncoder passwordEncoder ;
	public EmployeeService(EmployeeRepository repository, OrganisationRepository organisationRepository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.organisationRepository = organisationRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public String register(Object obj) {
		EmployeeDto dto = (EmployeeDto) obj;
		
		Employee employee = new Employee();
		employee.setEmailAddress(dto.getEmailAddress());
		employee.setFirstName(dto.getFirstName());
		System.out.println(dto.getId()); 
		employee.setId(dto.getId());
		
		employee.setPassword(passwordEncoder.encode(dto.getPassword()));
		employee.setPhoneNumber(dto.getPhoneNumber());
		employee.setSecondName(dto.getSecondName());
		try {
			String organizationCode = dto.getOrganisationCode();
			System.out.println(organizationCode);		
			employee.setOrganisation(organisationRepository.findById(dto.getOrganisationCode()).get());
		}
			
		catch(Exception e) {
			e.printStackTrace();
		}
		repository.save(employee);
		return "done";
	}
	
	public Employee fetchEmployee(String name){
		return repository.findById(name).orElseThrow(()-> new UsernameNotFoundException(" Could.nt find the "
				+ "motherfuckng user"));
	}
	
	public String delete(Object obj) {
		 String id = (String) obj;
		 
		
		 
		 return "to be determined";
	}

}
