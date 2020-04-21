package com.test.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CompanyDTO;

public interface CompanyService {
	
	abstract public void comNumCheck(int company_Number);
	
	abstract public ModelAndView searchId(ModelAndView mv, HttpServletRequest request);
	
	abstract public List<CompanyDTO> listsCompany(String companyType);

	abstract public CompanyDTO listThisCompany(int companyIdx);

	abstract public void insertTheCompany(HashMap<String, Object> cmap);

	abstract public ModelAndView company_signupDo(HashMap<String, Object> cmap);

	abstract public void comIdCheck(String company_Id);

	abstract public String search_pw_company(ModelAndView mv, HttpServletRequest request);

	abstract public ModelAndView profile(ModelAndView mv, HttpSession session);

	public abstract ModelAndView company_modify(ModelAndView mv, HttpSession session);
}
