<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block" style="text-align: left;">
    <table class="login-table" height="90">
      <tr>
        <td><label class="login-title">기업 회원가입</label></td>
      </tr>
    </table>
	<!-- 수정함 enctype="multipart/form-data"추가 -->
    <form
      class="w-50 ml-auto mr-auto mt-5 mb-5"
      action="company_signupDo"
      method="post"
       enctype="multipart/form-data"
    >
        <div class="form-group">
        <img
          id="imageFile"
          src="#" 
		style="width:100px; height:100px"  
			onerror=src="<c:url value='/resources/images/profile.png' />" 
			alt="image" 
			/>
        
        <input
          type="file" 
        onchange="readURL(this);" 
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
          required
        />
        <button type="button" id="checkCompanyId">중복확인</button>
        <div class="check_CI" id="companyId_check"></div>
      </div>
      <div class="form-group">
        <label for="Company_Password">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="Company_Password"
          placeholder="비밀번호"
          name="company_Password"
          required
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
          required
        />
      </div>

      <div class="form-group">
        <label for="Company_ResidentNumber">주민 등록 번호</label>
        <input
          type="text"
          class="form-control"
          id="Company_ResidentNumber"
          placeholder="사업자 주민등록번호"
          name="company_ResidentNumber"
          required
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
          required
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
          required
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
          required
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
          required
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
          required
        />
        <button type="button" id="checkCompanyNum">중복확인</button>
        <div class="check_CN" id="companyNum_check"></div>
      </div>

      <div class="form-group">
        <label for="Company_Type">업종</label>
        <input
          type="text"
          class="form-control"
          id="Company_Type"
          placeholder="업종"
          name="company_Type"
          required
        />
      </div>

      <button type="submit" class="btn btn-primary">회원가입</button>
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

  $("#checkCompanyId").click(function() {
  	var company_Id = $('#Company_Id').val();
  	$.ajax({
  		url : '${pageContext.request.contextPath}/company_checkcId?company_Id='+ company_Id,
  		method : 'GET',
  		async : false,
  		complete : function(data) {
  			if (data.responseText == 1) {
  				$("#companyId_check").text("중복입니다.");
  				$("#companyId_check").css("color", "red");
  				$('#Company_Id').val("");
  			} else {
  				$("#companyId_check").text("사용가능합니다.");
  				$("#companyId_check").css("color", "blue");
  				$('#Company_Id').attr("readonly",true);
  				$('#checkCompanyId').attr("disabled",true);
  			}
  		}
  	});

  });


  $("#checkCompanyNum").click(function() {
  	var company_Number = $('#Company_Number').val();
  	$.ajax({
  		url : '${pageContext.request.contextPath}/company_checkComNum?company_Number='+ company_Number,
  		method : 'GET',
  		async : false,
  		complete : function(data) {
  			if (data.responseText == 1) {
  				$("#companyNum_check").text("중복입니다.");
  				$("#companyNum_check").css("color", "red");
  				$('#Comapny_Number').val("");
  			} else {
  				$("#companyNum_check").text("사용가능합니다.");
  				$("#companyNum_check").css("color", "blue");
  				$("#Company_Number").attr("readonly",true);
  				$('#checkCompanyNum').attr("disabled",true);
  			}
  		}
  	});

  });
  
//이미지 미리보기	
	 function readURL(input) {
	  if (input.files && input.files[0]) {
	  var reader = new FileReader();
	  reader.onload = function (e) {  
	  $('#imageFile').attr('src', e.target.result); 
	}                                        
	reader.readAsDataURL(input.files[0]);     

	}
	}


</script>
