package com.test.dto;

public class Criteria {

	// 한 페이지당 보여줄 게시글 개수
	private int perPageNum;
	// 현재 페이지 번호
	private int page;

	/*
	 * 특정 페이지의 게시글 시작 번호 조회쿼리의 #{pageStart}에 전달
	 */
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	// 기본값을 1페이지, 10개로 지정함
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	// 페이지가 0보다 작을 경우에는 1페이지를 나타냄
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	// 페이지당 보여줄 게시글 수 10개 고정
	public void setPerPageNum(int pageCount) {
		int cnt = this.perPageNum;
		if (pageCount != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = pageCount;
		}
	}

}
