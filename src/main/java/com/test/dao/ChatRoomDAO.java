package com.test.dao;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import com.test.dto.ChatRoomDTO;

public interface ChatRoomDAO {
	
	abstract public List<ChatRoomDTO> listAllChatRoom();
	
	abstract public int insertTheChatRoom(String customerSession, String repName);
	
	abstract public ChatRoomDTO pickOneChatRoomByAdminIdxWithPriority(int adminIdx);
	
	abstract public ChatRoomDTO pickOneChatRoomByCustomerIdxWithPriority(int customerIdx);
	
	abstract public int specifyAdminWebSocketSessionToTheChatRoom(int chatRoomIdx, String adminWebSocketSession);
	
	abstract public int specifyCustomerWebSocketSessionToTheChatRoom(int chatRoomIdx, String customerWebSocketSession);

	abstract public ChatRoomDTO findChatRoomByAdminWebSocketSession(String adminWebSocketSession);
	
	abstract public ChatRoomDTO findChatRoomByCustomerWebSocketSession(String customerWebSocketSession);
	
	abstract public int deleteChatRoomByAdminWebSocketSession(String adminWebSocketSession);
	
	abstract public int deleteChatRoomByCustomerWebSocketSession(String customerWebSocketSession);
	
}
