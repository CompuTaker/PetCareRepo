<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="container">
		<div class="col-10 mx-auto main-block">

			<h1>
				<i class="fas fa-check"></i>
			</h1>
			<h1>
				일반회원님의 비밀번호는
				<%= request.getAttribute("password")%>
				입니다
			</h1>

			<hr>

			<a class="btn btn-lg btn-secondary mx-auto" href="login"
				.html" role="button">로그인 화면으로 돌아가기</a>
		</div>
	</div>
</div>