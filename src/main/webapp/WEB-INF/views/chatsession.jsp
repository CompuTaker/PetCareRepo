<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
</head>
<body>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="submit" />
	<div id="messageArea"></div>
</body>
<script type="text/javascript">
	$("#sendBtn").click(function() {
		sendMessage();
		$('#message').val('')
	});
	
	var isAdmin = "${isAdmin}";
	console.log(isAdmin);
	var idx = "${idx}";
	console.log(idx);
	console.log("~~~~~~~~~~~~");
	let sock = new SockJS("http://localhost:8272/hello/echo?idx="+idx+"&isAdmin="+isAdmin);
	// let sock = new SockJS("http://18.180.187.192:8080/hello/echo");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	// 메시지 전송
	function sendMessage() {
		sock.send($("#message").val());
	}
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		$("#messageArea").append(data + "<br/>");
	}
	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		$("#messageArea").append("연결 끊김");

	}
</script>
</html>