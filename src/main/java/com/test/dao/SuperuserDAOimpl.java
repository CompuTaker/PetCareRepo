package com.test.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.SuperuserDTO;

@Repository	//Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class SuperuserDAOimpl implements SuperuserDAO{

	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;

	/*
	 * 관리자가 로그인 했을 때 ID, PW가 superuser테이블에 존재하는지 확인하는 메서드이다.
	 */
	@Override
	public SuperuserDTO loginSuperuser(Map<String, String> superuser) {
		return sqlSession.selectOne("getSuperuser",superuser);			// mapper에서 "getSuperuser" id를 가지는 명령문에 superuser객체를 가지고 실행한다.
	}
}