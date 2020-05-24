package com.test.dto;

//페이징 관련 버튼을 만들기 위한 클래스
public class PageMaker {

	private Criteria cri;
	private int totalCount; // 전체 데이터 갯수
	private int startPage; // 화면에 보여질 시작 페이지 번호
	private int endPage; // 화면에 보여질 마지막 페이지 번호
	private boolean prev; // 이전 버튼
	private boolean next; // 다음 버튼
	private int displayPageNum = 5; // 화면에 보여질 페이징 버튼 번호 갯수

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPage();
	}

	// 버튼 계산 메서드
	private void calcPage() {
		/*
		 * 화면에 보여질 마지막 페이지 번호 계산 Math.ceil()은 소수점을 올림으로 처리함 cri.getPage()는 현재 페이지 번호
		 * Math.ceil(현재 페이지 번호/화면에 보여질 페이징 버튼 번호 갯수)*화면에 보여질 페이징 버튼 번호 갯수
		 */
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum)) * displayPageNum;

		/*
		 * 화면에 보여질 시작 페이지 번호 계산 (화면에 보여질 마지막 페이지 번호 - 화면에 보여질 페이징 버튼 번호 갯수) + 1
		 */
		startPage = (endPage - displayPageNum) + 1;

		if (startPage <= 0) {
			startPage = 1;
		}

		// 실제 마지막 페이지 번호
		int realEnd = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		if (realEnd < endPage) {
			endPage = realEnd;
		}

		prev = startPage > 1;
		next = endPage < realEnd;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

}
