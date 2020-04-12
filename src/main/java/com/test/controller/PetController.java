package com.test.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.dao.PetDAO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;
	
@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class PetController {
	
	@Autowired
	private PetDAO petDao;

	/*
	 * 고객이 로그인 한 후에 펫 등록하기 버튼을 눌렀을 경우 실행되는 메서드
	 */
	@RequestMapping("/pet_register")
	public String petRegister(Model model, HttpServletRequest request) {
		String url = "";
		HttpSession session = request.getSession();								// Session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");	// 가져온 Session에서 customer session을 가져와서 DTO로 타입캐스팅을 하고 customer에 저장한다.
		
		if (customer != null) {													// customer가 존재하면
			url = "pet/pet_register.tiles";												// 펫 등록 화면(pet_register.jsp)를 띄워준다.
			
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
	public void modify(@RequestParam("customer_Index")int cus, @RequestParam("pet_Index")int pet, Model model) {	// customerprofile.jsp에서 name값이 customer_Index, pet_Index인 값을 가져온다.
																													// 펫 정보수정을 누르면 값을 가져온다.	
		Map<String,Integer> petMap = new HashMap<String,Integer>();		// 넘어온 변수를 한 번에 저장하기 위해서 만든 Map객체
		petMap.put("customer_Index", cus);								// Map객체에 customer_Index를 저장한다.
		petMap.put("pet_Index", pet);									// Map객체에 pet_Index를 저장한다.
		
		PetDTO pt= this.petDao.read(petMap);							// customer_Index, pet_Index에 해당하는 펫 정보를 가져온다.
		model.addAttribute("pet",pt);									// 가져온 펫 정보를 model객체에 저장한다.
	}
	
	/*
	 * 고객이 등록한 반려 동물의 정보를 수정할 때 실행되는 메서드이다.
	 * RequestMethod.POST
	 */
	@RequestMapping(value="/pet_modify",method=RequestMethod.POST)
	public String postModify(PetDTO pet){
		this.petDao.update(pet);			// 펫 정보를 수정하고 수정하기 버튼을 누르면 form에 입력한 값들로 기존 펫 정보를 udpate한다.
		return "redirect:/customer/customer_profile.tiles";	// 펫 정보 수정이 끝나고 난 후 마이페이지로 돌아온다.
			
	}
	
	/*
	 * 펫 등록하기에서 등록하기 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/pet_ok")
	public String reserve_Ok(@RequestParam HashMap<String, Object> pmap, HttpServletRequest request) {	// form에 입력한 값을을 HashMap으로 묶어서 가져온다.
		HttpSession session = request.getSession();														// Session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");							// 가져온 Session에서 customer session을 가져와서 DTO로 타입캐스팅을 하고 customer에 저장한다.
		int customer_Index = customer.getCustomer_Index();												// customer에서 index값만 따로 int변수에 저장한다.
		pmap.put("customer_Index", customer_Index);														// HashMap객체에 form값 + custoer_Index를 저장한다.
		this.petDao.insertThePet(pmap);																	// pet테이블에다가 값을 insert한다.
		return "pet/pet_ok.tiles";
	}

	@RequestMapping("/petcheck")
	public String petcheck(Model model, HttpSession session) {						
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");	// 가져온 Session에서 customer session을 가져와서 DTO로 타입캐스팅을 하고 customer에 저장한다.
		int customer_Index = customer.getCustomer_Index();						// customer에서 index값만 따로 int변수에 저장한다.
		List<PetDTO> itsPets = new ArrayList<PetDTO>();							// pet정보를 가져와서 저장할 List객체를 생성한다.
		List<PetDTO> temp = this.petDao.listItsPets(customer_Index);			// customer_Index에 해당하는 모든 펫 정보를 pet테이블에서 전부 가져온다.
		itsPets.addAll(temp);													// itsPets에 저장한다.
		model.addAttribute("pet", itsPets);										// model객체에 모든 pet정보를 저장한다.
		return "pet/pet_check.tiles"; 
	}
}
