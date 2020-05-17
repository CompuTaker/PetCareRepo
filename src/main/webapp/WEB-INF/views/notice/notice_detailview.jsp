<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <label class="login-title" for="login-title">공지사항 상세보기</label>

    <div class="notice-table">
      <table class="table">
        <tr>
          <th>제목</th>
          <td>
            <label id="notice_Title" name="notice_Title"> ${noticeDetail.notice_Title }</label>
          </td>
        </tr>
        <tr>
          <th>날짜</th>
          <td>
            <label id="notice_Date" name="notice_Date"> ${noticeDetail.notice_Date }</label>
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <label id="notice_Content" name="notice_Content">
              ${ noticeDetail.notice_Content }</label>
          </td>
        </tr>

      </table>
    </div>


    <button type="button" class="btn btn-outline-secondary" onclick="location.href='noticePage?page=${param.page }'">
      목록
    </button>


  </div>
</div>