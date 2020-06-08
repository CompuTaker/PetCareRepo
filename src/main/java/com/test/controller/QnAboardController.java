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
@SessionAttributes({ "customer", "company", "superuser" }) // Model�뿉 ���옣�븳 媛믪쓣 http session�뿉 ���옣�븷 �닔 �엳寃� �빐二쇰뒗 Annotation
public class QnAboardController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private QnAboardService qnaService;

	/*
	 * 硫붿씤�솕硫댁뿉�꽌 QnA踰꾪듉�쓣 �늻瑜대㈃ QnA由ъ뒪�듃瑜� 蹂댁뿬二쇰뒗 硫붿꽌�뱶�씠�떎.
	 */
	@RequestMapping("/qnaPage")
	public String qnaPage(Model model, HttpServletRequest request,Criteria cri) {
		logger.info("/qnaPage - pna_list.jsp " + request.getMethod());
		List<QnAboardDTO> qnaDtoList = this.qnaService.selectQnaAllList(cri,model);
		model.addAttribute("qnalist", qnaDtoList);
		return "qna/qna_list.tiles";
	}

	/*
	 * QnA由ъ뒪�듃�뿉�꽌 湲� �옉�꽦 踰꾪듉�쓣 �늻瑜대㈃ �옉�꽦 �솕硫댁쓣 蹂댁뿬二쇰뒗 硫붿꽌�뱶�씠�떎.
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
	 * 湲� �벑濡� 踰꾪듉�쓣 �늻瑜대㈃ �떎�뻾�릺�뒗 硫붿꽌�뱶�씠�떎.
	 */
	@RequestMapping(value = "/qnaAdd", method = RequestMethod.POST)
	public String qnaAdd(QnAboardDTO qnaDto, HttpSession session, HttpServletRequest request,Model model) {
		logger.info("/qnaAdd " + request.getMethod());
		this.qnaService.insertQnaContents(qnaDto, (CustomerDTO) session.getAttribute("customer"));
		return "redirect:/searchQnA?page=1";
	}

	/*
	 * QnA由ъ뒪�듃�뿉�꽌 �옄�꽭�엳蹂닿린 �쐞�빐�꽌 �븳 以꾩쓣 �겢由��뻽�쓣 寃쎌슦
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
	
	// 吏덈Ц李얘린 : �쟾泥댁쭏臾�, 寃��깋�맂吏덈Ц
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
