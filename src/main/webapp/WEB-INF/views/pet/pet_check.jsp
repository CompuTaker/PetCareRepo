<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>


<div class="row mx-auto main-container">
	<div class="mx-auto main-block   col-12">
		<section>
			<h2 class="pet-check-title">펫 등록 조회</h2>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>펫 번호</th>
						<th>펫 이름</th>
						<th>펫 종</th>
						<th>나이</th>
						<th>성별</th>
						<th>중성화수술여부</th>
						<th>무게</th>
						<th>펫 히스토리</th>
					</tr>
				</thead>
				<c:forEach items="${ pet }" var="pet">
					<tr>
						<td>${ pet.pet_Index }</td>
						<td>${ pet.pet_Name }</td>
						<td>${ pet.pet_Type }</td>
						<td>${ pet.pet_Age }</td>
						<td>${ pet.pet_Gender }</td>
						<td>${ pet.pet_IsNeutralized }</td>
						<td>${ pet.pet_Weight }</td>
						<td>${ pet.pet_History }</td>
					</tr>
				</c:forEach>
			</table>

		</section>


	</div>
</div>
