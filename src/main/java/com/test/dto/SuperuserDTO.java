package com.test.dto;

public class SuperuserDTO {
//	관리자 페이지 만들기 위해서 관리자 유저 테이블을 추가했어요 superuser 가 테이블 이름이에요. 
//	그리고 필드로는 index(pk값), username(유저 아이디), password(비번) 이렇게 추가 했습니다.
	
	private int superuser_Index;
	private String superuser_Id;
	private String superuser_Password;
	
	public int getSuperuser_Index() {
		return superuser_Index;
	}
	public void setSuperuser_Index(int superuser_Index) {
		this.superuser_Index = superuser_Index;
	}
	public String getSuperuser_Id() {
		return superuser_Id;
	}
	public void setSuperuser_Id(String superuser_Id) {
		this.superuser_Id = superuser_Id;
	}
	public String getSuperuser_Password() {
		return superuser_Password;
	}
	public void setSuperuser_Password(String superuser_Password) {
		this.superuser_Password = superuser_Password;
	}
}
