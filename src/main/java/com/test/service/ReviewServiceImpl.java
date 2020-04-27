package com.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.ReservationDAO;
import com.test.dao.ReviewDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.ReviewDTO;

@Service
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDAO reviewDao;
	
	@Autowired
	private ReservationDAO reservationDao;
	
	@Override
	public String customerReviewList(Model model, HttpServletRequest request) {
		System.out.println("후기화면 리스트??");
		int company_Index 		   = Integer.parseInt(request.getParameter("company_Index"));				// company_view.jsp에 hidden으로 숨어져있는 company_Index를 후기 버튼을 누르면 가져온다.
		List<ReviewDTO> itsReviews = this.reviewDao.listItsReviews(company_Index);							// company_Index로 작성된 모든 후기를 가져온다. 
			
		List<String> list = new ArrayList<String>(); 														// 후기 작성자의 아이디를 저장할 List객체이다.
		for(int i=0;i<itsReviews.size();i++) {
			List<CustomerDTO> customer = this.reviewDao.searchCustomerName(itsReviews.get(i).getCustomer_id());	// 해당 아이디의 이름을 가져온다.
			if(customer.size() > 1) {
				for(int j = 0; j < customer.size(); j++) {
					list.add(customer.get(j).getCustomer_Name());															// 가져온 이름을 list에 저장한다.
				}
			} else {
				list.add(customer.get(0).getCustomer_Name());															// 가져온 이름을 list에 저장한다.
			}
			
		}	
		model.addAttribute("customerName", list);															// 가져온 이름 list를 model객체에 저장한다.
		model.addAttribute("review", itsReviews);															// 가져온 후기 리스트를 model객체에 저장한다.
		return "review/customer_review_list.tiles";
	}

	@Override
	public String companyReviewList(Model model, HttpSession session) {
		CompanyDTO company = (CompanyDTO) session.getAttribute("company");				// company session을 가져온다.
		int company_Index  = company.getCompany_Index();								// company에서 index를 따로 int변수에 저장한다.	
		List<ReviewDTO> itsReviews = this.reviewDao.listItsReviews(company_Index);		// 해당 company_Index에 해당하는 후기를 가져온다.
		
		List<String> list = new ArrayList<String>(); 									// 후기 작성자의 아이디를 저장할 List객체이다.
		for(int i=0;i<itsReviews.size();i++) {
			List<CustomerDTO> customer = this.reviewDao.searchCustomerName(itsReviews.get(i).getCustomer_id());	// 해당 아이디의 이름을 가져온다.
			if(customer.size() > 1) {
				for(int j = 0; j < customer.size(); j++) {
					list.add(customer.get(j).getCustomer_Name());															// 가져온 이름을 list에 저장한다.
				}
			} else {
				list.add(customer.get(0).getCustomer_Name());															// 가져온 이름을 list에 저장한다.
			}
			
		}	
		model.addAttribute("customerName", list);										// 가져온 이름 list를 model객체에 저장한다.
		model.addAttribute("review", itsReviews);										// 가져온 후기 리스트를 model객체에 저장한다.
		return "review/company_review_list.tiles";
	}

	@Override
	public String customerReviewView(Model model, int reviewIdx) {
		System.out.println("자세한 후기 보기?");
		ReviewDTO reviewDTO 	= this.reviewDao.listItsReview(reviewIdx);								// reviewIdx(customer_review_view.jsp에서)를 가지고 후기의 자세한 내용을 가져온다.
		List<CustomerDTO> customerDTO = this.reviewDao.searchCustomerName(reviewDTO.getCustomer_id());	// 가져온 후기 정보에서 고객의 id를 가지고 고객 정보를 가져온다.	
		model.addAttribute("customerName",customerDTO.get(0).getCustomer_Name());						// model에 고객 이름을 저장한다.
		model.addAttribute("review", reviewDTO);														// model에 후기의 모든 정보를 저장한다.		
		return "review/customer_review_view.tiles";
	}

	@Override
	public String CompanyReviewView(Model model, int reviewIdx) {
		ReviewDTO reviewDTO 	= this.reviewDao.listItsReview(reviewIdx);								// reviewIdx(company_review_list.jsp에서)를 가지고 후기의 자세한 내용을 가져온다.
		List<CustomerDTO> customerDTO = this.reviewDao.searchCustomerName(reviewDTO.getCustomer_id());	// 가져온 후기 정보에서 고객의 id를 가지고 고객 정보를 가져온다.	
		model.addAttribute("customerName",customerDTO.get(0).getCustomer_Name());						// model에 고객 이름을 저장한다.
		model.addAttribute("review", reviewDTO);														// model에 후기의 모든 정보를 저장한다.
		return "review/company_review_view.tiles";
	}

	@Override
	public ModelAndView customerReviewAdd(ModelAndView mv, int index) {
		int company_Index = this.reservationDao.selectCompanyIndex(index);	// reservation_Index를 가지고 company_Index를 찾아온다.
		mv.addObject("company_Index", company_Index);						// company_Index를 model객체에 저장한다.
		mv.setViewName("review/customer_review_add.tiles");								// 띄워줄 화면(customer_review_add.jsp)를 설정한다.
		return mv;
	}

	@Override
	public String review_Ok(HashMap<String, Object> rmap, HttpServletRequest request, HttpSession session) {
		CustomerDTO customerDTO = (CustomerDTO) session.getAttribute("customer");	// customser session을 가져온다.
		String customer_id 		= customerDTO.getCustomer_Id();						// customer id를 String객체에 저장한다.
		rmap.put("customer_id", customer_id);										// customer_id를 HashMap에 저장한다. (기존 form 데이터 + customer_Id)
		this.reviewDao.insertTheReview(rmap);										// review테이블에 값을 저장한다.
		return "review/review_ok.tiles";
	}

	@Override
	public String companyReviewOk(HashMap<String, Object> rmap, HttpServletRequest request, int reviewIdx) {
		rmap.put("review_Index", reviewIdx);		// form데이터 + reviewIdx가 HashMap에 저장된다.
		this.reviewDao.insertTheComent(rmap);		// 기업이 단 코멘트를 reviewIdx에 해당하는 review_Comment컬럼에 저장한다.
		return "review/company_review_ok.tiles";
	}

}
