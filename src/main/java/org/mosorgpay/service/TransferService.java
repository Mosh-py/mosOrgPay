package org.mosorgpay.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.mosorgpay.exception.NotEnoughBalanceException;
import org.mosorgpay.exception.PersonelNotFoundException;
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
	private final TransactionService transactionService;
	private final CustomWebSocketHandler handler;
	private final EmployeeRepository employeeRepository;
	
	public TransferService(TransactionService transactionService ,EmployeeRepository employeeRepository, TransactionRepository transactionRepository, CustomWebSocketHandler handler) {
		this.employeeRepository = employeeRepository;
		this.transactionRepository = transactionRepository;
		this.handler = handler;
		this.transactionService = transactionService;
	}
	
	public boolean hasEnoughBalance(BigDecimal senderBalance, BigDecimal amount) {
		 return senderBalance.max(amount).equals(senderBalance);
	}
	
	
	// check if they belong the the same organisation
	public Employee findTheReceiver(Employee sender, List<Employee> possibleReceivers) {
		
		String senderOrganisationId = sender.getOrganisation().getCompanyCode();
		
		for (Employee receiver: possibleReceivers) {
			if (senderOrganisationId.equals(receiver.getOrganisation().getCompanyCode())) {
				return receiver;
			}
		}
		
		
		return null;
	}
	
	/*
	 * First Check if they have enough balance using the <b> hasEnoughBlance </b> method
	 * also checks if they are in the same organistion using the method <b> inTheSameOrganisation </b>
	 * 
	 */
	@Transactional
	public String transfer(BigDecimal amount, Employee sender, String employeeUsername) throws IOException {
		
		
		BigDecimal senderBalance = sender.getBalance();
		
		List<Employee> possibleEmployees = employeeRepository.findAllByUsername(employeeUsername);
		
		
		try {
			Employee receiver = findTheReceiver(sender, possibleEmployees);
			
			if (receiver==null) {
				throw new PersonelNotFoundException( " can't fid the employee");
			}
			BigDecimal receiverBalance = receiver.getBalance();
			if ((!this.hasEnoughBalance(senderBalance, amount))) {
				throw new NotEnoughBalanceException("You dont have enough money");
			}
			
			
			
			BigDecimal newSenderBalance = senderBalance.subtract(amount);
			BigDecimal newReceiverBalance = receiverBalance.add(amount);
			String senderId = sender.getEmailAddress();
			logger.info(senderId);;
			String receiverId = receiver.getEmailAddress();
			employeeRepository.updateBalance(newSenderBalance ,senderId);
			employeeRepository.updateBalance(newReceiverBalance, receiverId);
			logger.info(sender.getUsername());
		} catch( NotEnoughBalanceException e) {
			return "not Enough Money";
		} catch (PersonelNotFoundException e) {
			return " Person Not found ";
		} catch(Exception e) {
			e.printStackTrace();
			return " Something went wrong, kindly try again ";
		}
		
		return "okay";
	}}
