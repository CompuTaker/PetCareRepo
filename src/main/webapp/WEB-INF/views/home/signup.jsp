<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <div class="row mb-5 mt-5 ml-auto mr-auto" style="max-width: 800px;">
      <a class="m-auto" href="signupDo?flag=comp">
        <div class="card" style="width: 18rem;">
          <img
            src="<c:url value='/resources/images/company.jpg' />"
            class="card-img-top"
            alt="company"
          />
          <div class="card-body">
            <p class="card-text">기업 회원가입</p>
          </div>
        </div>
      </a>
      <a class="m-auto" href="signupDo?flag=user">
        <div class="card" style="width: 18rem;">
          <img
            src="<c:url value='/resources/images/individual.jpg' />"
            class="card-img-top"
            alt="individual"
          />
          <div class="card-body">
            <p class="card-text">개인 회원가입</p>
          </div>
        </div>
        <input type="hidden" name="flag" value="user" />
      </a>
    </div>
  </div>
</div>
