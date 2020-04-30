package com.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.protobuf.TextFormat.ParseException;
import com.test.constants.Constant;
import com.test.constants.Constant.ESession;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.service.HomeService;
import com.test.controller.SocialLoginController;
import com.test.dao.CustomerDAO;

@Controller									//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({"customer", "company"})	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class HomeController {
	@Autowired
	private CustomerDAO customerDao;
	@Autowired	
	private HomeService homeService;
	@Autowired	
	private SocialLoginController socialLogin;
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	/*
	 * URL에 '/'과 'index'를 입력하면 실행되는 메서드이다.
	 * 따라서 메인화면(index.jsp)를 실행시켜준다.
	 */
	@RequestMapping({"/", "index"})
	public String index(Model model) {
		System.out.println("인덱스 페이지에용~");	
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
	public String signupDo(String flag) {					// signup.jsp에 hidden으로 숨겨진 flag값을 가져와서 어떤 회원가입 종류를 눌렀는지 확인한다.
		String url = "";
		if (flag.equals("user")) {							// flag가 user이면 고객 회원 가입 화면을 띄워준다.
			url = "customer/customer_Signup.tiles";
		} else if (flag.equals("comp")) {
			url = "company/company_Signup.tiles";			// flag가 comp이면 고객 회원 가입 화면을 띄워준다.
		} else {
			System.out.println("NoBody");	// 아무 flag가 없을 경우 예외처리
		}
		return url;
	}	
	
	// /*
	//  * 로그인 버튼을 누르면 실행되믄 메서드이다.
	//  */
	// @RequestMapping("/login")
	// public String login(Model model) {
	// 	System.out.println("로그인 화면");
	// 	return ""; // login.jsp
	// }
 
   @RequestMapping("/login")
   public String login(Model model, HttpSession session) {
      /* 카카오 로그인을 위한 코드 */
	  System.out.println(session);
      String kakaoUrl = socialLogin.getAuthorizationKakaoUrl(session);
	  String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session); //네이버아이디로 인증 URL을 생성하기 위하여 getAuthorizationUrl 메서드 호출한다.
      model.addAttribute("kakao_url", kakaoUrl);
		model.addAttribute("naver",naverAuthUrl); //login.jsp에서 네이버 로그인 버튼을 눌렀을 때 url 이동을 가능하게 해준다.
		
      return "home/login.tiles"; // login.jsp
   }
   //네이버 로그인 서공 시 callback 호출 메서드
	   //네이버 로그인 서공 시 callback 호출 메서드
   @RequestMapping("/naverCallback")
   public String naverCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session ) throws IOException, org.json.simple.parser.ParseException {
      OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state); //네이버아이디로그인 인증이 완료되면 code 파리미터가 전달되고, 이것을 통해 access token을 발급한다.
      apiResult = naverLoginBO.getUserProfile(oauthToken); //사용자 정보를 읽어온다.
      JSONParser parser = new JSONParser();
      JSONObject jsonObj = (JSONObject) parser.parse(apiResult); //String 형식인 apiResult 데이터를 JSON 형태로 바꿔준다.
      JSONObject response_obj = (JSONObject) jsonObj.get("response"); 

      String id = (String) response_obj.get("id"); //사용자 아이디를 가져온다.
      
      if(this.customerDao.checkCustomerID(id) == null) { //최초 로그인일 경우 회원가입을 진행한다. 
         String name = (String) response_obj.get("name"); //사용자 이름을 가져온다.
         String email = (String) response_obj.get("email"); //사용자 이메일을 가져온다.
         model.addAttribute("id", id); //model 객체에 id를 저장한다.
         model.addAttribute("nickname", name); //model 객체에 name을 저장한다.
         model.addAttribute("email", email); //model 객체에 email을 저장한다.
         System.out.println(response_obj);
         return "customer/customer_Signup.tiles"; //회원가입 페이지로 이동한다.
      }else {  
         CustomerDTO customer = this.customerDao.checkCustomerID(id); //아이디를 통해 고객 정보를 가져온다.
         model.addAttribute("customer", customer); //model 객체에 customer를 저장한다.
         Constant.eSession = ESession.eCustomer; // eSession의 값을 eCustomer로 변경해준다. 
         return "customer/customer_Profile.tiles"; //고객 마이페이지로 이동한다.
         
      }
   }
	/*
	 * 로그인에 값을 입력하고 로그인 버튼을 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/loginDo")
	public String loginDo(Model model, String id, String pw, HttpSession session, SessionStatus status) {	//login.jsp에서 name이 id, pw인 값을 가져온다.
		Map<String, String> loginInfo = new HashMap<String, String>();		// mapper에 변수값을 한 번에 전달하기 위해서 생성한 Map객체 
		loginInfo.put("id", id);											// Map객체에 Id값을 저장한다.
		loginInfo.put("pw", pw);											// Map객체에 PW값을 저장한다.
		
		//HomeService에 가서 고객인지 기업인지 확인한다. (Controller - Service - Dao)
		Object object = this.homeService.listThisMember(loginInfo);	
		String url = "";
		System.out.print(object);
	
		// Object가 CustomerDTO타입일 경우
		try {
			if(object==null) {
				status.setComplete();										// sessionAttribute를 초기화해준다.
				url = "login";		
			}else {
				url = "customer_Profile";						// 고객 마이페이지 화면을 띄워준다.
				model.addAttribute("customer", ((CustomerDTO)object));		// model객체에 customer테이블에서 가져온 customer값을 저장해준다.
				Constant.eSession = ESession.eCustomer;						// eSession의 값을 eCustomer로 변경해준다. (디폴트 = eNull)
			}
		// Object가 CompanyDTO타입일 경우
		} catch (ClassCastException e) {
			url = "company_Profile";						// 기업 마이페이지 화면을 띄워준다.
			model.addAttribute("company", ((CompanyDTO)object));		// model객체에 customer테이블에서 가져온 customer값을 저장해준다.
			Constant.eSession = ESession.eCompany;						// eSession의 값을 eCompany로 변경해준다. (디폴트 = eNull)	
		// Object가 Null인 경우 = 정상적인 경우 X
									// 로그인 화면을 띄워준다.			
		} catch (NullPointerException e) {
			status.setComplete();										// sessionAttribute를 초기화해준다.
			url = "login";												// 로그인 화면을 띄워준다.			
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
		return "redirect:/";
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
				url = "customer_Profile";			// 고객 마이페이지를 띄워준다.
				
			}else if(Constant.eSession == ESession.eCompany) {	// eSession = eCompany인 경우
				url = "company_Profile";			// 기업 마이페이지를 띄워준다.
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
