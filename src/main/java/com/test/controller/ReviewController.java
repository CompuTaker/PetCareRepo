package com.test.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.ReviewDAO;
import com.test.dto.Criteria;
import com.test.dto.CustomerDTO;
import com.test.dto.ReviewDTO;
import com.test.service.ReviewService;

@Controller // Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class ReviewController {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public ReviewService reviewService;

	@Autowired
	public ReviewDAO reviewDAO;

	/*
	 * 고객이 마이페이지에서 자신이 작성한 후기 모아보기 버튼을 눌렀을 때 후기 리스트가 나오도록 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_mylist")
	public String customerReviewMylist(Model model, HttpSession session , HttpServletRequest request) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer"); // customer session을 가져온다.
		String customer_id = customer.getCustomer_Id(); // customer에서 아이디를 따로 string 변수에 저장한다.
		List<ReviewDTO> myReviews = this.reviewDAO.listMyReviews(customer_id); // 아이디를 통해 자신이 작성한 후기 리스트를 가져온다.
		model.addAttribute("myreview", myReviews); // 가져온 후기 리스트를 model 객체에 저장한다.
		
		logger.info("/customer_review_mylist "+request.getMethod());
		return "review/customer_review_mylist.tiles";
		
	}

	/*
	 * 고객이 업체찾기>기업리스트 중 선택>후기 를 눌렀을 때 후기 리스트가 나오도록 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_list")
	public String customerReviewList(Model model, HttpServletRequest request) {
		logger.info("/customer_review_list "+request.getMethod());
		return this.reviewService.customerReviewList(model, request);
	}

	/*
	 * 기업이 업체찾기>기업리스트 중 선택>후기 를 눌렀을 때 후기 리스트가 나오도록 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_list")
	public String companyReviewList(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("/company_review_list "+request.getMethod());
		return this.reviewService.companyReviewList(model, session);
	}

	/*
	 * 기업이 후기 리스트 중에서 자세히 보고자 하는 후기 리스트를 클릭하면 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_view")
	public String CompanyReviewView(Model model, int reviewIdx, HttpServletRequest request) {
		logger.info("/company_review_view "+request.getMethod());
		return this.reviewService.CompanyReviewView(model, reviewIdx);
	}

	/*
	 * 고객이 후기 리스트 중에서 자세히 보고자 하는 후기 리스트를 클릭하면 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_view")
	public String customerReviewView(Model model, int review_Index, HttpServletRequest request) {
		logger.info("/customer_review_view "+request.getMethod());
		return this.reviewService.customerReviewView(model, review_Index);
	}

	/*
	 * 고객이 이용한 예약에 한 해서 후기를 작성하려고 후기 작성 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping(value = "/customer_review_add", method = RequestMethod.GET)
	public ModelAndView customerReviewAdd(ModelAndView mv, int index, @RequestParam("index") int reservation_index, HttpServletRequest request) { // customer_reserve_check.jsp에서

		// 후기
		// 작성
		// 버튼을
		// 누르면
		// reservation_Index가 넘어온다.
		logger.info("/customer_review_add "+request.getMethod());
		return this.reviewService.customerReviewAdd(mv, index, reservation_index);
	}

	/*
	 * 리뷰 작성을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/review_ok")
	public ModelAndView review_Ok(
			MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> rmap, HttpSession session, HttpServletRequest request) { // 리뷰 작성 화면에서 입력한 form값이 HashMap객체로 묶어서 가져온다.
		List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("review_Image");
		
		logger.info("/review_ok "+request.getMethod());
		return this.reviewService.review_Ok(rmap, multipartHttpServletRequest, session);
	}

	/*
	 * 고객이 작성한 후기에 기업이 답글을 달아주고 답글 달기 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/company_review_ok")
	public String companyReviewOk(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request,
			int reviewIdx) { // 답글 작성 화면에서 입력한 form값이 HashMap객체로 묶어서 가져온다. reviewIdx도 들어온다.
		
		logger.info("/company_review_ok "+request.getMethod());
		return this.reviewService.companyReviewOk(rmap, request, reviewIdx);
	}
	@RequestMapping("/company_comment_delete")
	public String companyReviewDelete(HttpServletRequest request,int reviewIdx) { // reviewIdx도 들어온다.

		logger.info("/company_comment_delete "+request.getMethod());
		return this.reviewService.companyCommentDelete(request, reviewIdx);
	}

	// 리뷰찾기 : 전체리뷰, 검색된리뷰
	@RequestMapping("/searchReview")
	public String searchReview(Model model, HttpServletRequest request, Criteria cri) {
		String url = "";
		
		List<ReviewDTO> reviewList = this.reviewService.listsAllReview(model,request,cri); // 리뷰를 가져온다.
		
		model.addAttribute("reviewList", reviewList); // model에 가져온 리뷰 정보를 저장한다.

		url = "home/search_review.tiles"; // 화면을 띄워준다.
		
		logger.info("/searchReview "+request.getMethod());
		return url;
	}
	
	// 리뷰 삭제
	@RequestMapping("/review_cancel")
	public ModelAndView review_cancel(@RequestParam String review_Index, ModelAndView mv) {
			return this.reviewService.review_cancel(review_Index, mv);
			}
	
	
	/*
	 * 고객 후기 수정
	 * 고객이 후기상세보기에서 후기를 수정을 원할때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_modify")
	public ModelAndView customerReviewModify(ModelAndView mv, int review_Index, HttpServletRequest request) {
		logger.info("/customer_review_modify "+request.getMethod());
		
		return this.reviewService.customerReviewModify(mv, review_Index);
	}
	
	/*
	 * 고객 후기 수정 완료
	 * 고객이 후기 수정을 완료했을 때, 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_review_modify_ok")
	public String customerReviewUpdate(@RequestParam HashMap<String, Object> rmap, Model model, HttpSession session,
			MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) {
		
		logger.info("/customer_review_modify_ok "+request.getMethod());
		
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer"); // customer session을 가져온다.
		String customer_id = customer.getCustomer_Id(); // customer에서 아이디를 따로 string 변수에 저장한다.
		List<ReviewDTO> myReviews = this.reviewDAO.listMyReviews(customer_id); // 아이디를 통해 자신이 작성한 후기 리스트를 가져온다.
		model.addAttribute("myreview", myReviews); // 가져온 후기 리스트를 model 객체에 저장한다.
		
		this.reviewService.customerReviewUpdate(multipartHttpServletRequest, rmap, model);
		
		return "review/customer_review_mylist.tiles";
	}
}
