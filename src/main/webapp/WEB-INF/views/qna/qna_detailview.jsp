<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <label class="login-title" for="login-title">Q&A 상세보기</label>

    <div class="qna-table">
      <table class="table table-striped table-hover">
        <tr>
          <th>제목</th>
          <td>
            <label id="qna_Title" name="qna_Title"> ${qnaDetail.title }</label>
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <label id="qna_Content" name="qna_Content">
              ${ qnaDetail.content }</label>
          </td>
        </tr>
        <tr>
          <th>작성자</th>
          <td>
            <label id="qna_Writer" name="qna_Writer">
              ${ qnaDetail.customer_Name}</label>
          </td>
        </tr>
        <tr>
          <th>댓글</th>
          <td>
            <label id="qna_reply" name="qna_reply">
              ${ qnaDetail.qna_Comment }</label>
          </td>
        </tr>
      </table>
    </div>


    <form id="qna_write" name="qna_write" action="" method="post">
      <div>
        <button type="button" class="btn btn-outline-success" onclick="location.href='qnaModify_view?qna_Id=${qna_Id}'">
          글 수정
        </button>
        <button type="button" class="btn btn-outline-secondary"
          onclick="location.href='searchQnA?page=${param.page }&term=${param.term }'">
          목록
        </button>

        <c:choose>
          <c:when test="${!empty superuser.superuser_Index}">
            <button type="button" class="btn btn-outline-success"
              onclick="location.href='qna_reply?qna_Id=${qna_Id}&writer_name=${qnaDetail.writer_name}'">
              답글 달기
            </button>
          </c:when>
        </c:choose>
      </div>
    </form>
  </div>
</div>