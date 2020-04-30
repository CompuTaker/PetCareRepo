<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

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
        <div>
          <a class="review-block-title" href="#">ㅇㅇ병원 </a>
          <span class="review-block-date">2개월전</span>
          <button class="btn">
            <i class="fas fa-trash"></i>
          </button>
          <div class="review-block-rate">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
          </div>
          <p class="review-block-description">
            좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요
            좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요
            좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요
            좋아요좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 좋아요 병원
            사진 첨부합니다~~~~
          </p>
          <div class="row">
            <div class="col-lg-3 col-md-6 m-b-20">
              <img
                src="resources/images/logo.png"
                alt="포토리뷰"
                class="img-responsive radius"
              />
              <img
                src="resources/images/logo.png"
                alt="포토리뷰"
                class="img-responsive radius"
              />
              <img
                src="resources/images/logo.png"
                alt="포토리뷰"
                class="img-responsive radius"
              />
            </div>
          </div>
        </div>
        <hr />
        <div>
          <a class="review-block-title" href="#">ㅁㅁ병원 </a>
          <span class="review-block-date">4개월전</span>
          <button class="btn">
            <i class="fas fa-trash"></i>
          </button>
          <div class="review-block-rate">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
          </div>
          <p class="review-block-description">
            별로예요 별로예요 별로예요 별로예요 별로예요 별로예요 별로예요
            별로예요 가지마세요
          </p>
          <div class="row"></div>
        </div>
        <hr />
        <div>
          <a class="review-block-title" href="#">ㅅㅅ병원 </a>
          <span class="review-block-date">4개월전</span>
          <button class="btn">
            <i class="fas fa-trash"></i>
          </button>
          <div class="review-block-rate">
            <span class="fa fa-star checked"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
            <span class="fa fa-star"></span>
          </div>
          <p class="review-block-description">
            별로예요 별로예요 별로예요 별로예요 별로예요 별로예요 별로예요
            별로예요
          </p>
          <div class="row"></div>
        </div>
      </div>
    </div>
  </div>
</div>
