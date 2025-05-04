package org.mosorgpay.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

import org.mosorgpay.handler.CustomWebSocketHandler;
import org.mosorgpay.model.Employee;
import org.mosorgpay.model.Transaction;
import org.mosorgpay.repository.EmployeeRepository;
import org.mosorgpay.repository.TransactionRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;

@Service
public class TransferService {

	private final TransactionRepository transactionRepository;
	private final Logger logger = Logger.getLogger(" Transfer Service ");
	
	private final CustomWebSocketHandler handler;
	private final EmployeeRepository employeeRepository;
	
	public TransferService(EmployeeRepository employeeRepository, TransactionRepository transactionRepository, CustomWebSocketHandler handler) {
		this.employeeRepository = employeeRepository;
		this.transactionRepository = transactionRepository;
		this.handler = handler;
	}

	
	@Transactional
	public String transfer(BigDecimal amount, Employee sender, String employeeId) throws IOException {
		
		BigDecimal senderBalance = sender.getBalance();
		
		// get the receiver employee
		Employee receiver = employeeRepository.findById(employeeId).orElseThrow(()-> new UsernameNotFoundException(" hkdss"));
		
		BigDecimal receiverBalance = receiver.getBalance();
		
		BigDecimal newSenderBalance = senderBalance.subtract(amount);
		BigDecimal newReceiverBalance = receiverBalance.add(amount);
		String senderId = sender.getId();
		String receiverId = receiver.getId();
		try {
			employeeRepository.updateBalance(newSenderBalance ,senderId);
			employeeRepository.updateBalance(newReceiverBalance, receiverId);
			logger.info(sender.getId());
		} catch( Exception e) {
			e.printStackTrace();
		}
		
		Transaction transaction = new Transaction(); 
		transaction.setAmount(amount);
		LocalDateTime date = LocalDateTime.now();
		transaction.setDate(date);
		handler.sendBalanceUpdate(receiverId, amount);
		transaction.setReceiver(receiverId);
		transaction.setEmployee(sender);
		transactionRepository.save(transaction);
		logger.info(" " + transaction.getId());
		return "good";
	}
}
