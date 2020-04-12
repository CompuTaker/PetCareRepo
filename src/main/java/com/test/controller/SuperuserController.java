package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.dao.SuperuserDAO;
import com.test.dto.SuperuserDTO;

@Controller		//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
public class SuperuserController {
	
	@Autowired
	private SuperuserDAO superuserDao;
	
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
		SuperuserDTO superuser = superuserDao.loginSuperuser(				// superuser테이블에 해당 ID, PW가 있는지 확인해본다.
										request.getParameter("username"), 	// admin.login.jsp에 name값이 username인 값을 가져온다.
										request.getParameter("password")	// admin.login.jsp에 name값이 password인 값을 가져온다.
								  );
		if(superuser != null) {												// superuser가 존재하는 경우
			HttpSession session = request.getSession();						// session을 가져온다.
			 session.setAttribute("superuser", superuser);					// session의 속성에 superuser를 붙여준다.
			return "admin/admin_drop.tiles";											// 탈퇴회원관리 화면을 띄워준다.
		}
		return "admin_login";												// superuser가 없는 경우 로그인 화면을 띄워준다.
	}
	
	/*
	 * 탈퇴회원관리를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/admin_drop")
	public String drop() {
		return "adimin/admin_drop.tiles";
	}
	
	/*
	 * 휴면회원관리를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/admin_dormant")
	public String dormant() {
		return "admin/admin_dormant.tiles";
	}	
}