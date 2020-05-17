package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CustomerDTO;
import com.test.dto.QnAboardDTO;

public interface QnAboardService {

	abstract public List<QnAboardDTO> selectQnaAllList();

	abstract public void insertQnaContents(QnAboardDTO qnaDto, CustomerDTO customer);

	abstract public QnAboardDTO selectQnaDetailView(String qna_Id);

	abstract public ModelAndView selectQnaWriterId(ModelAndView mv, HttpSession session, String qna_Id);

	abstract public void updateQnaContent(QnAboardDTO qnaDto);

	abstract public List<QnAboardDTO> selectQnaByTerm(HttpServletRequest request);

}
