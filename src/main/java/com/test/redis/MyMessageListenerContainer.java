package com.test.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

public class MyMessageListenerContainer extends RedisMessageListenerContainer {
	// public class MyMessageListenerContainer extends RedisMessageListenerContainer

	// @Autowired
	// private MyMessageListenerAdapter myMessageListener;
	// 안테나처럼 여러 웹소켓 서버에서부터 보내진 메시지들을,
	// Redis라는 임시 저장소를 거쳐, 여기서 모조리 모아주는 것!
	// => 여긴 Listener들을 총괄하는 ListenerContainer
	
	Map<Integer, MyMessageListenerAdapter> listenerMap = new HashMap<Integer, MyMessageListenerAdapter>();
	
	public void tuneChannel(int chatRoomIdx) {
		// TODO Auto-generated method stub
		
		// this.removeMessageListener(this.defaultMessageListener, this.defaultTopic);
		// Removes the given message listener completely (only for the default topic).
		
		MyMessageListenerAdapter newListener = new MyMessageListenerAdapter(new RedisMessageSubscriber(chatRoomIdx));
		this.listenerMap.put(chatRoomIdx, newListener);
		
		this.addMessageListener(newListener, new ChannelTopic(chatRoomIdx + ""));
		// ====================>>>> new RedisMessageSubscriber
		// =========>> should use new keyword
		// --------------- in order to register subscription made by multiple clients
		// (clients here include both customer and manager/superuser/admin)
		// only change the topic of this redis message listener
	}

	public Queue<String> scrapeTheMessageReceivedToThisChannel(int chatRoomIdx) {
		// TODO Auto-generated method stub
		return (this.listenerMap.get(chatRoomIdx)).getMyRedisMessageSubscriber().getMessageRedisQueue();
		// giving the pointer of the message queue originated from the specific channel
	}
	
}
