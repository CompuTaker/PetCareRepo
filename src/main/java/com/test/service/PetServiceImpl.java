package com.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.PetDAO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;

@Service
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class PetServiceImpl implements PetService{
	
	@Autowired
	private PetDAO petDao;

	@Override
	public String modify(int cust_Index, int pet_Index, Model model,HttpServletRequest  request) {
		Map<String,Integer> petMap = new HashMap<String,Integer>();		// 넘어온 변수를 한 번에 저장하기 위해서 만든 Map객체
		petMap.put("customer_Index", cust_Index);						// Map객체에 customer_Index를 저장한다.
		petMap.put("pet_Index", pet_Index);								// Map객체에 pet_Index를 저장한다.
		
		request.getSession().setAttribute("pet_Index", pet_Index);
		
		PetDTO pt= this.petDao.read(petMap);							// customer_Index, pet_Index에 해당하는 펫 정보를 가져온다.
		model.addAttribute("pet",pt);									// 가져온 펫 정보를 model객체에 저장한다.
		return "pet/pet_modify.tiles";
	}

	@Override
	public String postModify(PetDTO pet,HttpServletRequest  request) {
		HttpSession session = request.getSession();														// Session을 가져온다.
		
		PetDTO pet2 = pet;
		pet2.setCustomer_Index(((CustomerDTO) session.getAttribute("customer")).getCustomer_Index());
		pet2.setPet_Index((Integer) session.getAttribute("pet_Index"));		
		
		this.petDao.update(pet2);							// 펫 정보를 수정하고 수정하기 버튼을 누르면 form에 입력한 값들로 기존 펫 정보를 udpate한다.
		return "pet/pet_modify_ok.tiles";	// 펫 정보 수정이 끝나고 난 후 확인페이지로 이동
	}

	@Override
	public String reserve_Ok(HashMap<String, Object> pmap, HttpServletRequest request) {
		HttpSession session = request.getSession();														// Session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");							// 가져온 Session에서 customer session을 가져와서 DTO로 타입캐스팅을 하고 customer에 저장한다.
		int customer_Index = customer.getCustomer_Index();												// customer에서 index값만 따로 int변수에 저장한다.
		pmap.put("customer_Index", customer_Index);														// HashMap객체에 form값 + custoer_Index를 저장한다.
		this.petDao.insertThePet(pmap);																	// pet테이블에다가 값을 insert한다.
		return "pet/pet_ok.tiles";
	}

	@Override
	public String petcheck(Model model, HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");	// 가져온 Session에서 customer session을 가져와서 DTO로 타입캐스팅을 하고 customer에 저장한다.
		int customer_Index = customer.getCustomer_Index();						// customer에서 index값만 따로 int변수에 저장한다.
		List<PetDTO> itsPets = new ArrayList<PetDTO>();							// pet정보를 가져와서 저장할 List객체를 생성한다.
		List<PetDTO> temp = this.petDao.listItsPets(customer_Index);			// customer_Index에 해당하는 모든 펫 정보를 pet테이블에서 전부 가져온다.
		itsPets.addAll(temp);													// itsPets에 저장한다.
		model.addAttribute("pet", itsPets);										// model객체에 모든 pet정보를 저장한다.
		return "pet/pet_check.tiles";  
	}

	@Override
	public ModelAndView pet_cancel(String pet_Index, ModelAndView mv) {
		int petIdx = Integer.parseInt(pet_Index);
		this.petDao.deleteThePet(petIdx);
		mv.setViewName("redirect:customer_Profile");
		return mv;
	}
}
