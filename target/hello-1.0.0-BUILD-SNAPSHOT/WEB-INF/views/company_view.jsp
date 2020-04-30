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
			<div class="col-10 mx-auto main-block">
				<div class="left-div">
					<img id="company_Image" src="<c:url value='/resources/images/logo.png' />">

				</div>
				<div class="right-div">
						<h3 id="company_Name">${ thisCompany.company_Name }</h3>
						<form method="get" action="customer_review_list">
						<p class="company-detail">
							<label id="company_UserName">${ thisCompany.company_UserName }</label><br>
							<label id="company_Address">${thisCompany.company_Address}</label><br>
							<label id="company_Email">${thisCompany.company_Email}</label><br>
							<label id="company_UserPhoneNumber">${thisCompany.company_UserPhoneNumber}</label><br>
							<label id="company_Number">${thisCompany.company_Number}</label><br>
							<input type="hidden" name="company_Index"
										value="${thisCompany.company_Index}" />
							<input type="submit" class="btn btn-outline-primary"value="후기 ">
						</p>
						</form>
				</div>
			</div>
		</div>
	</main>
<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>