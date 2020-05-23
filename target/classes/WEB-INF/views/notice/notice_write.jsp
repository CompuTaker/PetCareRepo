<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <label class="login-title" for="login-title">공지사항 작성</label>

    <form id="notice_write" name="notice_write" action="notice_add" method="post">
      <div class="notice-table">
        <table class="table ">
          <tr>
            <td align="center">제목</td>
            <td><input id="notice_Title" name="notice_Title" size="50" maxlength="100" ></td>
          </tr>
          <tr>
            <td align="center">내용</td>
            <td>
              <textarea
                id="notice_Content"
                style="width: 400px;"
                name="notice_Content"
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
          onclick="location.href='noticePage'"
        >
          목록
        </button>
      </div>
    </form>
  </div>
</div>
<script>
  function check_blanck(title, content) {
    var title = document.getElementById("notice_Title");
    var content = document.getElementById("notice_Content");

    if (title.value == "" || title.value == null) {
      alert("제목을 입력해주세요");
    } else if (content.value == "" || content.value == null) {
      alert("내용을 입력해주세요");
    } else {
      location.href = "noticePage";
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
