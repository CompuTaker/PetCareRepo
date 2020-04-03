<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>

<html>
<head>
<title>404에러 페이지</title>
</head>
<body>
	<jsp:include page="Header.jsp" flush="false" />
	<main class="container-fluid">
	<div class="row mx-auto main-container">

		<div class="col-10 mx-auto main-block">
			<br>
			<div class="form-group">
				<img src="<c:url value='/resources/images/error.png' />" width="180"
					height="180" alt="error">
			</div>
			<br>
			<div class="form-group">
				<h3>요청하신 페이지는 존재하지 않습니다.</h3>
			</div>

			<form action=index>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">돌아가기</button>
				</div>
			</form>
		</div>

	</div>
	</main>
	<jsp:include page="Footer.jsp" flush="false" />
</body>
</html>