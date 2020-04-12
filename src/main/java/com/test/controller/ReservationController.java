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

import com.test.dao.CompanyDAO;
import com.test.dao.PetDAO;
import com.test.dao.ReservationDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;
import com.test.dto.ReservationDTO;

@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class ReservationController {
	
	@Autowired
	private CompanyDAO companyDao;

	@Autowired
	private PetDAO petDao;

	@Autowired
	private ReservationDAO reservationDAO;

	/*
	 * 상단에 예약하기 버튼을 누르면 실행되는 메서드이다.
	 */
	@RequestMapping("/reserve")
	public String reserve(Model model, HttpServletRequest request) {
		String url = "";
		HttpSession session = request.getSession();								// session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");	// 가져온 session에서 customer를 가져온다.
		if (customer != null) {													// customer가 존재하면
			int customerIdx 		 = customer.getCustomer_Index();			// customer에서 index값만 따로 int변수에 저장한다.
			List<PetDTO> itsPets	 = this.petDao.listItsPets(customerIdx);	// customerIdx에 해당하는 모든 펫 정보가 저장된다.			
			List<CompanyDTO> company = this.companyDao.listAllCompany();		// 모든 회사 정보를 전부 가져온다.
			
			model.addAttribute("petList", itsPets);								// model객체에 가져온 펫 정보가 저장된다.
			model.addAttribute("companyList", company);							// model객체에 가져온 회사 정보가 저장된다.
			url = "reserve/reserve.tiles";													// reserve.jsp화면을 띄워준다.
			
		} else {																// customer가 존재하지 않으면
			url = "redirect:/";													// 메인 화면을 띄워준다.
		}
		return url;
	}

	@RequestMapping("/reserve_ok")
	public String reserve_Ok(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request) {
		HttpSession session = request.getSession();										// session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");			// 가져온 session에서 customer를 가져온다. (DTO로 타입캐스팅)
		this.reservationDAO.insertTheReservation(rmap, customer.getCustomer_Index());	// form에 입력한 값 + customer_Index를 같이 Reservation테이블에 저장해준다.
		return "reserve/reserve_ok.tiles";	
	}

	/*
	 * 고객이 마이페이지에서 예약정보조회를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_reserve_check")					
	public String customer_reservecheck(Model model, HttpSession session) {
		if (session.getAttribute("customer") != null) {															// customer session이 존재할 경우
			CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");								// 가져온 session에서 customer를 가져온다. (DTO로 타입캐스팅)
			int customerIdx 	 = customer.getCustomer_Index();												// customer에서 index값을 따로 int변수에 저장해준다.
			List<PetDTO> itsPets = this.petDao.listItsPets(customerIdx);										// customerIdx에 해당하는 모든 펫 정보가 저장된다.	
			
			List<ReservationDTO> itsReservations = new ArrayList<ReservationDTO>();								// 모든 예약정보를 저장할 List객체이다.
			for (PetDTO petDTO : itsPets) {
				List<ReservationDTO> temp = this.reservationDAO.listItsCustReservations(petDTO.getPet_Index());	// 해당 펫 인덱스가 존재하는 예약정보를 가져온다.
				itsReservations.addAll(temp);																	// itsReservations List객체에 저장한다.
			}
			model.addAttribute("reservation", itsReservations);													// 가져온 예약정보를 model객체에 저장한다.
			model.addAttribute("pet", itsPets);																	// 가져온 펫 정보를 model객체에 저장한다.
		}
		return "reserve/customer_reserve_check.tiles"; 
	}

	/*
	 * 기업이 마이페이지에서 예약정보조회를 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/company_reserve_check")
	public String company_reservecheck(Model model, HttpSession session) {
		if (session.getAttribute("company") != null) {															// company session이 존재할 경우
			CompanyDTO company = (CompanyDTO) session.getAttribute("company");									// 가져온 session에서 company를 가져온다. (DTO로 타입캐스팅)
			int companyIndex = company.getCompany_Index();														// company에서 index값을 따로 int변수에 저장해준다.
			List<ReservationDTO> itsReservations = this.reservationDAO.listItsCompReservations(companyIndex);	// companyIndex에 해당하는 모든 예약정보를 가져온다.
			model.addAttribute("reservation", itsReservations);													// 가져온 예약정보를 model객체에 저장한다.
		}
		return "reserve/company_reserve_check.tiles"; 
	}
	
	/*
	 * 고객이 예약을 취소할 때 실행되는 메서드이다.
	 * 예약조회화면에서 정상예약건에 한해서 취소를 누를 수 있다.
	 */
	@RequestMapping(value = "/customer_reservation_cancel", method = RequestMethod.GET)
	public String customer_reservation_delete(Model model, HttpSession session, String index) {
		int reservationNum = Integer.parseInt(index);														// 정상예약화면에서 쓰레기통 모양을 누르면 메서드가 실행되면서 reservation_Index가 넘어온다. (customer_reserve_check.jsp)
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");								// customer session을 가져온다.
		int customerIdx = customer.getCustomer_Index();														// customer에서 index값을 따로 int변수에 저장해준다.
		List<ReservationDTO> itsReservations = this.reservationDAO.listItsCustReservations(customerIdx);	// customerIdx에 해당하는 모든 예약 정보를 가져온다.

		for (ReservationDTO reservationDTO : itsReservations) {
			if (reservationDTO.getReservation_Index() == reservationNum) {
				reservationDAO.cancelTheReservation(reservationNum);			// 취소하고자 하는 예약 인덱스와 동일한 예약은 테이블에서 reservation_Check = 'canceled'로 고친다.
			}
		}
		return "customer/customer_Profile.tiles";												// 후에 마이페이지로 이동한다.
	}

}
