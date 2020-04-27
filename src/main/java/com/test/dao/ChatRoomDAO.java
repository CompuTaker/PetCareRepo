package com.test.dao;

import java.util.List;

import com.test.dto.ChatRoomDTO;

public interface ChatRoomDAO {
	
	abstract public List<ChatRoomDTO> listAllChatRoom();
	
	abstract int insertTheChatRoom(String customerSession, String repName);
	
	abstract public int prepareInTheNewChatRoom(int adminIdx);
	
	abstract public ChatRoomDTO getChatRoomByAdminIdx(int adminIdx);
	
	abstract public ChatRoomDTO findAvailableChatRoomWithPriority();
	
	abstract public int enterTheChatRoom(int chatRoomIdx, int customerIdx);
	
}
