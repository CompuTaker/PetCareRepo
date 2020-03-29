package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

// @CrossOrigin(origins="http://13.113.211.163:8080")
@Controller
public class ChatController {
	
	@RequestMapping("/chat")
	public String chat(Model model, HttpServletRequest request, HttpServletResponse response) {
		
//		// response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, OPTIONS, PUT, DELETE");
//		// response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		// might be including security problems -- DELETE
//		// => OPTIONS must be excluded in preflight response / request for CORS
//		// preflight -- customized cases
//		response.setHeader("Access-Control-Max-Age", "3600"); // No Caching for test
//		// response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
//		response.setHeader("Access-Control-Allow-Headers", "X-PINGARUNER");
//		response.setHeader("Content-Type", "text/html"); // ("Content-Type", "application/json")
//		// ("Content-Type", "text/html; charset=UTF-8")
//		// response.setHeader("Accept", "text/html"); // ("Accept", "application/json")
//		response.setHeader("Accept", "text/html");
//		// CORS setting
		
		return "chat"; // chat.jsp
	}
	
	@RequestMapping("/chatsession")
	public String chatSession(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "chatsession"; // chatsession.jsp
	}
	
}
