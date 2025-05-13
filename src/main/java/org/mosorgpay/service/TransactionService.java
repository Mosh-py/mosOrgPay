package org.mosorgpay.service;

import java.time.LocalDateTime;

import org.mosorgpay.dto.TransactionDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.model.Transaction;
import org.mosorgpay.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
	
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	public void saveTransaction(Employee Sender,TransactionDto dto) {
		Transaction transaction = new Transaction();
		transaction.setAmount(dto.getAmount());
		transaction.setReceiver(dto.getReceiverEmail());
		LocalDateTime date = LocalDateTime.now();
		transaction.setDate(date);
		transaction.setEmployee(Sender);
		transactionRepository.save(transaction);
		
		// Creating new Transactions to Persist to our db
//				Transaction transaction = new Transaction(); 
//				transaction.setAmount(amount);
//				LocalDateTime date = LocalDateTime.now();
//				transaction.setDate(date);
//				handler.handleSomeStuff(receiverId, amount);
//				transaction.setReceiver(receiverId);
//				transaction.setEmployee(sender);
//				transactionRepository.save(transaction);
	}
}
