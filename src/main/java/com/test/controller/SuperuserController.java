package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.service.SuperuserService;

@Controller		//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
public class SuperuserController {
	
	@Autowired
	private SuperuserService superuserService;
	
	/*
	 * 상단의 관리자 버튼을 누르면 실행되는 메서드이다.
	 * RequestMethod.GET
	 */
	@RequestMapping("/admin_login")
	public String login(HttpServletRequest request) {
		if(request.getSession().getAttribute("superuser")!= null) {	// 관리자가 로그인해서 session을 가지면 탈퇴회원관리 화면으로 들어가진다.
			return "admin_drop";
		}
		return "admin/admin_login.tiles";										// 관리자가 session이 없을 경우 로그인 화면을 띄워준다.
	}
	
	/*
	 * 상단의 관리자 버튼을 눌러서 ID, PW를 누르고 로그인 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping(value="/admin_login", method = RequestMethod.POST)
	public String getAdmin(HttpServletRequest request) {
		return this.superuserService.getAdmin(request);
	}
	
	/*
	 * 탈퇴회원관리를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/admin_drop")
	public String drop() {
		return "admin/admin_drop.tiles";
	}
	
	/*
	 * 휴면회원관리를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/admin_dormant")
	public String dormant() {
		return "admin/admin_dormant.tiles";
	}	
}