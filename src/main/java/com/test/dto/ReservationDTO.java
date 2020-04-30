package com.test.dto;

public class ReservationDTO {

	/*
	 * Reservation_Index int not null auto_increment, Company_Index int not null,
	 * Customer_Index int not null, Reservation_Type varchar(20) not null,
	 * Reservation_PetName varchar(12) not null, Reservation_PetAge int(4) not null,
	 * Reservation_NeutralizationSurgery boolean default null, Reservation_PGender
	 * varchar(3) not null, Reservation_PWeight int not null, Reservation_PType
	 * varchar(15) not null, Reservation_Date Date not null, Reservation_Time
	 * Datetime not null
	 */
	// customer
	private int customer_Index;
	private String customer_Name;
	// pet
	private int pet_Index;
	private String pet_Name;
	private String pet_Type;
	private String pet_Gender;
	private int pet_Age;
	private boolean pet_IsNeutralized;
	private int pet_Weight;
	private String pet_History;
	private byte[] pet_Image;
	// reservation
	private int reservation_Index;
	private String reservation_Date;
	private String reservation_Time;
	private String reservation_DetailService;
	private String reservation_Check;
	// company
	private int company_Index;
	private String company_Id;
	private String company_Password;
	private String company_UserName;
	private String company_Address;
	private String company_Email;
	private String company_UserPhoneNumber;
	private String company_Name;
	private int company_Number;
	private String company_Type;
	private Byte[] company_Image;
	// review
	private int review_Check;

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

	public int getPet_Index() {
		return pet_Index;
	}

	public void setPet_Index(int pet_Index) {
		this.pet_Index = pet_Index;
	}

	public int getCustomer_Index() {
		return customer_Index;
	}

	public void setCustomer_Index(int customer_Index) {
		this.customer_Index = customer_Index;
	}

	public String getPet_Name() {
		return pet_Name;
	}

	public void setPet_Name(String pet_Name) {
		this.pet_Name = pet_Name;
	}

	public String getPet_Type() {
		return pet_Type;
	}

	public void setPet_Type(String pet_Type) {
		this.pet_Type = pet_Type;
	}

	public String getPet_Gender() {
		return pet_Gender;
	}

	public void setPet_Gender(String pet_Gender) {
		this.pet_Gender = pet_Gender;
	}

	public int getPet_Age() {
		return pet_Age;
	}

	public void setPet_Age(int pet_Age) {
		this.pet_Age = pet_Age;
	}

	public boolean isPet_IsNeutralized() {
		return pet_IsNeutralized;
	}

	public void setPet_IsNeutralized(boolean pet_IsNeutralized) {
		this.pet_IsNeutralized = pet_IsNeutralized;
	}

	public int getPet_Weight() {
		return pet_Weight;
	}

	public void setPet_Weight(int pet_Weight) {
		this.pet_Weight = pet_Weight;
	}

	public String getPet_History() {
		return pet_History;
	}

	public void setPet_History(String pet_History) {
		this.pet_History = pet_History;
	}

	public byte[] getPet_Image() {
		return pet_Image;
	}

	public void setPet_Image(byte[] pet_Image) {
		this.pet_Image = pet_Image;
	}

	public int getReservation_Index() {
		return reservation_Index;
	}

	public void setReservation_Index(int reservation_Index) {
		this.reservation_Index = reservation_Index;
	}

	public int getCompany_Index() {
		return company_Index;
	}

	public void setCompany_Index(int company_Index) {
		this.company_Index = company_Index;
	}

	public String getReservation_Date() {
		return reservation_Date;
	}

	public void setReservation_Date(String reservation_Date) {
		this.reservation_Date = reservation_Date;
	}

	public String getReservation_Time() {
		return reservation_Time;
	}

	public void setReservation_Time(String reservation_Time) {
		this.reservation_Time = reservation_Time;
	}

	public String getReservation_DetailService() {
		return reservation_DetailService;
	}

	public void setReservation_DetailService(String reservation_DetailService) {
		this.reservation_DetailService = reservation_DetailService;
	}

	public String getReservation_Check() {
		return reservation_Check;
	}

	public void setReservation_Check(String reservation_Check) {
		this.reservation_Check = reservation_Check;
	}

	public String getCustomer_Name() {
		return customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public int getReview_Check() {
		return review_Check;
	}

	public void setReview_Check(int review_Check) {
		this.review_Check = review_Check;
	}

}
