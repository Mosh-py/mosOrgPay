package org.mosorgpay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import org.mosorgpay.dto.MoneyRequestDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.service.RequestMoneyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app/requestMoney")
public class RequestMoneyController {

	private final RequestMoneyService requestMoneyService;
	
//	private Map<String, SseEmitter> emitters = new HashMap<>();
	private List<SseEmitter> emitters = new CopyOnWriteArrayList<SseEmitter>();
	private final Logger logger = Logger.getLogger(RequestMoneyController.class.toString());
	public RequestMoneyController(RequestMoneyService requestMoneyService) {
		this.requestMoneyService = requestMoneyService;
	}
	
	@CrossOrigin
	
	@GetMapping("subscribe")
	public SseEmitter listenToSubscriber(HttpSession session) {
		
		Employee employee = (Employee) session.getAttribute("employee");
		String username = employee.getUsername().toLowerCase();
		SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
		try {
			emitter.send(SseEmitter.event().name("INIT"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()  ;
		}
		emitter.onCompletion(()-> emitters.remove(emitter));
//		emitters.put(username, emitter);
		emitters.add(emitter);
		return emitter;
		
	}
	@GetMapping
	public String requestMoneyController(HttpSession session) {

		if (session.getAttribute("employee") != null) {  
			return "requestMoney.html";
		}
		return "redirect:/dashboard";
	}

	@PostMapping
	public String sendMoneyRequest(@ModelAttribute MoneyRequestDto moneyRequestDto ,HttpSession session) {
		
		
		Employee employee = (Employee) session.getAttribute("employee");
//		
//		String targetUsername = moneyRequestDto.getLendeeId();
//	
//		emitters.forEach((k,v)->{
//			if (k.equals(targetUsername)) {
//				
//			}
//		})
		for (SseEmitter emitter: emitters) {
			try {
				emitter.send(SseEmitter.event().name("moneyRequest").data("Hi"));
				requestMoneyService.requestFunds(employee, moneyRequestDto);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "test";
	}

}
