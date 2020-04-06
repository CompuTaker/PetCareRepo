package com.test.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String sessionId;
	private String key;
	private String value;
	private String message;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
