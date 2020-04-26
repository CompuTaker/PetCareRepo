package com.test.service;

import java.util.List;

import com.test.dto.CustomerDTO;
import com.test.dto.QnAboardDTO;

public interface QnAboardService {
	
	abstract public List<QnAboardDTO> selectQnaAllList();
	
	abstract public void insertQnaContents(QnAboardDTO qnaDto, CustomerDTO customer);

	abstract public QnAboardDTO selectQnaDetailView(String qna_Id);	
}
