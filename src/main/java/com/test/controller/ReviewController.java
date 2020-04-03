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

import com.test.dao.CompanyDAO;
import com.test.dao.CustomerDAO;
import com.test.dao.PetDAO;
import com.test.dao.ReservationDAO;
import com.test.dao.ReviewDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;
import com.test.dto.ReservationDTO;
import com.test.dto.ReviewDTO;

@Controller
@SessionAttributes({ "customer", "company" })
public class ReviewController {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private CompanyDAO companyDao;

	@Autowired
	private PetDAO petDao;

	@Autowired
	private ReservationDAO reservationDAO;

	@Autowired
	public ReviewDAO reviewDAO;

	
	@RequestMapping("/customer_review_list")
	public String customerReviewList(Model model, HttpServletRequest request) {
		int company_Index = Integer.parseInt(request.getParameter("company_Index"));
		List<ReviewDTO> itsReviews = this.reviewDAO.listItsReviews(company_Index);
		
		List<String> list = new ArrayList<String>(); 
		for(int i=0;i<itsReviews.size();i++) {
			CustomerDTO customer = this.reviewDAO.searchCustomerName(itsReviews.get(i).getCustomer_id());
			list.add(customer.getCustomer_Name());
			System.out.println("리뷰목록이다아아아아아아아아:작성자 이름"+list.get(i));
		}
		
		model.addAttribute("customer", list);
		model.addAttribute("review", itsReviews);
		return "customer_review_list";
	}
	
	@RequestMapping("/company_review_list")
	public String companyReviewList(Model model, HttpSession session) {
		CompanyDTO company = (CompanyDTO) session.getAttribute("company");
		int company_Index = company.getCompany_Index();
		System.out.println(company_Index);
		List<ReviewDTO> itsReviews = this.reviewDAO.listItsReviews(company_Index);
		List<String> list = new ArrayList<String>(); 
		for(int i=0;i<itsReviews.size();i++) {
			CustomerDTO customer = this.reviewDAO.searchCustomerName(itsReviews.get(i).getCustomer_id());
			list.add(customer.getCustomer_Name());
			System.out.println("리뷰목록이다아아아아아아아아:작성자 이름"+list.get(i));
		}
		model.addAttribute("customer", list);
		model.addAttribute("review", itsReviews);
		return "company_review_list";
	}
	
	@RequestMapping("/company_review_view")
	public String CompanyReviewView(Model model, int reviewIdx) {
		ReviewDTO reviewDTO = this.reviewDAO.listItsReview(reviewIdx);
		CustomerDTO customerDTO = this.reviewDAO.searchCustomerName(reviewDTO.getCustomer_id());
		
		model.addAttribute("customer",customerDTO.getCustomer_Name());
		model.addAttribute("review", reviewDTO);
		return "company_review_view";
	}
	@RequestMapping("/customer_review_view")
	public String customerReviewView(Model model, int reviewIdx) {
		
		ReviewDTO reviewDTO = this.reviewDAO.listItsReview(reviewIdx);
		
		CustomerDTO customerDTO = this.reviewDAO.searchCustomerName(reviewDTO.getCustomer_id());
		
		model.addAttribute("customer",customerDTO.getCustomer_Name());
		model.addAttribute("review", reviewDTO);
		
		return "customer_review_view";
	}
	
	@RequestMapping(value = "/customer_review_add", method = RequestMethod.GET)
	public ModelAndView customerReviewAdd(ModelAndView mv,int index) {
		System.out.println(index);
		int company_Index = this.reservationDAO.selectCompanyIndex(index);
		mv.addObject("company_Index", company_Index);
		mv.setViewName("customer_review_add");
		return mv;
	}
	@RequestMapping("/review_ok")
	public String review_Ok(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request, HttpSession session ) {
		System.out.println("rmap"+rmap);
		CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("customer");
		System.out.println(customerDTO.getCustomer_Id());
		String customer_id = customerDTO.getCustomer_Id();
		rmap.put("customer_id", customer_id);
		this.reviewDAO.insertTheReview(rmap);
		return "review_ok";
	}
	@RequestMapping("/company_review_ok")
	public String companyReviewOk(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request,int reviewIdx) {
		rmap.put("review_Index", reviewIdx);
		System.out.println(rmap.get("review_Index"));
		System.out.println(rmap.get("review_Comment"));
		this.reviewDAO.insertTheComent(rmap);
		return "company_review_ok";
	}
	
}
