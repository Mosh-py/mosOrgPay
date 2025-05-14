package org.mosorgpay.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class TestEmitterController {
	
	private List<SseEmitter> messengers = new CopyOnWriteArrayList<SseEmitter>();
	@CrossOrigin
	
	
	
	
	@GetMapping("/subscribe")
	public SseEmitter subscribe() throws IOException {
		SseEmitter sseEmitter = new SseEmitter();
		sseEmitter.send(SseEmitter.event().name("INIT"));
		messengers.add(sseEmitter);
		return sseEmitter;
	}
	
	@PostMapping("/dispatchValue")
	public void dispatchEvent(@RequestParam String freshNews) throws IOException {
		for (SseEmitter messenger: messengers) {
			messenger.send(SseEmitter.event().name("latestNews").data("freshNews"));
		}
	}
}
