package org.mosorgpay.service;

import java.time.LocalDateTime;

import org.mosorgpay.dto.MoneyRequestDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.model.MoneyRequest;
import org.mosorgpay.repository.MoneyRequestRepository;
import org.springframework.stereotype.Service;

@Service 
public class RequestMoneyService {

	private final MoneyRequestRepository repository;
	
	public RequestMoneyService(MoneyRequestRepository repository) {
		this.repository = repository;
	} 
	
	public void requestFunds(Employee borrower, MoneyRequestDto moneyRequestDto) {
		MoneyRequest moneyRequest = new MoneyRequest();
		moneyRequest.setLendeeId(moneyRequestDto.getLendeeId());
		LocalDateTime date = LocalDateTime.now();
		moneyRequest.setDate(date);
		repository.save(moneyRequest);
	}
	
}

