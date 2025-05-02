package org.mosorgpay.service;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.mosorgpay.model.Employee;
import org.mosorgpay.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;

@Service
public class TransferService {

	private final Logger logger = Logger.getLogger(" Transfer Service ");
	
	private final EmployeeRepository employeeRepository;
	
	public TransferService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	
	@Transactional
	public String transfer(BigDecimal amount, Employee sender, Employee receiver) {
		
//		BigDecimal senderBalance = sender.getBalance();
//		BigDecimal receiverBalance = receiver.getBalance();
//		
//		BigDecimal newSenderBalance = senderBalance.subtract(amount);
//		BigDecimal newReceiverBalance = senderBalance.add(amount);
		
		try {
			employeeRepository.updateBalance(new BigDecimal(2000), "moshPy");
			logger.info(sender.getId());
		} catch( Exception e) {
			e.printStackTrace();
		}
		
		return "good";
	}
}
