<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">업체찾기</label>
		</div>
		<div class="row my-3 justify-content-end">
			<form class="menu_form form-inline m-2  my-lg-0" action="searchCompany" method="get">
				<input class="menu_form_input form-control mr-sm-2" type="search" placeholder="업체명검색"
					aria-label="Search" name="term" />
				<button class="menu_form_btn btn btn-outline-success my-2 my-sm-0" type="submit">
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>

		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">업체명</th>
					<th scope="col">업종</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ companyList }" var="company">
					<tr onclick="location.href='company_view?companyIdx=${company.company_Index}'">
						<td>${company.company_Index }</td>
						<td>${company.company_Name}</td>
						<td>${company.company_Type}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="row my-3 justify-content-center">
			<nav aria-label="Page navigation example">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
			</nav>
		</div>
	</div>
</div>