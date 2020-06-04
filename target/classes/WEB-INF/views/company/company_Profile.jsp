<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">


	function logout() {
		location.href = "logout";
	}

	function deleteTheCompany() {
		location.href = "deleteTheCompany"; // 세션에서 customerIdx 받아오기!
	}
</script>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<table class="title-table ml-auto mr-auto" height="90">
			<tr>
				<div class="card mb-3 mt-5 ml-auto mr-auto p-2"
					style="max-width: 540px;">
					<div class="row no-gutters">
						<div class="col-md-4">
							<img id="Company_Image"
							src = "${company.company_Image}"  width="150"
								height="150"
								onerror="this.src='./resources/images/profile.png'"
								/>
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title">환영합니다! ${company.company_Name }님</h5>
								<p class="card-text">
								<p>이메일: ${company.company_Email }</p>
								<p>주소: ${company.company_Address }</p>
								<p>전화번호:${company.company_UserPhoneNumber }</p>
								</p>
								<p class="card-text">
									<small class="text-muted">${company.subscribe_Date }</small>
								</p>
							</div>
						</div>
					</div>
				</div>
				<td><label>기업 고객 마이페이지</label></td>
			</tr>
			<tr>
				<td><input type="button" class="mypage-btn-group" value="후기모아보기"
					onclick="location.href='company_review_list'">
					 <input
						type="button" class="mypage-btn-group" value="정보수정하기"
						onclick="location.href='company_modify'">
						
					<input type="button" class="mypage-btn-group" value="예약정보조회"
					onclick="location.href='company_reserve_check?page=1'"></td>
				
			</tr>
		</table>

		

		<input type="button" class="mypage-btn-group" value="로그아웃"
			onclick="logout();"> <input type="button"
			class="mypage-btn-group" value="회원탈퇴" onclick="deleteTheCompany();">
	</div>
</div>
