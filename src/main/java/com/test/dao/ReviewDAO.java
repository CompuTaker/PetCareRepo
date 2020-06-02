package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.Criteria;
import org.springframework.web.servlet.ModelAndView;
import com.test.dto.CustomerDTO;
import com.test.dto.ReviewDTO;
import com.test.dto.ReviewImageDTO;

public interface ReviewDAO {

	abstract public List<ReviewDTO> listItsReviews(int company_Index);

	abstract public ReviewDTO listItsReview(int review_Index);
	// abstract public int insertTheReview(HashMap<String, Object> rmap, int
	// customer_Index);

	abstract public int deleteTheReview(int review_Index);

	public abstract void insertTheReview(HashMap<String, Object> rmap);

	public abstract void insertTheReviewImage(HashMap<String, Object> rmap);

	public abstract void insertTheComent(HashMap<String, Object> rmap);

	// 고객 이름 찾기
	public abstract List<CustomerDTO> searchCustomerName(String customer_id);

	public abstract List<ReviewDTO> listMyReviews(String customer_id);

	abstract public List<ReviewDTO> listAllReviews(Criteria cri);

	abstract public List<ReviewDTO> listThisReviewByTerm(  Map<String, Object> map);

	public abstract List<ReviewImageDTO> listImages(int reservation_Index);

	// abstract public int updateTheReview(int review_Index);.

	abstract public int countReviewList();
	
	public abstract void updateTheReview(HashMap<String, Object> rmap);
	
	public abstract void deleteTheReviewImage(int reservation_Index);

	public abstract int countReviewByTerm(String term);

	public abstract int deleteTheComment(int review_Index);

}