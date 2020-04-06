//package com.test.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.test.chat.Car;
//
//@Controller
//public class TestController {
//	
//	@Autowired
//	private Car car;
//	
//	@Autowired
//	private RedisTemplate<Object, Object> redisTemplate;
//	
//	@RequestMapping("/test")
//	public String test(Model model) {
//		System.out.println("TestConfigCar => " + car.getCarName());
//		System.out.println("ControllerTestRedisTempalte => " + redisTemplate.toString());
//		return "chat"; // chat.jsp
//	} // This is not client so unnecessary
//	
//}
