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
import com.test.dao.CustomerDAO;
import com.test.dao.PetDAO;
import com.test.dao.ReservationDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;
import com.test.dto.ReservationDTO;

@Controller
@SessionAttributes({ "customer", "company" })
public class ReservationController {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private CompanyDAO companyDao;

	@Autowired
	private PetDAO petDao;

	@Autowired
	private ReservationDAO reservationDAO;

	@RequestMapping("/reserve")
	public String reserve(Model model, HttpServletRequest request) {
		String url = "";

		HttpSession session = request.getSession();
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		if (customer != null) {
			// 고객이 가지고 있는 펫 정보 다 가져오기
			int customerIdx = customer.getCustomer_Index();
			System.out.println("예약중인 고객 번호 : " + customerIdx);

			List<PetDTO> itsPets = this.petDao.listItsPets(customerIdx);
			model.addAttribute("petList", itsPets);
			// DB에 저장된 회사정보 가져오기
			List<CompanyDTO> company = this.companyDao.listAllCompany();
			model.addAttribute("companyList", company);

			url = "reserve";
		} else {
			url = "redirect:/";
		}

		return url;
	}

	@RequestMapping("/reserve_ok")
	public String reserve_Ok(@RequestParam HashMap<String, Object> rmap, HttpServletRequest request) {
		HttpSession session = request.getSession();
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		System.out.println("예약완료지롱");
		this.userReserve(rmap, customer);
		return "reserve_ok";
	}

	// 이것도 그냥 customer 인덱스만 넘기면 될 거 같긴 한데 ~~~~~~~~~~~~
	public int userReserve(HashMap<String, Object> rmap, CustomerDTO customer) {
		int res = this.reservationDAO.insertTheReservation(rmap, customer.getCustomer_Index());
		System.out.println("reservation insert result => " + res);
		return res;
	}

	@RequestMapping("/customer_reserve_check")
	public String customer_reservecheck(Model model, HttpSession session) {
		if (session.getAttribute("customer") != null) {
			CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
			int customerIdx = customer.getCustomer_Index();
			List<PetDTO> itsPets = this.petDao.listItsPets(customerIdx);
			List<ReservationDTO> itsReservations = new ArrayList<ReservationDTO>();
			for (PetDTO petDTO : itsPets) {
				System.out.println(petDTO.getPet_Age());
				List<ReservationDTO> temp = this.reservationDAO.listItsCustReservations(petDTO.getPet_Index());
				itsReservations.addAll(temp);
			}
			// 한번만 넣기
			model.addAttribute("reservation", itsReservations);
			model.addAttribute("pet", itsPets);
		}
		return "customer_reserve_check"; // login.jsp // views
	}

	@RequestMapping("/company_reserve_check")
	public String company_reservecheck(Model model, HttpSession session) {
		if (session.getAttribute("company") != null) {
			CompanyDTO company = (CompanyDTO) session.getAttribute("company");
			int companyIndex = company.getCompany_Index();
			List<ReservationDTO> itsReservations = this.reservationDAO.listItsCompReservations(companyIndex);
			model.addAttribute("reservation", itsReservations);
		}
		return "company_reserve_check"; // login.jsp // views
	}

	@RequestMapping(value = "/customer_reservation_cancel", method = RequestMethod.GET)
	public String customer_reservation_delete(Model model, HttpSession session, String index) {
		System.out.println(index);
		int reservationNum = Integer.parseInt(index);
		System.out.println("예약번호: " + reservationNum);
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");
		int customerIdx = customer.getCustomer_Index();
		List<ReservationDTO> itsReservations = this.reservationDAO.listItsCustReservations(customerIdx);

		for (ReservationDTO reservationDTO : itsReservations) {
			if (reservationDTO.getReservation_Index() == reservationNum) {
				reservationDAO.cancelTheReservation(reservationNum);
			}
		}
		System.out.println("예약취소되었습니다.");
		return "customerprofile";
	}

}
