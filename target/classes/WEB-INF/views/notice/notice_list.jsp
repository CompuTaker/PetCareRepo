<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">공지사항</label>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">날짜</th>
					<th scope="col">조회수</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="noticelist" items="${noticelist}" varStatus="status">

					<tr
						onClick="location.href='noticeDetailView?notice_Index=${noticelist.notice_Index }&page=${pageMaker.cri.page }'">
						<td>${noticelist.notice_Index}</td>
						<td>${noticelist.notice_Title}</td>
						<td>${noticelist.notice_Date}</td>
						<td>${noticelist.notice_Viewnum }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link"
							href='<c:url value="/noticePage?page=${pageMaker.startPage-1 }"/>'>&laquo;</a>
					</li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					<li class="page-item ${pageMaker.cri.page == pageNum ? " active":"" }"><a class="page-link"
							href='<c:url value="/noticePage?page=${pageNum }"/>'>${pageNum }</a>&nbsp;
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link"
							href='<c:url value="/noticePage?page=${pageMaker.endPage+1 }"/>'>&raquo;</a>
					</li>
				</c:if>
			</ul>
		</div>


	</div>
</div>