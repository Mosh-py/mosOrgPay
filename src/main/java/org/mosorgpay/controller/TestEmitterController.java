package org.mosorgpay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class TestEmitterController {
	
	private List<SseEmitter> messengers = new CopyOnWriteArrayList<SseEmitter>();
	
	private Logger logger = Logger.getLogger(" Emitter");
	
	@CrossOrigin
	@GetMapping("/moneyRequest")
	public SseEmitter subscribe() throws IOException {
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		logger.info(" just recorded an event");
		sseEmitter.send(SseEmitter.event().name("INIT"));
		
		sseEmitter.onCompletion(()-> messengers.remove(sseEmitter));
		messengers.add(sseEmitter);
		return sseEmitter;
	}
	
//	@PostMapping("/requestMoney")
//	public void dispatchEvent(@RequestParam String amount) {
//		logger.warning(" The number of emmiter is " + messengers.size());
//		List<SseEmitter> deadEmitters = new ArrayList<>();
//		for (SseEmitter messenger: messengers) {
//			
//			try {
//				messenger.send(SseEmitter.event().name("latestNe ws").data(freshNews));
//				logger.info(" Moshoood , it is doing well ");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				deadEmitters.add(messenger);
//				e.printStackTrace();
//			}
//		}
//		messengers.removeAll(deadEmitters);
//	}
}
