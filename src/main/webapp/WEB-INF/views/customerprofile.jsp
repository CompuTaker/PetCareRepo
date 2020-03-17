<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>

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
		location.href = "deleteTheCustomer"; // 세션에서 customerIdx 받아오기!
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
                               <img id="Customer_Image" src="<c:url value='/resources/images/logo.png' />" width="150"height="150">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">환영합니다! ${customer.customer_Name }님</h5>
                                    <p class="card-text">
                                    <p>이메일: ${customer.customer_Email }</p>
                                    <p>주소: ${customer.customer_Address }</p>
                                    <p>전화번호:  ${customer.customer_PhoneNumber } </p>
                                    </p>
                                    <p class="card-text"><small class="text-muted">회원가입 날짜 2019.11.12</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <td><label>
                    <input type="button" class="mypage-btn-group" value="펫등록하기" onclick="location.href='pet_register'">
                     <input type="button" class="mypage-btn-group"value="정보수정하기" onclick="location.href='customer_modify'">  
                      <input type="button" class="mypage-btn-group"value="예약조회하기" onclick="location.href='customer_reserve_check'">
                    </label></td>
                </tr>
            </table>

            <span class="mb-3">나의 반려견 정보</span>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">반려 동물 이름</th>
                    <th scope="col">반려 동물 종</th>
                    <th scope="col">나이</th>
                    <th scope="col">성별</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ pet }" var="pet">
                <tr>
                    <th scope="row">${pet.pet_Index}</th>
                    <td>${pet.pet_Name }</td>
                    <td>${pet.pet_Type}</td>
                    <td>${pet.pet_Age }</td>
                    <td>${pet.pet_Gender }</td>
                    <td>
                     	<button class="btn" onclick="location.href='pet_modify?customer_Index=${customer.customer_Index }&pet_Index=${pet.pet_Index }'">
                      <i class="fas fa-edit"></i></button>
                        <button class="btn"><i class="fas fa-trash"></i></button>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <input type="button" class="mypage-btn-group" value="로그아웃"onclick="logout();">
            <input type="button" class="mypage-btn-group" value="회원탈퇴"onclick="deleteTheCustomer();">
        </div>
    </div>
</main>

		
			

	<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>
