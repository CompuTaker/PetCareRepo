package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.test.scheduler.ApiRestScheduler;

@RestController
public class ApiController {
	
	@Autowired
	private ApiRestScheduler apiRestScheduler;
	
	// @Autowired
	// private RedisTemplate<Object, Object> redisTemplate;
	// // @Qualifier("redisTemplate") // javax
	
	// @Resource(name="redisTemplate")
	// private ValueOperations<String, Object> valueOps;
	
	// @Resource(name="redisTemplate")
	// private SetOperations<String, String> setOps;
	
	// @Resource(name="redisTemplate")
	// private ListOperations<String, Object> listOps;
	
	// @Resource(name="redisTemplate")
	// private ZSetOperations<String, String> zSetOps;
	
	// @Resource(name="redisTemplate")
	// private HashOperations<String, String, Object> hashOps;
	
	// @RequestMapping("/chat")
	// public String chat(Model model, HttpServletRequest request, HttpServletResponse response) {
	// 	return "chat"; // chat.jsp
	// }
	
//	@RequestMapping("/chatsession")
//	public String chatSession(Model model, HttpServletRequest request, HttpServletResponse response) {
//		this.chatRedisAndAPI();
//		return "chatsession"; // chatsession.jsp
//	}
//	
//	public void chatRedisAndAPI() {
//		// URLEncoder.encode("Q12A07", "UTF-8"); // 애견카페
//		try {
//			this.apiRestScheduler.batchProcess("Q12A07");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Public_API_Error -- batchProcess(Q12A07)");
//			e.printStackTrace();
//		}
//		
//		// System.out.println("RedisTemplate @ToStringWSH => " + this.redisTemplate.toString());
//		// System.out.println("REDIS => " + this.valueOps.get("test"));
//		// this.valueOps.set("settingtest", "well-done");
//	}
	
}