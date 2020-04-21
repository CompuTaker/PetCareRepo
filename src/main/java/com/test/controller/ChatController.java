package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	
	@RequestMapping("/chat")
	public String chat(Model model) {
		return "chat"; // chat.jsp
	} // This is not client so unnecessary
	
	@RequestMapping("/chatsession")
	public String chatSession(Model model) {
		return "chatsession"; // chatsession.jsp
	} // This is not client so unnecessary
	
//		this.chatRedisAndAPI();
//		// this.chatRoomDAO.listAllChatRoom();
//		// this.chatRoomDAO.insertTheChatRoom("testcus", "testrep");
//		return "chatsession"; // chatsession.jsp
	
//	public void chatRedisAndAPI() {
//		System.out.println("RedisTemplate @ToStringWSH => " + this.redisTemplate.toString());
//		
//		this.redisContainer.setChannel(); // test@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//		this.redisMessage();
//		
//		System.out.println("GOOOOOOOOOOOOODDDDDDDDD");
//	}
//	
//	public void redisMessage() {
//		String message = "Message!!";
//		this.redisMessagePublisher.publishToChannel(message);
//	}
	
//	@Autowired
//	private RedisTemplate<Object, Object> redisTemplate; // @Qualifier("redisTemplate") // javax
//	
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//	
//	@Autowired
//	private MyMessagePublisher redisMessagePublisher;
//	
//	@Autowired
//	private MyMessageListenerContainer redisContainer;
	
//	@Autowired
//	private ApiRestScheduler apiRestScheduler;
//	
//	@Resource(name="redisTemplate")
//	private ValueOperations<String, Object> valueOps;
//	
//	@Resource(name="redisTemplate")
//	private SetOperations<String, String> setOps;
//	
//	@Resource(name="redisTemplate")
//	private ListOperations<String, Object> listOps;
//	
//	@Resource(name="redisTemplate")
//	private ZSetOperations<String, String> zSetOps;
//	
//	@Resource(name="redisTemplate")
//	private HashOperations<String, String, Object> hashOps;
	
	// System.out.println("REDIS => " + this.valueOps.get("test"));
	// this.valueOps.set("settingtest", "well-done");
	// redis test
	
}
