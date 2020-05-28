<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
   />
<link
      href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean"
      rel="stylesheet"
    />


<!-- css -->
<link rel="stylesheet" href="<c:url value='/resources/css/style.css' />" />

<style type="text/css">
      * {
        font-family: "Poor Story", cursive;
      }
      
      .error-button {
    border: none;
    font-family: inherit;
    font-size: 15px;
    color: #ffffff;
    background: #ea4545;
    cursor: pointer;
    border-radius: 50px;
    padding: 20px 50px;
    letter-spacing: 1.5px;
    display: inline-block;
    margin: 15px 30px;
    text-transform: uppercase;
    letter-spacing: 1px;
    font-weight: 700;
    outline: none;
    position: relative;
}
    </style>

<head>
<title>500에러 페이지</title>
</head>

<div class="row mx-auto main-container">
   <div class="col-10 mx-auto main-block">
   <h2><b>500 ERROR</b></h2>
      <br>
      <div class="form-group">
         <img src="<c:url value='/resources/images/error.png' />" width="180"
            height="180" alt="error">
      </div>
      <br>
      <div class="form-group">
         <h3>
            서비스 사용에 불편을 끼쳐드려서 대단히 죄송합니다.<br> 빠른 시간내에 문제를 처리하겠습니다.
         </h3>
      </div>
      
      <form action=index>
         <div class="form-group">
           <button type="submit" class="btn error-button">돌아가기</button>
         </div>
      </form>
   </div>
</div>