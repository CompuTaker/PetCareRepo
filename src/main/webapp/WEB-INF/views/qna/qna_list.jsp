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
					<tbody>
						<c:forEach var="qnalist" items="${qnalist}" varStatus="status">
							<tr onClick="location.href='qnaDatailView?qna_Id=${qnalist.id }'">
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

				<a href='qnaWrite' class="btn btn-success">글쓰기</a>
			</div>

		</div>
	</div>
