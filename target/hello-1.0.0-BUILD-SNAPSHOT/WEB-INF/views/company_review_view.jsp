<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

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
					id="customer_Name" name="customer_Name"> 작성자(?)</label>
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
				<hr>
			</div>

			<form method="POST"
				action="company_review_ok?reviewIdx=${review.review_Index}"
				accept-charset="utf-8" name="review-comment" class="review-comment ">
				<div class="review-div">
					<label class="font-weight-bold" for="review_Comment">답글</label> <br>
					<textarea id="review_Comment" style="width: 90%;" rows="3"
						name="review_Comment">${ review.review_Comment }</textarea>
					<input type="submit"></input>
				</div>
			</form>


			<div class="row">
				<button onclick="location.href='review_list'"
					class="btn btn-secondary btn-lg mx-auto">목록</button>
			</div>
		</div>

	</div>
	</main>

	<jsp:include page="Footer.jsp" flush="false" />
	<script type="text/javascript">
		var loadFile = function(event) {
			var x = document.createElement('img'), y = document.getElementById(
					"images").appendChild(x);
			console.log('d');
			y.src = URL.createObjectURL(event.target.files[0]);
			y.width = '100';
			y.height = '100';
		};
	</script>
</body>

</html>
