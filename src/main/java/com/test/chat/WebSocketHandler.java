package com.test.chat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class WebSocketHandler extends TextWebSocketHandler {
	// @DependsOn(value = {"redisTemplate", "stringRedisTemplate"})
	// http://localhost:8272/hello/echo // Sub-Tomcat (for local test)
	// http://18.180.187.192:8080/hello/echo // ChattingServer
	
	// session list
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	// logger
	private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("{}로 부터 {}라는 내용을 받음", session.getId(), message.getPayload());
		System.out.println(session.getId() + "로 부터 " + message.getPayload() + "라는 내용을 받음");
		// Sending the Message to every user
        for(WebSocketSession eachSession : sessionList){
        	eachSession.sendMessage(new TextMessage(session.getId() + " => " + message.getPayload()));
        }
        
        // TextMessage echoMessage = new TextMessage("ECHO :" + message.getPayload());
		// session.sendMessage(echoMessage); // echoing test
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.sessionList.add(session);
		logger.info("{}가 연결됌", session.getId());
		System.out.println(session.getId() + "가 연결됌");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		this.sessionList.remove(session);
		logger.info("{}의 연결 끊김.", session.getId());
		System.out.println(session.getId() + "의 연결 끊김");
	}

}
