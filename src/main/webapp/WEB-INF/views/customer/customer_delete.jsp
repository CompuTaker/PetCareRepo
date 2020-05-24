<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<br> <br> <br> <br>
		<form action="customer_delete_ok" method="post">
			<table class="table table-striped table-hover">
				<tr>
					<td align="center">탈퇴를 위한 비밀번호를 입력해 주세요</td>
					<td><input type="password" id="customer_Password"
						name="customer_Password" size="50" maxlength="100" /></td>
				</tr>
			</table>

			<button type="button" class="btn btn-secondary mx-auto"
				onclick="location ='customer_Profile'">취소</button>
			<button type="submit" class="btn btn-danger mx-auto " id="btnDelete"
				onclick="checkValue()">탈퇴</button>

		</form>
	</div>
</div>
<script type="text/javascript">
	// 비밀번호 미입력시 경고창

	function checkValue() {

		if (!document.customer_Password.value) {

			alert("비밀번호를 입력하지 않았습니다.");

			return false;
		}
	}
	$(document).ready(function() {

		$("#btnDelete").click(function() {
			// 확인 대화상자 
			if (confirm("삭제하시겠습니까?")) {
				location.href = "customer_delete_ok";
				document.submit();
			}
		});

	});
</script>

