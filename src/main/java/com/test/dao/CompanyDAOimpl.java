package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.CompanyDTO;
import com.test.dto.Criteria;

@Repository // Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class CompanyDAOimpl implements CompanyDAO {

	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;

	/*
	 * Company테이블에 저장된 모든 회사 정보들을 가져올 때 실행되는 메서드이다.
	 */
	@Override
	public List<CompanyDTO> listAllCompany(Criteria cri) {
		return this.sqlSession.selectList("listAllCompany", cri);
	}

	/*
	 * ID, PW의 정보와 동일한 가입 회사가 있는지를 확인해보는 메서드이다.
	 */
	@Override
	public CompanyDTO listThisCompany(Map<String, String> loginInfo) {
		return this.sqlSession.selectOne("listThisCompany", loginInfo); // mapper에서 "listThisCompany" id를 가지는 명령문에
																		// company객체를 가지고 실행한다.
	}

	/*
	 * 해당 Index를 가지는 Company를 찾는 메서드이다.
	 */
	@Override
	public CompanyDTO listThisCompany(int company_Index) {
		return this.sqlSession.selectOne("listThisCompanyByIdx", company_Index); // mapper에서 "listThisCompanyByIdx" id를
																					// 가지는 명령문에 company_Index변수를 가지고
																					// 실행한다.
	}

	/*
	 * 기업회원가입을 한 후 company테이블에 값을 저장할 때 실행되는 메서드이다.
	 */
	@Override
	public int insertTheCompany(HashMap<String, Object> cmap) {
		return this.sqlSession.insert("insertTheCompany", cmap); // mapper에서 "insertTheCompany" id를 가지는 명령문에 cmap객체를 가지고
																	// 실행한다.
	}

	/*
	 * 탈퇴하고자 하는 기업 회원이 탈퇴하기를 눌렀을 때 실행되는 메서드이다.
	 */
	@Override
	public int deleteTheCompany(int company_Index) {
		return this.sqlSession.delete("deleteTheCompany", company_Index); // mapper에서 "deleteTheCompany" id를 가지는 명령문에
																			// company_Index변수를 가지고 실행한다.
	}

	/*
	 * Company_Type("미용실", "병원", "호텔")에 따라서 결과값을 가져오는 메서드이다.
	 */
	@Override
	public List<CompanyDTO> listsCompany(Map<String, Object> map) {
		return this.sqlSession.selectList("listsCompany", map); // mapper에서 "listsCompany" id를 가지는 명령문에 companyType변수를
																// 가지고 실행한다.

	}

	/*
	 * 사업자등록번호를 가지고 가입한 기업 회원의 아이디를 찾아주는 메서드이다.
	 */
	@Override
	public CompanyDTO searchCompanyID(Map<String, String> company) {
		return this.sqlSession.selectOne("searchCompanyID", company); // mapper에서 "searchId" id를 가지는 명령문에 companyNum변수를
																		// 가지고
		// 실행한다.
	}

	/*
	 * 이름, 아이디, 핸드폰번호를 가지고 기업 회원의 비밀번호를 찾아주는 메서드이다.
	 */
	@Override
	public CompanyDTO searchCompanyPW(Map<String, String> company) {
		return this.sqlSession.selectOne("searchCompanyPW", company); // mapper에서 "searchCompanyPW" id를 가지는 명령문에
																		// company객체를 가지고 실행한다.
	}

	/*
	 * 기업회원가입시 아이디 중복체크를 위해서 실행되는 메서드이다.
	 */
	@Override
	public CompanyDTO checkCompanyID(String company_Id) {
		return this.sqlSession.selectOne("companyId", company_Id); // mapper에서 "companyId" id를 가지는 명령문에 company_Id변수를
																	// 가지고 실행한다.

	}

	/*
	 * 기업회원가입시 사업자등록번호를 중복체크를 위해서 실행되는 메서드이다.
	 */
	@Override
	public CompanyDTO checkCompanyNumber(String company_Number) {
		return this.sqlSession.selectOne("companyNumber", company_Number); // mapper에서 "companyNumber" id를 가지는 명령문에
																			// company_Number변수를 가지고 실행한다.
	}

	/*
	 * 기업회원이 마이페이지에서 개인정보를 수정하고 수정하기 버튼을 눌러쓸 때 실행되는 메서드이다.
	 */
	@Override
	public int updateCompanyInfo(HashMap<String, Object> cmap) {
		return this.sqlSession.update("updateCompanyInfo", cmap);
	}

	@Override
	public int countCompanyList(String type) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("countCompanyList", type);
	}

	@Override
	public List<CompanyDTO> listThisCompanyByName(Map<String, Object> map) {
		return this.sqlSession.selectList("listThisCompanyByName", map);
	}

	public int deleteTheCompany(String company_Id) {
		return this.sqlSession.delete("deleteTheCompany", company_Id);

	}

	@Override
	public boolean checkPW(String company_Id, String company_Password) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("company_Id", company_Id);
		map.put("company_Password", company_Password);
		int count = sqlSession.selectOne("checkPWCompany", map);
		if (count == 1)
			result = true;
		return result;
	}

	@Override
	public int countCompanyByName(String company_Name) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("countCompanyByName", company_Name);
	}

	public List<CompanyDTO> getDropCompanys() {
		return this.sqlSession.selectList("getDropCompanys");
	}
}