<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <label class="login-title" for="login-title">Q&A 작성</label>

    <form id="qna_modify" name="qna_modify" action="qna_content_update" method="post">
      <div class="input-group mb-3">
        <div class="input-group-prepend"></div>
        문의 사항 종류: &nbsp;
        <select id="qna_type" name="qna_type">
          <option>A</option>
          <option>B</option>
          <option>C</option>
        </select>
      </div>
      <div class="qna-table">
        <table class="table table-striped table-hover">
          <tr>
            <td align="center">제목</td>
            <td><input id="title" name="title" size="50" maxlength="100" /></td>
          </tr>
          <tr>
            <td align="center">내용</td>
            <td>
              <textarea
                id="content"
                style="width: 400px;"
                name="content"
              ></textarea>
            </td>
          </tr>
        </table>
      </div>

      <div>
        <button
          type="submit"
          class="btn btn-outline-success"
          onclick="check_blanck()"
        >
          글 등록
        </button>
        <button
          type="button"
          class="btn btn-outline-secondary"
          onclick="location.href='qnaPage'"
        >
          목록
        </button>
      </div>
    </form>
  </div>
</div>
<script>
  function check_blanck(title, content) {
    var title = document.getElementById("title");
    var content = document.getElementById("content");

    if (title.value == "" || title.value == null) {
      alert("제목을 입력해주세요");
    } else if (content.value == "" || content.value == null) {
      alert("내용을 입력해주세요");
    } else {
      location.href = "qna_list";
    }
    function checkSpace(str) {
      if (str.search(/\s/) != -1) {
        return true;
      } else {
        return false;
      }
    }
  }
</script>
