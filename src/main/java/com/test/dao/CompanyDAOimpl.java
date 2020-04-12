package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.CompanyDTO;

@Repository		//Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class CompanyDAOimpl implements CompanyDAO {
	
	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;
	
	/*
	 * Company테이블에 저장된 모든 회사 정보들을 가져올 때 실행되는 메서드이다.
	 */
	@Override
	public List<CompanyDTO> listAllCompany() {
		return this.sqlSession.selectList("listAllCompany");	
	}
	
	/*
	 * ID, PW의 정보와 동일한 가입 회사가 있는지를 확인해보는 메서드이다.
	 */
	@Override
	public CompanyDTO listThisCompany(String company_Id, String company_Password) {
		// 추후에 다시 controller쪽으로 옮길 예정
		Map<String, String> company = new HashMap<String, String>();	// mapper에 변수값을 한 번에 전달하기 위해서 생성한 Map객체 
		company.put("company_Id", company_Id);							// Map객체에 Id값을 저장한다.
		company.put("company_Password", company_Password);				// Map객체에 PW값을 저장한다.
		return this.sqlSession.selectOne("listThisCompany", company);	// mapper에서 "listThisCompany" id를 가지는 명령문에 company객체를 가지고 실행한다.
	}
	
	/*
	 * 해당 Index를 가지는 Company를 찾는 메서드이다.
	 */
	@Override
	public CompanyDTO listThisCompany(int company_Index) {
		return this.sqlSession.selectOne("listThisCompanyByIdx", company_Index);	// mapper에서 "listThisCompanyByIdx" id를 가지는 명령문에 company_Index변수를 가지고 실행한다.
	}
	
	/*
	 * 기업회원가입을 한 후 company테이블에 값을 저장할 때 실행되는 메서드이다.
	 */
	@Override
	public int insertTheCompany(HashMap<String, Object> cmap) {	
		return this.sqlSession.insert("insertTheCompany", cmap);	// mapper에서 "insertTheCompany" id를 가지는 명령문에 cmap객체를 가지고 실행한다.
	}	
	
	/*
	 * 탈퇴하고자 하는 기업 회원이 탈퇴하기를 눌렀을 때 실행되는 메서드이다.
	 */
	@Override
	public int deleteTheCompany(int company_Index) {
		return this.sqlSession.delete("deleteTheCompany", company_Index);	// mapper에서 "deleteTheCompany" id를 가지는 명령문에 company_Index변수를 가지고 실행한다.
	}

	/*
	 * Company_Type("미용실", "병원", "호텔")에 따라서 결과값을 가져오는 메서드이다.
	 */
	@Override
	public List<CompanyDTO> listsCompany(String companyType) {
		return this.sqlSession.selectList("listsCompany", companyType);	// mapper에서 "listsCompany" id를 가지는 명령문에 companyType변수를 가지고 실행한다.

	}
	
	/*
	 * 사업자등록번호를 가지고 가입한 기업 회원의 아이디를 찾아주는 메서드이다.
	 */
	@Override
	public CompanyDTO searchId(int companyNum) {
		return this.sqlSession.selectOne("searchId", companyNum);	// mapper에서 "searchId" id를 가지는 명령문에 companyNum변수를 가지고 실행한다.
	}
	
	/*
	 * 이름, 아이디, 핸드폰번호를 가지고 기업 회원의 비밀번호를 찾아주는 메서드이다.
	 */
	@Override
	public CompanyDTO searchCompanyPW(String company_Name, String company_Id, String company_PhoneNumber) {
		// 추후에 다시 controller쪽으로 옮길 예정
		Map<String, String> company = new HashMap<String, String>();	// 넘어온 변수를 한 번에 mapper에 넘겨주기 위해서 만든 Map객체
		company.put("company_UserName", company_Name);					// Map객체에 이름을 저장한다.
		company.put("company_Id", company_Id);							// Map객체에 아이디를 저장한다.
		company.put("company_UserPhoneNumber", company_PhoneNumber);	// Map객체에 핸드폰번호를 저장한다.
		return this.sqlSession.selectOne("searchCompanyPW",company);	// mapper에서 "searchCompanyPW" id를 가지는 명령문에 company객체를 가지고 실행한다.
	}

	/*
	 * 기업회원가입시 아이디 중복체크를 위해서 실행되는 메서드이다.
	 */
	@Override
	public CompanyDTO checkCompanyID(String company_Id) {
		return this.sqlSession.selectOne("companyId",company_Id);	// mapper에서 "companyId" id를 가지는 명령문에 company_Id변수를 가지고 실행한다.

	}

	/*
	 * 기업회원가입시 사업자등록번호를 중복체크를 위해서 실행되는 메서드이다.
	 */
	@Override
	public CompanyDTO checkCompanyNumber(int company_Number) {
		return this.sqlSession.selectOne("companyNumber", company_Number);	// mapper에서 "companyNumber" id를 가지는 명령문에 company_Number변수를 가지고 실행한다.
	}
}