package com.test.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;
import com.test.dto.ReservationDTO;
import com.test.dto.ReviewDTO;

@Repository
public class ReviewDAOimpl implements ReviewDAO {

	@Autowired // root-context.xml 참고
	private SqlSessionTemplate sqlSession;

	@Override
	public List<ReviewDTO> listItsReviews(int company_Index) {
		
		
		
		return this.sqlSession.selectList("listItsReviews", company_Index);
	}

	
//	@Override
//	public int insertTheReview(HashMap<String, Object> rmap, int customer_Index) {
//		
//		return this.sqlSession.insert("insertTheReview", rmap);
//
//	}

	@Override
	public int deleteTheReview(int review_Index) {
		return this.sqlSession.delete("deleteTheReview", review_Index);
	}

	@Override
	public void insertTheReview(HashMap<String, Object> rmap) {
		this.sqlSession.insert("insertTheReview", rmap);	
	}

	@Override
	public ReviewDTO listItsReview(int reviewIdx) {
		return this.sqlSession.selectOne("listThisReviewByIdx", reviewIdx);
	}

	@Override
	public void insertTheComent(HashMap<String, Object> rmap) {
		this.sqlSession.update("updateTheComent", rmap);
	}

	@Override
	public CustomerDTO searchCustomerName(String customer_id) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("searchCustomerName", customer_id);
	}

//	@Override
//	public int updateTheReview(int review_Index) {
//		// TODO Auto-generated method stub
//		return this.sqlSession.update("updateTheReview", review_Index);
//	}

}