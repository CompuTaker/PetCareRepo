package com.test.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.SuperuserDTO;

@Repository
public class SuperuserDAOimpl implements SuperuserDAO{

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public SuperuserDTO loginSuperuser(String superuser_username, String superuser_password) {
		Map<String, String> superuser = new HashMap<String, String>();
		superuser.put("username", superuser_username);
		superuser.put("password", superuser_password);
		
		return sqlSession.selectOne("getSuperuser", superuser);
	}
}