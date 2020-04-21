<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<!-- css -->
<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />"
	type="text/css" />
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

<title>반려동물 예약</title>
</head>

<body>
	<header>
		<nav class="menu navbar navbar-expand-lg navbar-light" id="top">
			<a class="navbar-brand" href="index.html"> <img
				src="<c:url value='/resources/images/logo.png' />" width="70"
				height="50" alt="logo">PET
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link"
						href="index.html">홈 <span class="sr-only">(current)</span></a></li>
					<li class="nav-item"><a class="nav-link" href="#">소개</a></li>
					<li class="nav-item active"><a class="nav-link"
						href="reserve.html">예약하기</a></li>
					<li class="nav-item"><a class="nav-link" href="#">제휴문의</a></li>
					<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="#">후기</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Q&A</a></li>
					<li class="nav-item"><a class="nav-link" href="login.html">Login</a>
					</li>

				</ul>

				<form class="menu_form form-inline my-2 my-lg-0">
					<input class="menu_form_input form-control mr-sm-2" type="search"
						placeholder="검색" aria-label="Search">
					<button class="menu_form_btn btn btn-outline-success my-2 my-sm-0"
						type="submit">
						<i class="fas fa-search"></i>
					</button>
				</form>
			</div>
		</nav>
	</header>
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
						<tr onclick="location.href='customer_review_view?reviewIdx=${review.review_Index}'">
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
	<footer class="container-fluid">
		<div class="row footer-container">
			<div class="col-12">
				<p>
					<a href="#top" class="footer_text ">Back to top</a>
				</p>
				<p>
					<a href="#" class="footer_text ">Language</a> <a href="#"
						class="footer_text">privacy</a> <a href="#" class="footer_text">Terms</a>
				</p>
				<p>© Copyright 2019, All Rights Reserved</p>
			</div>
			<div class="col-12 footer_sns">
				<a href="#"><i class="footer_sns_i fab fa-twitter"></i></a> <a
					href="#"><i class="footer_sns_i fab fa-instagram"></i></a> <a
					href="#"><i class="footer_sns_i fab fa-facebook-square"></i></a>
			</div>
		</div>
	</footer>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
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
