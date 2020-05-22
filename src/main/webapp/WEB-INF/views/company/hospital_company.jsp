<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<div class="row mx-auto main-container">
	<div class="col-10 mx-auto main-block">
		<div class="logindiv col-md-6 form-group">
			<label class="login-title" for="login-title">병원업체목록</label>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>업체명</th>
					<th>대표자명</th>
					<th>전화번호</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ companyList }" var="company">
					<tr
						onclick="location.href='company_view?companyIdx=${company.company_Index}'">
						<td>${company.company_Name }</td>
						<td>${company.company_UserName}</td>
						<td>${company.company_UserPhoneNumber}</td>
						<td>${company.company_Address}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev }">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/hospitalCompany?page=${pageMaker.startPage-1 }"/>'>&laquo;</a></li>
				</c:if>
				<c:forEach begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }" var="pageNum">
					<li class="page-item ${pageMaker.cri.page == pageNum ? "active":"" }"><a class="page-link" href='<c:url value="/hospitalCompany?page=${pageNum }"/>'>${pageNum }</a>&nbsp;
					</li>
				</c:forEach>
				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li class="page-item"><a class="page-link"
						href='<c:url value="/hospitalCompany?page=${pageMaker.endPage+1 }"/>'>&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<div id="map" style="width: 500px; height: 400px;"></div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7f76972a1b8696a07a8087141aa07823"></script>
	<script>
		var container = document.getElementById("map");
		var options = {
			center : new kakao.maps.LatLng(33.450701, 126.570667),
			level : 3,
		};

		var map = new kakao.maps.Map(container, options);

		//html5 에서 제공되는 geolocation 을 통해 현재위치 좌표를 가져온다.
		if (navigator.geolocation) {
			navigator.geolocation
					.getCurrentPosition(
							function(position) {
								var lat = position.coords.latitude, // 위도
								lon = position.coords.longitude; // 경도
								var locPosition = new kakao.maps.LatLng(lat,
										lon), message = '<div style="padding:5px;">현재 위치</div>';
								displayMarker(locPosition, message);
								console.log(lat, lon);
							}, function(error) {
								console.log("error", error);
							});
		}

		function displayMarker(locPosition, message) {
			var marker = new kakao.maps.Marker({
				map : map,
				position : locPosition,
			});

			var iwContent = message, iwRemoveable = true;

			var infowindow = new kakao.maps.InfoWindow({
				content : iwContent,
				removable : iwRemoveable,
			});

			infowindow.open(map, marker);

			map.setCenter(locPosition);
		}
	</script>
	</section>
</div>
</div>
