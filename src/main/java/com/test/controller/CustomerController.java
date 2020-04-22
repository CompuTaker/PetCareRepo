package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.service.CustomerService;

@Controller										//Spring이 해당 클래스가 Controller인 걸 알려주는 Annotation
@SessionAttributes({ "customer", "company" })	// Model에 저장한 값을 http session에 저장할 수 있게 해주는 Annotation
public class CustomerController {

	 @Inject    //서비스를 호출하기 위해서 의존성을 주입
	 private   JavaMailSender mailSender;     //메일 서비스를 사용하기 위해 의존성을 주입함.
	
	@Autowired
	private CustomerService customerService;
	
	/*
	 * 고객 회원가입을 누르고 정보를 입력하고 회원가입 버튼을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping(value = "/customer_signupDo", method = RequestMethod.POST, headers = ("content-type=multipart/*"))
	public ModelAndView customer_signupDo(@RequestParam HashMap<String, Object> cmap, MultipartHttpServletRequest multipartHttpServletRequest) {	//form에서 입력한 값을 HashMap으로 묶어서 가져옴
		return this.customerService.customer_signupDo(multipartHttpServletRequest, cmap);
	}
	
	/*
	 * 고객 회원 가입 시 아이디가 중복되었는지 확인해주는 메서드
	 */
	@RequestMapping(value = "/customer_checkId", method = RequestMethod.GET)
	public void idCheck(@RequestParam("customer_Id") String customer_Id) {			// customer_signup.jsp에서 name이 customer_Id인 값을 가져와 String값으로 저장한다.		
		this.customerService.checkCustomerID(customer_Id);							// 해당 customer_Id가 있는지 customer테이블에서 확인해본다.
	}

	/*
	 * 고객 회원 가입 시 주민등록번호가 중복되었는지 확인해주는 메서드
	 */
	@RequestMapping(value = "/customer_chekResidentNumber", method = RequestMethod.GET)
	public void residentNumCheck(@RequestParam("customer_ResidentNumber") String customer_ResidentNumber) {	// customer_signup.jsp에서 name이 customer_ResidentNumber인 값을 가져와 String값으로 저장한다.
		this.customerService.checkCustomerResident(customer_ResidentNumber);								// 해당 customer_ResidentNumber가 있는지 customer테이블에서 확인해본다.	
	}
	@RequestMapping( value = "/send_email" , method=RequestMethod.POST )
    public void mailSending(@RequestParam("customer_Email") String customer_Email, HttpServletResponse response_email) throws IOException {
        Random r = new Random();
        int dice = r.nextInt(900000) + 100000;
        
        String setfrom = "mjuteamproject2020@gamil.com";
        String tomail = customer_Email; // 받는 사람 이메일
        String title = "회원가입 인증 이메일"; // 제목
        String content =
        
        System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
        
        System.getProperty("line.separator")+
                
        "Pet Care 홈페이지 입니다."
        
        +System.getProperty("line.separator")+
        
        System.getProperty("line.separator")+

        " 인증번호는 " +dice+ " 입니다. "
        
        +System.getProperty("line.separator")+
        
        System.getProperty("line.separator")+
        
        "받으신 인증번호를 홈페이지에 입력해주세요. "; // 내용
        
        
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    true, "UTF-8");

            messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(tomail); // 받는사람 이메일
            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
            messageHelper.setText(content); // 메일 내용
            
            
            
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
        response_email.setContentType("text/html; charset=UTF-8");
        PrintWriter out_email = response_email.getWriter();
        out_email.println("<script>alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');</script>");
        out_email.flush();
        
        
        
    }

	@RequestMapping(value = "/check_email_code{dice}", method = RequestMethod.POST)
    public void checkEmailCode(@RequestParam("customer_Email_Code") String customer_Email_Code,@PathVariable String dice,  HttpServletResponse response_equals) throws IOException {
 
        
        
        
        
        System.out.println("마지막 : customer_Email_Code : "+customer_Email_Code);
        
        
         
        
        if (customer_Email_Code.equals(dice)) {
            
            //인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 회원가입창으로 이동함
            
            
            
         
            
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하였습니다. 회원가입창으로 이동합니다.');</script>");
            out_equals.flush();
    
         
            
            
        }else if (customer_Email_Code != dice) {
            
            
         
            response_equals.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response_equals.getWriter();
            out_equals.println("<script>alert('인증번호가 일치하지않습니다. 인증번호를 다시 입력해주세요.'); history.go(-1);</script>");
            out_equals.flush();
            
    
          
            
        }    
    
       
        
    }
	   
	
	/*
	 * 고객회원이 로그인을 한 후 마이페이지로 이동하게 될 때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_Profile")
	public String profile(Model model, HttpSession session) {
		return this.customerService.profile(model, session);
	}

	/*
	 * 고객이 마이페이지에서 개인정보수정을 눌렀을 때 실행되는 메서드
	 */
	@RequestMapping("/customer_modify")
	public ModelAndView customer_modify(ModelAndView mv, HttpSession session) {
		return this.customerService.customer_modify(mv, session);
	}

	/*
	 * 개인정보수정를 고치고 수정 버튼을 눌렀을 때 실행되는 메서드이다.
	 */
	@RequestMapping("/customer_modify_ok")
	public String customer_modify(@RequestParam HashMap<String, Object> cmap) {	// form에서 입력한 정보를 HashMap으로 묶어서 가져온다.
		this.customerService.updateCustomerInfo(cmap);							// 가져온 cmap데이터를 기존 고객 데이터에 update시킨다.
		return "customer_modify_ok";
	}

	/*
	 * 로그인창에서 아이디 찾기를 누르고 고객 아이디찾기를 하면 실행되는 메서드
	 */
	@RequestMapping(value = "/search_id_customer", method = RequestMethod.POST)
	public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request) {
		return this.customerService.search_id_customer(mv, request);
	}

	/*
	 * 로그인창에서 비밀번호 찾기를 누르고 고객 비밀번호 찾기를 하면 실행되는 메서드
	 */
	@RequestMapping(value = "/search_pw_customer", method = RequestMethod.POST)
	public String search_pw_customer(ModelAndView mv, HttpServletRequest request) {
		return this.customerService.search_pw_customer(mv, request);
	}
}