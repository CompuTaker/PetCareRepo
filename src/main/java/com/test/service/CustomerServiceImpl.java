package com.test.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.amazon.s3;
import com.test.dao.CustomerDAO;
import com.test.dao.PetDAO;
import com.test.dto.CustomerDTO;
import com.test.dto.PetDTO;

@Service
@SessionAttributes({ "customer", "company" }) // Model�뿉 ���옣�븳 媛믪쓣 http session�뿉 ���옣�븷 �닔 �엳寃� �빐二쇰뒗 Annotation
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private PetDAO petDao;

	private boolean isCustomerIdChecked = false; // 怨좉컼 ID媛� 以묐났�씤吏� �븘�땶吏� �솗�씤�븯�뒗 Boolean
	private boolean isCustomerOk = false; // 理쒖쥌�쟻�쑝濡� 以묐났�씤吏� �븘�땶吏� �솗�씤�븯�뒗 Boolean
	

	@Override
	public ModelAndView customer_signupDo(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap) { // form에서 입력한 값을 HashMap으로 묶어서 가져옴

		ModelAndView ok = new ModelAndView("customer/customer_signup_ok.tiles"); // 중복체크까지 정상적으로 처리한 후 회원가입 버튼을 눌렀을 때 나올
																					// 화면과 함께 ModelAndView객체 생성
		ModelAndView redirect = new ModelAndView("customer/customer_Signup.tiles"); // 중복체크를 하지 않았을 경우 나올 화면과 함께
																					// ModelAndView객체 생성
		redirect.addObject("message", "중복체크 해주세요."); // 중복체크를 하지 않았을 경우 띄울 메시지를 redirect ModelAndView에 저장

		if (isCustomerIdChecked) { // ID와 주민등록번호 중복체크를 정상적으로 실행했을 경우
			System.out.println("중복체크했네");
			if (isCustomerOk) { // 최종확인 Boolean도 true일 경우
				System.out.println("중복도 아니네");
				try {
					Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
					Date date = new Date();
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
					String subScribe_Date = dateformat.format(date);
					System.out.println(subScribe_Date);
					cmap.put("subscribe_Date", subScribe_Date);
					HashMap<String, Object> newCustomer = imageUpload(null, fileMap, multipartHttpServletRequest, cmap);
					this.customerDao.insertTheCustomer(newCustomer); // form�뿉 �엯�젰�븳 媛믪쓣 company�뀒�씠釉붿뿉 ���옣�븳�떎.
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ok; // customer_signup_ok.jsp화면을 띄운다.
			}
		}
		System.out.println("중복체크 안함");
		// 중복체크가 하나라도 안되었을 경우 모든 체크값을 false로 초기화하고 customer_signup.jsp화면을 띄운다.
		isCustomerIdChecked = false;
		isCustomerOk = false;
		return redirect;
	}

	private HashMap<String, Object> imageUpload(String existingImage, Map<String, MultipartFile> fileMap,
			MultipartHttpServletRequest multipartHttpServletRequest, HashMap<String, Object> cmap) throws IOException {

		String baseUrl = "https://s3.ap-northeast-2.amazonaws.com/petcare2020/";
		MultipartFile multipartFile = multipartHttpServletRequest.getFile("imageFile");
		String fileName = "";
		try {
			fileName = multipartFile.getOriginalFilename(); // 파일명
		} catch (NullPointerException e) {
			fileName = "/resources/images/profile.png";
		}

		String folderName = "profile";

		if (fileMap.isEmpty()) { // if(imageFile == null) {
			System.out.println("NOTHING!!"); // null

		} else {
			if (multipartFile.isEmpty()) {
				cmap.put("customer_Image", existingImage);
			} else {
				String fullFileName = baseUrl + folderName + "/" + (String) cmap.get("customer_Id") + "_" + fileName;
				// 확장자확인
				int dotIdx = fileName.lastIndexOf(".");
				String fileExtension = fileName.substring(dotIdx + 1).toLowerCase();
				// Wrong file
				if (!fileExtension.equals("jpg") && !fileExtension.equals("jpeg") && !fileExtension.equals("png")) {

					System.out.println("File Not Valid");
					// Normal image file
				} else {
					// �굹癒몄� �젙蹂� DB�뿉 �뾽濡쒕뱶
					cmap.put("customer_Image", fullFileName);
					s3 s3 = new s3();
					// 이미지는 3S에 업로드
					s3.uploadFile(multipartFile, folderName, (String) cmap.get("customer_Id"));
				}
			}
		}
		return cmap;
	}

	@ResponseBody
	@Override
	public String checkCustomerID(String customer_Id) {
		String idCheck = "";

		System.out.println(customer_Id);
		// 해당 메서드가 실행되었다는 것은 중복체크 버튼을 누른 것이기 때문에 true로 변경
		this.isCustomerIdChecked = true;
		// 해당 customer_Id가 있는지 customer테이블에서 확인해본다.
		CustomerDTO customer = this.customerDao.checkCustomerID(customer_Id);

		if (customer != null) { // customer테이블에 존재하면
			System.out.println("아디중복");
			this.isCustomerOk = false; // 아이디가 중복이므로 최종확인은 false
			idCheck = "0";
		} else {
			System.out.println("아디사용가능");
			this.isCustomerOk = true; // customer테이블에 존재하지 않으면 중복이 아니므로 true
			idCheck = "1";
		}
		System.out.println(this.isCustomerOk);

		return idCheck;
	}

	@Override
	public void insertTheCustomer(HashMap<String, Object> cmap) {
		this.customerDao.insertTheCustomer(cmap); // form�뿉 �엯�젰�븳 媛믪쓣 company�뀒�씠釉붿뿉 ���옣�븳�떎.
	}

	@Override
	public void updateCustomerInfo(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap) {
		Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();

		System.out.println("�븘�씠�뵒" + cmap.get("customer_Id"));
		String customerId = (String) cmap.get("customer_Id");

		CustomerDTO customer = this.customerDao.checkCustomerID(customerId);
		System.out.println("湲곗〈�씠誘몄� : " + customer.getCustomer_Image());

		String existingImage = customer.getCustomer_Image();
		try {
			HashMap<String, Object> modifyCustomer = imageUpload(existingImage, fileMap, multipartHttpServletRequest,
					cmap);
			this.customerDao.updateCustomerInfo(modifyCustomer); // 媛��졇�삩 cmap�뜲�씠�꽣瑜� 湲곗〈 怨좉컼 �뜲�씠�꽣�뿉 update�떆�궓�떎.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String profile(Model model, HttpSession session, String customer_Id) {
		
		int flag = customerDao.deleteTheCustomer(customer_Id);
		if (session.getAttribute("customer") != null && flag == 0) { // customer session�씠 議댁옱�븯�뒗 寃쎌슦
			try {
				CustomerDTO customer = (CustomerDTO) session.getAttribute("customer"); // customer session�쓣 DTO濡�
																						// ���엯罹먯뒪�똿�쓣
																						// �븯�뿬 customer�뿉 ���옣

				Map<String, String> loginInfo = new HashMap<String, String>(); // mapper�뿉 蹂��닔媛믪쓣 �븳 踰덉뿉 �쟾�떖�븯湲�
																				// �쐞�빐�꽌 �깮�꽦�븳 Map媛앹껜
				loginInfo.put("id", customer.getCustomer_Id()); // Map媛앹껜�뿉 Id媛믪쓣 ���옣�븳�떎.
				loginInfo.put("pw", customer.getCustomer_Password()); // Map媛앹껜�뿉 PW媛믪쓣 ���옣�븳�떎.

				CustomerDTO getCusotmerInfo = this.customerDao.listThisCustomer(loginInfo); // customer媛믪뿉�꽌 Index瑜�
																							// 媛�吏�怨�
																							// �삁�빟�맂 媛믪씠 �엳�뒗吏�
																							// customer�뀒�씠釉붿뿉�꽌
																							// �솗�씤�븳�떎.
				List<PetDTO> itspets = this.petDao.listItsPets(customer.getCustomer_Index()); // customer_Index�뿉
																								// �빐�떦�븯�뒗 紐⑤뱺
																								// �렖 �젙蹂대��
																								// pet�뀒�씠釉붿뿉�꽌
																								// 媛��졇�삩�떎.

				model.addAttribute("pet", itspets); // model媛앹껜�뿉 媛��졇�삩 pet�젙蹂대�� ���옣�븳�떎.
				model.addAttribute("customer", getCusotmerInfo); // model媛앹껜�뿉 媛��졇�삩 customer�젙蹂대�� ���옣�븳�떎.

				return "customer/customer_Profile.tiles"; // customerprofile.jsp
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		} else {
			// 怨좉컼 session�씠 議댁옱�븯吏� �븡�뒗�뜲
			// customerprofile requestMapping�씠 �떎�뻾�릺�뿀�쓣 寃쎌슦
			// �삁�쇅泥섎━
		}
		return null; // �옒紐삳맂 �젒洹쇱씪 �븣
	}

	@Override
	public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request) {
		Map<String, String> customer = new HashMap<String, String>(); // �꽆�뼱�삩 蹂��닔瑜� �븳 踰덉뿉 ���옣�븯湲� �쐞�빐�꽌 留뚮뱺 Map媛앹껜
		customer.put("customer_Name", request.getParameter("customer_Name")); // Map媛앹껜�뿉 Name�쓣 ���옣�븳�떎.
		customer.put("customer_PhoneNumber", request.getParameter("customer_PhoneNumber")); // Map媛앹껜�뿉 PhoneNumber瑜�
																							// ���옣�븳�떎.

		System.out.println(request.getParameter("customer_Name"));
		System.out.println(request.getParameter("customer_PhoneNumber"));

		try {
			CustomerDTO customerDto = this.customerDao.searchCustomerID(customer); // form�뿉 �엯�젰�맂 name怨� phoneNumber瑜�
																					// 媛�吏�怨� customer�뀒�씠釉붿뿉 �엳�뒗吏�
																					// �솗�씤�빐蹂몃떎.
			request.setAttribute("customerId", customerDto.getCustomer_Id());
			mv.setViewName("customer/customer_show_id.tiles"); // ModelAndView媛앹껜�뿉 �떎�뻾�븷 �솕硫댁쓣 �뀑�똿�븳�떎.
		} catch (NullPointerException e) {
			mv.addObject("message", 1);
			mv.setViewName("redirect:/search_id");
		}
		return mv;
	}

	@Override
	public String search_pw_customer(Model model, HttpServletRequest request) {
		Map<String, String> customer = new HashMap<String, String>(); // �꽆�뼱�삩 蹂��닔瑜� �븳 踰덉뿉 ���옣�븯湲� �쐞�빐�꽌 留뚮뱺 Map媛앹껜
		customer.put("customer_Name", request.getParameter("customer_Name")); // Map媛앹껜�뿉 Name�쓣 ���옣�븳�떎.
		customer.put("customer_Id", request.getParameter("customer_Id")); // Map媛앹껜�뿉 ID瑜� ���옣�븳�떎.
		customer.put("customer_PhoneNumber", request.getParameter("customer_PhoneNumber")); // Map媛앹껜�뿉 PhoneNumber瑜�
																							// ���옣�븳�떎.

		System.out.println(request.getParameter("customer_Name"));

		String url = "";

		try {
			CustomerDTO customerDto = this.customerDao.searchCustomerPW(customer); // form�뿉 �엯�젰�맂 name怨� id,
																					// phoneNumber瑜� 媛�吏�怨�
																					// customer�뀒�씠釉붿뿉 �엳�뒗吏� �솗�씤�빐蹂몃떎.
			String passwordArr[] = customerDto.getCustomer_Password().split(""); // 媛��졇�삩 �뙣�뒪�썙�뱶瑜� �븯�굹�뵫 �쑐�뼱�꽌
																					// 諛곗뿴濡�
			// ���옣�븳�떎.
			String password = ""; // 釉붾윭泥섎━ �썑 �뙣�뒪�썙�뱶瑜� ���옣�븷 蹂��닔

			for (int i = 0; i < passwordArr.length; i++) {
				if (i > 2) {
					password += "*"; // �뙣�뒪�썙�뱶�쓽 �븵 2�옄由щ쭔 蹂댁뿬二쇨퀬 �굹癒몄��뒗 *濡� 釉붾윭泥섎━�븳�떎.
				} else {
					password += passwordArr[i];
				}
			}
			request.setAttribute("password", password); // company_show_pw.jsp�뿉�꽌 getAttribute濡� 媛믪쓣 �샇異쒗븯湲� �쐞�븳 蹂��닔
			url = "customer/customer_show_pw.tiles";
		} catch (NullPointerException e) {
			model.addAttribute("message", 1);
			url = "redirect:/search_pw";
		}
		return url;
	}

	@Override
	public ModelAndView customer_modify(ModelAndView mv, HttpSession session) {
		CustomerDTO customer = (CustomerDTO) session.getAttribute("customer"); // customer session�쓣 媛��졇�삩�떎.

		Map<String, String> loginInfo = new HashMap<String, String>(); // mapper�뿉 蹂��닔媛믪쓣 �븳 踰덉뿉 �쟾�떖�븯湲� �쐞�빐�꽌 �깮�꽦�븳
																		// Map媛앹껜
		loginInfo.put("id", customer.getCustomer_Id()); // Map媛앹껜�뿉 Id媛믪쓣 ���옣�븳�떎.
		loginInfo.put("pw", customer.getCustomer_Password()); // Map媛앹껜�뿉 PW媛믪쓣 ���옣�븳�떎.

		customer = this.customerDao.listThisCustomer(loginInfo); // customer媛믪뿉�꽌 Index瑜� 媛�吏�怨� �삁�빟�맂 媛믪씠 �엳�뒗吏�
																	// customer�뀒�씠釉붿뿉�꽌
																	// �솗�씤�븳�떎.
		mv.addObject("customer", customer); // ModelAndView�뿉 媛��졇�삩 customer�젙蹂대�� ���옣�븳�떎.
		mv.setViewName("customer/customer_modify.tiles"); // �떎�뻾�떆耳� 以� �솕硫�(customer_modify.jsp)�룄 �뀑�똿�빐以��떎.
		return mv;

	}

	@Override
	public void updateCustomerInfo(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap, Model model) {
		Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
		Map<String, String> loginInfo = new HashMap<String, String>(); // mapper�뿉 蹂��닔媛믪쓣 �븳 踰덉뿉 �쟾�떖�븯湲� �쐞�빐�꽌 �깮�꽦�븳
																		// Map媛앹껜
		loginInfo.put("id", (String) cmap.get("customer_Id")); // Map媛앹껜�뿉 Id媛믪쓣 ���옣�븳�떎.
		loginInfo.put("pw", (String) cmap.get("customer_Password"));

		CustomerDTO customer = this.customerDao.checkCustomerID((String) cmap.get("customer_Id"));
		System.out.println("湲곗〈�씠誘몄� : " + customer.getCustomer_Image());

		String existingImage = customer.getCustomer_Image();
		try {
			HashMap<String, Object> modifyCustomer = imageUpload(existingImage, fileMap, multipartHttpServletRequest,
					cmap);
			this.customerDao.updateCustomerInfo(modifyCustomer); // 媛��졇�삩 cmap�뜲�씠�꽣瑜� 湲곗〈 怨좉컼 �뜲�씠�꽣�뿉 update�떆�궓�떎.
			model.addAttribute("customer", this.customerDao.listThisCustomer(loginInfo)); // �깉濡� 諛붾�� 怨좉컼 �젙蹂대줈
																							// customer
																							// attribute瑜� �뾽�뜲�씠�듃
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkPW(String customer_Id, String customer_Password) {
		return customerDao.checkPW(customer_Id, customer_Password);
	}

	@Override
	public void deleteTheCustomer(String customer_Id, SessionStatus status) {
		int flag = customerDao.deleteTheCustomer(customer_Id);
		if (flag == 1) {
			status.setComplete();
			System.out.println("세션 만료");
		}
	}
	/*
	 * 탈퇴한 사람들의 리스트를 가져오는 메서드
	 */
	@Override
	public List<CustomerDTO> getDropCustomers() {
		return this.customerDao.getDropCustomers();
	}

}
