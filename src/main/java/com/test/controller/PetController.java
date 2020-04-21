package com.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.test.dao.CompanyDAO;
import com.test.dao.CustomerDAO;
import com.test.dao.PetDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;
import com.test.dto.ReservationDTO;

@Controller
@SessionAttributes({ "customer", "company" })
public class PetController {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private CompanyDAO companyDao;

	@Autowired
	private PetDAO petDao;

	@RequestMapping("/pet_register")
	public String petRegister(Model model, HttpServletRequest request) {
		String url = "";
		HttpSession session = request.getSession();
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		if (customer != null) {
			url = "pet_register";
		} else {
			url = "redirect:/";
		}

		return url;
	}


	
	@RequestMapping(value="/pet_modify",method=RequestMethod.GET)
	public void modify(@RequestParam("customer_Index")int cus,
			@RequestParam("pet_Index")int pet, Model model) {
		
		PetDTO pt= this.petDao.read(cus, pet);
		model.addAttribute("pet",pt);
		

	}
	
	@RequestMapping(value="/pet_modify",method=RequestMethod.POST)
	
	public String postModify(PetDTO pet){
		this.petDao.update(pet);
		return "redirect:/customerprofile";
		
	}
	
	@RequestMapping("/pet_ok")
	public String reserve_Ok(@RequestParam HashMap<String, Object> pmap, HttpServletRequest request) {
		System.out.println("펫 등록했지fhd");
		HttpSession session = request.getSession();
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		int customer_Index = customer.getCustomer_Index();
		pmap.put("customer_Index", customer_Index);
		System.out.println(pmap.get("pet_Index"));
		this.petRegister(pmap);
		return "pet_ok";
	}

	@RequestMapping("/petcheck")
	public String petcheck(Model model, HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		int customer_Index = customer.getCustomer_Index();
		List<PetDTO> itsPets = new ArrayList<PetDTO>();
		List<PetDTO> temp = this.petDao.listItsPets(customer_Index);
		itsPets.addAll(temp);
		model.addAttribute("pet", itsPets);
		return "pet_check"; 
	}

	public int petRegister(HashMap<String, Object> pmap) {
		int res = this.petDao.insertThePet(pmap);
		System.out.println(res);
		return res;
	}

}
