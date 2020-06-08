package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.Criteria;
import com.test.dto.NoticeDTO;

public interface NoticeDAO {

	abstract public void insertNotice(HashMap<String, Object> hmap);

	abstract public List<NoticeDTO> noticeAllList(Criteria cri);

	abstract public NoticeDTO noticeDetail(int notice_Index);

	abstract public void addNoticeViewnum(int notice_Index);

	abstract public int countNoticeList();

	public abstract List<NoticeDTO> selectNoticeByTerm(Map<String, Object> map);
	
	public abstract int countNoticeByTerm(String term);
}