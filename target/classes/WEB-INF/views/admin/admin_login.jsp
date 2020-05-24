<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <div class="row mx-auto main-container">
         <div class="col-10 mx-auto main-block">
               <div class="logindiv col-md-6 form-group">
                   <label class="login-title" for="login-title">관리자 로그인</label>
               </div>
                   
                   
            <form class="form-horizontal indi-form" action="admin_login" name="loginform"
               method="post">
               
                  <div class="logindiv col-md-6 form-group">
                     <input type="text" class="form-control" id="username" name="username" placeholder="ID">
                    </div>
                    <div class="logindiv col-md-6 form-group">
                     <input type="password" class="form-control" name="password" id="password" placeholder="PASSWORD">
                    </div>
                    <div class="logindiv col-md-6 form-group">
                       <button type="submit"  id="btn-login" name="login" class="btn btn-login">로그인</button>
                  </div>
   
            </form>
         </div>

      </div>
