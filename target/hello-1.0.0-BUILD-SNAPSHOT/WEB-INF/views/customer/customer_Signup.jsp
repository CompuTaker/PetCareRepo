<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block" style="text-align: left;">
		<table class="login-table" height="90">
			<tr>
				<td><label class="login-title">개인 회원가입</label></td>
			</tr>
		</table>

		<form class="w-50 ml-auto mr-auto mt-5 mb-5"
			action="customer_signupDo" method="post">
			<div class="mb-5">
				<img id="img-default"
					src="<c:url value='/resources/images/profile.png' />" width="100"
					height="100" /> <input type="file" id="Customer_Image"
					name="Customer_Image" accept="image/*" name="customer_Image" />
			</div>

			<div class="form-group">
				<label for="Customer_Id">아이디</label> <input type="text"
					class="form-control" id="Customer_Id" placeholder="아이디"
					name="customer_Id" required />
				<button type="button" id="checkId">중복확인</button>
				<div class="check_font" id="id_check"></div>
			</div>
			<div class="form-group">
				<label for="Customer_Password">비밀번호</label> <input type="password"
					class="form-control" id="Customer_Password" placeholder="비밀번호"
					name="customer_Password" required />
			</div>
			<div class="form-group">
				<label for="Customer_Name">이름</label> <input type="text"
					class="form-control" id="Customer_Name" placeholder="이름"
					name="customer_Name" required />
			</div>

			<div class="form-group">
				<label for="Customer_ResidentNumber">주민번호</label> <input type="text"
					class="form-control" id="Customer_ResidentNumber"
					placeholder="고객 주민등록번호" name="customer_ResidentNumber" required />
				<button type="button" id="checkResidentNum">중복확인</button>
				<div class="check_RN" id="residentNum_check"></div>
			</div>

			<div class="form-group">
				<label for="Customer_Email">이메일</label> <input type="text"
					class="form-control" id="Customer_Email" placeholder="이메일"
					name="customer_Email" required />
			</div>

			<div class="form-group">
				<label for="Customer_PhoneNumber">전화번호</label> <input type="text"
					class="form-control" id="Customer_PhoneNumber" placeholder="전화번호"
					name="customer_PhoneNumber" required />
			</div>

			<div class="form-group">
				<label for="Customer_Address">주소</label> <input type="text"
					class="form-control" id="Customer_Address" placeholder="주소"
					name="customer_Address" required />
			</div>

			<button type="submit" class="btn btn-primary">회원가입</button>
		</form>
	</div>
</div>
