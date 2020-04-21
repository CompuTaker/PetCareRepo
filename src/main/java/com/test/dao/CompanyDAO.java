package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.CompanyDTO;

public interface CompanyDAO {

	abstract public List<CompanyDTO> listAllCompany();

	abstract public CompanyDTO listThisCompany(String company_Id, String company_Password);

	abstract public CompanyDTO listThisCompany(int company_Index);

	abstract public int insertTheCompany(HashMap<String, Object> cmap);

	abstract public int deleteTheCompany(int company_Index);

	abstract public List<CompanyDTO> listsCompany(String companyType);

	
}
