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
	<div class="row mx-auto main-container">
		<div class="mx-auto main-block   col-12">
			<section>
				<h2 class="beauty-company-title">미용업체목록</h2>
				<div class="table-responsive">

				<table class="table table-hover">
					<thead>
						<tr>
							<th>업체명</th>
							<th>대표자명</th>
							<th>전화번호</th>
							<th>주소</th>
						</tr>
					</thead>
					<c:forEach items="${ companyList }" var="company">
						<tr
							onclick="location.href='company_view?companyIdx=${company.company_Index}'">
							<td>${company.company_Name }</td>
							<td>${company.company_UserName}</td>
							<td>${company.company_UserPhoneNumber}</td>
							<td>${company.company_Address}</td>
						</tr>
					</c:forEach>
				</table>
				</div>
			</section>
		</div>
	</div>
	</main>
	<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>