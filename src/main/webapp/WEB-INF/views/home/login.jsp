<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
							<div style="text-align: center"> 
							<a href="${kakao_url}">카카오 로그인</a> 
							</div>
							<a style="text-decoration: none;" href="signup.html">
							   회원가입 |
							<a style="text-decoration: none;" href="search_id"> 아이디 찾기   
							<a style="text-decoration: none;" href="search_pw"> 비밀번호 찾기      
						 </div>
				</form>
			</div>

		</div>
<script>
      setCookie('id', '', 1);
      setCookie('name', '', 1);
      setCookie('email', '', 1);
      setCookie('phone', '', 1);
      setCookie('address', '', 1);
   </script>