<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

</head>

<body>
	<jsp:include page="./WEB-INF/views/Header.jsp" flush="false" />
	<main class="container-fluid">
	<div class="row">
		<div class="col-12 bd-example p-0">
			<div id="carouselExampleCaptions" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleCaptions" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
					<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<svg class="bd-placeholder-img" width="100%" height="300px"
							xmlns="http://www.w3.org/2000/svg"
							preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
                                <rect width="100%" height="100%"
								fill="#777"></rect>
                            </svg>
						<div class="carousel-caption d-none d-md-block">
							<h5 class="display-3">반갑습니다!</h5>
							<p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
						</div>
					</div>
					<div class="carousel-item">
						<svg class="bd-placeholder-img" width="100%" height="300px"
							xmlns="http://www.w3.org/2000/svg"
							preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
                                <rect width="100%" height="100%"
								fill="#777"></rect>
                            </svg>
						<div class="carousel-caption d-none d-md-block">
							<h5 class="display-3">신규고객 10% 할인</h5>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
						</div>
					</div>
					<div class="carousel-item">
						<svg class="bd-placeholder-img" width="100%" height="300px"
							xmlns="http://www.w3.org/2000/svg"
							preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
                                <rect width="100%" height="100%"
								fill="#777"></rect>
                            </svg>
						<div class="carousel-caption d-none d-md-block">
							<h5 class="display-3">적립금 제도</h5>
							<p>Praesent commodo cursus magna, vel scelerisque nisl
								consectetur.</p>
						</div>
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleCaptions"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>

	</div>
	<div class="row p-5">
		<div class="col-lg-4 text-center">
			<svg class="bd-placeholder-img rounded-circle" width="140"
				height="140" xmlns="http://www.w3.org/2000/svg"
				preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
				aria-label="Placeholder: 140x140">
                    <title>미용</title>
                    <rect width="100%" height="100%" fill="#777"></rect>
					<text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
                </svg>
			<h2>미용</h2>
			<p>Donec sed odio dui. Etiam porta sem malesuada magna mollis
				euismod. Nullam id dolor id nibh ultricies vehicula ut id elit.
				Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
				Praesent commodo cursus magna.</p>
			<p>
				<a class="btn btn-secondary" href="beautyCompany" role="button">업체찾기
					»</a>
			</p>
		</div>
		<!-- /.col-lg-4 -->
		<div class="col-lg-4 text-center">
			<svg class="bd-placeholder-img rounded-circle" width="140"
				height="140" xmlns="http://www.w3.org/2000/svg"
				preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
				aria-label="Placeholder: 140x140">
                    <title>호텔</title>
                    <rect width="100%" height="100%" fill="#777"></rect>
					<text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
                </svg>
			<h2>호텔</h2>
			<p>Duis mollis, est non commodo luctus, nisi erat porttitor
				ligula, eget lacinia odio sem nec elit. Cras mattis consectetur
				purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo,
				tortor mauris condimentum nibh.</p>
			<p>
				<a class="btn btn-secondary" href="hotelCompany" role="button">업체찾기
					»</a>
			</p>
		</div>
		<!-- /.col-lg-4 -->
		<div class="col-lg-4 text-center">
			<svg class="bd-placeholder-img rounded-circle" width="140"
				height="140" xmlns="http://www.w3.org/2000/svg"
				preserveAspectRatio="xMidYMid slice" focusable="false" role="img"
				aria-label="Placeholder: 140x140">
                    <title>병원</title>
                    <rect width="100%" height="100%" fill="#777"></rect>
					<text x="50%" y="50%" fill="#777" dy=".3em">140x140</text>
                </svg>
			<h2>병원</h2>
			<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
				egestas eget quam. Vestibulum id ligula porta felis euismod semper.
				Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
				nibh, ut fermentum massa justo sit amet risus.</p>
			<p>
				<a class="btn btn-secondary" href="hospitalCompany" role="button">업체찾기
					»</a>
			</p>
		</div>
		<!-- /.col-lg-4 -->
	</div>
	</main>
	<jsp:include page="./WEB-INF/views/Footer.jsp" flush="false" />
</body>

</html>