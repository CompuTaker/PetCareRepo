package com.test.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
				CustomerDTO getCusotmerInfo = this.customerDao.listThisCustomer(customer.getCustomer_Id(), customer.getCustomer_Password());
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
	public String customer_signupDo(@RequestParam HashMap<String, Object> cmap) {
		int res = this.customerDao.insertTheCustomer(cmap);
		System.out.println("customer insert result => " + res);
		return "redirect:/";
	}

	@RequestMapping("/company_signupDo")
	public String company_signupDo(@RequestParam HashMap<String, Object> cmap) {
		int res = this.companyDao.insertTheCompany(cmap);
		System.out.println("company insert result => " + res);
		return "redirect:/";
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

}
