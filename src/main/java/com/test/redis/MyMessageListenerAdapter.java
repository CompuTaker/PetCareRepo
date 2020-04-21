package com.test.redis;

import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

public class MyMessageListenerAdapter extends MessageListenerAdapter{ // wrapper class
	
	private RedisMessageSubscriber myRedisMessageSubscriber;
	
	public MyMessageListenerAdapter(Object delegate) {
		// TODO Auto-generated constructor stub
		super(delegate); // super(new RedisMessageSubscriber());
		this.myRedisMessageSubscriber = (RedisMessageSubscriber) delegate;
	}

	public RedisMessageSubscriber getMyRedisMessageSubscriber() {
		return this.myRedisMessageSubscriber;
	}
	
}
