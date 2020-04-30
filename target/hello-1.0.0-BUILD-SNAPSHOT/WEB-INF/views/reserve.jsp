<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">

<head>

</head>

<body>
	<jsp:include page="Header.jsp" flush="false" />
	<main class="container-fluid">
	<div class="row m-3">
		<div class="col-12 main-block">
			<h1 class="reserve-title font-weight-bold p-5">
				<i class="far fa-calendar-check"></i> 예약
			</h1>
			<hr>
			<!-- 업체선택, 세분화,예약 날짜 ,시간 -->
			<form method="POST" action="reserve_ok" accept-charset="utf-8"
				name="reservation" class="reserve-content text-center ">
				<div>
					<div class=" form-group row">
						<label class="col-sm-2 pt-0 col-form-label font-weight-bold"
							for="Reservation_Type">업체찾기</label>
						<div class="col-sm-3 text-left" id="Reservation_Type">
							<input type="radio" name="Reservation_Type" value="beauty"
								checked="checked">미용 <input type="radio"
								name="Reservation_Type" value="hospital">병원 <input
								type="radio" name="Reservation_Type" value="hotel">호텔
						</div>
					</div>

					<div class="row my-5">
						<label class="col-sm-2 pt-0 col-form-label"></label>
						<div class="col-sm-10" id="beauty">
							<table class="table table-hover">
								<thead>
									<tr>
										<th></th>
										<th>번호</th>
										<th>기업명</th>
										<th>위치</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${companyList}" var="company">
										<c:if test="${company.company_Type eq '미용실'}">
											<tr class="record">
												<td><input type="radio" name="company_Index"
													value='${company.company_Index}'></td>
												<td align="center">${company.company_Index}</td>
												<td align="center">${company.company_Name}</td>
												<td align="center">${company.company_Address}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="col-sm-10" id="hospital">
							<table class="table table-hover ">
								<thead>
									<tr>
										<th></th>
										<th>번호</th>
										<th>기업명</th>
										<th>위치</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${companyList}" var="company">
										<c:if test="${company.company_Type eq '병원'}">
											<tr class="record">
												<td><input type="radio" name="company_Index"
													value='${company.company_Index}'></td>
												<td align="center">${company.company_Index}</td>
												<td align="center">${company.company_Name}</td>
												<td align="center">${company.company_Address}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="col-sm-10" id="hotel">
							<table class="table table-hover">
								<thead>
									<tr>
										<th></th>
										<th>번호</th>
										<th>기업명</th>
										<th>위치</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${companyList}" var="company">
										<c:if test="${company.company_Type eq '호텔'}">
											<tr class="record">
												<td><input type="radio" name="company_Index"
													value='${company.company_Index}'></td>
												<td align="center">${company.company_Index}</td>
												<td align="center">${company.company_Name}</td>
												<td align="center">${company.company_Address}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="form-group row my-5">
					<label class="col-sm-2 col-form-label font-weight-bold"
						for="petindex">펫선택</label> <select class="form-control col-sm-10"
						id="petindex" name="pet_Index" required>
						<c:forEach items="${petList}" var="pet">
							<option value="${pet.pet_Index}">${pet.pet_Name}</option>
						</c:forEach>
					</select>

				</div>
				<div class="form-group row my-5">
					<label class="col-sm-2 col-form-label font-weight-bold"
						for="DetailService">기타의견</label> <input type="text"
						class=" form-control col-sm-10" id="DetailService"
						placeholder="자세한 사항을 기입해주세요." name="reservation_DetailService"
						required>

				</div>
				<div class="form-group row my-5 font-weight-bold">
					<label class="col-sm-2 col-form-label" for="Reservation_PType">예약날짜</label>
					<input type="date" class=" form-control col-sm-4"
						id="Reservation_PType" placeholder="" name="reservation_Date"
						required> <label
						class="col-sm-2 col-form-label font-weight-bold"
						for="Reservation_PType">예약시간</label> <input type="time"
						class=" form-control col-sm-4" id="Reservation_PType"
						placeholder="" name="reservation_Time" required>
				</div>
				<div class="form-group row mt-5">
					<div class="form-check mx-auto">
						<input class="form-check-input" type="checkbox" value=""
							id="invalidCheck2" required> <label
							class="form-check-label" for="invalidCheck2"> Agree to
							terms and conditions </label>
					</div>
				</div>
				<div class="row">
					<button type="submit" class="btn btn-secondary btn-lg mx-auto">예약하기</button>
				</div>
			</form>
		</div>
	</div>
	</main>
	<jsp:include page="Footer.jsp" flush="false" />
	<script src="resources/script/reserve.js"></script>
</body>

</html>