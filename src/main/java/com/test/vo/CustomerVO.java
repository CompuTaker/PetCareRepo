package com.test.vo;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
public class CustomerVO {
	/*
	 * Customer_Index int not null auto_increment, Customer_Id varchar(12) not null,
	 * Customer_Password varchar(12) not null, Customer_Name varchar(10) not null,
	 * Customer_BirthDate varchar(30) not null, Customer_PhoneNumber varchar(13) not
	 * null, Customer_Address varchar(50) not null, Customer_Email varchar(80) not
	 * null, Customer_Image mediumblob, primary key(Customer_Index), unique
	 * key(Customer_Password), unique key(Customer_PhoneNumber)
	 */
	private int customer_Index;
	//ID는 4-10자 이내
	@Size(min=4, max =10)
	private String customer_Id;
	//PW는 영어, 특수기호, 숫자 포함 8-14자리
	@Pattern(regexp = "^.*(?=.{8,14})(?=.*[0-9])(?=.*[!,@,#,$,%,^,&,*,?,_,~])(?=.*[a-zA-Z]).*$")
	private String customer_Password;
	//name은 2-4자리 사이
	@Pattern(regexp = "\\S{2,4}")
	private String customer_Name;
	private String customer_PhoneNumber;
	private String customer_Address;
	@Email
	private String customer_Email;
	private String customer_Image;
	public int getCustomer_Index() {
		return customer_Index;
	}
	public void setCustomer_Index(int customer_Index) {
		this.customer_Index = customer_Index;
	}
	public String getCustomer_Id() {
		return customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}
	public String getCustomer_Password() {
		return customer_Password;
	}
	public void setCustomer_Password(String customer_Password) {
		this.customer_Password = customer_Password;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	
	public String getCustomer_PhoneNumber() {
		return customer_PhoneNumber;
	}
	public void setCustomer_PhoneNumber(String customer_PhoneNumber) {
		this.customer_PhoneNumber = customer_PhoneNumber;
	}
	public String getCustomer_Address() {
		return customer_Address;
	}
	public void setCustomer_Address(String customer_Address) {
		this.customer_Address = customer_Address;
	}
	public String getCustomer_Email() {
		return customer_Email;
	}
	public void setCustomer_Email(String customer_Email) {
		this.customer_Email = customer_Email;
	}
	public String getCustomer_Image() {
		return customer_Image;
	}
	public void setCustomer_Image(String customer_Image) {
		this.customer_Image = customer_Image;
	}
	
	
}