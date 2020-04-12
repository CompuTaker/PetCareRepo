<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block" style="text-align: left;">
    <div class="container">
      <table class="login-table" height="90">
        <tr>
          <td><label class="login-title">아이디 찾기</label></td>
        </tr>
      </table>

      <div
        style="margin-bottom: 10px;"
        class="custom-control custom-radio custom-control-inline"
      >
        <input
          type="radio"
          class="custom-control-input"
          id="search_1"
          name="search_total"
          onclick="search_check(1)"
          checked="checked"
        />
        <label class="custom-control-label" for="search_1">기업회원</label>
      </div>

      <div class="custom-control custom-radio custom-control-inline">
        <input
          type="radio"
          class="custom-control-input"
          id="search_2"
          name="search_total"
          onclick="search_check(2)"
        />
        <label class="custom-control-label" for="search_2">일반회원</label>
      </div>

      <div id="searchI">
        <form action="company_searchId" method="post">
          <div class="form-group">
            <label for="Company_Number">사업자 번호</label>
            <input
              type="text"
              class="form-control"
              id="Company_Number"
              name="company_Number"
              placeholder="12345"
              required
            />
          </div>

          <div class="form-group">
            <button type="submit" class="btn btn-primary">확인</button>
          </div>
        </form>
      </div>

      <div id="searchP" style="display: none;" for="search_check(2)">
        <form action="search_id_customer" method="POST">
          <label for="Customer_Name">이름</label>
          <input
            type="text"
            class="form-control"
            id="customer_Name"
            placeholder="이름"
            name="customer_Name"
            required
          />
          <label for="customer_PhoneNumber">핸드폰 번호</label>
          <input
            type="text"
            class="form-control"
            id="customer_PhoneNumber"
            placeholder="01012345678"
            name="customer_PhoneNumber"
            required
          />
          <button type="submit" class="btn btn-primary">확인</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  function search_check(num) {
    if (num == "1") {
      document.getElementById("searchP").style.display = "none";
      document.getElementById("searchI").style.display = "";
    } else {
      document.getElementById("searchI").style.display = "none";
      document.getElementById("searchP").style.display = "";
    }
  }
</script>
