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

import com.test.service.ReservationService;

@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class ReservationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private ReservationService reservationService;

	/*
	 * 상단에 예약하기 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/reserve")
	public String reserve(Model model, HttpServletRequest request) {
		logger.info("/reserve "+request.getMethod());
		return this.reservationService.reserve(model, request);
	}

	@RequestMapping("/reserve_ok")
	public String reserve_Ok(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request) {
		logger.info("/reserve_ok "+request.getMethod());
		return this.reservationService.reserve_OK(rmap, request);
	}

	/*
	 * 고객이 마이페이지에서 예약정보조회를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping(value="/customer_reserve_check", method=RequestMethod.GET)					
	public String customer_reservecheck(Model model, HttpSession session, String petName,HttpServletRequest request) {		
		logger.info("/customer_reserve_check "+request.getMethod());
		return this.reservationService.customer_reservecheck(model, session, petName);
	}

	/*
	 * 기업이 마이페이지에서 예약정보조회를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_reserve_check")
	public String company_reservecheck(Model model, HttpSession session,HttpServletRequest request) {
		logger.info("/company_reserve_check "+request.getMethod());
		return this.reservationService.company_reservecheck(model, session);
	}
	
	/*
	 * 고객이 예약을 취소할 때 실행되는 메서드이다.
	 * 예약조회화면에서 정상예약건에 한해서 취소를 누를 수 있다.
	 */
	@RequestMapping(value = "/customer_reservation_cancel", method = RequestMethod.GET)
	public String customer_reservation_cancel(Model model, HttpSession session, HttpServletRequest request, String index) {
		logger.info("/customer_reservation_cancel "+request.getMethod());
		return this.reservationService.customer_reservation_cancel(model, session, request, index);		
	}
}
