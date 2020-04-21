<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#Customer_Image").on('change', function() {
			readURL(this);
		});
	});

	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img-defult').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	function logout() {
		location.href = "logout";
	}

	function deleteTheCustomer() {
		location.href = "deleteTheCompany"; // 세션에서 customerIdx 받아오기!
	}
</script>
</head>

<body>
	<jsp:include page="Header.jsp" flush="false" />
	
<main class="container-fluid">
        <div class="row mx-auto main-container">
            <div class="col-10 mx-auto main-block">
                <table class="title-table ml-auto mr-auto" height="90">
                    <tr>
                       <div class="card mb-3 mt-5 ml-auto mr-auto p-2" style="max-width: 540px;">
  <div class="row no-gutters">
    <div class="col-md-4">
      <img  src="<c:url value='/resources/images/profile.png' />" class="card-img" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">환영합니다! ${company.company_Name }님</h5>
        <p class="card-text">
            <p>이메일: ${company.company_Email }</p>
            <p>주소: ${company.company_Address }</p>
            <p>전화번호:${company.company_UserPhoneNumber } </p>
        </p>
        <p class="card-text"><small class="text-muted">회원가입 날짜 2019.11.12</small></p>
      </div>
    </div>
  </div>
</div>
                        <td><label>기업 고객 마이페이지</label></td>                   
                    </tr>
                    <tr><td><input type="button" class="mypage-btn-group" value="후기작성" onclick="location.href='company_review_list'"></td></tr>
                </table>
               
			   <span class="mb-3">최근 예약 정보</span>
               <table class="table">
						<caption></caption>
						<thead>
							<tr>
								<th>예약 번호</th>
								<th>강아지 이름</th>
								<th>날짜</th>
								<th>시간</th>
								<th>요청사항</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items= "${ reserve }" var="reserve" varStatus="count">
								<tr>
									<td>${ count.index }</td>
									<td>${ reserve.pet_Name }</td>
									<td>${ reserve.reservation_Date }</td>
									<td>${ reserve.reservation_Time }</td>
									<td>${ reserve.reservation_DetailService }</td>
								</tr>
								<!-- session에 저장된 customer -->
							</c:forEach>
						</tbody>					
						<tfoot></tfoot>
					</table>

   			<input type="button" class="mypage-btn-group" value="로그아웃"onclick="logout();">
            <input type="button" class="mypage-btn-group" value="회원탈퇴"onclick="deleteTheCustomer();">
        </div>
        </div>
    </main>
	<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>
