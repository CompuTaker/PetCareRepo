package com.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.ui.Model;
import com.test.dao.NoticeDAO;
import com.test.dto.Criteria;
import com.test.dto.NoticeDTO;
import com.test.dto.PageMaker;

@Service
public class NoticeServiceimpl implements NoticeService {

	@Autowired
	private NoticeDAO noticedao;

	@Override
	public void insertNotice(HashMap<String, Object> hmap) {
		this.noticedao.insertNotice(hmap);
	}

	@Override
	public List<NoticeDTO> noticeAllList(Criteria cri) {
		// TODO Auto-generated method stub
		return this.noticedao.noticeAllList(cri);
	}

	@Override
	public NoticeDTO noticeDetail(int notice_Index) {
		return this.noticedao.noticeDetail(notice_Index);
	}

	@Override
	public void addNoticeViewnum(int notice_Index) {
		this.noticedao.addNoticeViewnum(notice_Index);
	}

	@Override
	public int countNoticeList() {
		// TODO Auto-generated method stub
		return this.noticedao.countNoticeList();
	}
	
	@Override
	public List<NoticeDTO> selectNoticeByTerm(HttpServletRequest request, Model model, Criteria cri) {
		String term = request.getParameter("term");
		
		PageMaker pageMaker = new PageMaker();
		
		if(term != null) {
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.noticedao.countNoticeByTerm(term));
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("page",cri.getPage());
		map.put("perPageNum",cri.getPerPageNum());
		map.put("pageStart", cri.getPageStart());
		map.put("term", term);
		model.addAttribute("pageMaker",pageMaker);
		
		
		return this.noticedao.selectNoticeByTerm(map);
		}
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.noticedao.countNoticeList());
		
		model.addAttribute("pageMaekr", pageMaker);
		return this.noticedao.noticeAllList(cri);
	}

}
