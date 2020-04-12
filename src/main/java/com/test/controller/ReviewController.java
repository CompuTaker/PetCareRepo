package com.test.controller;

import java.util.ArrayList;
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

import com.test.dao.ReservationDAO;
import com.test.dao.ReviewDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.ReviewDTO;

@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class ReviewController {	

	@Autowired
	private ReservationDAO reservationDAO;

	@Autowired
	public ReviewDAO reviewDAO;

	/*
	 * 고객이 업체찾기>기업리스트 중 선택>후기 를 눌렀을 때 후기 리스트가 나오도록
	 * 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_list")
	public String customerReviewList(Model model, HttpServletRequest request) {
		int company_Index 		   = Integer.parseInt(request.getParameter("company_Index"));	// company_view.jsp에 hidden으로 숨어져있는 company_Index를 후기 버튼을 누르면 가져온다.
		List<ReviewDTO> itsReviews = this.reviewDAO.listItsReviews(company_Index);				// company_Index로 작성된 모든 후기를 가져온다. 
		
		List<String> list = new ArrayList<String>(); 											// 후기 작성자의 아이디를 저장할 List객체이다.
		for(int i=0;i<itsReviews.size();i++) {
			CustomerDTO customer = this.reviewDAO.searchCustomerName(itsReviews.get(i).getCustomer_id());	// 해당 아이디의 이름을 가져온다.
			list.add(customer.getCustomer_Name());															// 가져온 이름을 list에 저장한다.
		}	
		model.addAttribute("customerName", list);												// 가져온 이름 list를 model객체에 저장한다.
		model.addAttribute("review", itsReviews);												// 가져온 후기 리스트를 model객체에 저장한다.
		return "review/customer_review_list.tiles";
	}
	
	/*
	 * 기업이 업체찾기>기업리스트 중 선택>후기 를 눌렀을 때 후기 리스트가 나오도록
	 * 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_list")
	public String companyReviewList(Model model, HttpSession session) {
		CompanyDTO company = (CompanyDTO) session.getAttribute("company");				// company session을 가져온다.
		int company_Index  = company.getCompany_Index();								// company에서 index를 따로 int변수에 저장한다.	
		List<ReviewDTO> itsReviews = this.reviewDAO.listItsReviews(company_Index);		// 해당 company_Index에 해당하는 후기를 가져온다.
		
		List<String> list = new ArrayList<String>(); 									// 후기 작성자의 아이디를 저장할 List객체이다.
		for(int i=0;i<itsReviews.size();i++) {
			CustomerDTO customer = this.reviewDAO.searchCustomerName(itsReviews.get(i).getCustomer_id());	// 해당 아이디의 이름을 가져온다.
			list.add(customer.getCustomer_Name());															// 가져온 이름을 list에 저장한다.
		}
		model.addAttribute("customerName", list);										// 가져온 이름 list를 model객체에 저장한다.
		model.addAttribute("review", itsReviews);										// 가져온 후기 리스트를 model객체에 저장한다.
		return "review/company_review_list.tiles";
	}
	
	/*
	 * 기업이 후기 리스트 중에서 자세히 보고자 하는 후기 리스트를 클릭하면 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_view")
	public String CompanyReviewView(Model model, int reviewIdx) {
		ReviewDTO reviewDTO 	= this.reviewDAO.listItsReview(reviewIdx);							// reviewIdx(company_review_list.jsp에서)를 가지고 후기의 자세한 내용을 가져온다.
		CustomerDTO customerDTO = this.reviewDAO.searchCustomerName(reviewDTO.getCustomer_id());	// 가져온 후기 정보에서 고객의 id를 가지고 고객 정보를 가져온다.	
		model.addAttribute("customerName",customerDTO.getCustomer_Name());							// model에 고객 이름을 저장한다.
		model.addAttribute("review", reviewDTO);													// model에 후기의 모든 정보를 저장한다.
		return "review/company_review_view.tiles";
	}
	
	/*
	 * 고객이 후기 리스트 중에서 자세히 보고자 하는 후기 리스트를 클릭하면 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_view")
	public String customerReviewView(Model model, int reviewIdx) {		
		ReviewDTO reviewDTO 	= this.reviewDAO.listItsReview(reviewIdx);							// reviewIdx(customer_review_view.jsp에서)를 가지고 후기의 자세한 내용을 가져온다.
		CustomerDTO customerDTO = this.reviewDAO.searchCustomerName(reviewDTO.getCustomer_id());	// 가져온 후기 정보에서 고객의 id를 가지고 고객 정보를 가져온다.	
		model.addAttribute("customerName",customerDTO.getCustomer_Name());							// model에 고객 이름을 저장한다.
		model.addAttribute("review", reviewDTO);													// model에 후기의 모든 정보를 저장한다.		
		return "review/customer_review_view.tiles";
	}
	
	/*
	 * 고객이 이용한 예약에 한 해서 후기를 작성하려고 후기 작성 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping(value = "/customer_review_add", method = RequestMethod.GET)
	public ModelAndView customerReviewAdd(ModelAndView mv, int index) {		// customer_reserve_check.jsp에서 후기 작성 버튼을 누르면 reservation_Index가 넘어온다.
		int company_Index = this.reservationDAO.selectCompanyIndex(index);	// reservation_Index를 가지고 company_Index를 찾아온다.
		mv.addObject("company_Index", company_Index);						// company_Index를 model객체에 저장한다.
		mv.setViewName("review/customer_review_add.tiles");								// 띄워줄 화면(customer_review_add.jsp)를 설정한다.
		return mv;
	}
	
	/*
	 * 리뷰 작성을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/review_ok")
	public String review_Ok(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request, HttpSession session ) {	// 리뷰 작성 화면에서 입력한 form값이 HashMap객체로 묶어서 가져온다.
		CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("customer");	// customser session을 가져온다.
		String customer_id 		= customerDTO.getCustomer_Id();						// customer id를 String객체에 저장한다.
		rmap.put("customer_id", customer_id);										// customer_id를 HashMap에 저장한다. (기존 form 데이터 + customer_Id)
		this.reviewDAO.insertTheReview(rmap);										// review테이블에 값을 저장한다.
		return "review/review_ok.tiles";
	}
	
	/*
	 * 고객이 작성한 후기에 기업이 답글을 달아주고 답글 달기 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_ok")
	public String companyReviewOk(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request, int reviewIdx) {	// 답글 작성 화면에서 입력한 form값이 HashMap객체로 묶어서 가져온다. reviewIdx도 들어온다.
		rmap.put("review_Index", reviewIdx);		// form데이터 + reviewIdx가 HashMap에 저장된다.
		this.reviewDAO.insertTheComent(rmap);		// 기업이 단 코멘트를 reviewIdx에 해당하는 review_Comment컬럼에 저장한다.
		return "review/company_review_ok.tiles";
	}
	
}
