package com.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.test.dao.CompanyDAO;
import com.test.dao.PetDAO;
import com.test.dao.ReservationDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.Criteria;
import com.test.dto.CustomerDTO;
import com.test.dto.PageMaker;
import com.test.dto.PetDTO;
import com.test.dto.ReservationDTO;
import com.test.dto.SuperuserDTO;

@Service
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private PetDAO petDao;
	
	@Autowired
	private ReservationDAO reservationDao;

	@Override
	public String reserve(Model model, HttpServletRequest request) {
		String url = "";
		HttpSession session = request.getSession();								// session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");	// 가져온 session에서 customer를 가져온다.
		SuperuserDTO superuser = (SuperuserDTO) session.getAttribute("superuser");
		
		if (customer != null) {													// customer가 존재하면
			int customerIdx 		 = customer.getCustomer_Index();			// customer에서 index값만 따로 int변수에 저장한다.
			List<PetDTO> itsPets	 = this.petDao.listItsPets(customerIdx);	// customerIdx에 해당하는 모든 펫 정보가 저장된다.			
			List<CompanyDTO> company = this.companyDao.listAllCompany();		// 모든 회사 정보를 전부 가져온다.
			
			model.addAttribute("petList", itsPets);								// model객체에 가져온 펫 정보가 저장된다.
			model.addAttribute("companyList", company);							// model객체에 가져온 회사 정보가 저장된다.
			url = "reserve/reserve.tiles";										// reserve.jsp화면을 띄워준다.
			
		} else {																// customer가 존재하지 않으면
			url = "redirect:/login";													// 메인 화면을 띄워준다.
		}
		if (superuser != null) {													// customer가 존재하면

			url = "reserve/reserve.tiles";										// reserve.jsp화면을 띄워준다.
			
		} 
		return url;
	}

	@Override
	public String reserve_OK(HashMap<String, Object> rmap, HttpServletRequest request) {
		HttpSession session = request.getSession();												// session을 가져온다.
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");					// 가져온 session에서 customer를 가져온다. (DTO로 타입캐스팅)
		
		SimpleDateFormat date 	  = new SimpleDateFormat("yyyy-MM-dd");							// MySQL에서 지원하는 날짜 포맷을 생성한다.
		SimpleDateFormat dateTime = new SimpleDateFormat("HH:mm:ss");							// MySQL에서 지원하는 시간 포맷을 생성한다.
				
		Date reservation_Date = null;															// 날짜를 저장할 변수 생성
		Date reservation_Time = null;															// 시간을 저장할 변수 생성
						
		try {
			reservation_Date = date.parse((rmap.get("reservation_Date")).toString());			// HashMap객체에 저장된 날짜를 가져온다.
			reservation_Time = dateTime.parse((rmap.get("reservation_Time")).toString()+":00");	// HashMap객체에 저장된 시간을 가져온다. (시간, 분만 나오므로 뒤에 초는 00을 임의로 붙여준다.)
		} catch (ParseException e) {
			e.printStackTrace();																// 파싱 예외처리
		}
			
		rmap.put("pet_Index", rmap.get("pet_Index"));											// HashMap객체에 예약테이블에 입력할 pet_Index를 저장한다.	
		rmap.put("company_Index", rmap.get("company_Index"));									// HashMap객체에 예약테이블에 입력할 company_Index를 저장한다.	
		rmap.put("reservation_Date", date.format(reservation_Date));							// HashMap객체에 예약테이블에 입력할 reservation_Date를 저장한다.	
		rmap.put("reservation_Time", dateTime.format(reservation_Time));						// HashMap객체에 예약테이블에 입력할 reservation_Time를 저장한다.	
		rmap.put("reservation_DetailService",rmap.get("reservation_DetailService"));			// HashMap객체에 예약테이블에 입력할 reservation_DetailService를 저장한다.	
		rmap.put("reservation_Check",rmap.get("reservation_Check"));							// HashMap객체에 예약테이블에 입력할 reservation_Check를 저장한다.	

		this.reservationDao.insertTheReservation(rmap, customer.getCustomer_Index());	// form에 입력한 값 + customer_Index를 같이 Reservation테이블에 저장해준다.
		return "reserve/reserve_ok.tiles";	
	}

	@Override
	public String customer_reservecheck(Model model, HttpSession session, String petName) {
		// customer session이 존재할 경우
		if (session.getAttribute("customer") != null) {		
			// 가져온 session에서 customer를 가져온다. (DTO로 타입캐스팅)
			CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");		
			// customer에서 index값을 따로 int변수에 저장해준다.
			int customerIdx 	 = customer.getCustomer_Index();							
			// customerIdx에 해당하는 모든 펫 정보가 저장된다.	
			List<PetDTO> itsPets = this.petDao.listItsPets(customerIdx);
			
			if(petName == null || petName.equals("all")) {															
				// 모든 예약정보를 저장할 List객체이다.
				List<ReservationDTO> itsReservations = new ArrayList<ReservationDTO>();							
				// 해당 펫 인덱스가 존재하는 예약정보를 가져온다.
				for (PetDTO petDTO : itsPets) {
					List<ReservationDTO> temp = this.reservationDao.listItsCustReservations(petDTO.getPet_Index());	
					// itsReservations List객체에 저장한다.
					itsReservations.addAll(temp);																	
				}
				// 가져온 예약정보를 model객체에 저장한다.
				model.addAttribute("reservation", itsReservations);
				// 가져온 펫 정보를 model객체에 저장한다.		
				model.addAttribute("pet", itsPets);
			} else {
				Map<String, Object> petInfo = new HashMap<String, Object>();
				petInfo.put("customer_Index", customerIdx);
				petInfo.put("pet_Name", petName);
				
				List<ReservationDTO> reservations = this.reservationDao.customer_pet_reserve_check(petInfo);
				
				model.addAttribute("reservation", reservations);
				model.addAttribute("pet", itsPets);
			}	
		} 
		return "reserve/customer_reserve_check.tiles"; 	
	}

	@Override
	public List<ReservationDTO> company_reservecheck(Model model, HttpSession session,Criteria cri) {
																// company session이 존재할 경우
			CompanyDTO company = (CompanyDTO) session.getAttribute("company");									// 가져온 session에서 company를 가져온다. (DTO로 타입캐스팅)
			int company_Index = company.getCompany_Index();														// company에서 index값을 따로 int변수에 저장해준다.
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(this.reservationDao.countCompReservations(company_Index));
			
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("page",cri.getPage());
			map.put("perPageNum",cri.getPerPageNum());
			map.put("pageStart", cri.getPageStart());
			map.put("company_Index", company_Index);
																		// 가져온 예약정보를 model객체에 저장한다.
			model.addAttribute("pageMaker", pageMaker);
		
		return this.reservationDao.listItsCompReservations(map); 
	}

	@Override
	public String customer_reservation_cancel(Model model, HttpSession session, HttpServletRequest request, String index) {
		int reservationNum 	 = 	Integer.parseInt(index);							// 정상예약화면에서 쓰레기통 모양을 누르면 메서드가 실행되면서 reservation_Index가 넘어온다. (customer_reserve_check.jsp)
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");										// customer session을 가져온다.
		int customerIdx 	 = customer.getCustomer_Index();														// customer에서 index값을 따로 int변수에 저장해준다.
		
		List<PetDTO> petList = this.petDao.listItsPets(customerIdx);												// customerIdx에 해당하는 모든 펫 정보를 가져온다.
		List<ReservationDTO> itsReservations = new ArrayList<ReservationDTO>();;
		
		for(int i = 0; i < petList.size(); i++) {
			List<ReservationDTO> temp = this.reservationDao.listItsCustReservations(petList.get(i).getPet_Index());	// customerIdx에 가져온 펫들로 예약된 모든 예약목록을 가져온다.
			itsReservations.addAll(temp);	
		}

		boolean delete = false;
		
		for (ReservationDTO reservationDTO : itsReservations) {
			if (reservationDTO.getReservation_Index() == reservationNum) {
				this.reservationDao.cancelTheReservation(reservationNum);												// 취소하고자 하는 예약 인덱스와 동일한 예약은 테이블에서 reservation_Check = 'canceled'로 고친다.
				delete = true;
				break;
			}
		}		
			
		if(delete) {
			return "redirect:/customer_reserve_check";																	// 후에 예약확인화면으로 이동한다.
		} else {
			return null; 
		}
		
	}
}
