package com.test.redis;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisMessageSubscriber implements MessageListener {
	
	private ObjectMapper objectMapper;
	
	private Queue<String> messageRedisQueue = new LinkedList<String>();
	public Queue<String> getMessageRedisQueue() {
		return this.messageRedisQueue;
	}
	
	private String channelName;
	public String getChannelName() {
		return channelName;
	}

	// constructor
	public RedisMessageSubscriber(int chatRoomIdx) {
		// TODO Auto-generated constructor stub
		super();
		this.channelName = chatRoomIdx + "";
	}
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		this.messageRedisQueue.offer(message.toString());
		System.out.println("Message received: " + message.toString());
	}
	
	
	
//    try {
//        // redis에서 발행된 데이터를 받아 deserialize
//        String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
//        // ChatMessage 객채로 맵핑
//        ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
//        // Websocket 구독자에게 채팅 메시지 Send
//        messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);
//    } catch (Exception e) {
//        log.error(e.getMessage());

}
