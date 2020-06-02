<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<label class="login-title" for="login-title">Q&A 댓글달기</label>

		<div class="qna-table">
			<table class="table table-striped table-hover">
				<tr>
					<th>제목</th>
					<td><label id="qna_Title" name="qna_Title">
							${qnaReply.title }</label></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><label id="qna_Content" name="qna_Content"> ${ qnaReply.content }</label>
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><label id="qna_Writer" name="qna_Writer"> ${ qnaReply.writer_name }</label>
					</td>
				</tr>
			</table>
		</div>

		<form id="qna_reply" name="qna_reply" action="qna_reply_ok?qnaId=${qnaReply.id}" method="post">
			<div class=" mb-3">

				<label for="title"> 댓글</label> <input type="text"
					class="form-control" name="qna_Comment" id="qna_Comment"
					value=${qnaReply.qna_Comment }>

			</div>
			<div>
        <button
          type="submit"
          class="btn btn-outline-success"
          onclick="location.href='qna_reply_ok'"
        >
          댓글 등록
        </button>
        </div>

		</form>
	</div>
</div>
