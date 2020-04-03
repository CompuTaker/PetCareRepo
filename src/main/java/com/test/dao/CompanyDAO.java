package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;

public interface CompanyDAO {

	abstract public List<CompanyDTO> listAllCompany();

	abstract public CompanyDTO listThisCompany(String company_Id, String company_Password);

	abstract public CompanyDTO listThisCompany(int company_Index);

	abstract public int insertTheCompany(HashMap<String, Object> cmap);

	abstract public int deleteTheCompany(int company_Index);

	abstract public List<CompanyDTO> listsCompany(String companyType);

	abstract public CompanyDTO searchId(int companyNum);

	public abstract CompanyDTO searchCompanyPW(String company_Name, String company_Id, String company_PhoneNumber);

	public abstract CompanyDTO checkCompanyID(String company_Id);
	
	public abstract CompanyDTO checkCompanyNumber(int company_Number);
	
}