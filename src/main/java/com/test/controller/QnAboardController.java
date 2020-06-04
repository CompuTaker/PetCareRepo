package com.test.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CompanyDTO;
import com.test.dto.Criteria;
import com.test.dto.CustomerDTO;
import com.test.dto.NoticeDTO;
import com.test.dto.QnAboardDTO;
import com.test.dto.SuperuserDTO;
import com.test.service.QnAboardService;

@Controller
@SessionAttributes({ "customer", "company", "superuser" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class QnAboardController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QnAboardService qnaService;

	/*
	 * 메인화면에서 QnA버튼을 누르면 QnA리스트를 보여주는 메서드이다.
	 */
	@RequestMapping("/qnaPage")
	public String qnaPage(Model model, HttpServletRequest request,Criteria cri) {
		logger.info("/qnaPage - pna_list.jsp " + request.getMethod());
		List<QnAboardDTO> qnaDtoList = this.qnaService.selectQnaAllList(cri,model);
		model.addAttribute("qnalist", qnaDtoList);
		return "qna/qna_list.tiles";
	}

	/*
	 * QnA리스트에서 글 작성 버튼을 누르면 작성 화면을 보여주는 메서드이다.
	 */
	@RequestMapping("/qnaWrite")
	public String qnaWrtie(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("/qnaWrite - qna_write.jsp " + request.getMethod());
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		CompanyDTO company = (CompanyDTO) session.getAttribute("company");
		SuperuserDTO admin = (SuperuserDTO) session.getAttribute("superuser");

		// jgood
		if (customer == null && company == null && admin == null) {
			return "home/login.tiles";
		} else {
			return "qna/qna_write.tiles";
		} // jgood
		// jpoo // if(Constant.eSession == ESession.eNull) {
		// return "home/login.tiles";
		// } else {
		// return "qna/qna_write.tiles";
		// } // jpoo
	}

	/*
	 * 글 등록 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping(value = "/qnaAdd", method = RequestMethod.POST)
	public String qnaAdd(QnAboardDTO qnaDto, HttpSession session, HttpServletRequest request) {
		logger.info("/qnaAdd " + request.getMethod());
		this.qnaService.insertQnaContents(qnaDto, (CustomerDTO) session.getAttribute("customer"));
		return "redirect:/qnaPage";
	}

	/*
	 * QnA리스트에서 자세히보기 위해서 한 줄을 클릭했을 경우
	 */
	@RequestMapping("/qnaDatailView")
	public String qnaDatailView(Model model, String qna_Id, HttpServletRequest request,Criteria cri) {
		logger.info("/qnaDatailView - qna_detailview.jsp " + request.getMethod());
		QnAboardDTO qnaDto = this.qnaService.selectQnaDetailView(qna_Id,cri);
		model.addAttribute("qnaDetail", qnaDto);
		model.addAttribute("qna_Id", qna_Id);
		return "qna/qna_detailview.tiles";
	}

	@RequestMapping("/qnaModify_view")
	public ModelAndView qnaModify_view(ModelAndView mv, HttpSession session, String qna_Id,
			HttpServletRequest request) {
		logger.info("/qnaModify_view " + request.getMethod());
		return this.qnaService.selectQnaWriterId(mv, session, qna_Id, request);
	}

	@RequestMapping(value = "/qna_content_update", method = RequestMethod.POST)
	public String qnaContentUpdate(ModelAndView mv, QnAboardDTO qnaDto, HttpServletRequest request) {
		logger.info("/qna_content_update " + request.getMethod());
		qnaDto.setId(Integer.parseInt(request.getParameter("qna_Id")));
		this.qnaService.updateQnaContent(mv, qnaDto);
		return "redirect:/qnaPage";
	}
	
	@RequestMapping("/qna_reply")
	public String qna_reply(Model model, String qna_Id, String writer_name, HttpServletRequest request) {
		logger.info("/qna_reply.jsp " + request.getMethod());
		System.out.println(qna_Id);
		return this.qnaService.qna_reply(model, qna_Id, writer_name);
	}
	
	@RequestMapping("/qna_reply_ok")
	public String qna_reply_ok(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request, String qnaId) { 		
		logger.info("/qna_reply_ok "+request.getMethod());
		System.out.println(qnaId);
		return this.qnaService.qna_reply_ok(rmap, request, qnaId);
	}
	
	// 질문찾기 : 전체질문, 검색된질문
	@RequestMapping("/searchQnA")
	public String searchQnA(Model model, HttpServletRequest request,Criteria cri) {
		logger.info("/searchQnA " + request.getMethod());
		String url = "";
		List<QnAboardDTO> qnaList = this.qnaService.selectQnaByTerm(request,model,cri); 
		
		model.addAttribute("qnalist", qnaList); 
		url = "qna/qna_list.tiles";
		return url;
	}
}
