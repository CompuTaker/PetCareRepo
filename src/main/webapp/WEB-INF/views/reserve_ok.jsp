<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">

<head>
	<!-- jQuery -->
	<!--
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	  -->
	<!-- iamport.payment.js -->
	<!--
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	 -->
	 
	 <script src="https://cdn.bootpay.co.kr/js/bootpay-3.2.2.min.js" type="application/javascript"></script>
</head>

<body>
	<jsp:include page="Header.jsp" flush="false" />
	<main class="container-fluid">
	<div class="row mx-auto main-container">
		<div class="col-10 mx-auto main-block">
			<h1>
				<i class="fas fa-check"></i>
			</h1>
			<h1>예약완료</h1>
			<p>예약이 완료되었습니다.</p>
			<hr>
			<a class="btn btn-lg btn-secondary mx-auto" role="button" id = "check_module">확인</a>
		</div>
	</div>
	<script>
    	BootPay.request({
    		price: '1000', //실제 결제되는 가격
    		application_id: "5e8b08c102f57e002fd63ed1",
    		name: '블링블링 마스카라', //결제창에서 보여질 이름
    		pg: 'kakao',
    		method: 'easy', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
    		show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
    		items: [
    			{
    				item_name: '나는 아이템', //상품명
    				qty: 1, //수량
    				unique: '123', //해당 상품을 구분짓는 primary key
    				price: 1000, //상품 단가
    				cat1: 'TOP', // 대표 상품의 카테고리 상, 50글자 이내
    				cat2: '티셔츠', // 대표 상품의 카테고리 중, 50글자 이내
    				cat3: '라운드 티', // 대표상품의 카테고리 하, 50글자 이내
    			}
    		],
    		user_info: {
    			username: '사용자 이름',
    			email: '사용자 이메일',
    			addr: '사용자 주소',
    			phone: '010-1234-4567'
    		},
    		order_id: '고유order_id_1234', //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
    		params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
    		account_expire_at: '2018-05-25', // 가상계좌 입금기간 제한 ( yyyy-mm-dd 포멧으로 입력해주세요. 가상계좌만 적용됩니다. )
    		extra: {
    		    start_at: '2019-05-10', // 정기 결제 시작일 - 시작일을 지정하지 않으면 그 날 당일로부터 결제가 가능한 Billing key 지급
    			end_at: '2022-05-10', // 정기결제 만료일 -  기간 없음 - 무제한
    	        vbank_result: 1, // 가상계좌 사용시 사용, 가상계좌 결과창을 볼지(1), 말지(0), 미설정시 봄(1)
    	        quota: '0,2,3' // 결제금액이 5만원 이상시 할부개월 허용범위를 설정할 수 있음, [0(일시불), 2개월, 3개월] 허용, 미설정시 12개월까지 허용
    		}
    	}).error(function (data) {
    		//결제 진행시 에러가 발생하면 수행됩니다.
    		console.log(data);
    	}).cancel(function (data) {
    		//결제가 취소되면 수행됩니다.
    		console.log(data);
    	}).ready(function (data) {
    		// 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
    		console.log(data);
    	}).confirm(function (data) {
    		//결제가 실행되기 전에 수행되며, 주로 재고를 확인하는 로직이 들어갑니다.
    		//주의 - 카드 수기결제일 경우 이 부분이 실행되지 않습니다.
    		console.log(data);
    		var enable = true; // 재고 수량 관리 로직 혹은 다른 처리
    		if (enable) {
    			BootPay.transactionConfirm(data); // 조건이 맞으면 승인 처리를 한다.
    		} else {
    			BootPay.removePaymentWindow(); // 조건이 맞지 않으면 결제 창을 닫고 결제를 승인하지 않는다.
    		}
    	}).close(function (data) {
    	    // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
    	    console.log(data);
    	}).done(function (data) {
    		//결제가 정상적으로 완료되면 수행됩니다
    		//비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.
    		console.log(data);
    	});
    </script>
    
    <!--
	<script>
    $("#check_module").click(function (){
    	var IMP = window.IMP; // 생략가능
    	   IMP.init('imp28449428');  // 가맹점 식별 코드

    	   IMP.request_pay({
    	      pg : 'kakao', // 결제방식
    	       pay_method : 'card',	// 결제 수단
    	       merchant_uid : 'merchant_' + new Date().getTime(),
    	      name : '주문명: 결제 테스트',	// order 테이블에 들어갈 주문명 혹은 주문 번호
    	       amount : '100',	// 결제 금액
    	       buyer_email : '',	// 구매자 email
    	      buyer_name :  '',	// 구매자 이름
    	       buyer_tel :  '',	// 구매자 전화번호
    	       buyer_addr :  '',	// 구매자 주소
    	       buyer_postcode :  '',	// 구매자 우편번호
    	       m_redirect_url : '/khx/payEnd.action'	// 결제 완료 후 보낼 컨트롤러의 메소드명
    	   }, function(rsp) {
    		if ( rsp.success ) { // 성공시
    			var msg = '결제가 완료되었습니다.';
    			msg += '고유ID : ' + rsp.imp_uid;
    			msg += '상점 거래ID : ' + rsp.merchant_uid;
    			msg += '결제 금액 : ' + rsp.paid_amount;
    			msg += '카드 승인번호 : ' + rsp.apply_num;
    		} else { // 실패시
    			var msg = '결제에 실패하였습니다.';
    			msg += '에러내용 : ' + rsp.error_msg;
    		}
    	});
    });
    </script>
    iamport no -->
    
	</main>
	<jsp:include page="Footer.jsp" flush="false" />
</body>

</html>