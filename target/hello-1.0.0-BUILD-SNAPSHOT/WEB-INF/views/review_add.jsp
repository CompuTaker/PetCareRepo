<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- css -->
    <link rel="stylesheet" href="resources/css/style.css" type="text/css" />
    <style type="text/css">
        .rate {
            float: left;
            height: 46px;
            padding: 0 10px;
        }

        .rate:not(:checked)>input {
            position: absolute;
            top: -9999px;
        }

        .rate:not(:checked)>label {
            float: right;
            width: 1em;
            overflow: hidden;
            white-space: nowrap;
            cursor: pointer;
            font-size: 30px;
            color: #ccc;
        }

        .rate:not(:checked)>label:before {
            content: '★ ';
        }

        .rate>input:checked~label {
            color: #ffc700;
        }

        .rate:not(:checked)>label:hover,
        .rate:not(:checked)>label:hover~label {
            color: #deb217;
        }

        .rate>input:checked+label:hover,
        .rate>input:checked+label:hover~label,
        .rate>input:checked~label:hover,
        .rate>input:checked~label:hover~label,
        .rate>label:hover~input:checked~label {
            color: #c59b08;
        }
    </style>

    <title>반려동물 예약</title>
</head>

<body>
    <header>
        <nav class="menu navbar navbar-expand-lg navbar-light" id="top">
            <a class="navbar-brand" href="index.html">
                <img src="resources/images/logo.png" width="70" height="50" alt="logo">PET</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.html">홈 <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">소개</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="reserve.html">예약하기</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">제휴문의</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">공지사항</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">후기</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Q&A</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="login.html">Login</a>
                    </li>

                </ul>

                <form class="menu_form form-inline my-2 my-lg-0">
                    <input class="menu_form_input form-control mr-sm-2" type="search" placeholder="검색"
                        aria-label="Search">
                    <button class="menu_form_btn btn btn-outline-success my-2 my-sm-0" type="submit">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
            </div>
        </nav>
    </header>
    <main class="container-fluid">
        <div class="row m-3">
            <div class="col-12 main-block">
                <h1 class="font-weight-bold p-5">Review</h1>
                <hr>
                <!-- 후기 index/고객 index/제목/내용/평점/이미지/답글(company용) -->
                <form method="POST" action="review_ok" accept-charset="utf-8" name="reservation"
                    class="reserve-content text-center ">
                    <div class="form-group row my-5">
                        <label class="col-sm-2 col-form-label font-weight-bold" for="title">제목</label>
                        <input type="text" class=" form-control col-sm-10" id="title" name="review_Title" required>
                    </div>
                    <div class="form-group row my-5">
                        <label class="col-sm-2 col-form-label font-weight-bold" for="content">내용</label>
                        <textarea class="form-control col-sm-10" id="content" rows="3" name="review_Content"
                            required></textarea>

                    </div>

                    <div class="form-group row my-5">
                        <label class="col-sm-2 col-form-label font-weight-bold" for="rating">평점</label>
                        <div class="rate" id="rating">
                            <input type="radio" id="star5" name="review_Rating" value="5" checked="checked" />
                            <label for="star5" title="아주 좋아요">5 stars</label>
                            <input type="radio" id="star4" name="review_Rating" value="4" />
                            <label for="star4" title="맘에 들어요">4 stars</label>
                            <input type="radio" id="star3" name="review_Rating" value="3" />
                            <label for="star3" title="보통이에요">3 stars</label>
                            <input type="radio" id="star2" name="review_Rating" value="2" />
                            <label for="star2" title="그냥 그래요">2 stars</label>
                            <input type="radio" id="star1" name="review_Rating" value="1" />
                            <label for="star1" title="별로에요">1 star</label>
                        </div>
                    </div>
                    <div class="form-group row my-5">
                        <label class="col-sm-2 col-form-label font-weight-bold" for="image">사진</label>
                        <input id="image" type="file" accept="image/*" onchange="loadFile(event)" name="review_Image"
                            multiple />
                        <div id="images"></div>
                    </div>


                    <div class="row">
                        <button type="submit" class="btn btn-secondary btn-lg mx-auto" onclick="ratingValue()">확인</button>
                    </div>
                    <input type="hidden" value="${company_Index}" name="company_Index">
                </form>

            </div>
        </div>
    </main>
    <footer class="container-fluid">
        <div class="row footer-container">
            <div class="col-12">
                <p><a href="#top" class="footer_text ">Back to top</a></p>
                <p><a href="#" class="footer_text ">Language</a> <a href="#" class="footer_text">privacy</a>
                    <a href="#" class="footer_text">Terms</a></p>
                <p>© Copyright 2019, All Rights Reserved</p>
            </div>
            <div class="col-12 footer_sns">
                <a href="#"><i class="footer_sns_i fab fa-twitter"></i></a>
                <a href="#"><i class="footer_sns_i fab fa-instagram"></i></a>
                <a href="#"><i class="footer_sns_i fab fa-facebook-square"></i></a>
            </div>
        </div>
    </footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script type="text/javascript">
        var loadFile = function (event) {
            var x = document.createElement('img'),
                y = document.getElementById("images").appendChild(x);
            console.log('d');
            y.src = URL.createObjectURL(event.target.files[0]);
            y.width = '100';
            y.height = '100';
        };
    </script>
</body>

</html>