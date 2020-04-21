package com.test.redis;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements MyMessagePublisher {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
    @Autowired
    private ChannelTopic defaultTopic;
	
	public RedisMessagePublisher(StringRedisTemplate stringRedisTemplate, ChannelTopic topic) {
		// TODO Auto-generated constructor stub
		// this.stringRedisTemplate = stringRedisTemplate;
		// this.defaultTopic = topic; // 기본 채널
	}
	
	@Override
	public void publish(String message) {
		// TODO Auto-generated method stub
		this.stringRedisTemplate.convertAndSend(this.defaultTopic.getTopic(), message); // 기본 채널
	}

	@Override
	public void publishToChannel(String channelName, String message) {
		// TODO Auto-generated method stub
		// this.stringRedisTemplate.convertAndSend(this.defaultTopic.getTopic(), message);
		this.stringRedisTemplate.convertAndSend(channelName, message);
	}
	
}
