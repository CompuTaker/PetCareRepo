<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block" style="text-align: left;">
		<div class="w-50 ml-auto mr-auto mt-5 mb-5">
			<table class="login-table" height="90">
				<tr>
					<td><label class="login-title">비밀번호 찾기</label></td>
				</tr>
			</table>

			<div style="margin-left: 10px"
				class="custom-control custom-radio custom-control-inline">
				<input type="radio" class="custom-control-input" id="search_1"
					name="search_total" onclick="search_check(1)" checked="checked">
				<label class="custom-control-label" for="search_1">기업회원 비밀번호
					찾기</label>
			</div>
			<div class="custom-control custom-radio custom-control-inline">
				<input type="radio" class="custom-control-input" id="search_2"
					name="search_total" onclick="search_check(2)"> <label
					class="custom-control-label" for="search_2">개인회원 비밀번호 찾기</label>
			</div>

			<div id="searchI">
				<form class="w-50 ml-auto mr-auto mt-5 mb-5"
					action="search_pw_company" method="POST">
					<div style="margin-top: 30px;" class="form-group">
						<label class="font-weight-bold" for="inputName_2">이름</label>
						<div>
							<input type="text" class="form-control" id="inputName_2"
								name="company_UserName" placeholder="">
						</div>
					</div>

					<div class="form-group">
						<label class="font-weight-bold" for="inputId_2">아이디</label>
						<div>
							<input type="text" class="form-control" id="inputId_2"
								name="company_Id" placeholder="ex) lovecat">
						</div>
					</div>
					<div class="form-group">
						<label class="font-weight-bold" for="inputPhone_2">사업자등록번호</label>
						<div>
							<input type="text" class="form-control" id="company_Number"
								name="company_Number" placeholder="ex) 9자리 사업자등록번호">
						</div>

					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">확인</button>
					</div>
				</form>
			</div>


			<div id="searchP" style="display: none;">
				<form class="w-50 ml-auto mr-auto mt-5 mb-5"
					action="search_pw_customer" method="POST">
					<div style="margin-top: 30px;" class="form-group">
						<label class="font-weight-bold" for="inputName_2">이름</label>
						<div>
							<input type="text" class="form-control" id="inputName_2"
								name="customer_Name" placeholder="">
						</div>
					</div>

					<div class="form-group">
						<label class="font-weight-bold" for="inputId_2">아이디</label>
						<div>
							<input type="text" class="form-control" id="inputId_2"
								name="customer_Id" placeholder="ex) lovecat">
						</div>
					</div>
					<div class="form-group">
						<label class="font-weight-bold" for="inputPhone_2">휴대폰번호</label>
						<div>
							<input type="text" class="form-control" id="inputPhone_2"
								name="customer_PhoneNumber" placeholder="ex) 01012345678">
						</div>

					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">확인</button>
					</div>
				</form>
			</div>
		</div>
	</div>


</div>

<script>
      function search_check(num) {
         if (num == '1') {
            document.getElementById("searchP").style.display = "none";
            document.getElementById("searchI").style.display = "";
         } else {
            document.getElementById("searchI").style.display = "none";
            document.getElementById("searchP").style.display = "";
         }
      }
   </script>
