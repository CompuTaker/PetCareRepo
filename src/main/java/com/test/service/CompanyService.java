package com.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CompanyDTO;

public interface CompanyService {

	abstract public void comNumCheck(int company_Number);

	abstract public ModelAndView searchId(ModelAndView mv, HttpServletRequest request);

	abstract public List<CompanyDTO> listsCompany(Map<String, Object> map);

	abstract public CompanyDTO listThisCompany(int companyIdx);

	abstract public void insertTheCompany(HashMap<String, Object> cmap);

	abstract public ModelAndView company_signupDo(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> cmap);

	abstract public void comIdCheck(String company_Id);

	abstract public String search_pw_company(ModelAndView mv, HttpServletRequest request);

	abstract public ModelAndView profile(ModelAndView mv, HttpSession session);

	abstract public ModelAndView company_modify(ModelAndView mv, HttpSession session);

	abstract public void updateCompanyInfo(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> cmap);

	abstract public int countCompanyList(String type);

	abstract public List<CompanyDTO> listsAllCompany(HttpServletRequest request);

}
