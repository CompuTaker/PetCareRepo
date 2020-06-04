package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.CompanyDTO;
import com.test.dto.Criteria;

// DAO 인터페이스 클래스
public interface CompanyDAO {

	abstract public List<CompanyDTO> listAllCompany(Criteria cri);
	
	abstract public List<CompanyDTO> listAllCompany();

	abstract public CompanyDTO listThisCompany(Map<String, String> loginInfo);

	abstract public CompanyDTO listThisCompany(int company_Index);

	abstract public int insertTheCompany(HashMap<String, Object> cmap);

	abstract public int deleteTheCompany(int company_Index);

	abstract public List<CompanyDTO> listsCompany(Map<String, Object> map);

	abstract public CompanyDTO searchCompanyID(Map<String, String> company);

	abstract public CompanyDTO searchCompanyPW(Map<String, String> company);

	abstract public CompanyDTO checkCompanyID(String company_Id);

	abstract public CompanyDTO checkCompanyNumber(String company_Number);

	abstract public int updateCompanyInfo(HashMap<String, Object> cmap);

	abstract public int countCompanyList(String type);

	abstract public List<CompanyDTO> listThisCompanyByName(Map<String, Object> map);

	public abstract int deleteTheCompany(String company_Id);

	public abstract boolean checkPW(String company_Id, String company_Password);
	
	public abstract int countCompanyByName(String company_Name);

	abstract public List<CompanyDTO> getDropCompanys();
	
	abstract public int countAllCompany();

	

}