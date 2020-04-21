package com.test.dao;

import java.util.HashMap;
import java.util.List;

import com.test.dto.ReservationDTO;
import com.test.dto.ReviewDTO;

public interface ReviewDAO {

	abstract public List<ReviewDTO> listItsReviews(int company_Index);
	
	abstract public ReviewDTO listItsReview(int reviewIdx);
//	abstract public int insertTheReview(HashMap<String, Object> rmap, int customer_Index);

	abstract public int deleteTheReview(int review_Index);

	public abstract void insertTheReview(HashMap<String, Object> rmap);

	public abstract void insertTheComent(HashMap<String, Object> rmap);

//	abstract public int updateTheReview(int review_Index);

}