<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<title>Petscare 소개 페이지</title>
</head>

<body>
	<!-- Header -->
	<header class="bg-light py-5 mb-5">
		<div class="container h-10">
			<div class="row h-10 align-items-center">
				<div class="col-lg-12">
					<h1 class="display-4 text-dark mt-5 mb-2">PetsCare 홈페이지에 방문해주셔서 감사합니다</h1>
					<p class="lead mb-5 text-dark-50">반려동물과 함께하는 행복한 삶을 만들어 가고
						싶습니다.</p>
				</div>
			</div>
		</div>
	</header>

	<!-- Page Content -->
	<div class="container">

		<div class="row">
			<div class="col-md-8 mb-5">
				<h2>
					PetsCare가 하는 일<img
						src="<c:url value='/resources/images/image5.jpg' />" width="100"
						height="80">
				</h2>
				<hr>
				<p>미용 예약 _나의 반려동물에게 더 예쁜 모습을 선물하세요.</p>
				<p>호텔 예약 _1년 365일 편안하고 안전한 호텔이 준비되어 있습니다.</p>
				<p>병원 예약 _나의 주변에 있는 병원을 파악하고 빠르게 예약할 수 있습니다.</p>

			</div>

			<div class="col-md-4 mb-5">
				<h2>만든 이들<img
						src="<c:url value='/resources/images/image5.jpg' />" width="100"
						height="80"></h2>
				<hr>
				<address>
					<strong>명지대학교 팀프로젝트</strong> <br>서울특별시 서대문구 <br>거북골로 34 <br>
				</address>
				<address>
					<abbr title="Phone">P:</abbr> (123) 456-7890 <br> <abbr
						title="Email">E:</abbr> <a href="mailto:#">name@example.com</a><br>
					<abbr title="Instagram">I:</abbr> <a href="mailto:#">https://www.instagram.com/mju_petscare</a><br>
					<abbr title="Facebook">F:</abbr> <a href="mailto:#">https://www.facebook.com/mju_petscare</a>
				</address>

			</div>

		</div>
	</div>


</body>

</html>