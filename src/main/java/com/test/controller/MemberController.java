package com.test.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.test.constants.Constant;
import com.test.constants.Constant.ESession;
import com.test.dao.CompanyDAO;
import com.test.dao.CustomerDAO;
import com.test.dao.PetDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;

@Controller
@SessionAttributes({ "customer", "company" })
public class MemberController {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private CompanyDAO companyDao;

	@Autowired
	private PetDAO petDao;

	// 기업 중복체크 (이거 리팩때 company controller로 옮길것)
	private boolean isCompanyIdChecked = false;
	private boolean isCompanyComNumChecked = false;
	private boolean isCompanyOk = false;

	// 개인 중복체크
	private boolean isCustomerIdChecked = false;
	private boolean isCustomerResidentNumberChecked = false;
	private boolean isCustomerOk = false;

	@RequestMapping("/login")
	public String login(Model model) {
		System.out.println("~~~login~~~");
		return "login"; // login.jsp // views ?占쏙옙?占쏙옙 ?占쏙옙
	}

	@RequestMapping("/loginDo")
	public String loginDo(Model model, String id, String pw, HttpSession session, SessionStatus status) {
		CustomerDTO customer = this.customerDao.listThisCustomer(id, pw);
		CompanyDTO company = this.companyDao.listThisCompany(id, pw);
		String url = "";

		if (customer != null && company == null) { // only customer
			url = "customerprofile";
			model.addAttribute("customer", customer);
			Constant.eSession = ESession.eCustomer;
		} else if (customer == null && company != null) { // only company
			model.addAttribute("company", company);
			url = "companyprofile";
			Constant.eSession = ESession.eCompany;
		} else if (customer != null && company != null) { // company == null
			System.out.println("고객 & 업체 중복 로그인 방지");
			status.setComplete();
			url = "/";
		} else { // customer == null && company == null
			url = "login";
		}

		return "redirect:" + url; // loginDo.jsp
	}

	@RequestMapping("/logout")
	public String logout(Model model, SessionStatus status) {
		Constant.eSession = ESession.eNull;
		status.setComplete();
		System.out.println("~~~logout~~~");
		return "redirect:/";
	}

	@RequestMapping("/customerprofile")
	public String profile(Model model, HttpSession session) {
		if (session.getAttribute("customer") != null) {
			try {
				CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
				System.out.println("customer => " + customer.getCustomer_Index());
				CustomerDTO getCusotmerInfo = this.customerDao.listThisCustomer(customer.getCustomer_Id(),
						customer.getCustomer_Password());
				List<PetDTO> itspets = this.petDao.listItsPets(customer.getCustomer_Index());
				model.addAttribute("pet", itspets);
				model.addAttribute("customer", getCusotmerInfo);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// false
			// Exception Handling
		}
		return "customerprofile"; // customerprofile.jsp // views
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		// insertTheCustomer
		return "signup"; // signup.jsp // views
	}

	@RequestMapping("/signupDo")
	public String signupDo(String flag) {
		// 기업인지 개인인지 구분
		String url = "";
		System.out.println(flag + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (flag.equals("user")) {
			// this.userSignup(cmap);
			url = "customer_signup";
		} else if (flag.equals("comp")) {
			// this.compSignup(cmap);
			url = "company_signup";
		} else {
			System.out.println("NoBody");
		}
		return url;
	}

	@RequestMapping("/customer_signupDo")
	public ModelAndView customer_signupDo(@RequestParam HashMap<String, Object> cmap) {
		ModelAndView ok = new ModelAndView("/customer_signup_ok");
		ModelAndView redirect = new ModelAndView("/customer_signup");
		redirect.addObject("message", "중복체크 해주세요.");

		if (isCustomerIdChecked && isCustomerResidentNumberChecked) {
			if(isCustomerOk) {
				int res = this.customerDao.insertTheCustomer(cmap);
				System.out.println("customer insert result => " + res);
				return ok;
			}
		}
		System.out.println("중복체크 안함");
		isCustomerIdChecked = false;
		isCustomerResidentNumberChecked = false;
		isCustomerOk = false;
		return redirect;
	}

	@RequestMapping("/company_signupDo")
	public ModelAndView company_signupDo(@RequestParam HashMap<String, Object> cmap) {
		ModelAndView ok = new ModelAndView("/company_signup_ok");
		ModelAndView redirect = new ModelAndView("/company_signup");
		redirect.addObject("message", "중복체크 해주세요.");

		if (isCompanyIdChecked && isCompanyComNumChecked) {
			if(isCompanyOk) {
				System.out.println("중복체크 함");
				int res = this.companyDao.insertTheCompany(cmap);
				System.out.println("company insert result => " + res);
				return ok;
			}


		}
		System.out.println("중복체크 안함");
		isCompanyIdChecked = false;
		isCompanyComNumChecked = false;
		isCompanyOk = false;
		return redirect;
	}

	@RequestMapping("/customer_modify")
	public ModelAndView customer_modify(ModelAndView mv, HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		// 해댱 ID와 비밀번호를 가진 고객을 가져온다.
		customer = this.customerDao.listThisCustomer(customer.getCustomer_Id(), customer.getCustomer_Password());
		mv.addObject("customer", customer);
		mv.setViewName("customer_modify");
		return mv;
	}

	@RequestMapping("/customer_modify_ok")
	public String customer_modify(@RequestParam HashMap<String, Object> cmap) {
		this.customerDao.updateCustomerInfo(cmap);

		return "customer_modify_ok";
	}

	// 아이디 찾기 화면 가져오기
	@RequestMapping("/search_id")
	public String searchID() {
		return "search_id";
	}

	// 기업회원 아이디 찾기
	@RequestMapping("/company_searchId")
	public String company_searchId(int company_Number, Model model) {
		System.out.println("기업 아이디 찾기");
		CompanyDTO com = this.companyDao.searchId(company_Number);
		if (com == null) {
			return "redirect:/search_id";
		}
		model.addAttribute("company", com.getCompany_Id());
		return "/company_show_id";
	}

	@RequestMapping(value = "/search_id_customer", method = RequestMethod.POST)
	public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request) {
		System.out.println("~~~search_id_Post~~~");
		CustomerDTO customer = this.customerDao.searchCustomerID(request.getParameter("customer_Name"),
				request.getParameter("customer_PhoneNumber"));
		mv.addObject("customer", customer);
		mv.setViewName("customer_show_id");
		return mv;
	}

	@RequestMapping("/search_pw")
	public String search_pw() {
		return "search_pw";
	}

	@RequestMapping(value = "/search_pw_company", method = RequestMethod.POST)
	public String search_pw_company(ModelAndView mv, HttpServletRequest request) {

		System.out.println("기업 비밀번호 찾기");
		CompanyDTO company = this.companyDao.searchCompanyPW(request.getParameter("company_UserName"),
				request.getParameter("company_Id"), request.getParameter("company_UserPhoneNumber"));

		System.out.println(company);
		String passwordArr[] = company.getCompany_Password().split("");
		String password = "";

		for (int i = 0; i < passwordArr.length; i++) {
			if (i > 2) {
				password += "*";
			} else {
				password += passwordArr[i];
			}
		}

		System.out.println(password);
		request.setAttribute("password", password);
		return "company_show_pw";
	}

	@RequestMapping(value = "/search_pw_customer", method = RequestMethod.POST)
	public String search_pw_customer(ModelAndView mv, HttpServletRequest request) {

		CustomerDTO customer = this.customerDao.searchCustomerPW(request.getParameter("customer_Name"),
				request.getParameter("customer_Id"), request.getParameter("customer_PhoneNumber"));

		String passwordArr[] = customer.getCustomer_Password().split("");
		String password = "";

		for (int i = 0; i < passwordArr.length; i++) {
			if (i > 2) {
				password += "*";
			} else {
				password += passwordArr[i];
			}
		}

		System.out.println(password);
		request.setAttribute("password", password);
		return "customer_show_pw";
	}

	// 고객 아이디 중복체크
	@RequestMapping(value = "/customer_checkId", method = RequestMethod.GET)
	@ResponseBody
	public int idCheck(@RequestParam("customer_Id") String customer_Id) {
		isCustomerIdChecked = true;
		CustomerDTO customer = customerDao.checkCustomerID(customer_Id);
		if (customer != null) {
			isCustomerOk = false;
			return 1;
		}
		isCustomerOk = true;
		return 0;
	}

	// 고객 주번 중복체크
	@RequestMapping(value = "/customer_chekResidentNumber", method = RequestMethod.GET)
	@ResponseBody
	public int residentNumCheck(@RequestParam("customer_ResidentNumber") String customer_ResidentNumber) {
		isCustomerResidentNumberChecked = true;
		CustomerDTO customer = customerDao.checkCustomerResident(customer_ResidentNumber);
		if (customer != null) {
			isCustomerOk = false;
			return 1;
		}
		isCustomerOk = true;
		return 0;
	}

	// 업체 아이디 중복체크
	@RequestMapping(value = "/company_checkcId", method = RequestMethod.GET)
	@ResponseBody
	public int comIdCheck(@RequestParam("company_Id") String company_Id) {
		isCompanyIdChecked = true;
		System.out.println("업체 아이디 중복체크");
		CompanyDTO company = companyDao.checkCompanyID(company_Id);
		if (company != null) {
			isCompanyOk = false;
			return 1;
		}
		isCompanyOk = true;
		return 0;
	}

	// 업체 사업자번호 중복체크
	@RequestMapping(value = "/company_checkComNum", method = RequestMethod.GET)
	@ResponseBody
	public int comNumCheck(@RequestParam("company_Number") int company_Number) {
		isCompanyComNumChecked = true;
		System.out.println("업체 사업자 번호 중복체크");
		CompanyDTO company = companyDao.checkCompanyNumber(company_Number);
		if (company != null) {
			isCompanyOk = false;
			return 1;
		}
		isCompanyOk = true;
		return 0;
	}

}