package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.test.dto.CompanyDTO;
import com.test.dto.CustomerDTO;

@Repository
public class CompanyDAOimpl implements CompanyDAO {
	// @Repository // DAO �겢�옒�뒪�뿉 �븘�닔�쟻�씤 �뼱�끂�뀒�씠�뀡, 洹몃옒�빞 �씤�떇 媛��뒫!
	
	@Autowired // root-context.xml 李멸퀬
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<CompanyDTO> listAllCompany() {
		return this.sqlSession.selectList("listAllCompany");	
	}
	
	@Override
	public CompanyDTO listThisCompany(String company_Id, String company_Password) {
		// TODO Auto-generated method stub
		Map<String, String> company = new HashMap<String, String>();
		company.put("company_Id", company_Id);
		company.put("company_Password", company_Password);
		return this.sqlSession.selectOne("listThisCompany", company);
	}
	
	@Override
	public CompanyDTO listThisCompany(int company_Index) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("listThisCompanyByIdx", company_Index);
	}
	
	@Override
	public int insertTheCompany(HashMap<String, Object> cmap) {
		// TODO Auto-generated method stub
		
		System.out.println("!!!!" + cmap.get("Company_Id"));
		System.out.println("!!!!" + cmap.get("Company_Password"));
		
		return this.sqlSession.insert("insertTheCompany", cmap);
	}
	
	@Override
	public int deleteTheCompany(int company_Index) {
		// TODO Auto-generated method stub
		System.out.println("!!!!" + company_Index);
		
		return this.sqlSession.delete("deleteTheCompany", company_Index);
	}

	@Override
	public List<CompanyDTO> listsCompany(String companyType) {
		return this.sqlSession.selectList("listsCompany", companyType);

	}
	@Override
	public CompanyDTO searchId(int companyNum) {
		return this.sqlSession.selectOne("searchId", companyNum);
	}
	
	@Override
	public CompanyDTO searchCompanyPW(String company_Name, String company_Id, String company_PhoneNumber) {
		Map<String, String> company = new HashMap<String, String>();
		company.put("company_UserName", company_Name);
		company.put("company_Id", company_Id);
		company.put("company_UserPhoneNumber", company_PhoneNumber);
		return this.sqlSession.selectOne("searchCompanyPW",company);
	}

	@Override
	public CompanyDTO checkCompanyID(String company_Id) {
		return this.sqlSession.selectOne("companyId",company_Id);

	}

	@Override
	public CompanyDTO checkCompanyNumber(int company_Number) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("companyNumber", company_Number);
	}
}