<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="tiles"
uri="http://tiles.apache.org/tags-tiles"%> <%@ taglib
uri="http://java.sun.com/jstl/core_rt" prefix="c"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <%@ taglib
uri="http://www.springframework.org/tags" prefix="spring"%> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
  $(function () {
    $("#Customer_Image").on("change", function () {
      readURL(this);
    });
  });

  function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
        $("#img-defult").attr("src", e.target.result);
      };

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

<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <table class="title-table ml-auto mr-auto" height="90">
      <tr>
        <div class="card mb-3 mt-5 ml-auto mr-auto p-2" style="max-width: 540px;">
          <div class="row no-gutters">
            <div class="col-md-4">
              <img id="Customer_Image" src="${customer.customer_Image}" width="150" height="150"
                onerror="this.src='./resources/images/profile.png'" />
            </div>
            <div class="col-md-8">
              <div class="card-body">
                <h5 class="card-title">
                  환영합니다! ${customer.customer_Name }님
                </h5>
                <p class="card-text"></p>
                <p>이메일: ${customer.customer_Email }</p>
                <p>주소: ${customer.customer_Address }</p>
                <p>전화번호: ${customer.customer_PhoneNumber }</p>
                <p class="card-text">
                  <small class="text-muted">회원가입 날짜 2019.11.12</small>
                </p>
              </div>
            </div>
          </div>
        </div>
        <td>
          <label>
            <input type="button" class="mypage-btn-group" value="펫등록하기" onclick="location.href='pet_register'" />
            <input type="button" class="mypage-btn-group" value="정보수정하기" onclick="location.href='customer_modify'" />
            <input type="button" class="mypage-btn-group" value="예약조회하기"
              onclick="location.href='customer_reserve_check'" />
            <input type="button" class="mypage-btn-group" value="후기모아보기"
              onclick="location.href='customer_review_mylist'" />
            <input type="button" class="mypage-btn-group" value="본인인증하기"
              onclick="location.href='customer_certification'" />
          </label>
        </td>
      </tr>
    </table>

    <span class="mb-3">나의 반려견 정보</span>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">반려 동물 이름</th>
          <th scope="col">반려 동물 종</th>
          <th scope="col">나이</th>
          <th scope="col">성별</th>
          <th scope="col">몸무게</th>
          <th scope="col">특이사항</th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${ pet }" var="pet">
          <tr>
            <td><a href="customer_reserve_check?petName=${pet.pet_Name }">${pet.pet_Name }</a></td>
            <td>${pet.pet_Type}</td>
            <td>${pet.pet_Age }</td>
            <td>${pet.pet_Gender }</td>
            <td>${pet.pet_Weight }</td>
            <td>${pet.pet_History }</td>
            <td>
              <button class="btn"
                onclick="location.href='pet_modify?customer_Index=${customer.customer_Index }&pet_Index=${pet.pet_Index }'">
                <i class="fas fa-edit"></i>
              </button>
              <button class="btn" onclick="location.href='pet_cancel?pet_Index=${pet.pet_Index}'">
                <i class="fas fa-trash"></i>
              </button>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <input type="button" class="mypage-btn-group" value="로그아웃" onclick="logout();" />
    <input type="button" class="mypage-btn-group" value="회원탈퇴" onclick="deleteTheCustomer();" />
  </div>
</div>