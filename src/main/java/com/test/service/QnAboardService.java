package com.test.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.Criteria;
import com.test.dto.CustomerDTO;
import com.test.dto.QnAboardDTO;

public interface QnAboardService {

	abstract public List<QnAboardDTO> selectQnaAllList(Criteria cri,Model model);

	abstract public void insertQnaContents(QnAboardDTO qnaDto, CustomerDTO customer);

	abstract public QnAboardDTO selectQnaDetailView(String qna_Id);

	abstract public ModelAndView selectQnaWriterId(ModelAndView mv, HttpSession session, String qna_Id, HttpServletRequest request);

	abstract public void updateQnaContent(ModelAndView mv, QnAboardDTO qnaDto);

	abstract public List<QnAboardDTO> selectQnaByTerm(HttpServletRequest request);

	public abstract String qna_reply(Model model, String qna_Id, String writer_name);

	public abstract String qna_reply_ok(HashMap<String, Object> rmap, HttpServletRequest request, String qna_Id);

	

}
