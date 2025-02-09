<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row m-5">
	<div class="col-12 main-block">
		<h1 class="font-weight-bold p-5">Review</h1>
		<hr>
		<div class="review-left-div">
			<label class="font-weight-bold" for="review_Title">제목 : </label> <label
				id="review_Title" name="review_Title"> ${ review.review_Title }</label>
		</div>
		<div class="review-right-div">
			<label class="font-weight-bold" for="customer_Name">작성자 : </label> <label
				id="customer_Name" name="customer_Name"> ${customerName }</label>
		</div>
		<div class="review-div">
			<label class="font-weight-bold" for="review_Content">내용 : </label>
		</div>
		<div class="review-div-image">
			<c:if test="${not empty review_Image}">
				<img src="./images/logo.png" id="review_Image" name="review_Image">
			</c:if>
		</div>
		<div class="review-div">
			<p id="review_Content" name="review_Content">
				<label id="review_Content" name="review_Content"> ${ review.review_Content }</label>
			</p>
		</div>
		<div class="review-div">
			<label class="font-weight-bold" for="review_Rating">별점 : </label> <label
				id="review_Rating" name="review_Rating">${ review.review_Rating }</label>
		</div>
		<hr>
		<div class="review-div">
			<label class="font-weight-bold" for="customer_Name">댓글 : </label> <label
				id="review_Comment" name="review_Comment"> ${ review.review_Comment }</label>
		</div>

		<button onclick="history.back()"
			class="btn btn-secondary btn-lg mx-auto">목록</button>

	</div>

</div>