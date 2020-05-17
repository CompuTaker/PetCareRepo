package com.test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.NoticeDAO;
import com.test.dto.Criteria;
import com.test.dto.NoticeDTO;

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

}
