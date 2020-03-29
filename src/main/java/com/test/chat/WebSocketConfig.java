package com.test.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {
	
	@Autowired
	private WebSocketHandler webSocketHandler;
	
}
