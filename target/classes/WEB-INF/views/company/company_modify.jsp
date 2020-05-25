<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block" style="text-align: left;">
    <table class="login-table" height="90">
      <tr>
        <td><label class="login-title">기업 정보 수정</label></td>
      </tr>
    </table>

    <form
      class="w-50 ml-auto mr-auto mt-5 mb-5"
      action="company_modify_ok"
      method="post"
      enctype="multipart/form-data"
    >
      <div class="mb-5">
        <img
          id="img-default"
          src="<c:url value='/resources/images/company.jpg' />"
          width="100"
          height="100"
        />
        <input
          type="file"
          id="Company_Image"
          name="imageFile"
          accept="image/*"
        />
      </div>

      <div class="form-group">
        <label for="Company_Id">아이디</label>
        <input
          type="text"
          class="form-control"
          id="Company_Id"
          placeholder="아이디"
          name="company_Id"
          value="${company.company_Id }"
          readonly
        />
      </div>
      <div class="form-group">
        <label for="Company_Password">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="Company_Password"
          placeholder="비밀번호"
          name="company_Password"
          value="${company.company_Password }"
        />
      </div>
      <div class="form-group">
        <label for="Company_UserName">업주 이름</label>
        <input
          type="text"
          class="form-control"
          id="Company_UserName"
          placeholder="업주 이름"
          name="company_UserName"
          value="${company.company_UserName }"
        />
      </div>

    

      <div class="form-group">
        <label for="Company_Address">업체 주소</label>
        <input
          type="text"
          class="form-control"
          id="Company_Address"
          placeholder="업체 주소"
          name="company_Address"
          value="${company.company_Address }"
        />
      </div>

      <div class="form-group">
        <label for="Company_Email">이메일</label>
        <input
          type="text"
          class="form-control"
          id="Company_Email"
          placeholder="이메일"
          name="company_Email"
          value="${company.company_Email }"
        />
      </div>

      <div class="form-group">
        <label for="Company_Name">업체 이름</label>
        <input
          type="text"
          class="form-control"
          id="Company_Name"
          placeholder="업체 이름"
          name="company_Name"
          value="${company.company_Name }"
        />
      </div>

      <div class="form-group">
        <label for="Company_UserPhoneNumber">업체 전화번호</label>
        <input
          type="text"
          class="form-control"
          id="Company_UserPhoneNumber"
          placeholder="업체 전화번호"
          name="company_UserPhoneNumber"
          value="${company.company_UserPhoneNumber }"
        />
      </div>

      <div class="form-group">
        <label for="Company_Number">사업자 번호</label>
        <input
          type="text"
          class="form-control"
          id="Company_Number"
          placeholder="사업자 번호"
          name="company_Number"
          value="${company.company_Number }"
          readonly
        />
      </div>

      <div class="form-group">
        <label for="Company_Type">업종</label>
        <input
          type="text"
          class="form-control"
          id="Company_Type"
          placeholder="업종"
          name="company_Type"
          value="${company.company_Type }"
        />
      </div>

      <button type="submit" class="btn btn-primary">수정하기</button>
    </form>
  </div>
</div>
