package com.test.dto;

public class ReviewDTO {

	/*
	 * create table Review ( review_Index int not null auto_increment, company_Index
	 * int not null, review_Title varchar(60) not null, review_Content text not
	 * null, review_Rating varchar(15) not null, review_ViewsNumber int,
	 * review_Image mediumblob, review_Comment text, ref int not null,
	 * 
	 */
	private int review_Index;
	private int company_Index;
	private String review_Title;
	private String review_Content;
	private String review_Rating;
	private int review_ViewsNumber;
	private byte[] review_Image;
	private String review_Comment;
	private int review_Ref;
	private String customer_id;
	
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public int getReview_Index() {
		return review_Index;
	}
	public void setReview_Index(int review_Index) {
		this.review_Index = review_Index;
	}
	public int getCompany_Index() {
		return company_Index;
	}
	public void setCompany_Index(int company_Index) {
		this.company_Index = company_Index;
	}
	public String getReview_Title() {
		return review_Title;
	}
	public void setReview_Title(String review_Title) {
		this.review_Title = review_Title;
	}
	public String getReview_Content() {
		return review_Content;
	}
	public void setReview_Content(String review_Content) {
		this.review_Content = review_Content;
	}
	public String getReview_Rating() {
		return review_Rating;
	}
	public void setReview_Rating(String review_Rating) {
		this.review_Rating = review_Rating;
	}
	public int getReview_ViewsNumber() {
		return review_ViewsNumber;
	}
	public void setReview_ViewsNumber(int review_ViewsNumber) {
		this.review_ViewsNumber = review_ViewsNumber;
	}
	public byte[] getReview_Image() {
		return review_Image;
	}
	public void setReview_Image(byte[] review_Image) {
		this.review_Image = review_Image;
	}
	public String getReview_Comment() {
		return review_Comment;
	}
	public void setReview_Comment(String review_Comment) {
		this.review_Comment = review_Comment;
	}
	public int getReivew_Ref() {
		return review_Ref;
	}
	public void setReivew_Ref(int review_Ref) {
		this.review_Ref = review_Ref;
	}
	

	
}
