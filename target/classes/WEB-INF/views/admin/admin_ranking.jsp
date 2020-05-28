<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
function logout() {
    location.href = "logout";
  }
</script>

<div class="wrapper">

	<!-- Sidebar  -->
	<nav id="sidebar">
		<div class="sidebar-header">
			<h4>
				<a href="admin.html" class="site_title"><i class="fa fa-paw"></i>관리자페이지</a>
			</h4>
		</div>

		<!-- menu profile quick info -->
		<div class="profile clearfix">
			<div class="profile_pic">
				<img src="resources/images/logo.png" width="70" height="50"
					class="img-circle">
			</div>
			<div class="profile_info">
				<span>Welcome,</span>
				<h5>홍길동</h5>
			</div>
		</div>

		<ul class="list-unstyled components">
			<li><a href="#"> <i class="fas fa-briefcase"></i> 업체계정관리
			</a></li>
			<li><a href="#"> <i class="fas fa-briefcase"></i> 신고업체목록
			</a></li>
			<li><a href="admin_ranking"> <i class="fas fa-briefcase"></i>
					업체랭킹관리
			</a></li>
			<li><a href="#homeSubmenu" data-toggle="collapse"
				aria-expanded="false" class="dropdown-toggle"> <i
					class="fas fa-briefcase"></i> 고객계정관리
			</a>
				<ul class="collapse list-unstyled" id="homeSubmenu">
					<li><a href="admin_drop">탈퇴계정관리</a></li>
					<li><a href="admin_dormant">휴면계정관리</a></li>
				</ul></li>
		</ul>
	</nav>

	<!-- Page Content  -->
	<div id="content">
		<nav class="navbar navbar-expand-lg">
			<div class="container-fluid">
				<h3>업체 랭킹 관리</h3>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="nav navbar-nav ml-auto">
						<li class="nav-item dropdown"><a
							class="user-profile dropdown-toggle" href="#" id="navbarDropdown"
							data-toggle="dropdown"> <img
								src="resources/images/logo.png" width="70" height="50">홍길동
						</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Profile</a> <a
									class="dropdown-item" href="#">Settings</a> <a
									class="dropdown-item" onclick="logout();">Log Out</a>
							</div></li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- 병원 리스트 -->
		<div class="container-fluid">
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h4>
						병원 <i class="fa fa-hospital" aria-hidden="true"></i>
					</h4>
				</div>
				<div class="card-body">
					<div class="table">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%;">
										<thead>
											<tr role="row">
												<th>rank</th>
												<th>이름</th>
												<th>위치</th>
												<th>Office</th>
												<th>평점</th>
										</thead>

										<tbody>
											<tr role="row" class="odd">
												<td>1</td>
												<td>ㅇㅇ병원</td>
												<td>Accountant</td>
												<td>Tokyo</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i>


														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="even">
												<td>2</td>
												<td>ㅁㅁ병원</td>
												<td>Chief Executive Officer (CEO)</td>
												<td>London</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="far fa-star"></i>

														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="odd">
												<td>3</td>
												<td>Ashton Cox</td>
												<td>Junior Technical Author</td>
												<td>San Francisco</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="even">
												<td>4</td>
												<td>Bradley Greer</td>
												<td>Software Engineer</td>
												<td>London</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="far fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="odd">
												<td>5</td>
												<td>Brenden Wagner</td>
												<td>Software Engineer</td>
												<td>San Francisco</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!-- 호텔 리스트 -->
		<div class="container-fluid">
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h4>
						호텔 <i class="fas fa-hotel"></i>
					</h4>
				</div>
				<div class="card-body">
					<div class="table">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%;">
										<thead>
											<tr role="row">
												<th>rank</th>
												<th>이름</th>
												<th>위치</th>
												<th>Office</th>
												<th>평점</th>
										</thead>

										<tbody>
											<tr role="row" class="odd">
												<td>1</td>
												<td>ㅇㅇ호텔</td>
												<td>Accountant</td>
												<td>Tokyo</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="even">
												<td>2</td>
												<td>ㅁㅁ호텔</td>
												<td>Chief Executive Officer (CEO)</td>
												<td>London</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>

														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="odd">
												<td>3</td>
												<td>Ashton Cox</td>
												<td>Junior Technical Author</td>
												<td>San Francisco</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="far fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>

														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="even">
												<td>4</td>
												<td>Bradley Greer</td>
												<td>Software Engineer</td>
												<td>London</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star-half-alt"></i> <i class="far fa-star"></i>
															<i class="far fa-star"></i> <i class="far fa-star"></i>

														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="odd">
												<td>5</td>
												<td>Brenden Wagner</td>
												<td>Software Engineer</td>
												<td>San Francisco</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 미용실 리스트 -->
		<div class="container-fluid">
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h4>
						미용실 <i class="fas fa-cut"></i>
					</h4>

				</div>
				<div class="card-body">
					<div class="table">
						<div id="dataTable_wrapper"
							class="dataTables_wrapper dt-bootstrap4">

							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered dataTable" id="dataTable"
										width="100%" cellspacing="0" role="grid"
										aria-describedby="dataTable_info" style="width: 100%;">
										<thead>
											<tr role="row">
												<th>rank</th>
												<th>이름</th>
												<th>위치</th>
												<th>Office</th>
												<th>평점</th>
										</thead>

										<tbody>
											<tr role="row" class="odd">
												<td>1</td>
												<td>ㅇㅇ미용실</td>
												<td>Accountant</td>
												<td>Tokyo</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i>

														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="even">
												<td>2</td>
												<td>ㅁㅁ미용실</td>
												<td>Chief Executive Officer (CEO)</td>
												<td>London</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="far fa-star"></i>

														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="odd">
												<td>3</td>
												<td>Ashton Cox</td>
												<td>Junior Technical Author</td>
												<td>San Francisco</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="fa fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="even">
												<td>4</td>
												<td>Bradley Greer</td>
												<td>Software Engineer</td>
												<td>London</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="fa fa-star"></i> <i class="far fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
											<tr role="row" class="odd">
												<td>5</td>
												<td>Brenden Wagner</td>
												<td>Software Engineer</td>
												<td>San Francisco</td>
												<td>
													<div class="score-wrap">
														<span class="stars"> <i class="fa fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i> <i
															class="far fa-star"></i> <i class="far fa-star"></i>
														</span>
													</div>
												</td>

											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>
</div>
