package com.test.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.Criteria;
import com.test.dto.NoticeDTO;

@Repository
public class NoticeDAOimpl implements NoticeDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertNotice(HashMap<String, Object> hmap) {
		this.sqlSession.insert("noticeWrite", hmap);
	}

	@Override
	public List<NoticeDTO> noticeAllList(Criteria cri) {
		return this.sqlSession.selectList("noticeAllList", cri);
	}

	@Override
	public NoticeDTO noticeDetail(int notice_Index) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("noticeDetail", notice_Index);
	}

	@Override
	public void addNoticeViewnum(int notice_Index) {
		this.sqlSession.update("addNoticeViewnum", notice_Index);
	}

	@Override
	public int countNoticeList() {
		return this.sqlSession.selectOne("countNoticeList");
	}

}
