package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.constants.Constant;
import com.test.constants.Constant.ESession;
import com.test.dto.CustomerDTO;
import com.test.dto.QnAboardDTO;
import com.test.service.QnAboardService;

@Controller
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class QnAboardController {
	
	@Autowired
	private QnAboardService qnaService;
	
	/*
	 * 메인화면에서 QnA버튼을 누르면 QnA리스트를 보여주는 메서드이다.
	 */
	@RequestMapping("/qnaPage")
	public String qnaPage (Model model) {
		List<QnAboardDTO> qnaDtoList = this.qnaService.selectQnaAllList();
		model.addAttribute("qnalist", qnaDtoList);
		return "qna/qna_list.tiles";
	}
	
	/*
	 * QnA리스트에서 글 작성 버튼을 누르면 작성 화면을 보여주는 메서드이다.
	 */
	@RequestMapping("/qnaWrite")
	public String qnaWrtie(Model model, HttpSession session) {
		if(Constant.eSession == ESession.eNull) {
			return "home/login.tiles";
		} else {
			return "qna/qna_write.tiles";
		}	
	}
	
	/*
	 * 글 등록 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping(value="/qnaAdd", method=RequestMethod.POST)
	public String qnaAdd(QnAboardDTO qnaDto, HttpSession session) {
		this.qnaService.insertQnaContents(qnaDto, (CustomerDTO)session.getAttribute("customer"));
		return "redirect:/qnaPage";
	}
	
	/*
	 * QnA리스트에서 자세히보기 위해서 한 줄을 클릭했을 경우
	 */
	@RequestMapping("/qnaDatailView")
	public String qnaDatailView(Model model, String qna_Id) {
		QnAboardDTO qnaDto = this.qnaService.selectQnaDetailView(qna_Id);
		model.addAttribute("qnaDetail", qnaDto);
		model.addAttribute("qna_Id", qna_Id);
		return "qna/qna_detailview.tiles";
	}
	
	@RequestMapping("/qnaModify_view")
	public ModelAndView qnaModify_view(ModelAndView mv, HttpSession session, String qna_Id) {
		return this.qnaService.selectQnaWriterId(mv, session, qna_Id);
	}
	
	@RequestMapping(value="/qna_content_update", method=RequestMethod.POST)
	public String qnaContentUpdate(Model model, QnAboardDTO qnaDto) {
		this.qnaService.updateQnaContent(qnaDto);
		return "redirect:/";
	}
}
