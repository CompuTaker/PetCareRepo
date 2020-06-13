package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.Criteria;
import com.test.dto.QnAboardDTO;

public interface QnAboardDAO {

	abstract public void insertQnaContents(QnAboardDTO qnaDto);

	abstract public List<QnAboardDTO> selectQnaAllList(Criteria cri);

	abstract public List<String> selectQnaWriterNames(Criteria cri, int page);

	abstract public void addViewnum(int qnaId);

	abstract public String selectQnaWriterId(String qna_Id);

	abstract public void updateQnaContent(QnAboardDTO qnaMap);

	abstract public List<QnAboardDTO> selectQnaByTerm(Map<String,Object> map);

	public abstract QnAboardDTO listItsQna(String qna_Id);

	public abstract void insertTQnaComment(HashMap<String, Object> rmap);
	
	public abstract int countAllQnA();
	
	
	public abstract int countQnAByTerm(String term);
}
