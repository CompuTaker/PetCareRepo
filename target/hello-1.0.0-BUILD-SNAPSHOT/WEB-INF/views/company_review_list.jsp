<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>

<style type="text/css">
.rate {
	float: left;
	height: 46px;
	padding: 0 10px;
}

.rate:not (:checked )>input {
	position: absolute;
	top: -9999px;
}

.rate:not (:checked )>label {
	float: right;
	width: 1em;
	overflow: hidden;
	white-space: nowrap;
	cursor: pointer;
	font-size: 30px;
	color: #ccc;
}

.rate:not (:checked )>label:before {
	content: '★ ';
}

.rate>input:checked ~label {
	color: #ffc700;
}

.rate:not (:checked )>label:hover, .rate:not (:checked )>label:hover
	 ~label {
	color: #deb217;
}

.rate>input:checked+label:hover, .rate>input:checked+label:hover ~label,
	.rate>input:checked ~label:hover, .rate>input:checked ~label:hover
	 ~label, .rate>label:hover ~input:checked ~label {
	color: #c59b08;
}
</style>

</head>

<body>
	<jsp:include page="Header.jsp" flush="false" />
	<main class="container-fluid">
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
					<c:forEach items="${ review }" var="review">
						<tr
							onclick="location.href='company_review_view?reviewIdx=${review.review_Index}'">
							<td id="reivew_Index">${ review.review_Index }</td>
							<td id="review_Title">${ review.review_Title }</td>
							<td id="customer_Name">작성자(?)</td>
						</tr>
					</c:forEach>
				</table>
			</section>

		</div>
	</div>
	</main>
	<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>
