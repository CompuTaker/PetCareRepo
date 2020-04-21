package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dao.CustomerDAO;

@Controller
@SessionAttributes({ "customer", "company" })
public class MultiPartController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping(value = "/customer_signupDo", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public String signupDo(MultipartHttpServletRequest multipartHttpServletRequest,
			@RequestParam HashMap<String, Object> cmap) throws IOException {
		// 해당 이미지는, form에서 imageFile이라는 name으로 지정되었으므로, "imageFile"이란 Key로 해당 FileMap에
		// 저장된다!
		// MultipartHttpServletRequest는, 기존 HttpServletRequest를 가져다가,
		// multipart 데이터를 처리할 수 있게끔 이를 바꾸어놓고, 이를 통해, 이미지 등을 가져올 수 있게 된다!
		// request.getParameter("imageFile")의 multipart 버전!

		Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
		File imf = new File("test");
		// (multipartHttpServletRequest.getFile("imageFile")).transferTo(imf);
		
		if (fileMap.isEmpty()) { // if(imageFile == null) {
			System.out.println("NOTHING!!"); // null

		} else {
			byte[] imageFile = fileMap.get("imageFile").getBytes(); // 파일
//			FileOutputStream fos = new FileOutputStream(new File("너네로컬경로"));
//			fos.write(imageFile);
//			fos.close();
			
			System.out.println(imageFile.toString() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			String fileName = fileMap.get("imageFile").getName(); // 파일명
			System.out.println(fileName + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!이름이름");

			int dotIdx = fileName.lastIndexOf(".");
			String fileExtension = fileName.substring(dotIdx + 1).toLowerCase();

			if (!fileExtension.equals("jpg") && !fileExtension.equals("jpeg") && !fileExtension.equals("png")) {
				// Wrong file

				System.out.println("File Not Valid");
				cmap.put("customer_Image", imageFile);
				int res = this.customerDAO.insertTheCustomer(cmap);

				System.out.println("customer insert result => " + res);
			} else {
				// Normal image file
				System.out.println("BLONDIE!!!!!!!!!!!!!!"); // not null

				cmap.put("customer_Image", imageFile);
				int res = this.customerDAO.insertTheCustomer(cmap);
				System.out.println("customer insert result => " + res);
			}
		}

		return "redirect:/"; // index.jsp // views 폴더 내
	}

}