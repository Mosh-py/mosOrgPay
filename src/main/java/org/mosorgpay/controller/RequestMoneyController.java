package org.mosorgpay.controller;

import org.mosorgpay.dto.MoneyRequestDto;
import org.mosorgpay.service.RequestMoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app/requestMoney")
public class RequestMoneyController {

	private final RequestMoneyService requestMoneyService;

	public RequestMoneyController(RequestMoneyService requestMoneyService) {
		this.requestMoneyService = requestMoneyService;
	}

	@GetMapping
	public String requestMoneyController(HttpSession session) {

		if (session.getAttribute("employee") != null) {  
			return "requestMoney.html";
		}
		return "redirect:/dashboard";
	}

	@PostMapping
	public String sendMoneyRequest(@RequestParam MoneyRequestDto dto,HttpSession session) {
//		String message = requestMoneyService.requestFunds();
//		return "test";
		return "test";
	}

}
