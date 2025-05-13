package org.mosorgpay.handler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Service
public class CustomWebSocketHandler extends TextWebSocketHandler {

	private List<WebSocketSession> activeUsers = new ArrayList<>();

	private final Logger logger = Logger.getLogger("from the handler");

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		activeUsers.add(session);
		logger.info(" " + session.getId() + " is  here and added and session size is " + activeUsers.size());

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		activeUsers.remove(session);
		logger.info(" " + session.getId() + " has been removed and session size is " + activeUsers.size());
	}

	public void sendBalanceUpdate(String id, BigDecimal amount) throws IOException {
		logger.info(" Oh, yeah, activated");
		logger.info(" The number of active users are " + activeUsers.size());
		for (WebSocketSession session : activeUsers) {

			logger.info("cool");
			session.sendMessage(new TextMessage(" hiiii"));
		}
	}

	public void handleSomeStuff(String id, BigDecimal amount) throws IOException {
		this.sendBalanceUpdate(id, amount);
	}
}
