package com.test.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

import com.test.dto.ChatRoomDTO;

@Repository
public class ChatRoomDAOimpl implements ChatRoomDAO {
	
	@Autowired // root-context.xml
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ChatRoomDTO> listAllChatRoom() {
		return this.sqlSession.selectList("listAllChatRoom");	
	}
	
	@Override
	public int insertTheChatRoom(String customerSession, String repName) {
		// TODO Auto-generated method stub
		System.out.println("!!!!" + customerSession);
		System.out.println("!!!!" + repName);
		HashMap <String, String> crmap = new HashMap<String, String>();
		crmap.put("customerName", customerSession);
		crmap.put("repName", repName);
		return this.sqlSession.insert("insertTheChatRoom", crmap);
	}
	
	@Override
	public ChatRoomDTO pickOneChatRoomByAdminIdxWithPriority(int adminIdx) {
		// TODO Auto-generated method stub
		System.out.println("~~~" + adminIdx);
		List<ChatRoomDTO> chatRooms = this.sqlSession.selectList("pickOneChatRoomByAdminIdxWithPriority", adminIdx);
		ChatRoomDTO mostUrgentChatRoom = chatRooms.get(0);
		int min = mostUrgentChatRoom.getPriority();
		for(ChatRoomDTO chatRoom : chatRooms) {
			int priority = chatRoom.getPriority();
			if(min > priority) {
				min = priority;
				mostUrgentChatRoom = chatRoom;
			}
		}
		return mostUrgentChatRoom;
	}
	
	@Override
	public ChatRoomDTO pickOneChatRoomByCustomerIdxWithPriority(int customerIdx) {
		// TODO Auto-generated method stub
		System.out.println("~~~" + customerIdx);
		List<ChatRoomDTO> chatRooms = this.sqlSession.selectList("pickOneChatRoomByCustomerIdxWithPriority", customerIdx);
		ChatRoomDTO mostUrgentChatRoom = chatRooms.get(0);
		int min = mostUrgentChatRoom.getPriority();
		for(ChatRoomDTO chatRoom : chatRooms) {
			int priority = chatRoom.getPriority();
			if(min > priority) {
				min = priority;
				mostUrgentChatRoom = chatRoom;
			}
		}
		return mostUrgentChatRoom;
	}
	
	@Override
	public int specifyAdminWebSocketSessionToTheChatRoom(int chatRoomIdx, String adminWebSocketSession) {
		// TODO Auto-generated method stub
		HashMap <String, Object> crmap = new HashMap<String, Object>();
		crmap.put("chatRoomIdx", chatRoomIdx);
		crmap.put("adminWebSocketSession", adminWebSocketSession);
		return this.sqlSession.update("specifyAdminWebSocketSessionToTheChatRoom", crmap);
	}
	
	@Override
	public int specifyCustomerWebSocketSessionToTheChatRoom(int chatRoomIdx, String customerWebSocketSession) {
		// TODO Auto-generated method stub
		HashMap <String, Object> crmap = new HashMap<String, Object>();
		crmap.put("chatRoomIdx", chatRoomIdx);
		crmap.put("customerWebSocketSession", customerWebSocketSession);
		return this.sqlSession.update("specifyCustomerWebSocketSessionToTheChatRoom", crmap);
	}

	@Override
	public ChatRoomDTO findChatRoomByAdminWebSocketSession(String adminWebSocketSession) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("findChatRoomByAdminWebSocketSession", adminWebSocketSession);
	}
	
	@Override
	public ChatRoomDTO findChatRoomByCustomerWebSocketSession(String customerWebSocketSession) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("findChatRoomByCustomerWebSocketSession", customerWebSocketSession);
	}
	
	@Override
	public int deleteChatRoomByAdminWebSocketSession(String adminWebSocketSession) {
		// TODO Auto-generated method stub
		return this.sqlSession.delete("deleteChatRoomByAdminWebSocketSession", adminWebSocketSession);
	}
	
	@Override
	public int deleteChatRoomByCustomerWebSocketSession(String customerWebSocketSession) {
		// TODO Auto-generated method stub
		return this.sqlSession.delete("deleteChatRoomByCustomerWebSocketSession", customerWebSocketSession);
	}
	
}
