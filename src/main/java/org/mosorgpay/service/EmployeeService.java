package org.mosorgpay.service;

import org.mosorgpay.model.Employee;
import org.mosorgpay.model.EmployeeRepository;
import org.mosorgpay.serviceInterface.Service;

@org.springframework.stereotype.Service
public class EmployeeService implements Service {

	private final EmployeeRepository repository;
	
	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}
	@Override
	public String save(Object obj ) {
		Employee employee = (Employee) obj;
		repository.save(employee);
		return " ";
	}

	@Override
	public String delete(Object obj) {
		// TODO Auto-generated method stub
		Employee employee = (Employee) obj;
		repository.delete(employee);
		return "";
	}

}
