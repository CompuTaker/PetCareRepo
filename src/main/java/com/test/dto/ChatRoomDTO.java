package com.test.dto;

public class ChatRoomDTO {
	
	// chatRoomIdx
	private int chatRoomIdx;
	
	// idx
	private int customerIdx;
	private int adminIdx;
	
	private int priority; // might be used
	private int stateFlag; // might be used
	
	// websocketsession
	private String customerWebSocketSession;
	private String adminWebSocketSession;
	
	/**
create table ChatRoom(
	chatRoomIdx INT(11) unsigned NOT NULL AUTO_INCREMENT,
	customerIdx INT(11),
	adminIdx INT(11),
	priority INT(11) default 1,
	stateFlag INT(11),
	customerWebSocketSession varchar(13),
	adminWebSocketSession varchar(13),
	primary key(chatRoomIdx)
);
	 **/
	
	// getters and setters
	public int getChatRoomIdx() {
		return chatRoomIdx;
	}
	public void setChatRoomIdx(int chatRoomIdx) {
		this.chatRoomIdx = chatRoomIdx;
	}
	public int getCustomerIdx() {
		return customerIdx;
	}
	public void setCustomerIdx(int customerIdx) {
		this.customerIdx = customerIdx;
	}
	public int getAdminIdx() {
		return adminIdx;
	}
	public void setAdminIdx(int adminIdx) {
		this.adminIdx = adminIdx;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getStateFlag() {
		return stateFlag;
	}
	public void setStateFlag(int stateFlag) {
		this.stateFlag = stateFlag;
	}
	public String getCustomerWebSocketSession() {
		return customerWebSocketSession;
	}
	public void setCustomerWebSocketSession(String customerWebSocketSession) {
		this.customerWebSocketSession = customerWebSocketSession;
	}
	public String getAdminWebSocketSession() {
		return adminWebSocketSession;
	}
	public void setAdminWebSocketSession(String adminWebSocketSession) {
		this.adminWebSocketSession = adminWebSocketSession;
	}
	
}
