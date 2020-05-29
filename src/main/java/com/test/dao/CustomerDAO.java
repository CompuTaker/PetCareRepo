package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.dto.CustomerDTO;

//DAO �씤�꽣�럹�씠�뒪 �겢�옒�뒪
public interface CustomerDAO {

	abstract public CustomerDTO listThisCustomer(Map<String, String> loginInfo);

	abstract public int insertTheCustomer(HashMap<String, Object> cmap);

	abstract public int deleteTheCustomer(String customer_Id);

	abstract public int updateCustomerInfo(HashMap<String, Object> cmap);

	abstract public List<CustomerDTO> listCustomerName(int customer_Index);
	
	abstract public CustomerDTO searchCustomerID(Map<String, String> customer);

	public abstract CustomerDTO searchCustomerPW(Map<String, String> customer );

	public abstract CustomerDTO checkCustomerID(String customer_Id);
	
	public abstract CustomerDTO checkCustomerResident(String customer_residentNumber);

	public abstract boolean checkPW(String customer_Id, String customer_Password);

	abstract public List<CustomerDTO> getDropCustomers();
	

	
}