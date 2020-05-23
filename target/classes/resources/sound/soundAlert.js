
function soundAlertFunction( test ){
	
	// console.log("soundAlertFunctionTest => " + test);
	
	var audioElement = document.createElement("AUDIO");
	
	// audioElement.src = "../sound/alertSound.mp3";
	audioElement.src = "alertSound.mp3";
	audioElement.autoplay = true;
	audioElement.loop = true;
	
	// audioElement.setAttribute("src", "../sound/alertSound.mp3");
	// audioElement.setAttribute("autolay", "true");
	// audioElement.setAttribute("loop", "true");
	
	// audioElement.load();
	// audioElement.play();
	
	audioElement.innerHTML = "audio 태그 미지원 (익스플로러 사용 금지)";
	
	// int companyIdx
	alert("예약 ~~~~가 ----에 잡혔다! 확인 안 누르면, 계속 소리 나야 할텐데");
	// alert이 문서실행을 중단시켜서, 확인 버튼을 눌러야 이 다음으로 넘어가진다!
	// => 소리가 계속 나고 있을 지는 의문
	
	audioElement.muted = true; // 소리 꺼지고, 이 스크립트 종료!
}
