"use strict"
// 전역변수 : 요청할 다음페이지 
let currentPage = 0;

/* 스크롤 페이징 */
window.onscroll = function(e){
	// 현재 보이는 창 높이
	let window_innerHeight = window.innerHeight;
	// 스크롤 위치
	let nowScroll = window.scrollY || document.documentElement.scrollTop;
	// 보여지는 html body 높이
	let fullHeight = document.body.scrollHeight;
	
	// 스크롤이 가장 바닦에 다았을때
	if((window_innerHeight + nowScroll) >= fullHeight){
		let boardNo = document.getElementById("boardNo").value
		console.log(boardNo)
		
		currentPage++;
		
		//boardNo =	Number(boardNo) or board *= 1
		// 비동기로 서버에 요청시 사용할 데이터
		let jsonObject = {boardNo:Number(boardNo), currentPage:currentPage}
		
		
		// async_xhr에 파라미터에 사용되는 클래스 	
		//const xhr_param = new Xhr_param("comment/loadCommentList.do", "POST", "json", "application/json", jsonObject)
		//xhr_param.setContentType("application/json")
		/* 문제 발생 : 
		 * post로 전송하여 사용히고 싶었지만 서버에서 
		 * xhr_param에 contentType = "application/json" 
		 * 이 적용이 안되고 있음
		 * 그래서 get방식으로 대처를했다. 
		 */
		
		
		const xhr_param = new Xhr_param("comment/loadCommentList.do", "GET", "json", undefined, jsonObject)
		console.log(xhr_param)
		
		// 비동기
		async_xhr(xhr_param, loadCommentList)
	}
	 
}



// XMLHTTpRequest
function async_xhr(Xhr_param, callBack){
	// #1
	const xhr = new XMLHttpRequest();
	
	// #4 onreadystatechange : 준비상태(200, 404, 500...)가 바뀌었을떄 트리거 되는 이벤트
	xhr.onreadystatechange = function() { 
		if(xhr.readyState === xhr.DONE){		// xhr.DONE는 서버에서 정상 응답되었을때
			if (xhr.status === 200 || xhr.status === 201) {
				// 응답시 받은 데이터 확인
				console.log("성공 메세지" + xhr.response);
				console.log("성공 데이터 확인" + xhr.response);
				
				// 데이터 콜백에 전달
				if(callBack){
					callBack(xhr.response)
				}
			
			} else {
				console.error("서버 에러 : " + xhr.response);
			}
		} else if(xhr.readyState === 4){		// 네트워크 문제 같은것으로 서버에 가지 못했을때 404 
			console.error("네트워크 에러 : " + xhr.responseText);
		}
	}
	
	// post방식
	if(Xhr_param.getMethod() === "POST" || Xhr_param.getMethod() === "post"){
		
		if(Xhr_param.getContentType() === "application/json"){		// Content-type가 쿼리스트링이 아니라면
			// #2
			xhr.open(Xhr_param.getMethod(), Xhr_param.getUrl());					// 열기 : 요청할 url 메소드 설정 
			
			xhr.responseType = Xhr_param.getResponseType();							// 응답 받을 타입 설정
			
			xhr.setRequestHeader('Content-type', Xhr_param.setContentType());		// 전송 방식 설정 : Content-type = application/json
			
			console.log(JSON.stringify(Xhr_param.getJsonObject()));
			//#3 
			xhr.send(JSON.stringify(Xhr_param.getJsonObject()));					// 전송 : post 방식일때는 보낼 데이터를 send(body)에 인자로 전달한다.
		
		} else {
			console.log("Content-Type가 맞지 않습니다.")
		}
	
	// GET방식	
	} else if(Xhr_param.getMethod() === "GET" || Xhr_param.getMethod() === "get") {
		if(Xhr_param.getContentType() === "application/x-www-form-urlencoded"){
			
			// 쿼리스트링 있을때
			if(Xhr_param.getJsonObject() !== {}){

				let query = "";
				let params = Xhr_param.getJsonObject();
				for (let key in params){
					query += key + "=" + params[key] + "&";
				}
				
				// #2
				xhr.open(Xhr_param.getMethod(), Xhr_param.getUrl() + "?" + query);		// 열기 : 요청할 url 메소드 설정 
				
				xhr.responseType = Xhr_param.getResponseType();							// 응답 받을 타입 설정
				
				// #3
				console.log("GET 방식 전송 : 쿼리스트링 있음 >> " + Xhr_param.getUrl() + "?" + query)
				xhr.send();																// 전송									
				
			// 쿼리스트링이 없을때
			} else {
				// #2
				xhr.open(Xhr_param.getMethod(), Xhr_param.getUrl());
				xhr.responseType = Xhr_param.getResponseType();
				
				// #3
				console.log("GET 방식 전송 : 쿼리스트링 없음")
				xhr.send();
				
			}
			
		} else {
			console.log("Content-Type이 맞지 않습니다.")
		} 
		
	} else {
		console.log("GET나 POST 방식으로 요청하세요")
	}
}
/*
 	request후에 웹에서 response가 트리거되는 순서
 
     onreadystatechange
      readyState === 4, xhr.DONE
                                   ⇓
 onload / onerror / onabort
                                   ⇓
         onloadend 
  
 */


function loadCommentList(response){	

	//let dto = new CommentDto(response)
	console.log(response)
	
	/* 여기까지 확인  */
	
	// response.commentList를 for문 돌려서 CommentDto에 담아준다.
	
	// CommentDto를 이용하여 뷰를 그린다.
}





/* 미완성 */
class CommentDto{
	constructor(response) {
		this.commentNo = response.commentNo;
		this.boardNo = response.boardNo;
		this.commentGroupNo = response.commentGroupNo;
		this.commentGroupSeq = response.commentGroupSeq;
		this.profileImg = response.profileImg;
		this.id = response.id;
		this.commentName = response.commentName;
		this.commentContent = response.commentContent;
		this.commentGegdate = response.commentGegdate;
		this.commentUpdateRegDate = response.commentUpdateRegDate;
		this.reCommentCount = response.reCommentCount;
		this.startBoardNo = response.startBoardNo;
		this.endBoardNo = response.endBoardNo;
	}
	// + get set
}


/* 자바스크립트로 클래스 사용법 1 */
class Xhr_param {
    constructor(url, method, responseType, contentType, jsonObject) {
    	this.url = url;
		this.method = method || "GET";
		this.responseType = responseType || "json";
		this.contentType = contentType || "application/x-www-form-urlencoded";	// get 방식 디폴트 == 쿼리스트링
		this.jsonObject = jsonObject || {}
    }

    getUrl() {
	    return this.url;
	}    
	setUrl(url){
		this.url = url;
	}
	
	getMethod(){
		return this.method;
	}
	setMethod(method){
		this.method = method;
	}
	
	getResponseType(){
		return this.responseType;
	}
	setResponseType(responseType){
		this.responseType = responseType
	}
	
	getContentType(){
		return this.contentType;
	}
	setContentType(contentType){
		this.contentType = contentType
	}
	
	
	getJsonObject(){
		return this.jsonObject;
	}
	setJsonObject(jsonObject){
		this.jsonObject = jsonObject;
	}
	
	
	static func01(){
		return "자바스크립트 클래스의 static이 붙은 함수는 자바의 static함수와 동일하게 사용됩니다. " +
				"즉 클래스를 new로 생성하지 않고 사용이 가능합니다. " +
				" ex) Xhr_param1.func01() ";
		
	}
}

/* 자바스크립트로 클래스 사용법 2 	>>> 2번 방법에는 문제가 있다. 해당 클래스를 생성하는것 만으로 포함된 함수(메서드)들이 모두 함깨 생성이 된다.*/
function Xhr_param2(url, method, responseType){ 
	this.url = url,
	this.method = method || "GET",
	this.responseType = responseType || "json",
	this.getUrl = function() {
	    return this.url;
	},
	this.setUrl = function(url){
		this.url = url;
	},
	this.getMethod = function(){
		return this.method;
	},
	this.setMethod = function(method){
		this.method = method;
	}
	this.getResponseType = function(){
		return this.responseType;
	},
	this.setResponseType = function(responseType){
		this.responseType = responseType
	}
	
};
