<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- css -->
    <link rel="stylesheet" href="css/style.css" type="text/css"/>

    <title>반려동물 예약</title>
</head>

<body>
<header>
    <nav class="menu navbar navbar-expand-lg navbar-light" id="top">
        <a class="navbar-brand" href="index.html">
            <img src="<c:url value='/resources/images/logo.png' />" width="70" height="50" alt="logo">PET</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.html">홈 <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">소개</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="reserve.html">예약하기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">제휴문의</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">공지사항</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">후기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Q&A</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="login.html">Login</a>
                </li>

            </ul>

            <form class="menu_form form-inline my-2 my-lg-0">
                <input class="menu_form_input form-control mr-sm-2" type="search" placeholder="검색"
                       aria-label="Search">
                <button class="menu_form_btn btn btn-outline-success my-2 my-sm-0" type="submit">
                    <i class="fas fa-search"></i>
                </button>
            </form>
        </div>
    </nav>
</header>
<main class="container-fluid">
    <div class="row mx-auto main-container">
        <div class="col-10 mx-auto main-block" style="text-align: left;">
            <table class="login-table" height="90">
                <tr>
                    <td><label class="login-title">기업 정보 수정</label></td>
                </tr>
            </table>

            <form class="w-50 ml-auto mr-auto mt-5 mb-5" action="company_signupDo" method="post">
                <div class="mb-5">
                    <img id="img-default" src="<c:url value='/resources/images/company.jpg' />" width="100" height="100">
                    <input type="file" id="Company_Image" name="Company_Image" accept="image/*"
                   	name="company_Image"/>
                </div>

                <div class="form-group">
                    <label for="Company_Id">아이디</label>
                    <input type="text" class="form-control" id="Company_Id" placeholder="아이디"
                    name="company_Id"/>
                </div>
                <div class="form-group">
                    <label for="Company_Password">비밀번호</label>
                    <input type="password" class="form-control" id="Company_Password" placeholder="비밀번호"
                    name="company_Password"/>
                </div>
                <div class="form-group">
                    <label for="Company_UserName">업주 이름</label>
                    <input type="text" class="form-control" id="Company_UserName" placeholder="업주 이름"
                    name="company_UserName"/>
                </div>
                
				<div class="form-group">
                    <label for="Company_ResidentNumber">주민 등록 번호</label>
                    <input type="text" class="form-control" id="Company_ResidentNumber" placeholder="사업자 주민등록번호"
                    name="company_ResidentNumber"/>
                </div>
                
                <div class="form-group">
                    <label for="Company_Address">업체 주소</label>
                    <input type="text" class="form-control" id="Company_Address" placeholder="업체 주소"
                    name="company_Address"/>
                </div>

                <div class="form-group">
                    <label for="Company_Email">이메일</label>
                    <input type="text" class="form-control" id="Company_Email" placeholder="이메일"
                    name="company_Email"/>
                </div>

                <div class="form-group">
                    <label for="Company_Name">업체 이름</label>
                    <input type="text" class="form-control" id="Company_Name" placeholder="업체 이름"
                    name="company_Name"/>
                </div>

                <div class="form-group">
                    <label for="Company_UserPhoneNumber">업체 전화번호</label>
                    <input type="text" class="form-control" id="Company_UserPhoneNumber" placeholder="업체 전화번호"
                    name="company_UserPhoneNumber"/>
                </div>

                <div class="form-group">
                    <label for="Company_Number">사업자 번호</label>
                    <input type="text" class="form-control" id="Company_Number" placeholder="사업자 번호"
                    name="company_Number"/>
                </div>

                <div class="form-group">
                    <label for="Company_Type">업종</label>
                    <input type="text" class="form-control" id="Company_Type" placeholder="업종"
                    name="company_Type"/>
                </div>

                <button type="submit" class="btn btn-primary">회원가입</button>
            </form>


        </div>
    </div>
    </div>
</main>
<footer class="container-fluid">
    <div class="row footer-container">
        <div class="col-12">
            <p><a href="#top" class="footer_text ">Back to top</a></p>
            <p><a href="#" class="footer_text ">Language</a> <a href="#" class="footer_text">privacy</a>
                <a href="#" class="footer_text">Terms</a></p>
            <p>© Copyright 2019, All Rights Reserved</p>
        </div>
        <div class="col-12 footer_sns">
            <a href="#"><i class="footer_sns_i fab fa-twitter"></i></a>
            <a href="#"><i class="footer_sns_i fab fa-instagram"></i></a>
            <a href="#"><i class="footer_sns_i fab fa-facebook-square"></i></a>
        </div>
    </div>
</footer>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</body>

</html>
