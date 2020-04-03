package com.test.dto;

public class PetDTO {

	/*
	 * Pet_Index int not null auto_increment, Customer_Index int not null , Pet_Name
	 * varchar(12) not null, Pet_Type varchar(15) not null, Pet_Gender varchar(3)
	 * not null, Pet_Age int(4) not null, Pet_History text, primary key(Pet_Index),
	 */

	private int pet_Index;
	private int customer_Index;
	private String pet_Name;
	private String pet_Type;
	private String pet_Gender;
	private int pet_Age;
	private boolean pet_IsNeutralized;
	private int pet_Weight;
	private String pet_History;

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
	
}
