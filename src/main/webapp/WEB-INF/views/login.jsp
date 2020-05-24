<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
</head>

<body>
	<jsp:include page="Header.jsp" flush="false" />
	<main class="container-fluid">
		<div class="row mx-auto main-container">
			<div class="col-10 mx-auto main-block">
					<div class="logindiv col-md-6 form-group">
							<label class="login-title" for="login-title">LOGIN</label>
						 </div>
						 
						 
				<form class="form-horizontal indi-form" action="loginDo" name="loginform"
					method="post">
					
						<div class="logindiv col-md-6 form-group">
							<input type="text" class="form-control" id="userid" name="id" placeholder="ID">
						  </div>
						  <div class="logindiv col-md-6 form-group">
							<input type="password" class="form-control" name="pw" id="password" placeholder="PASSWORD">
						  </div>
						  <div class="logindiv col-md-6 form-group">
						  	<button type="submit"  id="btn-login" name="login" class="btn btn-login">로그인</button>
						</div>
						<div class="logindiv col-md-6 form-group">
							<a style="text-decoration: none;" href="signup.html">
								회원가입 |
							<a style="text-decoration: none;" href=""> 비밀번호분실		
						</div>
			
					
					
	
				</form>
			</div>

		</div>

	</main>
	<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>