package com.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface CustomerService {

	abstract public void insertTheCustomer(HashMap<String, Object> cmap);

	abstract public void checkCustomerID(String customer_Id);

	abstract public void checkCustomerResident(String customer_ResidentNumber);

	abstract public void updateCustomerInfo(MultipartHttpServletRequest multipartHttpServletRequest, HashMap<String, Object> cmap);

	abstract public ModelAndView customer_signupDo(MultipartHttpServletRequest multipartHttpServletRequest, HashMap<String, Object> cmap);

	abstract public String profile(Model model, HttpSession session);

	abstract public ModelAndView search_id_customer(ModelAndView mv, HttpServletRequest request);

	abstract public String search_pw_customer(ModelAndView mv, HttpServletRequest request);

	abstract public ModelAndView customer_modify(ModelAndView mv, HttpSession session);

}
