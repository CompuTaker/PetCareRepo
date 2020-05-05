<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
							<tr onClick="location.href='noticeDetailView?notice_Index=${noticelist.notice_Index }'">
								<td>${noticelist.notice_Index}</td>
								<td>${noticelist.notice_Title}</td>
								<td>${noticelist.notice_Date}</td>
								<td>${noticelist.notice_Viewnum }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		
			<script>
			function fn_view(index){
			    
			    var form = document.getElementById("boardForm");
			    var url = "<c:url value='/board/viewContent.do'/>";
			    url = url + "?code=" + code;
			    
			    form.action = url;    
			    form.submit(); 
			}
			</script>
		</div>
	</div>
