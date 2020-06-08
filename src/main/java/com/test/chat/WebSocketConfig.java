package com.test.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.test.dao.ChatRoomDAO;
import com.test.redis.MyMessageListenerContainer;
import com.test.redis.MyMessagePublisher;

@Configuration
@EnableWebSocket
@CrossOrigin
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private ChatRoomDAO chatRoomDAO;
	
	@Autowired
	private MyMessageListenerContainer redisContainer;
	
	@Autowired
	private MyMessagePublisher redisPublisher;
	
//	@Resource(name="redisTemplate")
//	private ValueOperations<String, Object> valueOps;
//	
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//	
//	@Autowired
//	private RedisTemplate<Object, Object> redisTemplate; // @Qualifier("redisTemplate") // javax
	
	@Bean(name = "myHandler")
	WebSocketHandler webSocketHandler() {
		WebSocketHandler webSocketHandler = new WebSocketHandler(this.chatRoomDAO, this.redisContainer, this.redisPublisher);
		return webSocketHandler;
	}
	
	@Bean(name = "handShakeInterceptor")
	HandShakeInterceptor handShakeInterceptor() {
		HandShakeInterceptor handShakeInterceptor = new HandShakeInterceptor();
		return handShakeInterceptor;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(webSocketHandler(), "/echo").addInterceptors(handShakeInterceptor()).setAllowedOrigins("*").withSockJS();
		// CORS is now on global setting -- servlet-context.xml
	}

}
