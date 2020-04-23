var socket = io();

/* 접속 되었을 때 실행 */
socket.on('connect', function(){ // connect(클라용 명령어)이란 명령은 클라가 접속했을때 발생하는 이벤트
	
	/* 접속시 이름을 입력 받고 */
	var name = prompt('반갑습니다!.', '');
	
	
	/* 이름이 빈칸인 경우 */
	if(!name){
		name = '익명';
	}
	
	/* 서버에 새로운 유저가 왔다고 알림 */
	socket.emit('newUser', name); // 'newUser'이벤트 이름
	
});



socket.on('update', function(data){   // data = {type: 'connect', name: 'SERVER', message: newUserName + '님이 접속하였습니다.'}
	console.log(data.name +" : "+  data.message); 	// 서버 : newUserName + '님이 접속하였습니다.'
});

/* 메시지 전송 함수 */
function send() {
	// [input:text]입력된 데이터 가져오기
	var message = document.getElementById('text_id').value;
	console.log("내가 쓴 글 : "+message);
	
	// 가져온 데이터 빈칸 변경
	document.getElementById('text_id').value = '';
	
	// 서버로 message 이름으로 이벤트 전달 + 받아온 데이터 함께 전송
	socket.emit('message', {type: 'message', message: message});
											// 기본적으로 json의 key는 String로 들어간다.(변수x)
}