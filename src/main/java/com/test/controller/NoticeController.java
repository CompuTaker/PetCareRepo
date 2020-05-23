package com.test.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.dto.Criteria;
import com.test.dto.NoticeDTO;
import com.test.dto.PageMaker;
import com.test.service.NoticeService;

@Controller
@SessionAttributes({ "customer", "company", "superuser" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class NoticeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NoticeService noticeService;

	// 공지사항 작성 버튼 눌렀을 경우
	@RequestMapping("/notice_write")
	public String noticeWrite() {
		logger.info("/notice_write , GET");
		return "notice/notice_write.tiles";
	}

	// 공지사항 추가
	@RequestMapping("/notice_add")
	public String noticeAdd(@RequestParam HashMap<String, Object> hmap) {
		logger.info("/notice_add , POST");
		this.noticeService.insertNotice(hmap);
		return "redirect:/noticePage";
	}

	// 공지사항 상세보기
	@RequestMapping("/noticeDetailView")
	public String noticeDetailView(Model model, @RequestParam("notice_Index") int notice_Index) {
		logger.info("/notice_detailview , GET");
		NoticeDTO notice = this.noticeService.noticeDetail(notice_Index);
		this.noticeService.addNoticeViewnum(notice_Index);
		model.addAttribute("noticeDetail", notice);
		return "notice/notice_detailview.tiles";
	}

	// 공지사항을 누르면 공지사항 목록이 나온다.
	@RequestMapping("/noticePage")
	public String noticePage(Model model, Criteria cri) {
		logger.info("/noticePage , GET");
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.noticeService.countNoticeList());

		List<NoticeDTO> noticeList = this.noticeService.noticeAllList(cri);
		model.addAttribute("noticelist", noticeList);
		model.addAttribute("pageMaker", pageMaker);

		return "notice/notice_list.tiles";
	}
}
