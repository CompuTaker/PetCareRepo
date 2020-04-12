package com.test.dao;

import java.util.HashMap;
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
	public SuperuserDTO loginSuperuser(String superuser_username, String superuser_password) {
		// 추후에 controller로 이동
		Map<String, String> superuser = new HashMap<String, String>();	// mapper에 넘어온 변수들을 한 번에 보내기 위해 생성한 Map객체
		superuser.put("username", superuser_username);					// Map객체에 아이디를 저장한다.
		superuser.put("password", superuser_password);					// Map객체에 비밀번호를 저장한다.
		return sqlSession.selectOne("getSuperuser",superuser);			// mapper에서 "getSuperuser" id를 가지는 명령문에 superuser객체를 가지고 실행한다.
	}
}