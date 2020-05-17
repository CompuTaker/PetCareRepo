package com.test.service;

import java.util.HashMap;
import java.util.List;

import com.test.dto.Criteria;
import com.test.dto.NoticeDTO;

public interface NoticeService {

	abstract public void insertNotice(HashMap<String, Object> hmap);

	abstract public List<NoticeDTO> noticeAllList(Criteria cri);

	abstract public NoticeDTO noticeDetail(int notice_Index);

	abstract public void addNoticeViewnum(int notice_Index);

	abstract public int countNoticeList();
}
