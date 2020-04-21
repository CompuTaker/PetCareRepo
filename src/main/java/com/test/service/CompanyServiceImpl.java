package com.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.CompanyDAO;
import com.test.dao.ReservationDAO;
import com.test.dto.CompanyDTO;
import com.test.dto.ReservationDTO;


@Service
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private ReservationDAO reservationDao;
	
	private boolean isCompanyIdChecked 		= false;	// 기업 ID가 중복인지 아닌지 확인하는 Boolean
	private boolean isCompanyComNumChecked  = false;	// 기업 사업자등록번호가 중복인지 아닌지 확인하는 Boolean
	private boolean isCompanyOk 			= false;	// 최종적으로 중복인지 아닌지 확인하는 Boolean
	
	@Override
	public void insertTheCompany(HashMap<String, Object> cmap) {
		this.companyDao.insertTheCompany(cmap);
	}

	@Override
	public ModelAndView searchId(ModelAndView mv, HttpServletRequest request) {	
		Map<String, String> company = new HashMap<String, String>();		
		company.put("company_Number", request.getParameter("company_Number"));		// company_Number에 저장된다.
		CompanyDTO companyDto = this.companyDao.searchCompanyID(company);			// company_Number에 맞는 id가 있는지 company테이블에서 찾아본다.
		mv.addObject("company", companyDto);
		mv.setViewName("company/company_show_id.tiles");
		return mv;
				
	}
	
	@Override
	public List<CompanyDTO> listsCompany(String companyType) {		
		return this.companyDao.listsCompany(companyType);
	}

	@Override
	public CompanyDTO listThisCompany(int companyIdx) {
		return this.companyDao.listThisCompany(companyIdx);
	}

	@Override
	public ModelAndView company_signupDo(HashMap<String, Object> cmap) {
		ModelAndView ok 	  = new ModelAndView("company/company_signup_ok.tiles");					// 중복체크까지 정상적으로 처리한 후 회원가입 버튼을 눌렀을 때 나올 화면과 함께 ModelAndView객체 생성
		ModelAndView redirect = new ModelAndView("company/company_Signup.tiles");					// 중복체크를 하지 않았을 경우 나올 화면과 함께 ModelAndView객체 생성
		redirect.addObject("message", "중복체크 해주세요.");									// 중복체크를 하지 않았을 경우 띄울 메시지를 redirect ModelAndView에 저장

		if (isCompanyIdChecked && isCompanyComNumChecked) {								// ID와 사업자등록번호 중복체크를 정상적으로 실행했을 경우
			if(isCompanyOk) {															// 최종확인 Boolean도 true일 경우
				this.companyDao.insertTheCompany(cmap);									// form에 입력한 값을 company테이블에 저장한다.
				return ok;																// company_signup_ok.jsp화면을 띄운다.
			}
		}
		
		System.out.println("중복체크 안함");
		// 중복체크가 하나라도 안되었을 경우 모든 체크값을 false로 초기화하고 company_signup.jsp화면을 띄운다.
		isCompanyIdChecked 		= false;
		isCompanyComNumChecked  = false;
		isCompanyOk 			= false;
		return redirect;
	}

	@Override
	@ResponseBody
	public void comIdCheck(String company_Id) {
		isCompanyIdChecked = true;												// 해당 메서드가 실행되었다는 것은 중복체크 버튼을 누른 것이기 때문에 true로 변경
		CompanyDTO company = this.companyDao.checkCompanyID(company_Id);	// 해당 company_Id가 있는지 company테이블에서 확인해본다.
		if (company != null) {													// company테이블에 존재하면
			isCompanyOk = false;												// 아이디가 중복이므로 최종확인은 false
		}
		isCompanyOk = true;														// company테이블에 존재하지 않으면 중복이 아니므로 true
	}
	
	@Override
	@ResponseBody
	public void comNumCheck(int company_Number) {		
		isCompanyComNumChecked = true;												// 해당 메서드가 실행되었다는 것은 중복체크 버튼을 누른 것이기 때문에 true로 변경
		CompanyDTO company = this.companyDao.checkCompanyNumber(company_Number);	// 해당 company_Number가 있는지 company테이블에서 확인해본다.
		if (company != null) {														// company테이블에 존재하면
			isCompanyOk = false;													// 사업자등록번호가 중복이므로 최종확인은 false
		}
		isCompanyOk = true;															// company테이블에 존재하지 않으면 중복이 아니므로 true
	}

	@Override
	public String search_pw_company(ModelAndView mv, HttpServletRequest request) {
		Map<String, String> company = new HashMap<String, String>();								// 넘어온 변수를 한 번에 mapper에 넘겨주기 위해서 만든 Map객체
		company.put("company_UserName", request.getParameter("company_UserName"));					// Map객체에 이름을 저장한다.
		company.put("company_Id", request.getParameter("company_Id"));								// Map객체에 아이디를 저장한다.
		company.put("company_UserPhoneNumber", request.getParameter("company_UserPhoneNumber"));	// Map객체에 핸드폰번호를 저장한다.
		
		CompanyDTO companyDto = this.companyDao.searchCompanyPW(company);
		
		String passwordArr[] = companyDto.getCompany_Password().split("");							// 가져온 패스워드를 하나씩 뜯어서 배열로 저장한다.
		String password = "";																		// 블러처리 후 패스워드를 저장할 변수
		
		for (int i = 0; i < passwordArr.length; i++) {								
			if (i > 2) {
				password += "*";																	// 패스워드의 앞 2자리만 보여주고 나머지는 *로 블러처리한다.
			} else {
				password += passwordArr[i];
			}
		}
		
		request.setAttribute("password", password);													// company_show_pw.jsp에서 getAttribute로 값을 호출하기 위한 변수
		return "company/company_show_pw.tiles";
	}

	@Override
	public ModelAndView profile(ModelAndView mv, HttpSession session) {
		if (session.getAttribute("company") != null) {																				// company session이 존재하는 경우
			try {
				CompanyDTO company = (CompanyDTO) session.getAttribute("company");													// company session을 DTO로 타입캐스팅을 하여 company에 저장
				List<ReservationDTO> companyReserve = this.reservationDao.listItsCompReservations(company.getCompany_Index());	// company값에서 Index를 가지고 예약된 값이 있는지 Reservation테이블에서 확인한다.

				mv.addObject("reserve", companyReserve);																			// model객체에 가져온 예약리스트를 저장한다.
				mv.setViewName("company/company_Profile.tiles");																					// 실행할 view인 companyprofile.jsp를 설정해준다.
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 기업고객 session이 존재하지 않는데 
			// companyprofile requestMapping이 실행되었을 경우
			// 예외처리
		}
		return mv; // companyprofile.jsp
	}

	@Override
	public ModelAndView company_modify(ModelAndView mv, HttpSession session) {
		CompanyDTO company = (CompanyDTO) session.getAttribute("company"); // company session을 가져온다.
		Map<String, String> loginInfo = new HashMap<String, String>();	
		loginInfo.put("id", company.getCompany_Id());
		loginInfo.put("pw", company.getCompany_Password());
		company = this.companyDao.listThisCompany(loginInfo); 	// company에서
		mv.addObject("company", company); 						// ModelAndView에 가져온 company 정보를 저장한다.
		mv.setViewName("company/company_modify.tiles"); 		// 실행시켜 줄 화면(company_modify.jsp)도 셋팅해준다.
		return mv;
	}
}
