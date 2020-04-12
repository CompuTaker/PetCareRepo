package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.test.constants.Constant;
import com.test.constants.Constant.ESession;
import com.test.dao.CompanyDAO;
import com.test.dao.CustomerDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;

@Controller									//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({"customer", "company"})	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class HomeController {
	
	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private CompanyDAO companyDao;
	
	/*
	 * URL에 '/'과 'index'를 입력하면 실행되는 메서드이다.
	 * 따라서 메인화면(index.jsp)를 실행시켜준다.
	 */
	@RequestMapping({"/", "/index"})
	public String index(Model model) {
		System.out.println("/ or index");
		return "index"; // index.jsp
	}
	
	/*
	 * 로그인창에 회원가입 버튼을 누르면 실행되는 메서드이다.
	 * 기업 회원가입, 고객 회원가입 중 어떤 회원가입을 할 지 화면을 띄워준다.
	 */
	@RequestMapping("/signup")
	public String signup(Model model) {
		return "home/signup.tiles"; // signup.jsp
	}

	/*
	 * 기업, 고객 회원가입 중 하나를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/signupDo")
	public String signupDo(String flag) {	// signup.jsp에 hidden으로 숨겨진 flag값을 가져와서 어떤 회원가입 종류를 눌렀는지 확인한다.
		String url = "";
		if (flag.equals("user")) {			// flag가 user이면 고객 회원 가입 화면을 띄워준다.
			url = "customer/customer_Signup.tiles";
		} else if (flag.equals("comp")) {
			url = "company/company_Signup.tiles";			// flag가 comp이면 고객 회원 가입 화면을 띄워준다.
		} else {
			System.out.println("NoBody");	// 아무 flag가 없을 경우 예외처리
		}
		return url;
	}	
	
	/*
	 * 로그인 버튼을 누르면 실행되믄 메서드이다.
	 */
	@RequestMapping("/login")
	public String login(Model model) {
		return "home/login.tiles"; // login.jsp
	}

	/*
	 * 로그인에 값을 입력하고 로그인 버튼을 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/loginDo")
	public String loginDo(Model model, String id, String pw, HttpSession session, SessionStatus status) {	//login.jsp에서 name이 id, pw인 값을 가져온다.
		//id와 pw 값을 가지고 customer, company테이블에 넣어서 null인지 아닌지를 판별해본다.
		CustomerDTO customer = this.customerDao.listThisCustomer(id, pw);	
		CompanyDTO company = this.companyDao.listThisCompany(id, pw);
		String url = "";

		if (customer != null && company == null) { 			// customer만 null이 아닐 경우 = 고객 로그인
			url = "customer_Profile";						// 고객 마이페이지 화면을 띄워준다.
			model.addAttribute("customer", customer);		// model객체에 customer테이블에서 가져온 customer값을 저장해준다.
			Constant.eSession = ESession.eCustomer;			// eSession의 값을 eCustomer로 변경해준다. (디폴트 = eNull)
			
		} else if (customer == null && company != null) { 	// company만 null이 아닐 경우 = 기업 로그인
			url = "company_Profile";							// 기업 마이페이지 화면을 띄워준다.
			model.addAttribute("company", company);			// model객체에 customer테이블에서 가져온 customer값을 저장해준다.
			Constant.eSession = ESession.eCompany;			// eSession의 값을 eCompany로 변경해준다. (디폴트 = eNull)
			
		} else if (customer != null && company != null) {	// 둘 다 null이 아닌 경우 예외처리
			status.setComplete();							// sessionAttribute를 초기화해준다.
			url = "index";										// 메인화면을 띄워준다.
			
		} else {											// 둘 다 null인 경우 예외처리
			url = "login";									// 로그인 화면을 띄워준다.
		}

		return "redirect:" + url;
	}

	/*
	 * 마이페이지에서 로그아웃 버튼을 클릭했을 경우 실행되는 메서드
	 */
	@RequestMapping("/logout")
	public String logout(Model model, SessionStatus status) {
		Constant.eSession = ESession.eNull;	//eSession의 값을 null로 초기화
		status.setComplete();				// sessionAttribute를 초기화해준다.
		return "/index";
	}
	
	/*
	 * 상단의 Login버튼 or My page 버튼을 눌렀을 경우 화면 매핑을 다르게 해주기 위한 메서드
	 */
	@RequestMapping("/loginOrProfile")
	public String loginOrProfile(Model model, HttpSession session) {
		String url = "";
		if(Constant.eSession == ESession.eNull) {				// eSession = eNull인 경우
			url = "login";										// 로그인 화면을 띄워준다.
			
		}else if(Constant.eSession == ESession.eError){			// eSession = eError인 경우
			url = "/";											// 메인 화면을 띄워준다.
			
		}else {													// 둘 다 아닐경우
			if(Constant.eSession == ESession.eCustomer){		// eSession = eCustomer인 경우
				url = "customer_Profile";						// 고객 마이페이지를 띄워준다.
				
			}else if(Constant.eSession == ESession.eCompany) {	// eSession = eCompany인 경우
				url = "company_Profile";							// 기업 마이페이지를 띄워준다.
			}
		}
		return "redirect:" + url;
	}
	
	/*
	 * 로그인 창에서 아이디 찾기 버튼을 눌렀을 경우 실행되는 메서드
	 */
	@RequestMapping("/search_id")
	public String searchID() {
		return "home/search_id.tiles";
	}
	
	/*
	 * 로그인 창에서 비밀번호 찾기 버튼을 눌렀을 경우 실행되는 메서드
	 */
	@RequestMapping("/search_pw")
	public String search_pw() {
		return "home/search_pw.tiles";
	}
	
	
}
