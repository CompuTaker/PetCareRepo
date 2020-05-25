<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="mx-auto main-block   col-12">
		<section>
			<h2 class="reserve-check-title">예약조회</h2>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>예약번호</th>
						<th>서비스 종류</th>
						<th>이름</th>
						<th>나이</th>
						<th>중성화수술여부</th>
						<th>성별</th>
						<th>무게</th>
						<th>종</th>
						<th>예약날짜</th>
						<th>시간</th>
					</tr>
				</thead>
				<c:forEach items="${ reservation }" var="reservation">
					<tr>
						<td>${ reservation.reservation_Index }</td>
						<td>${ reservation.company_Type }</td>
						<td>${ reservation.pet_Name }</td>
						<td>${ reservation.pet_Age }</td>
						<td>${ reservation.pet_IsNeutralized }</td>
						<td>${ reservation.pet_Gender }</td>
						<td>${ reservation.pet_Weight }</td>
						<td>${ reservation.pet_Type }</td>
						<td>${ reservation.reservation_Date }</td>
						<td>${ reservation.reservation_Time }</td>
					</tr>
				</c:forEach>
			</table>

		</section>


	</div>
</div>
