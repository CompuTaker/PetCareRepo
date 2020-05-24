package com.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.CustomerService;

//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@Controller
// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
@SessionAttributes({ "customer", "company" })
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	/*
	 * 怨좉컼 �쉶�썝媛��엯�쓣 �늻瑜닿퀬 �젙蹂대�� �엯�젰�븯怨� �쉶�썝媛��엯 踰꾪듉�쓣 �닃���쓣 �븣 �떎�뻾�릺�뒗 硫붿꽌�뱶
	 */
	@RequestMapping(value = "/customer_signupDo", method = RequestMethod.POST, headers = "content-type=multipart/*")
	public Object customer_signupDo(@RequestParam HashMap<String, Object> cmap,
			MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request, Model model) { // form에서
																												// 입력한
																												// 값을
																												// HashMap으로
																												// 묶어서
																												// 가져옴
		return this.customerService.customer_signupDo(multipartHttpServletRequest, cmap);
	}

	/*
	 * 怨좉컼 �쉶�썝 媛��엯 �떆 �븘�씠�뵒媛� 以묐났�릺�뿀�뒗吏� �솗�씤�빐二쇰뒗 硫붿꽌�뱶
	 */
	@RequestMapping(value = "/customer_checkId", method = RequestMethod.GET)
	public void idCheck(@RequestParam("customer_Id") String customer_Id) { // customer_signup.jsp�뿉�꽌 name�씠
																			// customer_Id�씤
																			// 媛믪쓣 媛��졇�� String媛믪쑝濡� ���옣�븳�떎.
		this.customerService.checkCustomerID(customer_Id); // �빐�떦 customer_Id媛� �엳�뒗吏� customer�뀒�씠釉붿뿉�꽌 �솗�씤�빐蹂몃떎.
	}

	/*
	 * 怨좉컼�쉶�썝�씠 濡쒓렇�씤�쓣 �븳 �썑 留덉씠�럹�씠吏�濡� �씠�룞�븯寃� �맆 �븣 �떎�뻾�릺�뒗 硫붿꽌�뱶�씠�떎.
	 */
	@RequestMapping("/customer_Profile")
	public String profile(Model model, HttpSession session) {
		return this.customerService.profile(model, session);
	}

	/*
	 * 怨좉컼�씠 留덉씠�럹�씠吏��뿉�꽌 媛쒖씤�젙蹂댁닔�젙�쓣 �닃���쓣 �븣 �떎�뻾�릺�뒗 硫붿꽌�뱶
	 */
	@RequestMapping("/customer_modify")
	public ModelAndView customer_modify(ModelAndView mv, HttpSession session) {
		return this.customerService.customer_modify(mv, session);
	}

	/*
	 * 媛쒖씤�젙蹂댁닔�젙瑜� 怨좎튂怨� �닔�젙 踰꾪듉�쓣 �닃���쓣 �븣 �떎�뻾�릺�뒗 硫붿꽌�뱶�씠�떎.
	 */
	@RequestMapping("/customer_modify_ok")
	public String customer_modify(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap, Model model) { // form�뿉�꽌 �엯�젰�븳 �젙蹂대�� HashMap�쑝濡� 臾띠뼱�꽌
																		// 媛��졇�삩�떎.
		this.customerService.updateCustomerInfo(multipartHttpServletRequest, cmap, model); // 媛��졇�삩 cmap�뜲�씠�꽣瑜� 湲곗〈
																							// 怨좉컼 �뜲�씠�꽣�뿉
																							// update�떆�궓�떎.
		return "customer/customer_modify_ok.tiles";

	}

	/*
	 * 濡쒓렇�씤李쎌뿉�꽌 �븘�씠�뵒 李얘린瑜� �늻瑜닿퀬 怨좉컼 �븘�씠�뵒李얘린瑜� �븯硫� �떎�뻾�릺�뒗 硫붿꽌�뱶
	 */
	@RequestMapping(value = "/search_id_customer", method = RequestMethod.POST)
	public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request) {
		return this.customerService.search_id_customer(mv, request);
	}

	/*
	 * 濡쒓렇�씤李쎌뿉�꽌 鍮꾨�踰덊샇 李얘린瑜� �늻瑜닿퀬 怨좉컼 鍮꾨�踰덊샇 李얘린瑜� �븯硫� �떎�뻾�릺�뒗 硫붿꽌�뱶
	 */
	@RequestMapping(value = "/search_pw_customer", method = RequestMethod.POST)
	public String search_pw_customer(ModelAndView mv, HttpServletRequest request) {
		return this.customerService.search_pw_customer(mv, request);
	}

	// 탈퇴화면 페이지로 이동
	@RequestMapping("/deleteTheCustomer") // customer_delete을 요청하면
	public String deleteTheCustomer() {
		return "customer/customer_delete.tiles"; // customer_delete.jsp가 return되게

	}

	// 탈퇴처리(고객정보삭제)
	@RequestMapping("/customer_delete_ok")
	public String customer_delete_ok(HttpServletRequest request, HttpSession session) {

		Map<String, Object> cMap = new HashMap<String, Object>();
		String customer_Password = request.getParameter("customer_Password");
		cMap.put("customer_Password", customer_Password);

		String customer_Id = ((CustomerDTO) (session.getAttribute("customer"))).getCustomer_Id();
		// 비밀번호 체크
		boolean result = customerService.checkPW(customer_Id, customer_Password);
		System.out.println("비밀번호 체크");
		if (result) { // 비밀번호가 맞다면 삭제 처리
			customerService.deleteTheCustomer(customer_Id);
			System.out.println("탈퇴성공");
			if (result) {
				Constant.eSession = ESession.eNull;
				session.invalidate(); // 탈퇴시 로그아웃 처리
			}
			return "customer/customer_delete_ok.tiles";

		} else { // 비밀번호가 일치하지 않는다면
			return "customer/customer_delete.tiles";

		}

	}
}