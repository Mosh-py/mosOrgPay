package org.mosorgpay.controller;

import java.util.logging.Logger;

import org.mosorgpay.model.Employee;
import org.mosorgpay.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		Employee currentEmployee = (Employee) session.getAttribute("employee");
		logger.info(currentEmployee.getFirstName());
		transferService.transfer(null, currentEmployee, currentEmployee);
		return "test";
	}
}
