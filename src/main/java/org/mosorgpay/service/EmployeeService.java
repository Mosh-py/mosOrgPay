package org.mosorgpay.service;

import org.mosorgpay.dto.EmployeeDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.repository.EmployeeRepository;
import org.mosorgpay.repository.OrganisationRepository;
import org.mosorgpay.serviceInterface.Service;

@org.springframework.stereotype.Service
public class EmployeeService implements Service {

	private final EmployeeRepository repository;
	private final OrganisationRepository organisationRepository;
	
	public EmployeeService(EmployeeRepository repository, OrganisationRepository organisationRepository) {
		this.repository = repository;
		this.organisationRepository = organisationRepository;
	}
	
	public String register(Object obj) {
		EmployeeDto dto = (EmployeeDto) obj;
		
		Employee employee = new Employee();
		employee.setEmailAddress(dto.getEmailAddress());
		employee.setFirstName(dto.getFirstName());
		System.out.println(dto.getId());
		employee.setId(dto.getId());
		employee.setPassword(dto.getPassword());
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
	
	public String delete(Object obj) {
		 String id = (String) obj;
		 
		
		 
		 return "to be determined";
	}

}
