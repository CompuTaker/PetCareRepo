<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>

<body>
	<jsp:include page="Header.jsp" flush="false" />
	<main class="container-fluid">
	<div class="row mx-auto main-container">
		<div class="mx-auto main-block   col-12">
			<h1 class="pet_register_title">펫 등록하기</h1>
			<form class="pet_register" name="pet_register" method="post"
				action="pet_ok">
				<div class="form-group row">
					<label class="col-sm-2 " for="pet_Name">이름</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" id="pet_Name"
							name="pet_Name" placeholder="멍멍이" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label" for="pet_Type">종</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" id="pet_Type"
							name="pet_Type" placeholder="강아지" required>
					</div>
				</div>
				<div class="form-group row">
					<label class=" col-sm-2 col-form-label" for="pet_Gender">성별</label>
					<div class="gender-radio-group col-sm-8">
						<input type="radio" name="pet_Gender" value="암컷" required>암컷 <input
							type="radio" name="pet_Gender" value="수컷" required>수컷
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label" for="pet_Age">나이(개월수)</label>
					<div class="col-sm-8">
						<input class="form-control" type="number" id="pet_Age"
							name="pet_Age" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label" for="pet_IsNeutralized">중성화수술</label>
					<div class="isNeutralized-radio-group col-sm-8" id="pet_IsNeutralized">
						<input type="radio" name="pet_IsNeutralized" value="1"
							required> <i class="far fa-circle"></i> <input type="radio"
							name="pet_IsNeutralized" value="0" required> <i class="far fa-times-circle"></i>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label" for="pet_Weight">무게</label>
					<div class="col-sm-8">
						<input type="number" class="form-control" id="pet_Weight"
							placeholder="kg" name="pet_Weight" required>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 col-form-label" for="pet_History">기타사항</label>
					<div class="col-sm-8">
						<input class="form-control" type="text" id="pet_History"
							name="pet_History" >
					</div>
				</div>
				
					<button type="submit" class="register-btn">등록하기</button>
	
			</form>
		</div>
	</div>
	</main>
	<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>