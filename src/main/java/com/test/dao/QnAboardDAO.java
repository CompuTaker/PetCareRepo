package com.test.dao;

import java.util.HashMap;
import java.util.List;

import com.test.dto.Criteria;
import com.test.dto.QnAboardDTO;

public interface QnAboardDAO {

	abstract public void insertQnaContents(QnAboardDTO qnaDto);

	abstract public List<QnAboardDTO> selectQnaAllList(Criteria cri);

	abstract public List<String> selectQnaWriterNames(Criteria cri);

	abstract public void addViewnum(int qnaId);

	abstract public String selectQnaWriterId(String qna_Id);

	abstract public void updateQnaContent(QnAboardDTO qnaDto);

	abstract public List<QnAboardDTO> selectQnaByTerm(String term);

	public abstract QnAboardDTO listItsQna(String qna_Id);

	public abstract void insertTQnaComment(HashMap<String, Object> rmap);
	
	public abstract int countAllQnA();
}
