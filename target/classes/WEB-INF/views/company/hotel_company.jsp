<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">호텔업체목록</label>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">업체명</th>
					<th scope="col">대표자명</th>
					<th scope="col">전화번호</th>
					<th scope="col">주소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ companyList }" var="company">
					<tr onclick="location.href='company_view?companyIdx=${company.company_Index}'">
						<td>${company.company_Name }</td>
						<td>${company.company_UserName}</td>
						<td>${company.company_UserPhoneNumber}</td>
						<td>${company.company_Address}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pull-right">
			<ul class="btn-group pagination">
				<c:if test="${pageMaker.prev }">
					<li><a href='<c:url value="/hotelCompany?page=${pageMaker.startPage-1 }"/>'><i
								class="fa fa-chevron-left"></i></a>
					</li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					<li><a href='<c:url value="/hotelCompany?page=${pageNum }"/>'>${pageNum }</a>&nbsp;
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li><a href='<c:url value="/hotelCompany?page=${pageMaker.endPage+1 }"/>'><i
								class="fa fa-chevron-right"></i></a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
	</section>


</div>
</div>