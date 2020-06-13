package com.test.dto;

public class QnAboardDTO {
	
	private int id;
	private String title;
	private String date;
	private int viewnum;
	private int writer;
	private String qna_type;
	private String content;
	private int is_answered;
	private String writer_name;
	private String qna_Comment;
	private String customer_Name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getViewnum() {
		return viewnum;
	}
	public void setViewnum(int viewnum) {
		this.viewnum = viewnum;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public String getQna_type() {
		return qna_type;
	}
	public void setQna_type(String qna_type) {
		this.qna_type = qna_type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getIs_answered() {
		return is_answered;
	}
	public void setIs_answered(int is_answered) {
		this.is_answered = is_answered;
	}
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}
	public String getQna_Comment() {
		return qna_Comment;
	}
	public void setQna_Comment(String qna_Comment) {
		this.qna_Comment = qna_Comment;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	
}
