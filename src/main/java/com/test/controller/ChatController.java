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
	
}
