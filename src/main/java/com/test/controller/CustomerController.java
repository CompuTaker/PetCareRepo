package com.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.CustomerService;

@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class CustomerController {

	@Autowired
	private CustomerService customerService;
  
	/*
	 * 고객 회원가입을 누르고 정보를 입력하고 회원가입 버튼을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping(value = "/customer_signupDo", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public ModelAndView customer_signupDo(@RequestParam HashMap<String, Object> cmap, MultipartHttpServletRequest multipartHttpServletRequest) {	//form에서 입력한 값을 HashMap으로 묶어서 가져옴
		return this.customerService.customer_signupDo(multipartHttpServletRequest, cmap);
	}
	
	/*
	 * 고객 회원 가입 시 아이디가 중복되었는지 확인해주는 메서드
	 */
	@RequestMapping(value = "/customer_checkId", method = RequestMethod.GET)
	public void idCheck(@RequestParam("customer_Id") String customer_Id) {			// customer_signup.jsp에서 name이 customer_Id인 값을 가져와 String값으로 저장한다.		
		this.customerService.checkCustomerID(customer_Id);							// 해당 customer_Id가 있는지 customer테이블에서 확인해본다.
	}

	/*
	 * 고객 회원 가입 시 주민등록번호가 중복되었는지 확인해주는 메서드
	 */
	@RequestMapping(value = "/customer_chekResidentNumber", method = RequestMethod.GET)
	public void residentNumCheck(@RequestParam("customer_ResidentNumber") String customer_ResidentNumber) {	// customer_signup.jsp에서 name이 customer_ResidentNumber인 값을 가져와 String값으로 저장한다.
		this.customerService.checkCustomerResident(customer_ResidentNumber);								// 해당 customer_ResidentNumber가 있는지 customer테이블에서 확인해본다.	
	}

	/*
	 * 고객회원이 로그인을 한 후 마이페이지로 이동하게 될 때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_Profile")
	public String profile(Model model, HttpSession session) {
		return this.customerService.profile(model, session);
	}

	/*
	 * 고객이 마이페이지에서 개인정보수정을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping("/customer_modify")
	public ModelAndView customer_modify(ModelAndView mv, HttpSession session) {
		return this.customerService.customer_modify(mv, session);
	}

	/*
	 * 개인정보수정를 고치고 수정 버튼을 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_modify_ok")
	public String customer_modify(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap) {	// form에서 입력한 정보를 HashMap으로 묶어서 가져온다.
		this.customerService.updateCustomerInfo(multipartHttpServletRequest, cmap);							// 가져온 cmap데이터를 기존 고객 데이터에 update시킨다.
		return "customer/customer_modify_ok.tiles";
		
	}

	/*
	 * 로그인창에서 아이디 찾기를 누르고 고객 아이디찾기를 하면 실행되는 메서드
	 */
	@RequestMapping(value = "/search_id_customer", method = RequestMethod.POST)
	public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request) {
		return this.customerService.search_id_customer(mv, request);
	}

	/*
	 * 로그인창에서 비밀번호 찾기를 누르고 고객 비밀번호 찾기를 하면 실행되는 메서드
	 */
	@RequestMapping(value = "/search_pw_customer", method = RequestMethod.POST)
	public String search_pw_customer(ModelAndView mv, HttpServletRequest request) {
		return this.customerService.search_pw_customer(mv, request);
	}
}