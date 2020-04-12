package com.test.dao;

import java.util.HashMap;
import java.util.List;

import com.test.dto.CompanyDTO;

// DAO 인터페이스 클래스
public interface CompanyDAO {

	abstract public List<CompanyDTO> listAllCompany();

	abstract public CompanyDTO listThisCompany(String company_Id, String company_Password);

	abstract public CompanyDTO listThisCompany(int company_Index);

	abstract public int insertTheCompany(HashMap<String, Object> cmap);

	abstract public int deleteTheCompany(int company_Index);

	abstract public List<CompanyDTO> listsCompany(String companyType);

	abstract public CompanyDTO searchId(int companyNum);

	abstract public CompanyDTO searchCompanyPW(String company_Name, String company_Id, String company_PhoneNumber);

	abstract public CompanyDTO checkCompanyID(String company_Id);
	
	abstract public CompanyDTO checkCompanyNumber(int company_Number);
	
}