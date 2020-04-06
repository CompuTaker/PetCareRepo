package com.test.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOimpl implements MessageDAO {
	
	@Autowired // root-context.xml 참고
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int sendTheMessage(int chatterId) {
		// TODO Auto-generated method stub
		return this.sqlSession.insert("sendTheMessage", chatterId);
	}
	
}
