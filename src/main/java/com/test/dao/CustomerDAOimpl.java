package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.CustomerDTO;

@Repository	//Spring에 DAO(Data Access Object)클래스라고 알려주는 Annotation
public class CustomerDAOimpl implements CustomerDAO {
	
	@Autowired // root-context.xml, 마이바티스를 이용해서 MySQL에 접근하는 변수
	private SqlSessionTemplate sqlSession;
	
	/*
	 * ID, PW가 customer테이블에 존재하는지를 확인하는 메서드이다.
	 */
	@Override
	public CustomerDTO listThisCustomer(String customer_Id, String customer_Password) {
		// 추후에 controller로 보낼 예정
		Map<String, String> customer = new HashMap<String, String>();	// 넘어온 변수를 한 번에 저장하기 위해서 만든 Map객체
		customer.put("customer_Id", customer_Id);						// Map객체에 ID를 저장한다.
		customer.put("customer_Password", customer_Password);			// Map객체에 PW를 저장한다.
		return this.sqlSession.selectOne("listThisCustomer", customer);	// mapper에서 "listThisCustomer" id를 가지는 명령문에 customer객체를 가지고 실행한다.
	}
	
	/*
	 * customer의 인덱스를 가지고 해당 고객의 이름을 찾아오는 메서드이다.
	 */
	@Override
	public List<CustomerDTO> listCustomerName(int customer_Index) {	
		return this.sqlSession.selectList("listCustomerName", customer_Index);	// mapper에서 "listCustomerName" id를 가지는 명령문에 customer_Index변수를 가지고 실행한다.
	}

	/*
	 * 고객 회원가입시 입력한 정보를 가지고 customer테이블에 저장하는 메서드이다.
	 */
	@Override
	public int insertTheCustomer(HashMap<String, Object> cmap) {
		return this.sqlSession.insert("insertTheCustomer", cmap);	// mapper에서 "insertTheCustomer" id를 가지는 명령문에 cmap객체를 가지고 실행한다.
	}

	/*
	 * 고객이 탈퇴하기를 눌렀을 경우 customer테이블에서 고객을 삭제하기 위한 메서드이다.
	 */
	@Override
	public int deleteTheCustomer(int customer_Index) {
		return this.sqlSession.delete("deleteTheCustomer", customer_Index);	// mapper에서 "deleteTheCustomer" id를 가지는 명령문에 customer_Index변수를 가지고 실행한다.
	}
	
	/*
	 * 고객이 마이페이지에서 개인정보를 수정하고 수정하기 버튼을 눌렀을 때 실행되는 메서드이다.
	 */
	@Override
	public int updateCustomerInfo(HashMap<String, Object> cmap) {
		return this.sqlSession.update("updateCustomerInfo", cmap);	// mapper에서 "updateCustomerInfo" id를 가지는 명령문에 cmap객체를 가지고 실행한다.		
	}

	/*
	 * 고객 아이디찾기를 눌렀을 경우에 실행되는 메서드이다.
	 */
	@Override
	public CustomerDTO searchCustomerID(String customer_Name, String customer_PhoneNumber) {
		// 추후에 controller로 이동
		Map<String, String> customer = new HashMap<String, String>();	// 넘어온 변수를 한 번에 저장하기 위해서 만든 Map객체
		customer.put("customer_Name", customer_Name);					// Map객체에 Name을 저장한다.
		customer.put("customer_PhoneNumber", customer_PhoneNumber);		// Map객체에 PhoneNumber를 저장한다.
		return this.sqlSession.selectOne("searchCustomerId",customer);	// mapper에서 "searchCustomerId" id를 가지는 명령문에 customer객체를 가지고 실행한다.	
	}

	/*
	 * 고객비밀번호찾기를 눌렀을 경우에 실행되는 메서드이다.
	 */
	@Override
	public CustomerDTO searchCustomerPW(String customer_Name, String customer_Id, String customer_PhoneNumber) {
		// 추후에 controller로 이동
		Map<String, String> customer = new HashMap<String, String>();	// 넘어온 변수를 한 번에 저장하기 위해서 만든 Map객체
		customer.put("customer_Name", customer_Name);					// Map객체에 Name을 저장한다.
		customer.put("customer_Id", customer_Id);						// Map객체에 ID를 저장한다.
		customer.put("customer_PhoneNumber", customer_PhoneNumber);		// Map객체에 PhoneNumber를 저장한다.
		return this.sqlSession.selectOne("searchCustomerPW",customer);	// mapper에서 "searchCustomerPW" id를 가지는 명령문에 customer객체를 가지고 실행한다.	
	}	

	/*
	 * 고객이 회원가입시 아이디 중복을 확인하는 메서드이다.
	 */
	@Override
	public CustomerDTO checkCustomerID(String customer_Id) {	
		return this.sqlSession.selectOne("getId",customer_Id);	// mapper에서 "getId" id를 가지는 명령문에 customer_Id변수를 가지고 실행한다.	
	}

	/*
	 * 고객이 회원가입시 주민등록번호 중복을 확인하는 메서드이다.
	 */
	@Override
	public CustomerDTO checkCustomerResident(String customer_residentNumber) {
		return this.sqlSession.selectOne("getResidentNumber",customer_residentNumber);	// mapper에서 "getResidentNumber" id를 가지는 명령문에 customer_residentNumber변수를 가지고 실행한다.	
	}
}