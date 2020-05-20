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
			@RequestParam HashMap<String, Object> cmap) { // form�뿉�꽌 �엯�젰�븳 媛믪쓣 HashMap�쑝濡� 臾띠뼱�꽌 媛��졇�샂

		ModelAndView ok = new ModelAndView("customer/customer_signup_ok.tiles"); // 以묐났泥댄겕源뚯� �젙�긽�쟻�쑝濡� 泥섎━�븳 �썑
																					// �쉶�썝媛��엯 踰꾪듉�쓣 �닃���쓣 �븣 �굹�삱
																					// �솕硫닿낵 �븿猿� ModelAndView媛앹껜 �깮�꽦
		ModelAndView redirect = new ModelAndView("customer/customer_Signup.tiles"); // 以묐났泥댄겕瑜� �븯吏� �븡�븯�쓣 寃쎌슦 �굹�삱
																					// �솕硫닿낵 �븿猿�
																					// ModelAndView媛앹껜 �깮�꽦
		redirect.addObject("message", "以묐났泥댄겕 �빐二쇱꽭�슂."); // 以묐났泥댄겕瑜� �븯吏� �븡�븯�쓣 寃쎌슦 �쓣�슱 硫붿떆吏�瑜� redirect
															// ModelAndView�뿉 ���옣

		if (isCustomerIdChecked) { // ID�� 二쇰�쇰벑濡앸쾲�샇 以묐났泥댄겕瑜� �젙�긽�쟻�쑝濡� �떎�뻾�뻽�쓣 寃쎌슦
			if (isCustomerOk) { // 理쒖쥌�솗�씤 Boolean�룄 true�씪 寃쎌슦
				try {
					Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
					HashMap<String, Object> newCustomer = imageUpload(null, fileMap, multipartHttpServletRequest, cmap);
					this.customerDao.insertTheCustomer(newCustomer); // form�뿉 �엯�젰�븳 媛믪쓣 company�뀒�씠釉붿뿉 ���옣�븳�떎.
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ok; // customer_signup_ok.jsp�솕硫댁쓣 �쓣�슫�떎.
			}
		}
		System.out.println("以묐났泥댄겕 �븞�븿");
		// 以묐났泥댄겕媛� �븯�굹�씪�룄 �븞�릺�뿀�쓣 寃쎌슦 紐⑤뱺 泥댄겕媛믪쓣 false濡� 珥덇린�솕�븯怨�
		// customer_signup.jsp�솕硫댁쓣 �쓣�슫�떎.
		isCustomerIdChecked = false;
		isCustomerOk = false;
		return redirect;
	}

	private HashMap<String, Object> imageUpload(String existingImage, Map<String, MultipartFile> fileMap,
			MultipartHttpServletRequest multipartHttpServletRequest, HashMap<String, Object> cmap) throws IOException {

		String baseUrl = "https://s3.ap-northeast-2.amazonaws.com/petcare2020/";
		MultipartFile multipartFile = multipartHttpServletRequest.getFile("imageFile");
		String fileName = multipartFile.getOriginalFilename(); // �뙆�씪紐�

		if (fileMap.isEmpty()) { // if(imageFile == null) {
			System.out.println("NOTHING!!"); // null

		} else {
			if (multipartFile.isEmpty()) {
				System.out.println("�꼸�씠�깘???????" + existingImage);
				cmap.put("customer_Image", existingImage);
				System.out.println(cmap.get("customer_Image"));
			} else {
				String fullFileName = baseUrl + "profile_" + (String) cmap.get("customer_Id") + "_" + fileName;
				// �솗�옣�옄�솗�씤
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
					// �씠誘몄��뒗 3S�뿉 �뾽濡쒕뱶
					s3.uploadFile(multipartFile, (String) cmap.get("customer_Id"));
				}
			}
		}
		return cmap;
	}

	@Override
	@ResponseBody
	public void checkCustomerID(String customer_Id) {
		System.out.println(customer_Id);

		this.isCustomerIdChecked = true; // �빐�떦 硫붿꽌�뱶媛� �떎�뻾�릺�뿀�떎�뒗 寃껋� 以묐났泥댄겕 踰꾪듉�쓣 �늻瑜� 寃껋씠湲� �븣臾몄뿉 true濡� 蹂�寃�
		CustomerDTO customer = this.customerDao.checkCustomerID(customer_Id); // �빐�떦 customer_Id媛� �엳�뒗吏�
																				// customer�뀒�씠釉붿뿉�꽌
																				// �솗�씤�빐蹂몃떎.
		if (customer != null) { // customer�뀒�씠釉붿뿉 議댁옱�븯硫�
			System.out.println("�븘�뵒以묐났泥댄겕�븞�맖");
			this.isCustomerOk = false; // �븘�씠�뵒媛� 以묐났�씠誘�濡� 理쒖쥌�솗�씤�� false
		}
		System.out.println("�븘�뵒以묐났泥댄겕�셿猷�");
		this.isCustomerOk = true; // customer�뀒�씠釉붿뿉 議댁옱�븯吏� �븡�쑝硫� 以묐났�씠 �븘�땲誘�濡� true

		System.out.println(this.isCustomerOk);
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
	public String profile(Model model, HttpSession session) {
		if (session.getAttribute("customer") != null) { // customer session�씠 議댁옱�븯�뒗 寃쎌슦
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
			} catch (IllegalArgumentException e) {
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

		CustomerDTO customerDto = this.customerDao.searchCustomerID(customer); // form�뿉 �엯�젰�맂 name怨� phoneNumber瑜�
																				// 媛�吏�怨�
																				// customer�뀒�씠釉붿뿉 �엳�뒗吏� �솗�씤�빐蹂몃떎.

		mv.addObject("customer", customerDto); // 媛��졇�삩 customer媛믪쓣 ModelAndView媛앹껜�뿉 ���옣�븳�떎.
		mv.setViewName("customer/customer_show_id.tiles"); // ModelAndView媛앹껜�뿉 �떎�뻾�븷 �솕硫댁쓣 �뀑�똿�븳�떎.
		return mv;
	}

	@Override
	public String search_pw_customer(ModelAndView mv, HttpServletRequest request) {
		Map<String, String> customer = new HashMap<String, String>(); // �꽆�뼱�삩 蹂��닔瑜� �븳 踰덉뿉 ���옣�븯湲� �쐞�빐�꽌 留뚮뱺 Map媛앹껜
		customer.put("customer_Name", request.getParameter("customer_Name")); // Map媛앹껜�뿉 Name�쓣 ���옣�븳�떎.
		customer.put("customer_Id", request.getParameter("customer_Id")); // Map媛앹껜�뿉 ID瑜� ���옣�븳�떎.
		customer.put("customer_PhoneNumber", request.getParameter("customer_PhoneNumber")); // Map媛앹껜�뿉 PhoneNumber瑜�
																							// ���옣�븳�떎.

		CustomerDTO customerDto = this.customerDao.searchCustomerPW(customer); // form�뿉 �엯�젰�맂 name怨� id, phoneNumber瑜�
																				// 媛�吏�怨�
																				// customer�뀒�씠釉붿뿉 �엳�뒗吏� �솗�씤�빐蹂몃떎.

		String passwordArr[] = customerDto.getCustomer_Password().split(""); // 媛��졇�삩 �뙣�뒪�썙�뱶瑜� �븯�굹�뵫 �쑐�뼱�꽌 諛곗뿴濡�
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
		return "customer/customer_show_pw.tiles";
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
	public void deleteTheCustomer(String customer_Id) {
		customerDao.deleteTheCustomer(customer_Id);

	}

}
