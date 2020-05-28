<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- 페이지 제목 -->
<div class="row myreview-titles">
  <div class="col-md-5 col-8 align-self-center">
    <h3>My Review</h3>
  </div>
</div>

<!-- 내가 쓴 리뷰 목록 -->
<div class="container-fluid">
  <div class="row">
    <div class="col-md-12 col-sm-12">
      <hr style="border: solid 1px #a0855b;" />
      <div class="review-block">
        <c:forEach items ="${myreview }" var="myreview">
        	<a id="review_Title" name="reveiw_Title" href="customer_review_view?review_Index=${myreview.review_Index }">${myreview.review_Title }</a>
        	
        	  <button class="btn" onclick="location.href='review_cancel?review_Index=${myreview.review_Index}'">
                <i class="fas fa-trash"></i>
              </button>
	         <br>
        	<span id="review_Rating">${myreview.review_Rating }</span>
        	<p id="review_Content" name="review_Content">${myreview.review_Content }</p>
        	<p id="review_Image">${myreview.review_Image }</p>
        	<hr />
        </c:forEach>
       </div>
      </div>
     </div>
    </div> 
        
