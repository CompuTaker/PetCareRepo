package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.dao.ChatRoomDAO;
import com.test.dto.ChatRoomDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.SuperuserDTO;

@Controller
@SessionAttributes({ "customer", "company", "superuser" })
public class ChatController {
	
	@Autowired
	private ChatRoomDAO chatRoomDAO;
	
	@RequestMapping("/chat")
	public String chat(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "chat/chat.tiles"; // chat.jsp
	}
	
	@RequestMapping("/chatsession")
	public String chatSession(Model model, HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		SuperuserDTO superuser = (SuperuserDTO) session.getAttribute("superuser");
		String url = "";
		if(customer != null) {
			
			ChatRoomDTO chatRoom = this.chatRoomDAO.findAvailableChatRoomWithPriority();
			this.chatRoomDAO.enterTheChatRoom(chatRoom.getChatRoomIdx(), customer.getCustomer_Index());
			model.addAttribute("isAdmin", "false");
			model.addAttribute("idx", customer.getCustomer_Index()); // toString()
			url = "chat/chatsession.tiles";
			System.out.println("CUSTOMER_TIME");
			// ChatRoomDTO chatRoom = this.chatRoomDAO.getChatRoomByCustomerIdx(customer.getCustomer_Index());
		}else if(superuser != null) {
			
			this.chatRoomDAO.prepareInTheNewChatRoom(superuser.getIndex()); // admin.getIdx
			model.addAttribute("isAdmin", "true");
			model.addAttribute("idx", superuser.getIndex()); // toString()
			url = "chat/chatsession.tiles";
			System.out.println("ADMIN_TIME");
			// ChatRoomDTO chatRoom = this.chatRoomDAO.getChatRoomByAdminIdx(superuser.getIndex());
		}else {
			url = "redirect:index.tiles";
		}
		return url; // chatsession.jsp
	}
	
	
	
}
