package com.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public List<String> selectQnaWriterNames() {
		return this.sqlSession.selectList("selectQnaWriterNames");
	}

	@Override
	public List<QnAboardDTO> selectQnaAllList() {
		List<QnAboardDTO> qnaDto = this.sqlSession.selectList("selectQnaAllList");
		return qnaDto;
	}

	@Override
	public void addViewnum(int qnaId) {
		this.sqlSession.update("addViewnum", qnaId);
		
	}
}
