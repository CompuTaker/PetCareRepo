<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
   href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
   integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
   crossorigin="anonymous">
<link
   href="https://fonts.googleapis.com/css?family=Poor+Story&display=swap&subset=korean"
   rel="stylesheet">
<!-- css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<title>반려동물 예약</title>
<style type="text/css">
* {
   font-family: 'Poor Story', cursive;
}
</style>
</head>

<body>
      <jsp:include page="Header.jsp" flush="false" />

    <main class="container-fluid">
        <div class="row mx-auto main-container">
            <div class="col-10 mx-auto main-block">
                <h1><i class="fas fa-check"></i></h1>
                <h1>비밀번호 변경 완료</h1>
                <p>비밀번호가 변경되었습니다.</p>
                <hr>
                <a class="btn btn-lg btn-secondary mx-auto " href="${pageContext.request.contextPath}" role="button">확인</a>
            </div>
        </div>
    </main>
         <jsp:include page="Footer.jsp" flush="false" />


   <!-- Optional JavaScript -->
   <!-- jQuery first, then Popper.js, then Bootstrap JS -->
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"></script>
   <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"></script>
   <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"></script>
   <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</body>

</html>