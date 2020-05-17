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
			<label class="login-title" for="login-title">업체</label>
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

		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">후기</label>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ reviewList }" var="review">
					<tr onclick="location.href='customer_review_view?reviewIdx=${review.review_Index}'">
						<td>${review.review_Index }</td>
						<td>${review.review_Title}</td>
						<td>${review.customer_id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">Q&A</label>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">문의종류</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">날짜</th>
					<th scope="col">조회수</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="qnalist" items="${qnalist}" varStatus="status">
					<tr onClick="location.href='qnaDatailView?qna_Id=${qnalist.id }'">
						<td>${qnalist.id}</td>
						<td>${qnalist.qna_type}</td>
						<td>${qnalist.title}</td>
						<td>${qnalist.writer}</td>
						<td>${qnalist.date}</td>
						<td>${qnalist.viewnum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


</div>