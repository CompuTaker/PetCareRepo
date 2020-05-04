package com.test.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.dao.CompanyDAO;
import com.test.dao.CustomerDAO;
import com.test.dao.SuperuserDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.SuperuserDTO;

@Service
@SessionAttributes({ "customer", "company" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class HomeServiceImpl implements HomeService {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private CompanyDAO companyDao;

	@Autowired
	private SuperuserDAO superuserDao;

	@Override
	public Object listThisMember(Map<String, String> loginInfo) {
		// id와 pw 값을 가지고 customer, company테이블에 넣어서 null인지 아닌지를 판별해본다.
		CustomerDTO customer = this.customerDao.listThisCustomer(loginInfo);
		CompanyDTO company = this.companyDao.listThisCompany(loginInfo);
		SuperuserDTO admin = this.superuserDao.loginSuperuser(loginInfo);
		System.out.println(admin);
		// customer만 null이 아닐 경우 = 고객 로그인
		if (customer != null && company == null && admin == null) {
			return customer;
		}

		// company만 null이 아닐 경우 = 기업 로그인
		else if (customer == null && company != null && admin == null) {
			return company;
		} else if (customer == null && company == null && admin != null) {
			return admin;
		}

		// 둘 다 null이 아닌 경우 예외처리
		else if (customer != null && company != null && company != null) {
			return null;
		}

		// 둘 다 null인 경우 예외처리
		else {
			return null;
		}
	}
}
