package com.test.redis;

public interface MyMessagePublisher {
	
	abstract public void publish(String message);
	
	abstract public void publishToChannel(String channelName, String message);
	
}
