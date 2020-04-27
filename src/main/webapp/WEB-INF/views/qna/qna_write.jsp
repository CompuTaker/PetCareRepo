<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <label class="login-title" for="login-title">Q&A 작성</label>

    <form id="qna_write" name="qna_write" action="qnaAdd" method="post">
      <div class="input-group mb-3">
        <div class="input-group-prepend"></div>
        문의 사항 종류: &nbsp;
        <select id="qna_type" name="qna_type">
          <option>A</option>
          <option>B</option>
          <option>C</option>
        </select>
      </div>
      	<div class="clearfix"></div>
				<div class="x_content">
      
      <div class="qna-table">
       <table class="table">
          <tr>
            <td>제목</td>
            <td><input id="title" name="title" class="form-control"  value=${qnaData.title }></td>
          </tr>
          <tr>
           <td>내용</td>
            <td>
              <textarea class="form-control col-sm-12" rows="8"
                id="content"
                
                name="content"
              >${qnaData.content }</textarea>
            </td>
          </tr>
        </table>
      </div>
		</div>
     <div class="qna_bottom">
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
