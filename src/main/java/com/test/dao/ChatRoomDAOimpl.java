package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public int prepareInTheNewChatRoom(int adminIdx) {
		// TODO Auto-generated method stub
		return this.sqlSession.insert("prepareInTheNewChatRoom", adminIdx);
	}
	
	@Override
	public ChatRoomDTO getChatRoomByAdminIdx(int adminIdx) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("getChatRoomByAdminIdx", adminIdx);
	}
	
	@Override
	public ChatRoomDTO findAvailableChatRoomWithPriority() {
		// TODO Auto-generated method stub
		int priority = 1; // for example
		return this.sqlSession.selectOne("findAvailableChatRoomWithPriority", priority);
	}
	
	@Override
	public int enterTheChatRoom(int chatRoomIdx, int customerIdx) {
		// TODO Auto-generated method stub
		Map<String, Integer> crmap = new HashMap<String, Integer>();
		crmap.put("chatRoomIdx", chatRoomIdx);
		crmap.put("customerIdx", customerIdx);
		return this.sqlSession.update("enterTheChatRoom", crmap);
	}
	
}
