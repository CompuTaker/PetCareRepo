<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block" style="text-align: left;">
    <table class="login-table" height="90">
      <tr>
        <td><label class="login-title">비밀번호 수정</label></td>
      </tr>
    </table>

    <form
      class="w-50 ml-auto mr-auto mt-5 mb-5"
      action="company_renew_ok.html"
      method="post"
    >
      <div class="form-group">
        <label for="Company_Password">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="Customer_Password"
          placeholder="비밀번호"
          value="${customer.customer_Password}"
          name="customer_Password"
        />
      </div>

      <button type="submit" class="btn btn-primary">수정하기</button>
    </form>
  </div>
</div>
