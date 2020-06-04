
package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CompanyDTO;
import com.test.dto.Criteria;
import com.test.dto.PageMaker;
import com.test.service.CompanyService;
import com.test.vo.CompanyVO;

@Controller // Spring에 Controller 클래스라고 알려주는 Annotation
@SessionAttributes({ "customer", "company" }) // Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class CompanyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CompanyService companyService;

	/*
	 * 기업 고객이 company_signup.jsp에서 회원가입 버튼을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping(value = "/company_signupDo", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public ModelAndView company_signupDo(@Valid CompanyVO vo, BindingResult result, @RequestParam HashMap<String, Object> cmap,
			MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) { // form에서 입력한 값을
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			ModelAndView redirect = new ModelAndView("company/company_Signup.tiles");
			redirect.addObject("message", "입력값을 확인 해주세요.");
			return redirect;
//			return this.customerService.customer_signupDo(multipartHttpServletRequest, null);
		} // HashMap으로묶어서가져옴																		// HashMap으로 묶어서 가져옴

		logger.info("/company_signupDo " + request.getMethod() + " user: " + request.getSession());
		return this.companyService.company_signupDo(multipartHttpServletRequest, cmap);
	}

	/*
	 * 기업 회원 가입 시 아이디가 중복되었는지 확인해주는 메서드
	 */
	@ResponseBody
	@RequestMapping(value = "/company_checkId", method = RequestMethod.GET)
	public String comIdCheck(@RequestParam("company_Id") String company_Id, HttpServletRequest request,
			HttpSession session) {
		// company_signup.jsp에서 name이 company_Id인 값을
		CompanyDTO company = (CompanyDTO) session.getAttribute("company"); // jpgood

		// jpoo // if(Constant.eSession == ESession.eNull) { // jpoo
		if (company == null) { // jpgood
			logger.info("/company_checkcId " + request.getMethod());
		} // 가져와 String값으로 저장한다.
		return this.companyService.comIdCheck(company_Id);
	}

	/*
	 * 기업 회원 가입 시 사업자등록번호가 중복되었는지 확인해주는 메서드
	 */
	@ResponseBody
	@RequestMapping(value = "/company_checkComNum", method = RequestMethod.GET)
	public String comNumCheck(@RequestParam("company_Number") String company_Number, HttpServletRequest request) { // company_signup.jsp에서
																													// name이
		// company_Number인 값을 가져와 int값으로
		logger.info("/company_checkComNum " + request.getMethod()); // 저장한다.
		return this.companyService.comNumCheck(company_Number);
	}

	/*
	 * 로그인창에 있는 아이디 찾기 중에서 기업회원 아이디 찾기를 눌렀을 경우
	 */
	@RequestMapping("/company_searchId")
	public ModelAndView company_searchId(ModelAndView mv, HttpServletRequest request) { // search_id.jsp에서 name값이
																						// company_Number인 값이 int
		logger.info("/company_searchId " + request.getMethod()); // company_Number에 저장된다.
		return this.companyService.searchId(mv, request); // company_Number에 맞는 id가 있는지 company테이블에서 찾아본다.
	}

	/*
	 * 로그인창에 있는 비밀번호 찾기 중에서 기업회원 비밀번호 찾기를 눌렀을 경우
	 */
	@RequestMapping(value = "/search_pw_company", method = RequestMethod.POST)
	public String search_pw_company(Model model, HttpServletRequest request) {
		logger.info("/search_pw_company " + request.getMethod());
		return this.companyService.search_pw_company(model, request);
	}

	/*
	 * 기업회원이 로그인을 한 후 마이페이지로 이동하게 될 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_Profile")
	public ModelAndView profile(HttpSession session, ModelAndView mv,Model model, HttpServletRequest request) {
		logger.info("/company_Profile " + request.getMethod());
		return this.companyService.profile(mv, session,model);
	}

	/*
	 * 기업회원이 정보를 수정하고자 할 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_modify")
	public ModelAndView company_modify(ModelAndView mv, HttpSession session, HttpServletRequest request) {
		logger.info("/company_modify " + request.getMethod());
		return this.companyService.company_modify(mv, session);
	}

	/*
	 * 개인정보수정를 고치고 수정 버튼을 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_modify_ok")
	public String customer_modify(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request,
			@RequestParam HashMap<String, Object> cmap) { // form에서 입력한 정보를 HashMap으로 묶어서 가져온다.
		this.companyService.updateCompanyInfo(multipartHttpServletRequest, cmap); // 가져온 cmap데이터를 기존 고객 데이터에 update시킨다.
		logger.info("/company_modify_ok " + request.getMethod());
		return "company/company_modify_ok.tiles";

	}

	/*
	 * 메인화면의 업체찾기에서 미용실 업체찾기를 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/beautyCompany")
	public String beautyCompany(Model model, HttpServletRequest request, Criteria cri) {
		logger.info("/beautyCompany " + request.getMethod());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.companyService.countCompanyList("미용"));

		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("page", cri.getPage());
		criteria.put("perPageNum", cri.getPerPageNum());
		criteria.put("pageStart", cri.getPageStart());
		criteria.put("company_Type", "미용");

		String url = "";
		List<CompanyDTO> beautyCompanyList = this.companyService.listsCompany(criteria); // company_Type이 미용실인 모든 회사를
																							// 가져온다.
		model.addAttribute("companyList", beautyCompanyList); // model에 가져온 회사 정보를 저장한다.
		model.addAttribute("pageMaker", pageMaker);

		url = "company/beauty_company.tiles"; // beauty_company.jsp화면을 띄워준다.
		return url;
	}

	/*
	 * 메인화면의 업체찾기에서 병원 업체찾기를 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/hospitalCompany")
	public String hospitalCompany(Model model, HttpServletRequest request, Criteria cri) {
		logger.info("/hospitalCompany " + request.getMethod());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.companyService.countCompanyList("병원"));

		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("page", cri.getPage());
		criteria.put("perPageNum", cri.getPerPageNum());
		criteria.put("pageStart", cri.getPageStart());
		criteria.put("company_Type", "병원");
		String url = "";
		List<CompanyDTO> hospitalCompanyList = this.companyService.listsCompany(criteria); // company_Type이 병원인 모든 회사를
																							// 가져온다.
		model.addAttribute("companyList", hospitalCompanyList); // model에 가져온 회사 정보를 저장한다.
		model.addAttribute("pageMaker", pageMaker);
		url = "company/hospital_company.tiles"; // hospital_company.jsp화면을 띄워준다.
		return url;
	}

	/*
	 * 메인화면의 업체찾기에서 호텔 업체찾기를 눌렀을 경우 실행되는 메서드이다.
	 */
	@RequestMapping("/hotelCompany")
	public String hotelCompany(Model model, HttpServletRequest request, Criteria cri) {
		logger.info("/hotelCompany " + request.getMethod());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(this.companyService.countCompanyList("호텔"));

		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("page", cri.getPage());
		criteria.put("perPageNum", cri.getPerPageNum());
		criteria.put("pageStart", cri.getPageStart());
		criteria.put("company_Type", "호텔");

		String url = "";
		List<CompanyDTO> hotelCompanyList = this.companyService.listsCompany(criteria); // company_Type이 호텔인 모든 회사를
																						// 가져온다.
		model.addAttribute("companyList", hotelCompanyList); // model에 가져온 회사 정보를 저장한다.
		model.addAttribute("pageMaker", pageMaker);
		url = "company/hotel_company.tiles"; // hotel_company.jsp화면을 띄워준다.
		return url;
	}

	/*
	 * 각 beautyCompany.jsp, hospitalCompany.jsp, hotelCompany.jsp파일에서 회사를 클릭해서 자세한
	 * 정보를 보려고 할 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_view")
	public String companyView(Model model, int companyIdx, HttpServletRequest request) { // 각 beautyCompany.jsp,
																							// hospitalCompany.jsp,
		// hotelCompany.jsp파일에서
		// company리스트를 클릭하면 companyIdx값이 넘어온다. (onclick href 참조)
		CompanyDTO company = this.companyService.listThisCompany(companyIdx); // 넘어온 companyIdx값을 company테이블에 가서 값을
																				// 가져온다.

		logger.info("/company_view " + request.getMethod());
		model.addAttribute("thisCompany", company); // 가져온 company의 값을 model객체에 저장한다.
		return "company/company_view.tiles";
	}

	// 업체찾기 : 전체엄체, 검색된업체
	@RequestMapping("/searchCompany")
	public String searchCompany(Model model, HttpServletRequest request, Criteria cri) {
		logger.info("/searchCompany " + request.getMethod());
		String url = "";
		List<CompanyDTO> companyList = this.companyService.listsAllCompany(model, request, cri); // 회사를 가져온다.
		model.addAttribute("companyList", companyList); // model에 가져온 회사 정보를 저장한다.
		url = "home/search_company.tiles"; // 화면을 띄워준다.
		return url;
	}

	// 탈퇴화면 페이지로 이동
	@RequestMapping("/deleteTheCompany") // company_delete을 요청하면
	public String deleteTheCompany() {
		return "company/company_delete.tiles"; // company_delete.jsp가 return되게

	}

	// 탈퇴처리(고객정보삭제)
	@RequestMapping("/company_delete_ok")
	public String company_delete_ok(HttpServletRequest request, HttpSession session) {

		Map<String, Object> cMap = new HashMap<String, Object>();
		String company_Password = request.getParameter("company_Password");
		cMap.put("company_Password", company_Password);

		String company_Id = ((CompanyDTO) (session.getAttribute("company"))).getCompany_Id();
		// 비밀번호 체크
		boolean result = companyService.checkPW(company_Id, company_Password);
		System.out.println("비밀번호 체크");
		if (result) { // 비밀번호가 맞다면 삭제 처리
			companyService.deleteTheCompany(company_Id);
			System.out.println("탈퇴성공");
			if (result) {
				// jpoo // Constant.eSession = ESession.eNull;
				session.invalidate(); // 탈퇴시 로그아웃 처리
			}
			return "company/company_delete_ok.tiles";

		} else { // 비밀번호가 일치하지 않는다면
			return "company/company_delete.tiles";

		}

	}
}
