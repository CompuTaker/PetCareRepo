package com.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.dao.SuperuserDAO;
import com.test.dto.SuperuserDTO;

@Service
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class SuperuserServiceImpl implements SuperuserService{
	
	@Autowired
	private SuperuserDAO superuserDao;

	@Override
	public String getAdmin(HttpServletRequest request) {
		Map<String, String> superuser = new HashMap<String, String>();		// mapper에 넘어온 변수들을 한 번에 보내기 위해 생성한 Map객체
		superuser.put("username", request.getParameter("username"));		// Map객체에 아이디를 저장한다.
		superuser.put("password", request.getParameter("password"));		// Map객체에 비밀번호를 저장한다.
			
		SuperuserDTO superuserDto = superuserDao.loginSuperuser(superuser);	// superuser테이블에 해당 ID, PW가 있는지 확인해본다.
							  
		if(superuser != null) {												// superuser가 존재하는 경우
			HttpSession session = request.getSession();						// session을 가져온다.
			 session.setAttribute("superuser", superuserDto);				// session의 속성에 superuser를 붙여준다.
			return "admin/admin_drop.tiles";											// 탈퇴회원관리 화면을 띄워준다.
		} 
		
		return "admin/admin_login.tiles";									// superuser가 없는 경우 로그인 화면을 띄워준다.
		
	}

}
