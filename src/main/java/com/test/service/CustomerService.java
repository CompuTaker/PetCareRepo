package com.test.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CustomerDTO;

public interface CustomerService {

	abstract public void insertTheCustomer(HashMap<String, Object> cmap);

	abstract public String checkCustomerID(String customer_Id);

	abstract public void updateCustomerInfo(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> cmap);

	abstract public ModelAndView customer_signupDo(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> cmap);

	abstract public String profile(Model model, HttpSession session, String customer_Id);

	abstract public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request);

	abstract public String search_pw_customer(Model model, HttpServletRequest request);

	abstract public ModelAndView customer_modify(ModelAndView mv, HttpSession session);

	abstract public void updateCustomerInfo(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> cmap, Model model);

	abstract public boolean checkPW(String customer_Id, String customer_Password);

	abstract public List<CustomerDTO> getDropCustomers();

	public abstract void deleteTheCustomer(String customer_Id,  SessionStatus status);

	
}
