package org.mosorgpay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import org.mosorgpay.dto.TransactionDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.repository.EmployeeRepository;
import org.mosorgpay.service.TransactionService;
import org.mosorgpay.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class TransferController {

	private final TransferService transferService;
	private final Logger logger = Logger.getLogger(" Transfer Controller");
	private final TransactionService transactionService;
	
	public TransferController(TransferService transferService, TransactionService transactionService) {
		this.transferService = transferService;
		this.transactionService = transactionService;
	}
	
	@GetMapping("/transfer")
	public String tryTransfer(HttpSession session) {
		if (session.getAttribute("employee") == null) {
			return "redirect:/dashboard";
		}
		return "transferPage";
	}
	
	@PostMapping("/transfer")
	public String doTransfer(@ModelAttribute TransactionDto transactionDto, HttpSession session, Model model) throws IOException {
		
		Employee sender =  (Employee) session.getAttribute("employee");
		BigDecimal amount = transactionDto.getAmount();
		String receiverId = transactionDto.getReceiverEmail();
		String message = transferService.transfer(amount, sender , receiverId );
		
		model.addAttribute("message", message);
		if (message != "okay") {
			return "HandleError";
		}
		transactionService.saveTransaction(sender, transactionDto);
		return "transferPage";
	}
}
