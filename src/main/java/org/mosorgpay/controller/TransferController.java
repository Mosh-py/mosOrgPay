package org.mosorgpay.controller;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.mosorgpay.model.Employee;
import org.mosorgpay.repository.EmployeeRepository;
import org.mosorgpay.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class TransferController {

	private final TransferService transferService;
	private final Logger logger = Logger.getLogger(" Transfer Controller");
	public TransferController(TransferService transferService) {
		this.transferService = transferService;
	}
	
	@GetMapping("/transfer")
	public String tryTransfer(HttpSession session) {
		
		return "transferPage";
	}
	
	@PostMapping("/transfer")
	public String doTransfer(@RequestParam String receiverId, @RequestParam BigDecimal amount, HttpSession session) {
		
		Employee sender =  (Employee) session.getAttribute("employee");
		transferService.transfer(amount, sender , receiverId );
		return "transferPage";
	}
}
