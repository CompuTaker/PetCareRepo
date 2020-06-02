<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="mx-auto main-block   col-12">
		<h1 class="font-weight-bold p-5">Review</h1>
		<hr>
		<section>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>

					</tr>
				</thead>
				<c:forEach items="${ review }" var="review" varStatus="status">
					<tr onclick="location.href='customer_review_view?review_Index=${review.review_Index}'">
						<td id="review_Index">${ review.review_Index }</td>
						<td id="review_Title">${ review.review_Title }</td>
						<td id="customer_Name">${customerName[status.index] }</td>
					</tr>
				</c:forEach>
			</table>
		</section>

	</div>
</div>