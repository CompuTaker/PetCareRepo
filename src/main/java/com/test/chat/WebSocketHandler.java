package com.test.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
 
@Controller
public class WebSocketHandler extends TextWebSocketHandler {
	// http://localhost:8172/hello/echo
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TextMessage echoMessage = new TextMessage("ECHO :" + message.getPayload());
		session.sendMessage(echoMessage);
	}
	
}
