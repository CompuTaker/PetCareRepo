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
        <div class="container">
            <div class="col-10 mx-auto main-block">
            
                <h1><i class="fas fa-check"></i></h1>
                <h1>일반회원님의 비밀번호는
                <%= request.getAttribute("password")%>          
                 입니다</h1>
               
                <hr>
                  
                <a class="btn btn-lg btn-secondary mx-auto" href="login" .html" role="button">로그인 화면으로 돌아가기</a>
            </div>
        </div>
    </main>
      <jsp:include page="Footer.jsp" flush="false" />

   <!-- Optional JavaScript -->
   <!-- jQuery first, then Popper.js, then Bootstrap JS -->
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"></script>
   <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"></script>
   <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"></script>
   <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>