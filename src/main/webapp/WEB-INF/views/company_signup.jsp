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
			<div class="col-10 mx-auto main-block" style="text-align: left;">
				<table class="login-table" height="90">
					<tr>
						<td><label class="login-title">기업 회원가입</label></td>
					</tr>
				</table>
               <form class="w-50 ml-auto mr-auto mt-5 mb-5" action="company_signupDo" method="post">
                <div class="mb-5">
                    <img id="img-default" src="<c:url value='/resources/images/profile.png' />" width="100" height="100">
                    <input type="file" id="Company_Image" name="Company_Image" accept="image/*">
                </div>

                <div class="form-group">
                    <label for="Company_Id">아이디</label>
                    <input type="text" class="form-control" id="Company_Id" placeholder="아이디" name="company_Id">
                </div>
                <div class="form-group">
                    <label for="Company_Password">비밀번호</label>
                    <input type="password" class="form-control" id="Company_Password" placeholder="비밀번호" name="company_Password">
                </div>
                <div class="form-group">
                    <label for="Company_UserName">업주 이름</label>
                    <input type="text" class="form-control" id="Company_UserName" placeholder="업주 이름" name="company_UserName">
                </div>
                
                <div class="form-group">
                    <label for="Company_ResidentNumber">주민 등록 번호</label>
                    <input type="text" class="form-control" id="Company_ResidentNumber" placeholder="사업자 주민등록번호" name="company_ResidentNumber">
                </div>
                
                <div class="form-group">
                    <label for="Company_Address">업체 주소</label>
                    <input type="text" class="form-control" id="Company_Address" placeholder="업체 주소" name="company_Address">
                </div>

                <div class="form-group">
                    <label for="Company_Email">이메일</label>
                    <input type="text" class="form-control" id="Company_Email" placeholder="이메일" name="company_Email">
                </div>

                <div class="form-group">
                    <label for="Company_Name">업체 이름</label>
                    <input type="text" class="form-control" id="Company_Name" placeholder="업체 이름" name="company_Name">
                </div>

                <div class="form-group">
                    <label for="Company_UserPhoneNumber">업체 전화번호</label>
                    <input type="text" class="form-control" id="Company_UserPhoneNumber" placeholder="업체 전화번호" name="company_UserPhoneNumber">
                </div>

                <div class="form-group">
                    <label for="Company_Number">사업자 번호</label>
                    <input type="text" class="form-control" id="Company_Number" placeholder="사업자 번호" name="company_Number">
                </div>

                <div class="form-group">
                    <label for="Company_Type">업종</label>
                    <input type="text" class="form-control" id="Company_Type" placeholder="업종" name="company_Type">
                </div>

                <button type="submit" class="btn btn-primary">회원가입</button>
            </form>

        </div>
    </div>
    </div>
</main>
<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>
