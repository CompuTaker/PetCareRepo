<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block" style="text-align: left;">
		<table class="login-table" height="90">
			<tr>
				<td><label class="login-title">개인 정보 수정</label></td>
			</tr>
		</table>

		<form class="w-50 ml-auto mr-auto mt-5 mb-5"
			action="customer_modify_ok" method="post"  enctype="multipart/form-data">
			<div class="mb-5">
				<img id="img-default"
					src="${customer.customer_Image }"  width="100" height="100" onerror="this.src='./resources/images/profile.png'"> 
				<input type="file" id="Customer_Image" name="imageFile" accept="image/*" onchange="loadFile(event)"/>
			</div>

			<div class="form-group">
				<label for="Customer_Id">아이디</label> <input type="text"
					class="form-control" id="Customer_Id" placeholder="아이디"
					name="customer_Id" value="${customer.customer_Id}" readonly />
			</div>

			<div class="form-group">
				<label for="Customer_Password">비밀번호</label> <input type="password"
					class="form-control" id="Customer_Password" placeholder="비밀번호"
					value="${customer.customer_Password}" name="customer_Password" required/>
					
			</div>

			<div class="form-group">
				<label for="Customer_Name">이름</label> <input type="text"
					class="form-control" id="Customer_Name" placeholder="이름"
					name="customer_Name" value="${customer.customer_Name}" readonly />
			</div>


			<div class="form-group">
				<label for="Customer_Email">이메일</label> <input type="text"
					class="form-control" id="Customer_Email" placeholder="이메일"
					name="customer_Email" value="${customer.customer_Email}" required/>
					
			</div>

			<div class="form-group">
				<label for="Customer_PhoneNumber">전화번호</label> <input type="text"
					class="form-control" id="Customer_PhoneNumber" placeholder="전화번호"
					name="customer_PhoneNumber"
					value="${customer.customer_PhoneNumber}" required />
					
			</div>

			<div class="form-group">
				<label for="Customer_Address">주소</label> <input type="text"
					class="form-control" id="Customer_Address" placeholder="주소"
					name="customer_Address" value="${customer.customer_Address}" required/>
			</div>
			<button type="submit" class="btn btn-primary">수정하기</button>
		</form>
	</div>
</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">

    $(function(){
        var responseMessage = "<c:out value="${message}" />";
        if(responseMessage != ""){
            alert(responseMessage)
        }
    }) 
    
	var loadFile = function(event) {
	    var output = document.getElementById('img-default');
	    output.src = URL.createObjectURL(event.target.files[0]);
	    output.onload = function() {
	      URL.revokeObjectURL(output.src)
	    }
	  };

</script>