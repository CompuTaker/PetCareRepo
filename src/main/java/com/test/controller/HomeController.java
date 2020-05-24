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
		if(Constant.eSession == ESession.eNull) {
			// not logged in
			url = "login";
		}else if(Constant.eSession == ESession.eError){
			// both logged in
			url = "/";
		}else {
			// eCompany, eCustomer
			if(Constant.eSession == ESession.eCustomer){
				url = "customerprofile";
				System.out.println("url"+url);
			}else if(Constant.eSession == ESession.eCompany) {
				url = "companyprofile";
			}
		}
		return "redirect:" + url;
		// profile.jsp OR login.jsp
	}
	
	
}
