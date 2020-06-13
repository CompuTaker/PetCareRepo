package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.Criteria;
import com.test.dto.QnAboardDTO;

@Repository
public class QnAboardDAOimpl implements QnAboardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertQnaContents(QnAboardDTO qnaDto) {
		this.sqlSession.insert("insertQnaContents", qnaDto);
	}

	@Override
	public List<String> selectQnaWriterNames(Criteria cri, int page) {
		int limit1 = 0;
		if(page == 1) {
			limit1 = 0;
		} else {
			limit1 = 19 + (page - 1);
		}
		 
		return this.sqlSession.selectList("selectQnaWriterNames", limit1);
	}

	@Override
	public List<QnAboardDTO> selectQnaAllList(Criteria cri) {
		List<QnAboardDTO> qnaDto = this.sqlSession.selectList("selectQnaAllList",cri);
		return qnaDto;
	}

	@Override
	public void addViewnum(int qnaId) {
		this.sqlSession.update("addViewnum", qnaId);

	}

	@Override
	public String selectQnaWriterId(String qna_Id) {	
		return this.sqlSession.selectOne("selectQnaWriterId", qna_Id);
	}

	@Override
	public void updateQnaContent(QnAboardDTO qnaDto) {
		this.sqlSession.update("updateQnaContent", qnaDto);
	}

	@Override
	public List<QnAboardDTO> selectQnaByTerm(Map<String,Object> map) {
		return this.sqlSession.selectList("listThisQnaByTerm", map);
	}

	@Override
	public QnAboardDTO listItsQna(String qna_Id) {
		return this.sqlSession.selectOne("listItsQna", qna_Id);
	}

	@Override
	public void insertTQnaComment(HashMap<String, Object> rmap) {
		this.sqlSession.update("updateQnaComment", rmap);
		
	}

	@Override
	public int countAllQnA() {
		return this.sqlSession.selectOne("countAllQnA");
	}

	@Override
	public int countQnAByTerm(String term) {
		return this.sqlSession.selectOne("countQnAByTerm", term);
	}
}
