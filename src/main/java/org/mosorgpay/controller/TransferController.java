package org.mosorgpay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.mosorgpay.apiResponse.TransferApiResponse;
import org.mosorgpay.dto.TransactionDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.repository.EmployeeRepository;
import org.mosorgpay.service.TransactionService;
import org.mosorgpay.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class TransferController {

	private final TransferService transferService;
	private final Logger logger = Logger.getLogger(" Transfer Controller");
	private final TransactionService transactionService;
	private Map<String, SseEmitter> emitters = new HashMap<>();
	
	public TransferController(TransferService transferService, TransactionService transactionService) {
		this.transferService = transferService;
		this.transactionService = transactionService;
	}
	
	@ResponseBody
	@CrossOrigin
	@GetMapping("/balanceUpdates")
	public SseEmitter subscribeToBalance(HttpSession session) {
		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
		Employee employee = (Employee) session.getAttribute("employee");
		String email = employee.getEmailAddress().toLowerCase();
		try {
			emitter.send(SseEmitter.event().name("balanceUpdate").data("INIT"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		emitter.onCompletion(()-> {
			emitters.remove(email);
		});
		emitters.put(email, emitter);
		return emitter;
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
		TransferApiResponse response = transferService.transfer(amount, sender , receiverId );
		String receiverEmail = response.getReceiverEmail().toLowerCase();
		
		emitters.forEach((k, emitter) ->{
			if (k.equals(receiverEmail)) {
				try {
					emitter.send(SseEmitter.event().name("BalanceUpdate").data(response.getReceiverBalance()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					emitters.remove(k,emitter);
					e.printStackTrace();
				}
			}
		});
		model.addAttribute("message", response.getStatus());
		if (response.getStatus() != "okay") {
			return "HandleError";
		}
		transactionService.saveTransaction(sender, transactionDto);
		return "transferPage";
	}
}
