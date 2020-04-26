package com.test.dao;

import java.util.List;

import com.test.dto.QnAboardDTO;


public interface QnAboardDAO {

	abstract public void insertQnaContents(QnAboardDTO qnaDto);

	abstract public List<QnAboardDTO> selectQnaAllList();

	abstract public List<String> selectQnaWriterNames();

	abstract public void addViewnum(int qnaId);

}
