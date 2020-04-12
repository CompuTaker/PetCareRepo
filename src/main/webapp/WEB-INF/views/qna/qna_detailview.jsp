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
            <label id="qna_Title" name="qna_Title"> ${ list.qna_Title }</label>
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <label id="qna_Content" name="qna_Content">
              ${ list.qna_Content }</label
            >
          </td>
        </tr>
        <tr>
          <th>작성자</th>
          <td>
            <label id="qna_Writer" name="qna_Writer">
              ${ list.qna_Writer }</label
            >
          </td>
        </tr>
      </table>
    </div>

    <form id="qna_write" name="qna_write" action="" method="post">
      <div>
        <button type="button" class="btn btn-outline-success" onclick="">
          글 수정
        </button>
        <button
          type="button"
          class="btn btn-outline-secondary"
          onclick="location.href='qna_list'"
        >
          목록
        </button>
        <button type="button" class="btn btn-outline-danger" onclick="">
          답변
        </button>
      </div>
    </form>
  </div>
</div>
