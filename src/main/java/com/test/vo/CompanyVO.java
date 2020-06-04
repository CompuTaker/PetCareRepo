package com.test.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

public class CompanyVO {
	/*
	 * Company_Index int not null auto_increment, Company_Id varchar(12) not null,
	 * Company_Password varchar(12) not null, Company_UserName varchar(10) not null,
	 * Company_Address varchar(50) not null, Company_Email varchar(80) not null,
	 * Company_PhoneNumber varchar(13) not null, Company_Name varchar(20) not null,
	 * Company_Number int(15) not null, Company_Type varchar(20)not null,
	 * Company_Image mediumblob , primary key(Company_Index), unique
	 * key(Company_Password)
	 */
	private int company_Index;
	// ID는 4-10자 이내
	@Size(min = 4, max = 10)
	private String company_Id;
	// PW는 영어, 특수기호, 숫자 포함 8-14자리
	@Pattern(regexp = "([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9]){8,14}")
	private String company_Password;
	// name은 2-4자리 사이
	@Pattern(regexp = "\\S{2,4}")
	private String company_UserName;
	private String company_ResidentNumber;

	private String company_Address;
	@Email
	private String company_Email;
	private String company_UserPhoneNumber;
	private String company_Name;
	// 사업자 번호는 원래는 10자린데 9자리만 지원,,
	@Range(min = 100000000, max = 999999999)
	private int company_Number;
	private String company_Type;
	private Byte[] company_Image;

	public int getCompany_Index() {
		return company_Index;
	}

	public void setCompany_Index(int company_Index) {
		this.company_Index = company_Index;
	}

	public String getCompany_Id() {
		return company_Id;
	}

	public void setCompany_Id(String company_Id) {
		this.company_Id = company_Id;
	}

	public String getCompany_Password() {
		return company_Password;
	}

	public void setCompany_Password(String company_Password) {
		this.company_Password = company_Password;
	}

	public String getCompany_UserName() {
		return company_UserName;
	}

	public void setCompany_UserName(String company_UserName) {
		this.company_UserName = company_UserName;
	}

	public String getCompany_ResidentNumber() {
		return company_ResidentNumber;
	}

	public void setCompany_ResidentNumber(String company_ResidentNumber) {
		this.company_ResidentNumber = company_ResidentNumber;
	}

	public String getCompany_Address() {
		return company_Address;
	}

	public void setCompany_Address(String company_Address) {
		this.company_Address = company_Address;
	}

	public String getCompany_Email() {
		return company_Email;
	}

	public void setCompany_Email(String company_Email) {
		this.company_Email = company_Email;
	}

	public String getCompany_UserPhoneNumber() {
		return company_UserPhoneNumber;
	}

	public void setCompany_UserPhoneNumber(String company_UserPhoneNumber) {
		this.company_UserPhoneNumber = company_UserPhoneNumber;
	}

	public String getCompany_Name() {
		return company_Name;
	}

	public void setCompany_Name(String company_Name) {
		this.company_Name = company_Name;
	}

	public int getCompany_Number() {
		return company_Number;
	}

	public void setCompany_Number(int company_Number) {
		this.company_Number = company_Number;
	}

	public String getCompany_Type() {
		return company_Type;
	}

	public void setCompany_Type(String company_Type) {
		this.company_Type = company_Type;
	}

	public Byte[] getCompany_Image() {
		return company_Image;
	}

	public void setCompany_Image(Byte[] company_Image) {
		this.company_Image = company_Image;
	}
}