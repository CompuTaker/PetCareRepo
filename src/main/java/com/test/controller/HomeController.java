package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.constants.Constant;
import com.test.constants.Constant.ESession;

@Controller
@SessionAttributes({"customer", "company"})
public class HomeController {
	
//	@Autowired
//	private SpringJmsProducer springJmsProducer;

//	@Autowired
//	private SpringJmsConsumer springJmsConsumer;
	
	@RequestMapping({"/", "index"})
	public String index(Model model) {
		System.out.println("/ or index");
		return "redirect:/"; // index.jsp
	}
	
	@RequestMapping("/loginOrProfile")
	public String loginOrProfile(Model model, HttpSession session) {
		String url = "";

		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		CompanyDTO company = (CompanyDTO) session.getAttribute("company");

		if(customer == null && company == null) {
			// not logged in
			url = "login";
		}else if(customer == null && company == null){
			// both logged in
			url = "logout";
		}else {
			// eCompany, eCustomer
			if(customer != null){
				url = "customerprofile";
				System.out.println("url"+url);
			}else if(company != null) {
				url = "companyprofile";
			}
		}
		return "redirect:" + url;
		// profile.jsp OR login.jsp
	}
	
	
}
