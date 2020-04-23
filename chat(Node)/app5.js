
/* jshint esversion: 6*/

// 4번째 >>> 

/* 설치한 express 모듈 불러오기  */
const express = require('express');

/* 설치한 socket.io 모듈 불러오기 */
const socket = require('socket.io');

/* Node.js 기본 내장 모듈 불러오기 */
const http = require('http');


/* 추가 Node.js 기본 내장 모듈 불러오기  >> 일종에 스트림
 * 파일관련된 처리 >>> 읽기
 */
const fs = require('fs');


/* express 객체 생성 */
const app = express();

/* express http 서버 생성 */
const server = http.createServer(app);

/* 생성된 서버를 socket.io에 바인딩 */
const io = socket(server);

/*  정적파일을 사용하기 위해 미들웨어(Middleware)를 사용하는 코드 
 *     app.use()를 이용해 미들웨어를 추가
 *     *정적파일  : 
			프로그래밍적으로 만들어진 파일이 동적 파일이고
			사람이 작성한 것이 언제나 똑같이 보이는 것이 정적 파일이다.
 *     >>> 정적 파일이 있는 폴더를 연동 시켜둔다.  >>> 의문 서버는 정적 파일을 사용하지 않는데 왜 가져올까?
 */
app.use('/css', express.static('./static/css'));
app.use('/js', express.static('./static/js'));


/* Get방식으로 / 경로에 접속하면 실행 됨 */
app.get('/', function(request, response){
	fs.readFile('./static/index5.html', function(err, data){ // index.html을 읽어들여서  data에 담는다.
		if(err){
			response.send('에러');
		} else {
			// 해더에 문서의 타입을 작성
			response.writeHead(200, {'Content-Type':'text/html'});
			// html 데이터를 보낸다.
			response.write(data);
			response.end(); //write를 통해 열어준 통로를 닫기, 종료선언
		}
	});
});

// io.sockets 는 접속되는 모든 소켓 
// on()은 소켓에서 해당 이벤트를 받으면 콜백함수가 실행됩니다!
// 	>>> 이벤트는  'newUser', 'message', 'disconnect'(기본이벤트) 3개 
io.sockets.on('connection', function(socket){   // connection(서버용 명령어)이라는 접속이벤트 동시에 콜백함수로 전달되는 소켓은 접속된  해당 소켓
	
	/* 새로운 유저가 접속하면 모든 소켓에게 전송 */
	socket.on('newUser', function(newUserName) {
		console.log(newUserName + "님이 접속하였습니다.");
		
		/* 소켓에 이름 저장해 두기 */
		socket.name = newUserName;  // socket의 {name: newUserName}		
									//  io.sockets.on()바디 안에서 socket의 {name: newUserName}은 계속 살아 있는다.
		
		/* 모든 소켓에게 전송 */
		io.sockets.emit('update', {type: 'connect', name: 'SERVER', message: newUserName + '님이 접속하였습니다.'})
									/* json key : type, name, message >>> 기본적으로 키값을 스트링(변수 사용x) */
	});
	
	/* 서버로 전송된 메시지 받아서  >> 보낸사람 제외하고 모두 전송 */
	socket.on('message', function(data){
		/* 받은 데이터에 누가 보냈는지 이름 추가  */
		data.name = socket.name;    // date의 {name: socket{name: '값'}}
		
		console.log(data);
		
		/* 보낸 사람을 제외한 나머지 유저에게 메시지 전송 */
		socket.broadcast.emit('update', data);
				// socket.broadcast.emit() >>> 보낸 사람 제외 모두에게 전달
				// io.sockets.emit()	 >>> 모두에게 전달
	});
	
	
	/* 접속 종료 */
	socket.on('disconnect', function(){  // socket.io 기본 이벤트 disconnect로 연결되어 있던 소켓과 접속이 끊어지면 자동으로 실행
		console.log(socket.name + '님이 나가셨습니다.');
		
		/* 나가는 사람을 제외한 유저에게 메시지 전송 */
		socket.broadcast.emit('update', {type: 'disconnect', name: 'SERVER', message: socket.name + '님이 나가셨습니다.'});
	});
});


/* 서버를 8080포트로 listen */
server.listen(3000, function(){
	console.log('서버 실행 중...');
});