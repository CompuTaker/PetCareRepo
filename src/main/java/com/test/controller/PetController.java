package com.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;
import com.test.service.PetService;
	
@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class PetController {

	@Autowired
	private PetService petService;

	/*
	 * 고객이 로그인 한 후에 펫 등록하기 버튼을 눌렀을 경우 실행되는 메서드
	 */
	@RequestMapping("/pet_register")
	public String petRegister(Model model, HttpServletRequest request) {
		String url = "";
		HttpSession session = request.getSession();								// Session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");	// 가져온 Session에서 customer session을 가져와서 DTO로 타입캐스팅을 하고 customer에 저장한다.
		
		if (customer != null) {													// customer가 존재하면
			url = "pet/pet_register.tiles";										// 펫 등록 화면(pet_register.jsp)를 띄워준다.
			
		} else {																// customer가 존재하지 않으면
			url = "redirect:/";													// 메인 화면을 띄워준다.
		}
		return url;
	}
	
	/*
	 * 고객이 등록한 반려 동물의 정보를 수정할 때 실행되는 메서드이다.
	 * RequestMethod.GET
	 */
	@RequestMapping(value="/pet_modify",method=RequestMethod.GET)
	public String modify(@RequestParam("customer_Index")int cust_Index, @RequestParam("pet_Index")int pet_Index, Model model) {	// customerprofile.jsp에서 name값이 customer_Index, pet_Index인 값을 가져온다.
																																// 펫 정보수정을 누르면 값을 가져온다.	
		return this.petService.modify(cust_Index, pet_Index, model);
	}
	
	/*
	 * 고객이 등록한 반려 동물의 정보를 수정할 때 실행되는 메서드이다.
	 * RequestMethod.POST
	 */
	@RequestMapping(value="/pet_modify",method=RequestMethod.POST)
	public String postModify(PetDTO pet){
		return this.petService.postModify(pet);		
	}
	
	/*
	 * 펫 등록하기에서 등록하기 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/pet_ok")
	public String reserve_Ok(@RequestParam HashMap<String, Object> pmap, HttpServletRequest request) {	// form에 입력한 값을을 HashMap으로 묶어서 가져온다.
		return this.petService.reserve_Ok(pmap, request);
	}

	@RequestMapping("/petcheck")
	public String petcheck(Model model, HttpSession session) {	
		return this.petService.petcheck(model, session);
	}
	
	@RequestMapping("/pet_cancel")
	public ModelAndView pet_cancel(@RequestParam String pet_Index, ModelAndView mv) {
		return this.petService.pet_cancel(pet_Index, mv);
	}
}
