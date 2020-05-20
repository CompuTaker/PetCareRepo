package com.test.dto;

public class ReviewImageDTO {
	private int image_index;
	private String image_Url;
	private int reservation_Index;
	
	public int getImage_index() {
		return image_index;
	}
	public void setImage_index(int image_index) {
		this.image_index = image_index;
	}
	public String getImage_Url() {
		return image_Url;
	}
	public void setImage_Url(String image_Url) {
		this.image_Url = image_Url;
	}
	public int getReservation_Index() {
		return reservation_Index;
	}
	public void setReservation_Index(int reservation_Index) {
		this.reservation_Index = reservation_Index;
	}
	
}
