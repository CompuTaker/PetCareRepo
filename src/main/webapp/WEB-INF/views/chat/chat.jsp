<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChattingAppTestPage</title>
<!--
<script type="text/javascript" src="<c:url value="/static/js/jquery-1.11.2.js"/>"></script>
jquery -->
<!--
<script type="text/javascript" src="<c:url value="/static/sockjs-0.3.4.js"/>"></script>
sockjs -->
<!-- crossorigin="anonymous" -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.min.js" crossorigin="anonymous"></script> -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// var xhr = new XMLHttpRequest
		$("#sendBtn").click(function() {
			sendMessage();
		});
	});
	var sock;
	function sendMessage() {
		// sock = new SockJS("<c:url value="/echo"/>");
		// sock = new SockJS("http://localhost:8080/TestMVC/echo");
		// sock = new SockJS("http://localhost:8172/hello/echo");
		// 18.178.110.208 // ChattingApp
		// 18.180.187.192 => Seperate Chatting Server
		sock = new SockJS("http://18.180.187.192:8080/hello/echo");
		sock.onmessage = onMessage;
		sock.onclose = onClose;
		sock.onopen = function() {
			sock.send($("#message").val());
		};
	}
	function onMessage(evt) {
		var data = evt.data;
		$("#data").append(data);
		sock.close();
	}
	function onClose(evt) {
		$("#data").append(" => Connection Closed!");
	}
</script>
</head>
<body>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="SEND" />
	<div id="data"></div>
</body>
</html>