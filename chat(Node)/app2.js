// 2번째 >>> #표시 추가 코드

/* 설치한 express 모듈 불러오기  */
const express = require('express');

/* 설치한 socket.io 모듈 불러오기 */
const socket = require('socket.io');

/* Node.js 기본 내장 모듈 불러오기 */
const http = require('http');


/* #추가 Node.js 기본 내장 모듈 불러오기  >> 일종에 스트림
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
 *     app.use()를 미들웨어를 추가
 *     *정적파일  : 미리 소스를 넣어서 만들어둔 파일
 *     >>> 정적 파일이 있는 폴더를 연동 시켜둔다.
 */
app.use('/css', express.static('./static/css'));
app.use('/js', express.static('./static/js'));
app.use('/my_model', express.static('./static/my_model'))


/* # Get방식으로 / 경로에 접속하면 실행 됨 */
app.get('/', function(request, response){
	fs.readFile('./static/deeplearningTest.html', function(err, data){ // index.html을 읽어들여서  data에 담는다.
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


/* 서버를 8080포트로 listen */
server.listen(3000, function(){
	console.log('서버 실행 중...');
});