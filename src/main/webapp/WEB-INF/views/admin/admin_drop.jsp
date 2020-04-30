<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
			<li><a href="#"> <i class="fas fa-briefcase"></i> 업체랭킹관리
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
				<h3>탈퇴계정관리</h3>
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
									class="dropdown-item" href="admin_login">Log Out</a>
							</div></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="right_col" role="main">
			<!-- 개인회원목록  -->
			<div class="col-md-12 col-sm-12">
				<div class="x_panel">
					<div class="x_title">
						<h4>개인회원</h4>
					</div>

					<div class="x_content">
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th><input type="checkbox" id="check-all" class="flat"></th>
										<th class="column-title">이름</th>
										<th class="column-title">아이디</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">이름</th>
										<th class="column-title"></th>
									</tr>
								</thead>
								<tbody>
									<tr class="customer_list">
										<td class="a-center "><input type="checkbox" class="flat"
											name="table_records"></td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</a></td>
										<td class=" "><button class="btn">
												<i class="fas fa-trash"></i>
											</button></td>

									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<!-- 기업회원목록  -->
			<div class="col-md-12 col-sm-12">
				<div class="x_panel">
					<div class="x_title">
						<h4>기업회원</h4>
					</div>

					<div class="x_content">
						<div class="table-responsive">
							<table class="table table-striped jambo_table bulk_action">
								<thead>
									<tr class="headings">
										<th><input type="checkbox" id="check-all" class="flat">
										</th>
										<th class="column-title">이름</th>
										<th class="column-title">아이디</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">가입날짜</th>
										<th class="column-title">이름</th>
										<th class="column-title"></th>
									</tr>
								</thead>
								<tbody>
									<tr class="company_list">
										<td class="a-center"><input type="checkbox" class="flat"
											name="table_records"></td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" ">asdf</td>
										<td class=" "><button class="btn">
												<i class="fas fa-trash"></i>
											</button></td>
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