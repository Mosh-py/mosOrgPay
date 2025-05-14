package org.mosorgpay.service;

import org.mosorgpay.model.Employee;
import org.mosorgpay.repository.MoneyRequestRepository;
import org.springframework.stereotype.Service;

@Service 
public class RequestMoneyService {

	private final MoneyRequestRepository repository;
	
	public RequestMoneyService(MoneyRequestRepository repository) {
		this.repository = repository;
	}
	public void requestFunds(Employee borrower,String lendeeId) {
		
		
	}
}
