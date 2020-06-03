<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">Q&A</label>
		</div>
		<form id="qna_write" name="qna_write" action="qna_write" method="post">
		<div style = "float : right;">

			<a href='qnaWrite' class="btn btn-success">글쓰기</a>
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
						<tr onClick="location.href='qnaDatailView?qna_Id=${qnalist.id }&page=${pageMaker.cri.page }'">
							<td>${qnalist.id}</td>
							<td>${qnalist.qna_type}</td>
							<td>${qnalist.title}</td>
							<td>${qnalist.writer_name}</td>
							<td>${qnalist.date}</td>
							<td>${qnalist.viewnum}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link"
							href='<c:url value="/qnaPage?page=${pageMaker.startPage-1 }"/>'>&laquo;</a>
					</li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="pageNum">
					<li class="page-item ${pageMaker.cri.page == pageNum ? " active":"" }"><a class="page-link"
							href='<c:url value="/qnaPage?page=${pageNum }"/>'>${pageNum }</a>&nbsp;
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link"
							href='<c:url value="/qnaPage?page=${pageMaker.endPage+1 }"/>'>&raquo;</a>
					</li>
				</c:if>
			</ul>
		</div>
		</form>
		<script>
			function fn_view(index) {

				var form = document.getElementById("boardForm");
				var url = "<c:url value='/board/viewContent.do'/>";
				url = url + "?code=" + code;

				form.action = url;
				form.submit();
			}
		</script>

		

	</div>
</div>