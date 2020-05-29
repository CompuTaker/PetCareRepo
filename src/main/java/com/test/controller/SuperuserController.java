package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.SuperuserDTO;
import com.test.service.CompanyService;
import com.test.service.CustomerService;
import com.test.service.SuperuserService;

@Controller // Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company", "superuser" })
public class SuperuserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SuperuserService superuserService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	/*
	 * 상단의 관리자 버튼을 누르면 실행되는 메서드이다. RequestMethod.GET
	 */
	@RequestMapping("/admin_login")
	public String login(HttpServletRequest request, HttpSession session) {
		// SuperuserDTO superuser = (SuperuserDTO) session.getAttribute("superuser");
		// 둘 다 세션검사가 되긴 하네
		if (request.getSession().getAttribute("superuser") != null) { // 관리자가 로그인해서 session을 가지면 탈퇴회원관리 화면으로 들어가진다.
			logger.info("/admin_login " + request.getMethod());
			return "admin/admin_drop.tiles";
		}
		return "admin/admin_login.tiles"; // 관리자가 session이 없을 경우 로그인 화면을 띄워준다.
	}

	/*
	 * 상단의 관리자 버튼을 눌러서 ID, PW를 누르고 로그인 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping(value = "/admin_login", method = RequestMethod.POST)
	public String getAdmin(HttpServletRequest request) {
		logger.info("/admin_login " + request.getMethod());
		return this.superuserService.getAdmin(request);
	}

	/*
	 * 관리자 로그아웃
	 */
	@RequestMapping("/admin_logout")
	public String logout(HttpServletRequest request, HttpSession session, SessionStatus status) {
		SuperuserDTO superuser = (SuperuserDTO) session.getAttribute("superuser");
		if (superuser != null) { // 관리자가 로그인해서 session을 가지면 탈퇴회원관리 화면으로 들어가진다.
			status.setComplete(); // sessionAttribute를 초기화해준다.
			logger.info("/admin_logout " + request.getMethod());
			return "admin/admin_login.tiles";
		}
		return "admin/admin_drop.tiles"; // 관리자가 session이 없을 경우 로그인 화면을 띄워준다.
	}

	/*
	 * 탈퇴회원관리를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/admin_drop")
	public String drop(HttpServletRequest request, Model model) {
		logger.info("/admin_drop " + request.getMethod());
		
		List<CustomerDTO> customers = this.customerService.getDropCustomers();
		List<CompanyDTO> companys = this.companyService.getDropCompanys();
		
		model.addAttribute("dropCustomer", customers);		
		model.addAttribute("dropCompany", companys);
		return "admin/admin_drop.tiles";
	}
	

	/*
	 * 휴면회원관리를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/admin_dormant")
	public String dormant(HttpServletRequest request) {
		logger.info("/admin_dormant " + request.getMethod());
		return "admin/admin_dormant.tiles";
	}
}