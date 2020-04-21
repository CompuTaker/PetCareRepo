package com.test.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.amazon.s3;
import com.test.dao.CustomerDAO;
import com.test.dao.PetDAO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;

@Service
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private PetDAO petDao;
	
	private boolean isCustomerIdChecked				 = false;	// 고객 ID가 중복인지 아닌지 확인하는 Boolean
	private boolean isCustomerResidentNumberChecked  = false;	// 고객 주민등록번호가 중복인지 아닌지 확인하는 Boolean
	private boolean isCustomerOk 					 = false;	// 최종적으로 중복인지 아닌지 확인하는 Boolean
	
	@RequestMapping(value = "/customer_signupDo", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public ModelAndView customer_signupDo(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap) { // form에서 입력한 값을 HashMap으로 묶어서 가져옴
			
		ModelAndView ok = new ModelAndView("customer/customer_signup_ok.tiles"); 	// 중복체크까지 정상적으로 처리한 후 회원가입 버튼을 눌렀을 때 나올
																					// 화면과 함께 ModelAndView객체 생성
		ModelAndView redirect = new ModelAndView("customer/customer_Signup.tiles"); // 중복체크를 하지 않았을 경우 나올 화면과 함께
																					// ModelAndView객체 생성
		redirect.addObject("message", "중복체크 해주세요."); 								// 중복체크를 하지 않았을 경우 띄울 메시지를 redirect ModelAndView에 저장

		if (isCustomerIdChecked && isCustomerResidentNumberChecked) { 				// ID와 주민등록번호 중복체크를 정상적으로 실행했을 경우
			if (isCustomerOk) { // 최종확인 Boolean도 true일 경우
				try {
					Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
					HashMap<String, Object> newCustomer = imageUpload(fileMap,multipartHttpServletRequest,cmap);
					this.customerDao.insertTheCustomer(newCustomer); 				// form에 입력한 값을 company테이블에 저장한다.
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ok; // customer_signup_ok.jsp화면을 띄운다.
			}
		}
		System.out.println("중복체크 안함");
		// 중복체크가 하나라도 안되었을 경우 모든 체크값을 false로 초기화하고 customer_signup.jsp화면을 띄운다.
		isCustomerIdChecked = false;
		isCustomerResidentNumberChecked = false;
		isCustomerOk = false;
		return redirect;
	}
	
	private HashMap<String, Object> imageUpload(Map<String, MultipartFile> fileMap,
			MultipartHttpServletRequest multipartHttpServletRequest, HashMap<String, Object> cmap) throws IOException {
		
		String baseUrl = "https://s3.ap-northeast-2.amazonaws.com/petcare2020/";
		
		if (fileMap.isEmpty()) { // if(imageFile == null) {
			System.out.println("NOTHING!!"); // null

		} else {

			MultipartFile multipartFile = multipartHttpServletRequest.getFile("imageFile");
			String fileName = multipartFile.getOriginalFilename(); // 파일명
			String fullFileName = baseUrl + fileName;

			// 확장자확인
			int dotIdx = fileName.lastIndexOf(".");
			String fileExtension = fileName.substring(dotIdx + 1).toLowerCase();
			// Wrong file
			if (!fileExtension.equals("jpg") && !fileExtension.equals("jpeg") && !fileExtension.equals("png")) {

				System.out.println("File Not Valid");
				// Normal image file
			} else {
				// 나머지 정보 DB에 업로드
				cmap.put("customer_Image", fullFileName);
				// 이미지는 3S에 업로드
				s3.uploadFile(multipartFile);
			}
		}
		return cmap;
	}

	@Override
	@ResponseBody
	public void checkCustomerID(String customer_Id) {
		this.isCustomerIdChecked = true;											// 해당 메서드가 실행되었다는 것은 중복체크 버튼을 누른 것이기 때문에 true로 변경
		CustomerDTO customer = this.customerDao.checkCustomerID(customer_Id);		// 해당 customer_Id가 있는지 customer테이블에서 확인해본다.	
		if (customer != null) {														// customer테이블에 존재하면
			System.out.println("아디중복체크안됨");
			this.isCustomerOk = false;												// 아이디가 중복이므로 최종확인은 false
		}
		System.out.println("아디중복체크완료");
		this.isCustomerOk = true;													// customer테이블에 존재하지 않으면 중복이 아니므로 true
	}

	@Override
	@ResponseBody
	public void checkCustomerResident(String customer_ResidentNumber) {
		this.isCustomerResidentNumberChecked = true;													// 해당 메서드가 실행되었다는 것은 중복체크 버튼을 누른 것이기 때문에 true로 변경
		CustomerDTO customer = this.customerDao.checkCustomerResident(customer_ResidentNumber);			// 해당 customer_ResidentNumber가 있는지 customer테이블에서 확인해본다.	
		if (customer != null) {																			// customer테이블에 존재하면
			System.out.println("주번중복체크안됨");
			this.isCustomerOk = false;																	// 주민등록번호가 중복이므로 최종확인은 false
		}
		System.out.println("주번중복체크완료");
		this.isCustomerOk = true;																		// customer테이블에 존재하지 않으면 중복이 아니므로 true
	}
	
	@Override
	public void insertTheCustomer(HashMap<String, Object> cmap) {
		this.customerDao.insertTheCustomer(cmap);				// form에 입력한 값을 company테이블에 저장한다.
	}
	
	@Override
	public void updateCustomerInfo(HashMap<String, Object> cmap) {
		this.customerDao.updateCustomerInfo(cmap);		
	}
	
	@Override
	public String profile(Model model, HttpSession session) {
		if (session.getAttribute("customer") != null) {												// customer session이 존재하는 경우
			try {		
				CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");				// customer session을 DTO로 타입캐스팅을 하여 customer에 저장
				
				Map<String, String> loginInfo = new HashMap<String, String>();						// mapper에 변수값을 한 번에 전달하기 위해서 생성한 Map객체 
				loginInfo.put("id", customer.getCustomer_Id());										// Map객체에 Id값을 저장한다.
				loginInfo.put("pw", customer.getCustomer_Password()	);								// Map객체에 PW값을 저장한다.
					
				CustomerDTO getCusotmerInfo = this.customerDao.listThisCustomer(loginInfo);			// customer값에서 Index를 가지고 예약된 값이 있는지 customer테이블에서 확인한다.
				List<PetDTO> itspets = this.petDao.listItsPets(customer.getCustomer_Index());		// customer_Index에 해당하는 모든 펫 정보를  pet테이블에서 가져온다.
				
				model.addAttribute("pet", itspets);													// model객체에 가져온 pet정보를 저장한다.
				model.addAttribute("customer", getCusotmerInfo);									// model객체에 가져온 customer정보를 저장한다.	
				
				return "customer/customer_Profile.tiles"; 			// customerprofile.jsp 
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		} else {
			// 고객 session이 존재하지 않는데 
			// customerprofile requestMapping이 실행되었을 경우
			// 예외처리
		}
		return null;								// 잘못된 접근일 때
	}

	@Override
	public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request) {
		Map<String, String> customer = new HashMap<String, String>();						// 넘어온 변수를 한 번에 저장하기 위해서 만든 Map객체
		customer.put("customer_Name", request.getParameter("customer_Name"));				// Map객체에 Name을 저장한다.
		customer.put("customer_PhoneNumber", request.getParameter("customer_PhoneNumber"));	// Map객체에 PhoneNumber를 저장한다.
		
		CustomerDTO customerDto = this.customerDao.searchCustomerID(customer);				// form에 입력된 name과 phoneNumber를 가지고 customer테이블에 있는지 확인해본다.
  
		mv.addObject("customer", customerDto);												// 가져온 customer값을 ModelAndView객체에 저장한다.
		mv.setViewName("customer/customer_show_id.tiles");									// ModelAndView객체에 실행할 화면을 셋팅한다.
		return mv;
	}

	@Override
	public String search_pw_customer(ModelAndView mv, HttpServletRequest request) {
		Map<String, String> customer = new HashMap<String, String>();							// 넘어온 변수를 한 번에 저장하기 위해서 만든 Map객체
		customer.put("customer_Name", request.getParameter("customer_Name"));					// Map객체에 Name을 저장한다.
		customer.put("customer_Id", request.getParameter("customer_Id"));						// Map객체에 ID를 저장한다.
		customer.put("customer_PhoneNumber", request.getParameter("customer_PhoneNumber"));		// Map객체에 PhoneNumber를 저장한다.

		CustomerDTO customerDto = this.customerDao.searchCustomerPW(customer);					// form에 입력된 name과 id, phoneNumber를 가지고 customer테이블에 있는지 확인해본다.	
		
		String passwordArr[] = customerDto.getCustomer_Password().split("");					// 가져온 패스워드를 하나씩 뜯어서 배열로 저장한다.
		String password = "";																	// 블러처리 후 패스워드를 저장할 변수

		for (int i = 0; i < passwordArr.length; i++) {
			if (i > 2) {	
				password += "*";																// 패스워드의 앞 2자리만 보여주고 나머지는 *로 블러처리한다.
			} else {
				password += passwordArr[i];
			}
		}

		request.setAttribute("password", password);												// company_show_pw.jsp에서 getAttribute로 값을 호출하기 위한 변수
		return "customer/customer_show_pw.tiles";
	}

	@Override
	public ModelAndView customer_modify(ModelAndView mv, HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer");			// customer session을 가져온다.
		
		Map<String, String> loginInfo = new HashMap<String, String>();					// mapper에 변수값을 한 번에 전달하기 위해서 생성한 Map객체 
		loginInfo.put("id", customer.getCustomer_Id());									// Map객체에 Id값을 저장한다.
		loginInfo.put("pw", customer.getCustomer_Password()	);							// Map객체에 PW값을 저장한다.
		
		customer = this.customerDao.listThisCustomer(loginInfo);						// customer값에서 Index를 가지고 예약된 값이 있는지 customer테이블에서 확인한다.
		mv.addObject("customer", customer);												// ModelAndView에 가져온 customer정보를 저장한다.
		mv.setViewName("customer/customer_modify.tiles");							// 실행시켜 줄 화면(customer_modify.jsp)도 셋팅해준다.
		return mv;
	}
}
