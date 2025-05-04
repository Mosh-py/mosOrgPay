package org.mosorgpay.handler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class CustomWebSocketHandler extends TextWebSocketHandler {
	
	private Set<WebSocketSession> activeUsers = new HashSet<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		activeUsers.add(session);
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception{
		
	}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception{
		activeUsers.remove(session);
	} 
	
	public void sendBalanceUpdate(String id, BigDecimal amount) throws IOException {
		for (WebSocketSession session: activeUsers) {
			session.sendMessage(new TextMessage(" hiiii"));
		}
	}
	
}
