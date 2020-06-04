<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">Q&A</label>
		</div>
		<div class="row my-3 justify-content-end">
			<form class="menu_form form-inline m-2  my-lg-0" action="searchQnA"
				method="get">
				<input class="menu_form_input form-control mr-sm-2" type="search"
					placeholder="제목,작성자 검색" aria-label="Search" name="term" /> <input
					type="hidden" name="page" value="1" />
				<button class="menu_form_btn btn btn-outline-success my-2 my-sm-0"
					type="submit">
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>

		<form id="qna_write" name="qna_write" action="qna_write" method="post">

			<div style="float: right;">

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
						<tr
							onClick="location.href='qnaDatailView?qna_Id=${qnalist.id }&page=${pageMaker.cri.page }&term=${param.term }'">
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
			<div>
				<ul class="pagination justify-content-center">
					<c:if test="${pageMaker.prev }">
						<li class="page-item"><a class="page-link"
							href='<c:url value="/searchQnA?page=${pageMaker.startPage-1 }&term=${param.term }"/>'>&laquo;</a>
						</li>
					</c:if>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="pageNum">
						<li class="page-item ${pageMaker.cri.page == pageNum ? "active":"" }"><a
							class="page-link"
							href='<c:url value="/searchQnA?page=${pageNum }&term=${param.term }"/>'>${pageNum }</a>&nbsp;
						</li>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
						<li class="page-item"><a class="page-link"
							href='<c:url value="/searchQnA?page=${pageMaker.endPage+1 }&term=${param.term }"/>'>&raquo;</a>
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