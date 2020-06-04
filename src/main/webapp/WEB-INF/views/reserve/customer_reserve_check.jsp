<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
  function isReviewOrCancel(theButton, index) {
    var selectedIndex = document.getElementById("selectedIndex");

    var indexTag = document.getElementsByClassName("ff" + index)[0];
    var theForm = document.getElementById("cancelOrReview");
    var reservation_Index = indexTag.innerHTML;
    selectedIndex.value = reservation_Index;
    alert(selectedIndex.value + "~~~~~~~~~");

    if (theButton.id == "reviewButton") {
      theForm.action = "customer_review_add";
    } else if (theButton.id == "cancelButton") {
      theForm.action = "customer_reservation_cancel";
    }
    theForm.submit();
    // 세션에서 customerIdx 받아오기!
  }
</script>

<div class="row mx-auto main-container">
  <div class="col-10 mx-auto main-block">
    <div class="row ml-auto mr-auto mt-3" style="max-width: 300px;">
      <i class="fas fa-calendar" style="width: 50px; height: 50px;"></i>
      <h1 class="ml-3">나의 예약조회</h1>
      <c:set var = "str" value="${param.petName}"/>
      
      <select onchange="location.href='customer_reserve_check?petName='+this.value">
      	<option value="all">펫 이름 선택</option>
      	<option value="all">모두</option>
      	
      	<c:forEach items="${ pet }" var="pet">
      		<c:set var="selectedPet" value="${pet.pet_Name }"/>
      		<option value="${pet.pet_Name }" <c:if test="${str eq selectedPet}">selected</c:if>>${pet.pet_Name }</option>
        </c:forEach>
      </select>
      
      
    </div>

    <div class="row mt-5">
      <div class="col-3">
        <div
          class="nav flex-column nav-pills"
          id="v-pills-tab"
          role="tablist"
          aria-orientation="vertical"
        >
          <a
            class="nav-link active"
            id="v-pills-home-tab"
            data-toggle="pill"
            href="#v-pills-home"
            role="tab"
            aria-controls="v-pills-home"
            aria-selected="true"
            >예약</a
          >
          <a
            class="nav-link"
            id="v-pills-profile-tab"
            data-toggle="pill"
            href="#v-pills-profile"
            role="tab"
            aria-controls="v-pills-profile"
            aria-selected="false"
            >취소예약</a
          >
          <a
            class="nav-link"
            id="v-pills-messages-tab"
            data-toggle="pill"
            href="#v-pills-messages"
            role="tab"
            aria-controls="v-pills-messages"
            aria-selected="false"
            >완료 예약</a
          >
        </div>
      </div>
      <div class="col-9">
        <div class="tab-content" id="v-pills-tabContent">
          <!-- 예약중인 리스트 -->

          <div
            class="tab-pane fade show active"
            id="v-pills-home"
            role="tabpanel"
            aria-labelledby="v-pills-home-tab"
          >
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">반려 동물 이름</th>
                  <th scope="col">종류</th>
                  <th scope="col">업체 이름</th>
                  <th scope="col">예약 날짜</th>
                  <th scope="col">예약 시간</th>
                  <th scope="col">기타의견</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach
                  items="${reservation}"
                  var="reservation"
                  varStatus="counter"
                >
                  <c:if test="${reservation.reservation_Check eq 'reserved'}">
                    <tr>
                      <th scope="row">${ counter.index }</th>
                      <td>${ reservation.pet_Name }</td>
                      <td>${ reservation.company_Type }</td>
                      <td>${ reservation.company_Name }</td>
                      <td>${ reservation.reservation_Date }</td>
                      <td>${ reservation.reservation_Time }</td>
                      <td>${ reservation.reservation_DetailService }</td>
                      <td>
                        <button
                          class="btn"
                          onclick="location.href='customer_reservation_cancel?index='+${ reservation.reservation_Index }"
                        >
                          <i class="fas fa-trash"></i>
                        </button>
                      </td>
                    </tr>
                  </c:if>
                </c:forEach>
              </tbody>
            </table>
          </div>

          <!-- 취소된 예약 리스트 -->

          <div
            class="tab-pane fade"
            id="v-pills-profile"
            role="tabpanel"
            aria-labelledby="v-pills-profile-tab"
          >
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">반려 동물 이름</th>
                  <th scope="col">종류</th>
                  <th scope="col">업체 이름</th>
                  <th scope="col">예약 날짜</th>
                  <th scope="col">예약 시간</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach
                  items="${ reservation }"
                  var="reservation"
                  varStatus="counter"
                >
                  <c:if test="${reservation.reservation_Check eq 'canceled'}">
                    <tr>
                      <th scope="row">${ counter.index }</th>
                      <td>${ reservation.pet_Name }</td>
                      <td>${ reservation.company_Type }</td>
                      <td>${ reservation.company_Name }</td>
                      <td>${ reservation.reservation_Date }</td>
                      <td>${ reservation.reservation_Time }</td>
                      <td></td>
                    </tr>
                  </c:if>
                </c:forEach>
              </tbody>
            </table>
          </div>

          <!-- 완료된 예약 리스트 -->

          <div
            class="tab-pane fade"
            id="v-pills-messages"
            role="tabpanel"
            aria-labelledby="v-pills-messages-tab"
          >
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">반려 동물 이름</th>
                  <th scope="col">종류</th>
                  <th scope="col">업체 이름</th>
                  <th scope="col">예약 날짜</th>
                  <th scope="col">예약 시간</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach
                  items="${ reservation }"
                  var="reservation"
                  varStatus="counter"
                >
                  <c:choose>
                    <c:when test="${reservation.review_Check eq 0 }">
                      <c:if
                        test="${reservation.reservation_Check eq 'finished'}"
                      >
                        <tr>
                          <th scope="row">${ counter.index }</th>
                          <td>${ reservation.pet_Name }</td>
                          <td>${ reservation.company_Type }</td>
                          <td>${ reservation.company_Name }</td>
                          <td>${ reservation.reservation_Date }</td>
                          <td>${ reservation.reservation_Time }</td>

                          <td>
                            <button
                              class="btn"
                              onclick="location.href='customer_review_add?index='+${ reservation.reservation_Index }"
                            >
                              <i class="fas fa-edit"></i>
                            </button>
                          </td>
                        </tr>
                      </c:if>
                    </c:when>
                    <c:otherwise>
                      <c:if
                        test="${reservation.reservation_Check eq 'finished'}"
                      >
                        <tr>
                          <th scope="row">${ counter.index }</th>
                          <td>${ reservation.pet_Name }</td>
                          <td>${ reservation.company_Type }</td>
                          <td>${ reservation.company_Name }</td>
                          <td>${ reservation.reservation_Date }</td>
                          <td>${ reservation.reservation_Time }</td>

                          <td></td>
                        </tr>
                      </c:if>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
