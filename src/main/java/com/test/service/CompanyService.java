package com.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.test.dto.CompanyDTO;
import com.test.dto.Criteria;

public interface CompanyService {

	abstract public String comNumCheck(String company_Number);

	abstract public ModelAndView searchId(ModelAndView mv, HttpServletRequest request);

	abstract public List<CompanyDTO> listsCompany(Map<String, Object> map);

	abstract public CompanyDTO listThisCompany(int companyIdx);

	abstract public void insertTheCompany(HashMap<String, Object> cmap);

	abstract public ModelAndView company_signupDo(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> cmap);

	abstract public String comIdCheck(String company_Id);

	abstract public String search_pw_company(Model model, HttpServletRequest request);

	abstract public ModelAndView profile(ModelAndView mv, HttpSession session,Model model, String company_Id);

	abstract public ModelAndView company_modify(ModelAndView mv, HttpSession session);

	abstract public ModelAndView updateCompanyInfo(MultipartHttpServletRequest multipartHttpServletRequest,
			HashMap<String, Object> cmap);

	abstract public int countCompanyList(String type);

	abstract public List<CompanyDTO> listsAllCompany(Model model, HttpServletRequest request, Criteria cri);

	abstract public boolean checkPW(String company_Id, String company_Password);

	abstract public void deleteTheCompany(String company_Id, SessionStatus status);

	abstract public List<CompanyDTO> getDropCompanys();
}
