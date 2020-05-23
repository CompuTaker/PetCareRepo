package com.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.constants.Constant;
import com.test.constants.Constant.ESession;
import com.test.dto.CustomerDTO;
import com.test.service.CustomerService;

//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@Controller 
//Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
@SessionAttributes({ "customer", "company" }) 
public class CustomerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerService customerService;

	/*
	 * 고객 회원가입을 누르고 정보를 입력하고 회원가입 버튼을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping(value = "/customer_signupDo", method = RequestMethod.POST, headers = "content-type=multipart/*")	
	public Object customer_signupDo(@RequestParam HashMap<String, Object> cmap,
			MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request, Model model) { // form에서 입력한 값을 HashMap으로 묶어서 가져옴
		
		if(Constant.eSession == ESession.eNull) {
			logger.info("/customer_signupDo "+request.getMethod()+"user : "+"guest");
		}
		logger.info("/customer_signupDo "+request.getMethod());
		return this.customerService.customer_signupDo(multipartHttpServletRequest, cmap);
	}

	/*
	 * 고객 회원 가입 시 아이디가 중복되었는지 확인해주는 메서드
	 */
	@RequestMapping(value = "/customer_checkId", method = RequestMethod.GET)
	public void idCheck(@RequestParam("customer_Id") String customer_Id , HttpServletRequest request) { // customer_signup.jsp에서 name이 customer_Id인
		logger.info("/customer_checkId "+request.getMethod());												// 값을 가져와 String값으로 저장한다.
		this.customerService.checkCustomerID(customer_Id); // 해당 customer_Id가 있는지 customer테이블에서 확인해본다.
	}

	/*
	 * 고객회원이 로그인을 한 후 마이페이지로 이동하게 될 때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_Profile")
	public String profile(Model model, HttpSession session,HttpServletRequest  request) {
		
			logger.info("/customer_Profile "+request.getMethod());	
		
		return this.customerService.profile(model, session);
	}

	/*
	 * 고객이 마이페이지에서 개인정보수정을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping("/customer_modify")
	public ModelAndView customer_modify(ModelAndView mv, HttpSession session,HttpServletRequest  request) {
		logger.info("/customer_modify "+request.getMethod());
		return this.customerService.customer_modify(mv, session);
	}

	/*
	 * 개인정보수정를 고치고 수정 버튼을 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_modify_ok")
	public String customer_modify(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap, Model model ,HttpServletRequest  request) { // form에서 입력한 정보를 HashMap으로 묶어서 가져온다.
		this.customerService.updateCustomerInfo(multipartHttpServletRequest, cmap, model); // 가져온 cmap데이터를 기존 고객 데이터에
																							// update시킨다.
		
		logger.info("/customer_modify_ok "+request.getMethod());

		return "customer/customer_modify_ok.tiles";

	}

	/*
	 * 로그인창에서 아이디 찾기를 누르고 고객 아이디찾기를 하면 실행되는 메서드
	 */
	@RequestMapping(value = "/search_id_customer", method = RequestMethod.POST)
	public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request) {
		logger.info("/search_id_customer"+request.getMethod());
		return this.customerService.search_id_customer(mv, request);
	}

	/*
	 * 로그인창에서 비밀번호 찾기를 누르고 고객 비밀번호 찾기를 하면 실행되는 메서드
	 */
	@RequestMapping(value = "/search_pw_customer", method = RequestMethod.POST)
	public String search_pw_customer(ModelAndView mv, HttpServletRequest request) {
		logger.info("/search_pw_customer"+request.getMethod());
		return this.customerService.search_pw_customer(mv, request);
	}
}