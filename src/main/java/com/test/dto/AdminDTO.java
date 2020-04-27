package com.test.dto;

public class AdminDTO {
//	관리자 페이지 만들기 위해서 관리자 유저 테이블을 추가했어요 superuser 가 테이블 이름이에요. 
//	그리고 필드로는 index(pk값), username(유저 아이디), password(비번) 이렇게 추가 했습니다.
	
	private int index;
	private String username;
	private String password;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
