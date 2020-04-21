<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<head>
<title>500에러 페이지</title>
</head>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<br>
		<div class="form-group">
			<img src="<c:url value='/resources/images/error.png' />" width="180"
				height="180" alt="error">
		</div>
		<br>
		<div class="form-group">
			<h3>
				서비스 사용에 불편을 끼쳐드려서 대단히 죄송합니다.<br> 빠른 시간내에 문제를 처리하겠습니다.
			</h3>
		</div>
	</div>
</div>