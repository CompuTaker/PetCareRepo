<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

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
      <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />" type="text/css"/>
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
            <img src="<c:url value='/resources/images/logo.png' />" width="70" height="50" alt="logo">PET</a>
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
        <div class="row m-5">
            <div class="col-12 main-block">
                <h1 class="font-weight-bold p-5">Review</h1>
                <hr>
               <div class="review-left-div">
                    <label class="font-weight-bold" for="review_Title">제목 : </label>
                    <!--  <label id="review_Title" name="review_Title" > 안녕하세요-->
               </div>
               <div class="review-right-div">
                    <label class="font-weight-bold" for="customer_Name">작성자 : </label>
                    <!--<label id="customer_Name" name="customer_Name" > 누구-->
               </div>
               <div class="review-div">
                    <label class="font-weight-bold" for="review_Content">내용 : </label>
               </div>
               <div class="review-div-image">
                    <img src="./images/logo.png" id="review_Image" name="review_Image">
               </div>
               <div class="review-div">
                    <p id="review_Content" name="review_Content">
                            Donec sed odio dui. 
                            Etiam porta sem malesuada magna mollis euismod. 
                            Nullam id dolor id nibh ultricies vehicula ut id elit. 
                            Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
                             Praesent commodo cursus magna.
                   </p>
                </div>
                <div class="review-div">
                    <label class="font-weight-bold" for="review_Rating">별점 : </label>
                    <!--<label id="review_Rating" name="review_Rating">5점</label>-->
                    <hr>
                </div>
               
                <form method="POST" action="" accept-charset="utf-8" name="review-comment" class="review-comment ">
                    <div class="review-div">
                        <label class="font-weight-bold" for="review_Comment">답글</label>
                            <br> 
                        <textarea  id="review_Comment"  style="width:90%;" rows="3" name="review_Comment"></textarea>
                    <button type="submit"  >등록</button>
                        </div>
                </form>

                
                <div class="row">
                        <button onclick="location.href='review_list.html'" class="btn btn-secondary btn-lg mx-auto">목록</button>
                    </div>
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
