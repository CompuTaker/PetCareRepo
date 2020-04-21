package com.test.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.CustomerDTO;
import com.test.dto.ReviewDTO;

@Repository	//Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class ReviewDAOimpl implements ReviewDAO {

	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;

	/*
	 * 해당 회사의 후기 리스트를 가져오기 위한 메서드이다.
	 */
	@Override
	public List<ReviewDTO> listItsReviews(int company_Index) {
		return this.sqlSession.selectList("listItsReviews", company_Index);	// mapper에서 "listItsReviews" id를 가지는 명령문에 company_Index변수를 가지고 실행한다.
	}

	/*
	 * 고객이 후기를 삭제하기를 눌렀을 때 실행되는 메서드이다.
	 */
	@Override
	public int deleteTheReview(int review_Index) {
		return this.sqlSession.delete("deleteTheReview", review_Index);	// mapper에서 "deleteTheReview" id를 가지는 명령문에 review_Index변수를 가지고 실행한다.
	}

	/*
	 * 후기 작성을 한 후 등록하기 버튼을 누르면 실행되는 메서드이다.
	 */
	@Override
	public void insertTheReview(HashMap<String, Object> rmap) {
		this.sqlSession.insert("insertTheReview", rmap);	// mapper에서 "insertTheReview" id를 가지는 명령문에 rmap객체를 가지고 실행한다.
	}

	/*
	 * 여러 후기 리스트 중 자세히 보고싶은 후기를 눌렀을 경우 실행되는 메서드이다.
	 */
	@Override
	public ReviewDTO listItsReview(int reviewIdx) {
		return this.sqlSession.selectOne("listThisReviewByIdx", reviewIdx);	// mapper에서 "listThisReviewByIdx" id를 가지는 명령문에 reviewIdx변수를 가지고 실행한다.
	}
	
	/*
	 * 기업이 고객이 남긴 후기에 답글을 달아줄 경우 실행되는 메서드이다.
	 */
	@Override
	public void insertTheComent(HashMap<String, Object> rmap) {
		this.sqlSession.update("updateTheComent", rmap);	// mapper에서 "updateTheComent" id를 가지는 명령문에 rmap객체를 가지고 실행한다.
	}

	/*
	 * 후기에 작성자 이름을 띄우기 위해 실행되는 메서드이다.
	 */
	@Override
	public List<CustomerDTO> searchCustomerName(String customer_id) {
		return this.sqlSession.selectList("searchCustomerName", customer_id);	// mapper에서 "searchCustomerName" id를 가지는 명령문에 customer_id객체를 가지고 실행한다.
	}
}