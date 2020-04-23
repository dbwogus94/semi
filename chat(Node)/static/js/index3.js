var socket = io();

/* 접속 되었을 때 실행 */
socket.on('connect', function(){ // connect(클라용 명령어)이란 명령은 클라가 접속했을때 발생하는 이벤트
	var input = document.getElementById('text_id');
	input.value = '접속 됨';  // id가 'text_id'에 값으로 '접속됨 전달'
});

/* 전송 함수 send() 정의 */
function send() {
	// [input:text]에 입력되어있는 데이터 데이터 가져오기
	var message = document.getElementById('text_id').value;
	
	// 가져온 데이터 빈칸으로 변경
	document.getElementById('text_id').value = '';  

	// 서버로 send라는 이름으로 이벤트 전달 + 데이터를 json형태로 담아 함께 전달
	socket.emit('send', {msg: message}); // json 방식으로 전달
	
	/*
	 *  .emit('send',...)으로 이벤트를 서버에 전달했다면 
	 *  서버에서는 
	 *  .on('send',...)로 받아야 한다.(같은 이름 사용) 
	 *  즉  emit()를 통해 이벤트를 전송
	 *     on()를 통해 이벤트를 받는다. 
	 */
}