package com.test.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.ReviewDAO;
import com.test.dto.CustomerDTO;
import com.test.dto.ReviewDTO;
import com.test.service.ReviewService;

@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class ReviewController {	
	
	@Autowired
	public ReviewService reviewService;

		@Autowired
	public ReviewDAO reviewDAO;
/*
	 * 고객이 마이페이지에서 자신이 작성한 후기 모아보기 버튼을 눌렀을 때 후기 리스트가 나오도록 
	 * 실행되는 메서드이다. 
	 */
	@RequestMapping("/customer_review_mylist")
	public String customerReviewMylist(Model model, HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer"); 	//customer session을 가져온다.
		String customer_id= customer.getCustomer_Id();							//customer에서 아이디를 따로 string 변수에 저장한다.
		List<ReviewDTO> myReviews = this.reviewDAO.listMyReviews(customer_id);	//아이디를 통해 자신이 작성한 후기 리스트를 가져온다.
		
		
		model.addAttribute("myreview", myReviews);								//가져온 후기 리스트를 model 객체에 저장한다.
		return "customer_review_mylist";
	}
	/*
	 * 고객이 업체찾기>기업리스트 중 선택>후기 를 눌렀을 때 후기 리스트가 나오도록
	 * 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_list")
	public String customerReviewList(Model model, HttpServletRequest request) {
		return this.reviewService.customerReviewList(model, request);
	}
	
	/*
	 * 기업이 업체찾기>기업리스트 중 선택>후기 를 눌렀을 때 후기 리스트가 나오도록
	 * 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_list")
	public String companyReviewList(Model model, HttpSession session) {
		return this.reviewService.companyReviewList(model, session);
	}
	
	/*
	 * 기업이 후기 리스트 중에서 자세히 보고자 하는 후기 리스트를 클릭하면 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_view")
	public String CompanyReviewView(Model model, int reviewIdx) {
		return this.reviewService.CompanyReviewView(model, reviewIdx);
	}
	
	/*
	 * 고객이 후기 리스트 중에서 자세히 보고자 하는 후기 리스트를 클릭하면 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_view")
	public String customerReviewView(Model model, int reviewIdx) {
		return this.reviewService.customerReviewView(model, reviewIdx);
	}
	
	/*
	 * 고객이 이용한 예약에 한 해서 후기를 작성하려고 후기 작성 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping(value = "/customer_review_add", method = RequestMethod.GET)
	public ModelAndView customerReviewAdd(ModelAndView mv, int index) {		// customer_reserve_check.jsp에서 후기 작성 버튼을 누르면 reservation_Index가 넘어온다.
		return this.reviewService.customerReviewAdd(mv, index);
	}
	
	/*
	 * 리뷰 작성을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/review_ok")
	public String review_Ok(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request, HttpSession session ) {	// 리뷰 작성 화면에서 입력한 form값이 HashMap객체로 묶어서 가져온다.
		return this.reviewService.review_Ok(rmap, request, session);
	}
	
	/*
	 * 고객이 작성한 후기에 기업이 답글을 달아주고 답글 달기 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_ok")
	public String companyReviewOk(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request, int reviewIdx) {	// 답글 작성 화면에서 입력한 form값이 HashMap객체로 묶어서 가져온다. reviewIdx도 들어온다.
		return this.reviewService.companyReviewOk(rmap, request, reviewIdx);
	}
	
}
