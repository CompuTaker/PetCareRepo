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
			<label class="login-title" for="login-title">후기</label>
		</div>
		<div class="row my-3 justify-content-end">
			<form class="menu_form form-inline m-2  my-lg-0" action="searchReview" method="get">
				<input class="menu_form_input form-control mr-sm-2" type="search" placeholder="검색" aria-label="Search"
					name="term" />
				<input type="hidden" name="page" value="1" />
				<button class="menu_form_btn btn btn-outline-success my-2 my-sm-0" type="submit">
					<i class="fas fa-search"></i>
				</button>
			</form>
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
				<c:forEach items="${ reviewList }" var="review" varStatus="status">
					<tr
						onclick="location.href='customer_review_view?review_Index=${review.review_Index}&term=${param.term }&page=${pageMaker.cri.page }'">
						<td>${review.review_Index}</td>
						<td>${review.review_Title}</td>
						<td>${customerName[status.index] }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item">
					<c:choose>
						<c:when test="${parma.term eq null}">
							<a class="page-link" href='<c:url value="/searchReview?page=${pageMaker.startPage-1 }"/>'>&laquo;</a>
						</c:when>
						<c:otherwise>
							<a class="page-link" href='<c:url value="/searchReview?page=${pageMaker.startPage-1 }"/>'>&laquo;</a>
						</c:otherwise>
					</c:choose>
					
					</li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					<li class="page-item ${pageMaker.cri.page == pageNum ? " active":"" }">
					<c:choose>
						<c:when test="${param.term eq null }">
							<a class="page-link" href='<c:url value="/searchReview?page=${pageNum }"/>'>${pageNum}</a>&nbsp;
						</c:when>
						<c:otherwise>
							<a class="page-link" href='<c:url value="/searchReview?term=${param.term }&page=${pageNum }"/>'>${pageNum}</a>&nbsp;
						</c:otherwise>
					</c:choose>
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item">
					<c:choose>
						<c:when test="${param.term eq null }">
							<a class="page-link" href='<c:url value="/searchReview?page=${pageMaker.endPage+1 }"/>'>&raquo;</a>
						</c:when>
						<c:otherwise>
							<a class="page-link"href='<c:url value="/searchReview?term=${param.term }&page=${pageMaker.endPage+1 }"/>'>&raquo;</a>
						</c:otherwise>
					</c:choose>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</div>