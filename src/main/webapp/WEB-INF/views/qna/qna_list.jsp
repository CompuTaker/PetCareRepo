<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="row mx-auto main-container">
		<div class="col-10 mx-auto main-block">
			<div class="logindiv col-md-6 form-group">
				<label class="login-title" for="login-title">Q&A</label>
			</div>
			<form id="qna_write" name="qna_write" action="qna_write"
				method="post">
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
					<c:forEach var="list" items="${list }" varStatus="status">
						<tr>
							<th scope="row">
							<th>
							<td><c:out value="${list.qna_Index}" /></td>
							<td><c:out value="${list.qna_Type }" /></td>
							<td><a href='#' onClick='fn_view(${list.qna_Title})'><c:out
										value="${list.qna_Title }" /></a></td>
							<td><c:out value="${list.qna_Writer }" /></td>
							<td><c:out value="${list.qna_Date }" /></td>
							<td><c:out value="${list.qna_Viewnum}" /></td>
							<td></td>
						</tr>

					</c:forEach>
					<tbody>
						<tr onclick="location.href='qna_detailview'">

							<th scope="row">1</th>
							<td>A</td>
							<td>궁금한게 있습니다.</td>
							<td>작성자</td>
							<td>2019-04-09</td>
							<td>10</td>

						</tr>

					</tbody>

				</table>
			</form>
			<script>
			function fn_view(index){
			    
			    var form = document.getElementById("boardForm");
			    var url = "<c:url value='/board/viewContent.do'/>";
			    url = url + "?code=" + code;
			    
			    form.action = url;    
			    form.submit(); 
			}
			</script>

			<div>

				<a href='qna_write' class="btn btn-success">글쓰기</a>
			</div>

		</div>
	</div>
