package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.CompanyDAO;
import com.test.dao.ReservationDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.ReservationDTO;
import com.test.scheduler.ApiRestScheduler;


@Controller // Spring에 Controller 클래스라고 알려주는 Annotation
@SessionAttributes({ "customer", "company" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class CompanyController {

@Autowired
	private ApiRestScheduler apiRestScheduler;

	@Autowired
	private CompanyDAO companyDao;

	@Autowired
	private ReservationDAO reservationDAO;

	private boolean isCompanyIdChecked = false; // 기업 ID가 중복인지 아닌지 확인하는 Boolean
	private boolean isCompanyComNumChecked = false; // 기업 사업자등록번호가 중복인지 아닌지 확인하는 Boolean
	private boolean isCompanyOk = false; // 최종적으로 중복인지 아닌지 확인하는 Boolean

	/*
	 * 기업 고객이 company_signup.jsp에서 회원가입 버튼을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping("/company_signupDo")
	public ModelAndView company_signupDo(@RequestParam HashMap<String, Object> cmap) { // form에서 입력한 값을 HashMap으로 묶어서
																						// 가져옴
		ModelAndView ok = new ModelAndView("company/company_signup_ok.tiles"); // 중복체크까지 정상적으로 처리한 후 회원가입 버튼을 눌렀을 때 나올
																				// 화면과 함께 ModelAndView객체 생성
		ModelAndView redirect = new ModelAndView("company/company_signup.tiles"); // 중복체크를 하지 않았을 경우 나올 화면과 함께
																					// ModelAndView객체 생성
		redirect.addObject("message", "중복체크 해주세요."); // 중복체크를 하지 않았을 경우 띄울 메시지를 redirect ModelAndView에 저장

		if (isCompanyIdChecked && isCompanyComNumChecked) { // ID와 사업자등록번호 중복체크를 정상적으로 실행했을 경우
			if (isCompanyOk) { // 최종확인 Boolean도 true일 경우
				this.companyDao.insertTheCompany(cmap); // form에 입력한 값을 company테이블에 저장한다.
				return ok; // company_signup_ok.jsp화면을 띄운다.
			}
		}

		System.out.println("중복체크 안함");
		// 중복체크가 하나라도 안되었을 경우 모든 체크값을 false로 초기화하고 company_signup.jsp화면을 띄운다.
		isCompanyIdChecked = false;
		isCompanyComNumChecked = false;
		isCompanyOk = false;
		return redirect;
	}

	/*
	 * 기업 회원 가입 시 아이디가 중복되었는지 확인해주는 메서드
	 */
	@RequestMapping(value = "/company_checkcId", method = RequestMethod.GET)
	@ResponseBody
	public int comIdCheck(@RequestParam("company_Id") String company_Id) { // company_signup.jsp에서 name이 company_Id인 값을
																			// 가져와 String값으로 저장한다.

		isCompanyIdChecked = true; // 해당 메서드가 실행되었다는 것은 중복체크 버튼을 누른 것이기 때문에 true로 변경
		CompanyDTO company = companyDao.checkCompanyID(company_Id); // 해당 company_Id가 있는지 company테이블에서 확인해본다.
		if (company != null) { // company테이블에 존재하면
			isCompanyOk = false; // 아이디가 중복이므로 최종확인은 false
			return 1;
		}
		isCompanyOk = true; // company테이블에 존재하지 않으면 중복이 아니므로 true
		return 0;
	}

	/*
	 * 기업 회원 가입 시 사업자등록번호가 중복되었는지 확인해주는 메서드
	 */
	@RequestMapping(value = "/company_checkComNum", method = RequestMethod.GET)
	@ResponseBody
	public int comNumCheck(@RequestParam("company_Number") int company_Number) { // company_signup.jsp에서 name이
																					// company_Number인 값을 가져와 int값으로
																					// 저장한다.
		isCompanyComNumChecked = true; // 해당 메서드가 실행되었다는 것은 중복체크 버튼을 누른 것이기 때문에 true로 변경
		CompanyDTO company = companyDao.checkCompanyNumber(company_Number); // 해당 company_Number가 있는지 company테이블에서
																			// 확인해본다.
		if (company != null) { // company테이블에 존재하면
			isCompanyOk = false; // 사업자등록번호가 중복이므로 최종확인은 false
			return 1;
		}
		isCompanyOk = true; // company테이블에 존재하지 않으면 중복이 아니므로 true
		return 0;
	}

	/*
	 * 로그인창에 있는 아이디 찾기 중에서 기업회원 아이디 찾기를 눌렀을 경우
	 */
	
	@RequestMapping(value="/company_searchId", method = RequestMethod.POST)
	public ModelAndView company_searchId(ModelAndView mv, HttpServletRequest request) { // search_id.jsp에서 name값이 company_Number인 값이 int
		Map<String, String> company = new HashMap<String, String>();		
		company.put("company_Number", request.getParameter("company_Number"));																// company_Number에 저장된다.
		CompanyDTO companyDto = this.companyDao.searchCompanyID(company); // company_Number에 맞는 id가 있는지 company테이블에서 찾아본다.
		
		mv.addObject("company", companyDto); // com값이 있을 경우 model객체에 값을 저장한다. (Key:company,
															// value:com.getCompany_Id())
		mv.setViewName("company/company_show_id.tiles"); // company_show_id.jsp화면을 띄운다.
		return mv;
	}

	/*
	 * 로그인창에 있는 비밀번호 찾기 중에서 기업회원 비밀번호 찾기를 눌렀을 경우
	 */
	@RequestMapping(value = "/search_pw_company", method = RequestMethod.POST)
	public String search_pw_company(ModelAndView mv, HttpServletRequest request) {
			Map<String, String> company = new HashMap<String, String>();
			company.put("company_UserName",request.getParameter("company_UserName"));
						company.put("company_Id",request.getParameter("company_Id"));		
			company.put("company_UserPhoneNumber",request.getParameter("company_UserPhoneNumber"));		

		CompanyDTO companyDto = this.companyDao.searchCompanyPW(company);
		String passwordArr[] = companyDto.getCompany_Password().split(""); // 가져온 패스워드를 하나씩 뜯어서 배열로 저장한다.
		String password = ""; // 블러처리 후 패스워드를 저장할 변수

		for (int i = 0; i < passwordArr.length; i++) {
			if (i > 2) {
				password += "*"; // 패스워드의 앞 2자리만 보여주고 나머지는 *로 블러처리한다.
			} else {
				password += passwordArr[i];
			}
		}

		request.setAttribute("password", password); // company_show_pw.jsp에서 getAttribute로 값을 호출하기 위한 변수
		return "company/company_show_pw.tiles";
	}

	/*
	 * 기업회원이 로그인을 한 후 마이페이지로 이동하게 될 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_Profile")
	public ModelAndView profile(Model model, HttpSession session, ModelAndView mv) {
		if (session.getAttribute("company") != null) { // company session이 존재하는 경우
			try {
				CompanyDTO company = (CompanyDTO) session.getAttribute("company"); // company session을 DTO로 타입캐스팅을 하여
																					// company에 저장
				List<ReservationDTO> companyReserve = this.reservationDAO
						.listItsCompReservations(company.getCompany_Index()); // company값에서 Index를 가지고 예약된 값이 있는지
																				// Reservation테이블에서 확인한다.

				mv.addObject("reserve", companyReserve); // model객체에 가져온 예약리스트를 저장한다.
				mv.setViewName("company/company_Profile.tiles"); // 실행할 view인 companyprofile.jsp를 설정해준다.
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 기업고객 session이 존재하지 않는데
			// companyprofile requestMapping이 실행되었을 경우
			// 예외처리
		}
		return mv; // companyprofile.jsp
	}

	/*
	 * 기업회원이 정보를 수정하고자 할 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_modify")
	public ModelAndView company_modify(ModelAndView mv, HttpSession session) {
		CompanyDTO company = (CompanyDTO) session.getAttribute("company"); // company session을 가져온다.
		Map<String, String> loginInfo = new HashMap<String, String>();	
		loginInfo.put("id", company.getCompany_Id());
		loginInfo.put("pw", company.getCompany_Password());
		company = this.companyDao.listThisCompany(loginInfo); // company에서
		mv.addObject("company", company); // ModelAndView에 가져온 company 정보를 저장한다.
		mv.setViewName("company/company_modify.tiles"); // 실행시켜 줄 화면(company_modify.jsp)도 셋팅해준다.
		return mv;
	}

	/*
	 * 개인정보수정를 고치고 수정 버튼을 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_modify_ok")
	
	public String company_modify(@RequestParam HashMap<String, Object> cmap) { // form에서 입력한 정보를 HashMap으로 묶어서 가져온다.
		this.companyDao.updateCompanyInfo(cmap); // 가져온 cmap데이터를 기존 고객 데이터에 update시킨다.
		return "company/company_modify_ok.tiles";
	}

	/*
	 * 메인화면의 업체찾기에서 미용실 업체찾기를 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/beautyCompany")
	public String beautyCompany(Model model, HttpServletRequest request) {
		String url = "";
		List<CompanyDTO> beautyCompanyList = this.companyDao.listsCompany("미용실"); // company_Type이 미용실인 모든 회사를 가져온다.
		model.addAttribute("companyList", beautyCompanyList); // model에 가져온 회사 정보를 저장한다.
		url = "company/beauty_company.tiles"; // beauty_company.jsp화면을 띄워준다.
		return url;
	}

	/*
	 * 메인화면의 업체찾기에서 병원 업체찾기를 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/hospitalCompany")
	public String hospitalCompany(Model model, HttpServletRequest request) {
		String url = "";
		List<CompanyDTO> hospitalCompanyList = this.companyDao.listsCompany("병원"); // company_Type이 병원인 모든 회사를 가져온다.
		model.addAttribute("companyList", hospitalCompanyList); // model에 가져온 회사 정보를 저장한다.
		this.chatRedisAndAPI();
		url = "company/hospital_company.tiles"; // hospital_company.jsp화면을 띄워준다.
		return url;
	}

	/*
	 * 메인화면의 업체찾기에서 호텔 업체찾기를 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/hotelCompany")
	public String hotelCompany(Model model, HttpServletRequest request) {
		String url = "";
		List<CompanyDTO> hotelCompanyList = this.companyDao.listsCompany("호텔"); // company_Type이 호텔인 모든 회사를 가져온다.
		model.addAttribute("companyList", hotelCompanyList); // model에 가져온 회사 정보를 저장한다.
		url = "company/hotel_company.tiles"; // hotel_company.jsp화면을 띄워준다.
		return url;
	}

	/*
	 * 각 beautyCompany.jsp, hospitalCompany.jsp, hotelCompany.jsp파일에서 회사를 클릭해서 자세한
	 * 정보를 보려고 할 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_view")
	public String companyView(Model model, int companyIdx) { // 각 beautyCompany.jsp, hospitalCompany.jsp,
																// hotelCompany.jsp파일에서
																// company리스트를 클릭하면 companyIdx값이 넘어온다. (onclick href 참조)
		CompanyDTO company = this.companyDao.listThisCompany(companyIdx); // 넘어온 companyIdx값을 company테이블에 가서 값을 가져온다.
		model.addAttribute("thisCompany", company); // 가져온 company의 값을 model객체에 저장한다.
		return "company/company_view.tiles";
	}

public void chatRedisAndAPI() {
		// URLEncoder.encode("Q12A07", "UTF-8"); // 애견카페
		try {
			this.apiRestScheduler.batchProcess("Q12A07");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Public_API_Error -- batchProcess(Q12A07)");
			e.printStackTrace();
		}
		
		// System.out.println("RedisTemplate @ToStringWSH => " + this.redisTemplate.toString());
		// System.out.println("REDIS => " + this.valueOps.get("test"));
		// this.valueOps.set("settingtest", "well-done");
	}
}
