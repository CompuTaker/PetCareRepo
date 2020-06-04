package com.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.test.dao.CustomerDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.Criteria;
import com.test.dto.CustomerDTO;
import com.test.dto.NoticeDTO;
import com.test.dto.QnAboardDTO;
import com.test.dto.ReviewDTO;
import com.test.dto.SuperuserDTO;
import com.test.service.CompanyService;
import com.test.service.HomeService;
import com.test.service.NoticeService;
import com.test.service.QnAboardService;
import com.test.service.ReviewService;

@Controller // Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company", "superuser" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private HomeService homeService;
	@Autowired
	private SocialLoginController socialLogin;
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	public ReviewService reviewService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private QnAboardService qnaBoardService;
	@Autowired
	private NoticeService noticeService;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	/*
	 * URL에 '/'과 'index'를 입력하면 실행되는 메서드이다. 따라서 메인화면(index.jsp)를 실행시켜준다.
	 */
	@RequestMapping({ "/", "index" })
	public String index(Model model, HttpServletRequest request) {
		logger.info(" / - index.jsp " + request.getMethod());
		return "index"; // index.jsp
	}

	/*
	 * 로그인창에 회원가입 버튼을 누르면 실행되는 메서드이다. 기업 회원가입, 고객 회원가입 중 어떤 회원가입을 할 지 화면을 띄워준다.
	 */
	@RequestMapping("/signup")
	public String signup(Model model, HttpServletRequest request) {
		logger.info("/signup - signup.jsp " + request.getMethod());
		return "home/signup.tiles"; // signup.jsp
	}

	/*
	 * 기업, 고객 회원가입 중 하나를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/signupDo")
	public String signupDo(String flag, HttpServletRequest request) { // signup.jsp에 hidden으로 숨겨진 flag값을 가져와서 어떤 회원가입
																		// 종류를 눌렀는지 확인한다.
		String url = "";
		logger.info(" /signupDo " + request.getMethod());
		if (flag.equals("user")) { // flag가 user이면 고객 회원 가입 화면을 띄워준다.
			logger.info(" /customer_Signup " + request.getMethod());
			url = "customer/customer_Signup.tiles";
		} else if (flag.equals("comp")) {
			logger.info(" /company_Signup " + request.getMethod());
			url = "company/company_Signup.tiles"; // flag가 comp이면 고객 회원 가입 화면을 띄워준다.
		} else {
			logger.info(" /signupDo , [NoBody] " + request.getMethod());
		}
		return url;
	}

	@RequestMapping("/introPage")
	public String introPage() {

		return "home/introPage.tiles";
	}
	// /*
	// * 로그인 버튼을 누르면 실행되믄 메서드이다.
	// */
	// @RequestMapping("/login")
	// public String login(Model model) {
	// System.out.println("로그인 화면");
	// return ""; // login.jsp
	// }

	@RequestMapping("/login")
	public String login(Model model, HttpSession session, HttpServletRequest request) {
		logger.info(" login.jsp " + request.getMethod());
		/* 카카오 로그인을 위한 코드 */
		String kakaoUrl = socialLogin.getAuthorizationKakaoUrl(session);
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session); // 네이버아이디로 인증 URL을 생성하기 위하여 getAuthorizationUrl
																			// 메서드 호출한다.
		model.addAttribute("kakao_url", kakaoUrl);
		model.addAttribute("naver", naverAuthUrl); // login.jsp에서 네이버 로그인 버튼을 눌렀을 때 url 이동을 가능하게 해준다.

		return "home/login.tiles"; // login.jsp
	}

	// 네이버 로그인 서공 시 callback 호출 메서드
	// 네이버 로그인 서공 시 callback 호출 메서드
	@RequestMapping("/naverCallback")
	public String naverCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, org.json.simple.parser.ParseException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state); // 네이버아이디로그인 인증이 완료되면 code
																							// 파리미터가 전달되고, 이것을 통해 access
																							// token을 발급한다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // 사용자 정보를 읽어온다.
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(apiResult); // String 형식인 apiResult 데이터를 JSON 형태로 바꿔준다.
		JSONObject response_obj = (JSONObject) jsonObj.get("response");

		String id = (String) response_obj.get("id"); // 사용자 아이디를 가져온다.

		if (this.customerDao.checkCustomerID(id) == null) { // 최초 로그인일 경우 회원가입을 진행한다.
			String name = (String) response_obj.get("name"); // 사용자 이름을 가져온다.
			String email = (String) response_obj.get("email"); // 사용자 이메일을 가져온다.
			model.addAttribute("id", id); // model 객체에 id를 저장한다.
			model.addAttribute("nickname", name); // model 객체에 name을 저장한다.
			model.addAttribute("email", email); // model 객체에 email을 저장한다.
			return "customer/customer_Signup.tiles"; // 회원가입 페이지로 이동한다.
		} else {
			CustomerDTO customer = this.customerDao.checkCustomerID(id); // 아이디를 통해 고객 정보를 가져온다.
			model.addAttribute("customer", customer); // model 객체에 customer를 저장한다.
			// jpoo // Constant.eSession = ESession.eCustomer; // eSession의 값을 eCustomer로
			// 변경해준다.
			return "customer/customer_Profile.tiles"; // 고객 마이페이지로 이동한다.

		}
	}

	/*
	 * 로그인에 값을 입력하고 로그인 버튼을 눌렀을 경우 실행되는 메서드이다. login.jsp에서 name이 id,pw인 값을 가져온다.
	 */
	@RequestMapping(value = "/loginDo", method = RequestMethod.POST)
	public Object loginDo(Model model, String id, String pw, HttpSession session, SessionStatus status,
			HttpServletRequest request) {

		logger.info(" /loginDo " + request.getMethod());
		Map<String, String> loginInfo = new HashMap<String, String>(); // mapper에 변수값을 한 번에 전달하기 위해서 생성한 Map객체
		loginInfo.put("id", id); // Map객체에 Id값을 저장한다.
		loginInfo.put("pw", pw); // Map객체에 PW값을 저장한다.

		// HomeService에 가서 고객인지 기업인지 확인한다. (Controller - Service - Dao)
		Object object = this.homeService.listThisMember(loginInfo);
		String url = "";
		try {

			if (object == null) {
				// sessionAttribute를 초기화해준다.
				status.setComplete();
				model.addAttribute("message", 1);
				logger.info(" /login " + request.getMethod());
				url = "login";
			} else if (object instanceof CustomerDTO) {
				// 고객 마이페이지 화면을 띄워준다.
				logger.info(" /customer_Profile " + request.getMethod());
				url = "customer_Profile";
				// model객체에 customer테이블에서 가져온 customer값을 저장해준다.
				model.addAttribute("customer", ((CustomerDTO) object));
				// eSession의 값을 eCustomer로 변경해준다. (디폴트 = eNull)
				// jpoo // Constant.eSession = ESession.eCustomer;
			} else if (object instanceof CompanyDTO) {
				logger.info(" /company_Profile " + request.getMethod());
				// 기업 마이페이지 화면을 띄워준다.
				url = "company_Profile";
				// model객체에 customer테이블에서 가져온 customer값을 저장해준다.
				model.addAttribute("company", ((CompanyDTO) object));
				// eSession의 값을 eCompany로 변경해준다. (디폴트 = eNull)
				// jpoo // Constant.eSession = ESession.eCompany;
			} else if (object instanceof SuperuserDTO) {
				logger.info(" /admin_drop " + request.getMethod());
				// 기업 마이페이지 화면을 띄워준다.
				url = "admin_drop";
				// model객체에 customer테이블에서 가져온 customer값을 저장해준다.
				model.addAttribute("superuser", ((SuperuserDTO) object));
				// eSessio의 값을 eSuperuser로 변경해준다. (디폴트 = eNull)
				// jpoo // Constant.eSession = ESession.eSuperuser;
			}
			// Object가 CompanyDTO타입일 경우
		} catch (ClassCastException e) {
			// Object가 Null인 경우 = 정상적인 경우 X
			// 로그인 화면을 띄워준다.
		} catch (NullPointerException e) {
			// sessionAttribute를 초기화해준다.
			status.setComplete();
			// 로그인 화면을 띄워준다.
			logger.info(" /login ");
			url = "login";
		}
		return "redirect:" + url;

	}

	/*
	 * 마이페이지에서 로그아웃 버튼을 클릭했을 경우 실행되는 메서드
	 */
	@RequestMapping("/logout")
	public String logout(Model model, SessionStatus status, HttpServletRequest request) {
		logger.info("/logout " + request.getMethod());
		// jpoo // Constant.eSession = ESession.eNull; // eSession의 값을 null로 초기화
		status.setComplete(); // sessionAttribute를 초기화해준다.
		return "redirect:/";
	}

	/*
	 * 상단의 Login버튼 or My page 버튼을 눌렀을 경우 화면 매핑을 다르게 해주기 위한 메서드
	 */
	@RequestMapping("/loginOrProfile")
	public String loginOrProfile(Model model, HttpSession session, HttpServletRequest request) {
		logger.info("/loginOrProfile , GET");
		String url = "";

		// jpoo // logger.info(Constant.eSession.toString());

		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		CompanyDTO company = (CompanyDTO) session.getAttribute("company");
		SuperuserDTO admin = (SuperuserDTO) session.getAttribute("superuser");

		if (customer == null && company == null && admin == null) { // eSession = eNull인 경우
			url = "login"; // 로그인 화면을 띄워준다.
		} else { // 둘 다 아닐경우
			// jpoo // if (Constant.eSession == ESession.eCustomer) { // eSession =
			// eCustomer인 경우
			// url = "customer_Profile"; // 고객 마이페이지를 띄워준다.
			// logger.info("/customer_Profile " + request.getMethod());
			// } else if (Constant.eSession == ESession.eCompany) { // eSession = eCompany인
			// 경우
			// url = "company_Profile"; // 기업 마이페이지를 띄워준다.
			// logger.info("/company_Profile " + request.getMethod());
			// } else if (Constant.eSession == ESession.eSuperuser) { // eSession =
			// eCompany인 경우
			// url = "admin_drop"; // 기업 마이페이지를 띄워준다.
			// logger.info("/admin_drop " + request.getMethod());
			// } // jpoo
			// jpgood
			if (customer == null && company == null && admin == null) { // eSession = eNull인 경우
				url = "login"; // 로그인 화면을 띄워준다.
			} else { // 둘 다 아닐경우
				if (customer != null) { // eSession = eCustomer인 경우
					url = "customer_Profile"; // 고객 마이페이지를 띄워준다.
					logger.info("/customer_Profile " + request.getMethod());
				} else if (company != null) { // eSession = eCompany인 경우
					url = "company_Profile"; // 기업 마이페이지를 띄워준다.
					logger.info("/company_Profile " + request.getMethod());
				} else if (admin != null) { // eSession = eCompany인 경우
					url = "admin_drop"; // 기업 마이페이지를 띄워준다.
					logger.info("/admin_drop " + request.getMethod());
				}
			}
			// jpgood
		}
		return "redirect:" + url;
	}

	/*
	 * 로그인 창에서 아이디 찾기 버튼을 눌렀을 경우 실행되는 메서드
	 */
	@RequestMapping("/search_id")
	public String searchID(HttpServletRequest request) {
		logger.info("/search_id - search_id.jsp " + request.getMethod());
		return "home/search_id.tiles";
	}

	/*
	 * 로그인 창에서 비밀번호 찾기 버튼을 눌렀을 경우 실행되는 메서드
	 */
	@RequestMapping("/search_pw")
	public String search_pw(HttpServletRequest request) {
		logger.info("/search_pw - search_pw.jsp " + request.getMethod());
		return "home/search_pw.tiles";
	}

	// 통합검색
	@RequestMapping("/searchByTerm")
	public String searchByTerm(Model model, HttpServletRequest request, Criteria cri) {
		String url = "";
		logger.info("/searchByTerm - search_all.jsp " + request.getMethod());
		List<CompanyDTO> companyList = this.companyService.listsAllCompany(model, request, cri); // 회사를 가져온다.
		List<ReviewDTO> reviewList = this.reviewService.listsAllReview(model, request, cri); // 리뷰를 가져온다.
		List<QnAboardDTO> qnaList = this.qnaBoardService.selectQnaByTerm(request,model, cri);
		List<NoticeDTO> noticeList = this.noticeService.selectNoticeByTerm(request, model, cri);

		model.addAttribute("companyList", companyList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("qnalist", qnaList);
		model.addAttribute("noticelist", noticeList);

		url = "home/search_all.tiles"; // 화면을 띄워준다.
		return url;
	}
}
