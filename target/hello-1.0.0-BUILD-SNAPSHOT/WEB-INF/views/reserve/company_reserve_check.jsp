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
						<th>예약상태</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${ reservation }" var="reservation">

					<form method="get" action="company_reservation_cancel">
						<tr>
							<td><input type="text"
								class="border-0 bg-white font-weight-bold text-center"
								name="index" placeholder=${ reservation.reservation_Index }
								value=${ reservation.reservation_Index } readonly="readonly" /></td>
							<td>${ reservation.company_Type }</td>
							<td>${ reservation.pet_Name }</td>
							<td>${ reservation.pet_Age }</td>
							<td><c:choose>
									<c:when test="${reservation.pet_IsNeutralized eq 'false'}">x</c:when>
									<c:when test="${reservation.pet_IsNeutralized eq 'true'}">o</c:when>
								</c:choose></td>
							<td>${ reservation.pet_Gender }</td>
							<td>${ reservation.pet_Weight }</td>
							<td>${ reservation.pet_Type }</td>
							<td>${ reservation.reservation_Date }</td>
							<td>${ reservation.reservation_Time }</td>
							<td>${ reservation.reservation_Check }</td>
							<td><c:if
									test="${reservation.reservation_Check eq 'reserved'}">
									<input type="submit" class="btn btn-outline-danger" value="취소">
								</c:if></td>
						</tr>
					</form>
				</c:forEach>
			</table>

		</section>


	</div>
</div>
