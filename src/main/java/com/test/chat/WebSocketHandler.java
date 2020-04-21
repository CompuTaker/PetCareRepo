package com.test.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.test.constants.Constants;
import com.test.dao.ChatRoomDAO;
import com.test.dto.ChatRoomDTO;
import com.test.redis.MyMessageListenerContainer;
import com.test.redis.MyMessagePublisher;

public class WebSocketHandler extends TextWebSocketHandler {
	// @DependsOn(value = {"redisTemplate", "stringRedisTemplate"})
	// http://localhost:8272/hello/echo // Sub-Tomcat (for local test)
	// http://18.180.187.192:8080/hello/echo // ChattingServer
	
	// Manually DI the Bean
	private MyMessageListenerContainer redisContainer;
	
	// Manually DI the Bean
	private MyMessagePublisher redisPulisher;
	
	// Manually DI the Bean
	private ChatRoomDAO chatRoomDAO;
	
	// Manually DI the Bean
	// private ValueOperations<String, Object> valueOps;
	
	// logger
	private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	
	// SessionMap
	private Map<String,WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
	// ++++++++++ REDIS
	
	// constructor
	public WebSocketHandler(ChatRoomDAO chatRoomDAO,
			MyMessageListenerContainer redisContainer,
			MyMessagePublisher redisPulisher) {
		// TODO Auto-generated constructor stub
		this.chatRoomDAO = chatRoomDAO;
		this.redisContainer = redisContainer;
		this.redisPulisher = redisPulisher;
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 여기서 session => 발신자 측의 WebSocketSession
		logger.info("{}로 부터 {}라는 내용을 받음", session.getId(), message.getPayload());
		System.out.println(session.getId() + "로 부터 " + message.getPayload() + "라는 내용을 받음");
		
		Map<String, Object> map = session.getAttributes();
		int idx = Integer.parseInt(map.get(Constants.IDX) + "");
		boolean isAdmin = Boolean.parseBoolean(map.get(Constants.IS_ADMIN) + "");
		System.out.println("여기서도 나오려나 idx => " + idx);
		System.out.println("여기서도 나오려나 isAdmin => " + isAdmin);
		
		// session list
		List<WebSocketSession> tempSessionList = new ArrayList<WebSocketSession>();
		tempSessionList.add(session); // 현재 발신자의 session을 추가
		ChatRoomDTO chatRoom = null;
		String opponentSessionId = null;
		if(isAdmin){
			chatRoom = this.chatRoomDAO.findChatRoomByAdminWebSocketSession(session.getId());
			opponentSessionId = chatRoom.getCustomerWebSocketSession();
		}else {
			chatRoom = this.chatRoomDAO.findChatRoomByCustomerWebSocketSession(session.getId());
			opponentSessionId = chatRoom.getAdminWebSocketSession();
		} // 한 사용자 당 ChatRoom이 여러개 일 수 있기 때문에, WebSocketSession으로 정확한 방 하나를 특정해오는 것
		
		if(opponentSessionId != null) {
			tempSessionList.add(this.sessionMap.get(opponentSessionId));
		} // 상대방 지정
		
		// Sending the Message to every user
		for (WebSocketSession eachSession : tempSessionList) {
			System.out.println("!!!onlyInThisChatRoom => " + eachSession.getId());
			eachSession.sendMessage(new TextMessage(session.getId() + " => " + message.getPayload()));
		}
		
		// RRRRRRRREEEEEEEEEEDDDDDDDDIIIIIIISSSSSSSSSSS
		this.redisPulisher.publishToChannel(chatRoom.getChatRoomIdx() + "", message.getPayload());
		Queue<String> theQ = this.redisContainer.scrapeTheMessageReceivedToThisChannel(chatRoom.getChatRoomIdx());
		int theQ_size = theQ.size();
		for(int i=0; i < theQ_size; i++) {
			System.out.println("qqq ::=> " + theQ.poll());
		}
		
		// TextMessage echoMessage = new TextMessage("ECHO :" + message.getPayload());
		// session.sendMessage(echoMessage); // echoing test
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
//		Map<String, Object> httpSession = session.getAttributes();
//		CustomerDTO loggedCustomer = (CustomerDTO) httpSession.get("customer");
//		System.out.println(loggedCustomer.getCustomer_Id());
		// different server so different session // not available, no need
		
		// this.sessionList.add(session);
		logger.info("{}가 연결됌", session.getId());
		System.out.println(session.getId() + "가 연결됌");
		
		Map<String, Object> map = session.getAttributes();
		
		int idx = Integer.parseInt(map.get(Constants.IDX) + "");
		boolean isAdmin = Boolean.parseBoolean(map.get(Constants.IS_ADMIN) + "");
		System.out.println("////" + idx);
		System.out.println("////" + isAdmin);
		System.out.println("^^^^" + this.chatRoomDAO.toString());
		
		int topicNum = -1;
		ChatRoomDTO chatRoom = null;
		
		// 별도 스레드로 구동되는데, IO 인터럽트는 또 별도 큐에서 빠져서 실행될 예정이 되버리는 것
		// IO 인터럽트가 걸렸을 때, 즉, 저 함수가 실행 들어왔는데,
		// getChatRoomIdx()는 별도 쓰레드로 따로 실행되니까, 이에 대한 종료 시점을 정확히 보장 불가
		// 그래서 가끔 여기서 출력해볼 시, NullPointerException이 발생!
		// System.out.println("AdminOut_RemoveChatRoom_chatRoomIdx => " + chatRoom.getChatRoomIdx() + "///" + session.getId());
		
		if(isAdmin) {
			chatRoom = this.chatRoomDAO.pickOneChatRoomByAdminIdx(idx);
			// System.out.println("chatRoomIdx => " + chatRoom.getChatRoomIdx() + "///" + session.getId());
			this.chatRoomDAO.specifyAdminWebSocketSessionToTheChatRoom(chatRoom.getChatRoomIdx(), session.getId());
			// admin
		}else {
			chatRoom = this.chatRoomDAO.pickOneChatRoomByCustomerIdx(idx);
			// System.out.println("chatRoomIdx => " + chatRoom.getChatRoomIdx() + "///" + session.getId());
			this.chatRoomDAO.specifyCustomerWebSocketSessionToTheChatRoom(chatRoom.getChatRoomIdx(), session.getId());
			// customer
		}
		
		// WebSocketSession의 id에 해당하는 문자열로, 바로 세션을 못 가져오니까 여기 저장해야해!
		// => 나중에 Redis로 또 여러 WebSocketServer로부터, sessionMap들을 공유해줘야겠지!
		this.sessionMap.put(session.getId(), session);
		// ++++++++++ REDIS
		
		topicNum = chatRoom.getChatRoomIdx();
		this.redisContainer.tuneChannel(topicNum);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 여기서 session은, 남아있어서 연결 끊긴 사람의 WebSocketSession
		// 나간 사람은 아예 연결이 끊겼다고 서비스를 받지도 않네!
		// 멀쩡하다가 다른 이상으로 연결이 박살나면, 둘 모두가 별도 스레드로 여길 실행, 그럼 각각 별도 session으로 여기 따로 들어오겠지
		// this.sessionList.remove(session);
		logger.info("{}의 연결 끊김.", session.getId());
		System.out.println(session.getId() + "의 연결 끊김");
		
		Map<String, Object> map = session.getAttributes();
		
		int idx = Integer.parseInt(map.get(Constants.IDX) + "");
		boolean isAdmin = Boolean.parseBoolean(map.get(Constants.IS_ADMIN) + "");
		System.out.println("////" + idx);
		System.out.println("////" + isAdmin);
		System.out.println("^^^^" + this.chatRoomDAO.toString());
		
		int topicNum = -1;
		ChatRoomDTO chatRoom = null;
		
		// this.chatRoomDAO.removechatroom();
		String opponentSessionId = null;
		if(isAdmin) {
			chatRoom = this.chatRoomDAO.pickOneChatRoomByAdminIdx(idx);
			opponentSessionId = chatRoom.getCustomerWebSocketSession();
			this.chatRoomDAO.deleteChatRoomByAdminWebSocketSession(session.getId()); // 채팅방 삭제
			// admin
		}else {
			chatRoom = this.chatRoomDAO.pickOneChatRoomByCustomerIdx(idx);
			// System.out.println("CustomerOut_RemoveChatRoom_chatRoomIdx => " + chatRoom.getChatRoomIdx() + "///" + session.getId());
			opponentSessionId = chatRoom.getAdminWebSocketSession();
			this.chatRoomDAO.deleteChatRoomByCustomerWebSocketSession(session.getId()); // 채팅방 삭제
			// customer
		}
		
		
		if(opponentSessionId != null) {
			this.sessionMap.remove(opponentSessionId);
			// ++++++++++ REDIS
		} // 상대방 색출해서 세션맵에서 삭제
		this.sessionMap.remove(session.getId()); // 본인 세션맵에서 삭제
		// ++++++++++ REDIS
	}
	
}
