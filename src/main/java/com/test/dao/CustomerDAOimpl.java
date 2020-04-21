package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.dto.CustomerDTO;

@Repository
public class CustomerDAOimpl implements CustomerDAO {
	// @Repository // DAO �겢�옒�뒪�뿉 �븘�닔�쟻�씤 �뼱�끂�뀒�씠�뀡, 洹몃옒�빞 �씤�떇 媛��뒫!
	
	@Autowired // root-context.xml 李멸퀬
	private SqlSessionTemplate sqlSession;
	
	@Override
	public CustomerDTO listThisCustomer(String customer_Id, String customer_Password) {
		// TODO Auto-generated method stub
		Map<String, String> customer = new HashMap<String, String>();
		customer.put("customer_Id", customer_Id);
		customer.put("customer_Password", customer_Password);
		return this.sqlSession.selectOne("listThisCustomer", customer);
	}
	
	@Override
	public List<CustomerDTO> listCustomerName(int customer_Index) {	
		return this.sqlSession.selectList("listCustomerName", customer_Index);
	}

	@Override
	public int insertTheCustomer(HashMap<String, Object> cmap) {
		// TODO Auto-generated method stub
		// System.out.println("!!!!" + cmap.get("userId"));
		// System.out.println("!!!!" + cmap.get("cname"));
		
		return this.sqlSession.insert("insertTheCustomer", cmap);
	}

	@Override
	public int deleteTheCustomer(int customer_Index) {
		// TODO Auto-generated method stub
		System.out.println("!!!!" + customer_Index);
		
		return this.sqlSession.delete("deleteTheCustomer", customer_Index);
	}
	@Override
	public int updateCustomerInfo(HashMap<String, Object> cmap) {
		return this.sqlSession.update("updateCustomerInfo", cmap);		
	}


}
