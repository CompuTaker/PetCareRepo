<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
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

<link
	href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean"
	rel="stylesheet">

<!-- css -->
<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />" />

<title>반려동물 예약</title>

<style type="text/css">
* {
	font-family: 'Poor Story', cursive;
}
</style>


</head>
<body>
	<header>
		<nav class="menu navbar navbar-expand-lg navbar-light" id="top">
			<a class="navbar-brand" href="index.jsp"> <img
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
					<li class="nav-item"><a class="nav-link" href="reserve">예약하기</a></li>
					<li class="nav-item"><a class="nav-link" href="#">제휴문의</a></li>
					<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Q&A</a></li>
					<li class="nav-item"><a class="nav-link" href="loginOrProfile">Login</a>
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
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
</body>
</html>