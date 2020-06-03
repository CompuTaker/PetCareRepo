<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<nav class="menu navbar navbar-expand-lg navbar-light" id="top">
	<a class="navbar-brand" href="index"> <img src="<c:url value='/resources/images/logo.png' />" width="70" height="50"
			alt="logo" />PET
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="index">홈
					<span class="sr-only">(current)</span>
				</a></li>
			<li class="nav-item"><a class="nav-link" href="#">소개</a></li>
			<li class="nav-item"><a class="nav-link" href="reserve">예약하기</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">제휴문의</a></li>
			<li class="nav-item"><a class="nav-link" href="noticePage?page=1">공지사항</a></li>
			<li class="nav-item"><a class="nav-link" href="qnaPage?page=1">Q&A</a></li>
			<li class="nav-item"><a class="nav-link" href="searchCompany?page=1">업체찾기</a></li>
			<li class="nav-item"><a class="nav-link" href="searchReview?page=1">후기</a></li>
			<c:choose>
				<c:when
					test="${empty customer.customer_Index && empty company.company_Index && empty superuser.superuser_Index}">
					<li class="nav-item"><a class="nav-link" href="loginOrProfile">Login</a>
				</c:when>
				<c:otherwise>

					<li class="nav-item"><a class="nav-link" href="loginOrProfile">My page</a></li>
				</c:otherwise>
			</c:choose>

		</ul>

		<form class="menu_form form-inline my-2 my-lg-0" action="searchByTerm" method="get">
			<input class="menu_form_input form-control mr-sm-2" type="search" placeholder="검색" aria-label="Search"
				name="term" />
			<button class="menu_form_btn btn btn-outline-success my-2 my-sm-0" type="submit">
				<i class="fas fa-search"></i>
			</button>
		</form>
	</div>
</nav>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	var setCookie = function (name, value, day) {
		var date = new Date();
		date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString()
			+ ';path=/';
	};

	var getCookie = function (name) {
		var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
		return value ? value[2] : null;
	};

</script>