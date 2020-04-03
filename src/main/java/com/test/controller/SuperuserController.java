package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.constants.Constant;
import com.test.constants.Constant.ESession;
import com.test.dao.SuperuserDAO;
import com.test.dto.SuperuserDTO;

@Controller
public class SuperuserController {
	
	@Autowired
	private SuperuserDAO superuserDao;
	
	//관리자 로그인 페이지
	@RequestMapping("/admin_login")
	public String login(HttpServletRequest request) {
		System.out.println("관리자 로그인 GET");
		if(request.getSession().getAttribute("superuser")!= null) {
			return "admin_drop";
		}
		return "admin_login";
	}
	
	//관리자 탈퇴 페이지
	@RequestMapping("/admin_drop")
	public String drop() {
		System.out.println("관리자 탈퇴회원 GET");
		return "admin_drop";
	}
	
	//관리자 휴면 페이지
	@RequestMapping("/admin_dormant")
	public String dormant() {
		System.out.println("관리자 휴면회원 GET");
		return "admin_dormant";
	}

	//관리자 로그인
	@RequestMapping(value="/admin_login", method = RequestMethod.POST)
	public String getAdmin(HttpServletRequest request) {
		System.out.println("관리자 로그인 POST");
		SuperuserDTO superuser = superuserDao.loginSuperuser(request.getParameter("username"), request.getParameter("password"));
		if(superuser != null) {
			System.out.println(superuser.getUsername()+"- 로그인");
			HttpSession session = request.getSession();
			 session.setAttribute("superuser", superuser);
			return "admin_drop";
		}
		return "admin_login";
	}
	
}