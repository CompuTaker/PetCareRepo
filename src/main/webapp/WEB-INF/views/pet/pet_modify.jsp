<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="mx-auto main-block   col-12">
		<h1 class="pet_modify_title">펫 정보 수정하기</h1>


		<form action="pet_modify" class="pet_modify" name="modify"
			method="post">
			<input type="hidden" name="customer_Index" value="${pet.customer_Index }"> 
			<input type="hidden" name="pet_Index" value="${pet.pet_Index}"> 
			
			<div class="form-group row">
				<label class="col-sm-2 " for="pet_Name">이름</label>
				<div class="col-sm-8">
					<input class="form-control" type="text" id="pet_Name"
						name="pet_Name" value="${pet.pet_Name }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="pet_Type">종</label>
				<div class="col-sm-8">
					<input class="form-control" type="text" id="pet_Type"
						name="pet_Type" value="${pet.pet_Type }">
				</div>
			</div>
			<div class="form-group row">
				<label class=" col-sm-2 col-form-label" for="pet_Gender">성별</label>
				<div class="gender-radio-group col-sm-8">
					<input type="radio" name="pet_Gender" value="암컷"
						<c:if test="${pet.pet_Gender eq '암컷'}">checked="checked"</c:if>>암컷
					<input type="radio" name="pet_Gender" value="수컷"
						<c:if test="${pet.pet_Gender eq '수컷'}">checked="checked"</c:if>>수컷
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="pet_Age">나이(개월수)</label>
				<div class="col-sm-8">
					<input class="form-control" type="number" id="pet_Age"
						name="pet_Age" value="${pet.pet_Age }">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="pet_IsNeutralized">중성화수술</label>
				<div class="gender-radio-group col-sm-8" id="pet_IsNeutralized">
					<input type="radio" name="pet_IsNeutralized" value="1"
						<c:if test="${pet.pet_IsNeutralized eq true}">checked="checked"</c:if>>
					<i class="far fa-circle"></i> <input type="radio"
						name="pet_IsNeutralized" value="0"
						<c:if test="${pet.pet_IsNeutralized eq false}">checked="checked"</c:if>>
					<i class="far fa-times-circle"></i>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="pet_Weight">무게</label>
				<div class="col-sm-8">
					<input type="number" class="form-control" id="pet_Weight"
						placeholder="kg" name="pet_Weight" value="${pet.pet_Weight}">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label" for="pet_History">기타사항</label>
				<div class="col-sm-8">
					<input class="form-control" type="text" id="pet_History"
						name="pet_History" value="${pet.pet_History }">
				</div>
			</div>

			<button type="submit" class="register-btn">수정</button>

		</form>
	</div>
</div>
