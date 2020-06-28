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
		
		// post 	
		//const xhr_param = new Xhr_param("comment/loadCommentList.do", "POST", "json", "application/json; charset=utf-8", jsonObject)
		
		// get
		//const xhr_param = new Xhr_param("comment/loadCommentList_GET.do", "GET", "json", undefined, jsonObject)
		//console.log(xhr_param)
		
		// 비동기
		//async_xhr(xhr_param, loadCommentList)
		
		
		
		//post
		const xhr = new Xhr("comment/loadCommentList.do", "POST", "json", "application/json; charset=utf-8", jsonObject)
		xhr.async_POST(loadCommentList)
		
		//get
		//const xhr = new Xhr("comment/loadCommentList_GET.do", "GET", "json", undefined, jsonObject)
		//xhr.async_GET(loadCommentList)
	}
	 
}



// XMLHTTpRequest		>> 리팩토링 하자 : get, post 둘다 이미지 받는거 가능하게 처리, post방식에 body로 보낼값 없을때 .send() 추가할 것
// async_xhr와 Xhr_param 합치기
function async_xhr(Xhr_param, callBack){
	// #1
	const xhr = new XMLHttpRequest();
	
	// #4 onreadystatechange : 준비상태(200, 404, 500...)가 바뀌었을떄 트리거 되는 이벤트
	xhr.onreadystatechange = function() { 
		if(xhr.readyState === xhr.DONE){		// xhr.DONE는 서버에서 정상 응답되었을때
			if (xhr.status === 200 || xhr.status === 201) {
				// 응답시 받은 데이터 확인
				console.log("성공 데이터 확인 : " + JSON.stringify(xhr.response));
				
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
		
		if(Xhr_param.getContentType() === "application/json; charset=utf-8"
			|| Xhr_param.getContentType() === "application/json"){		// Content-type가 쿼리스트링이 아니라면
			// #2
			xhr.open(Xhr_param.getMethod(), Xhr_param.getUrl());					// 열기 : 요청할 url 메소드 설정 
			
			xhr.responseType = Xhr_param.getResponseType();							// 응답 받을 타입 설정
			
			xhr.setRequestHeader('Content-type', Xhr_param.getContentType());		// 전송 방식 설정 : Content-type = application/json
			
			console.log(JSON.stringify(Xhr_param.getJsonObject()));
			//#3 
			xhr.send(JSON.stringify(Xhr_param.getJsonObject()));					// 전송 : post 방식일때는 보낼 데이터를 send(body)에 인자로 전달한다.
		
		} else {
			console.log("Content-Type가 맞지 않습니다.")
		}
	
	// GET방식	
	} else if(Xhr_param.getMethod() === "GET" || Xhr_param.getMethod() === "get") {
		if(Xhr_param.getContentType() === "application/x-www-form-urlencoded; charset=utf-8"
			|| Xhr_param.getContentType() === "application/x-www-form-urlencoded"){
			
			// 쿼리스트링 있을때
			if(Xhr_param.getJsonObject() !== {}){

				let query = "";
				let params = Xhr_param.getJsonObject();	//{key: value, key2: value2}
				for (let key in params){
					query += key + "=" + params[key] + "&";
				}
				
				// #2
				xhr.open(Xhr_param.getMethod(), Xhr_param.getUrl() + "?" + query);		// 열기 : 요청할 url 메소드 설정 
				
				xhr.responseType = Xhr_param.getResponseType();							// 응답 받을 타입 설정
				
				
				console.log("GET 방식 전송 : 쿼리스트링 있음 >> " + Xhr_param.getUrl() + "?" + query)
				
			// 쿼리스트링이 없을때
			} else {
				// #2
				xhr.open(Xhr_param.getMethod(), Xhr_param.getUrl());
				xhr.responseType = Xhr_param.getResponseType();
				
				
				console.log("GET 방식 전송 : 쿼리스트링 없음")
			}
			// #3
			xhr.send();		// get방식 전송		
			
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
	console.log(response)

	let commentList = new Array();

	for(let i = 0; i<response.length; i++){
		commentList[i] = new CommentDto(response[i])
	}
	console.log(commentList)
	
	
	/* 여기까지 확인  */
	
	// commentList를 이용하여 뷰를 그린다.
	// 뷰그리는 함수()
}


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
	getCommentNo() {
		return this.commentNo;
	}
	setCommentNo(commentNo){
		this.commentNo = commentNo;
	}
	
	getBoardNo(){
		return this.boardNo;
	}
	setBoardNo(boardNo){
		this.boardNo = boardNo;
	}
	
	getCommentGroupNo(){
		return this.commentGroupNo;
	}
	setCommentGroupNo(commentGroupNo){
		this.commentGroupNo = commentGroupNo;
	}
	
	getCommentGroupSeq(){
		return this.commentGroupSeq;
	}
	setCommentGroupSeq(commentGroupSeq){
		this.commentGroupSeq = commentGroupSeq
	}
	
	getProfileImg(){
		return this.profileImg;
	}
	setProfileImg(profileImg){
		this.profileImg = profileImg;
	}
	
	getId(){
		return this.id;
	}
	setId(id){
		this.id = id
	}
	
	getCommentName(){
		return this.commentName;
	}
	setCommentName(commentName){
		this.commentName = commentName;
	}

	getCommentContent(){
		return this.commentContent;
	}
	setCommentContent(commentContent){
		this.commentContent = commentContent;
	}
	
	getCommentGegdate(){
		return this.commentGegdate;
	}
	setCommentGegdate(commentGegdate){
		this.commentGegdate = commentGegdate;
	}
	
	getCommentUpdateRegDate(){
		return this.commentUpdateRegDate;
	}
	setCommentUpdateRegDate(commentUpdateRegDate){
		this.commentUpdateRegDate = commentUpdateRegDate
	}
	
	getReCommentCount(){
		return this.reCommentCount;
	}
	setReCommentCount(reCommentCount){
		this.reCommentCount = reCommentCount;
	}
	
	getStartBoardNo(){
		return this.startBoardNo;
	}
	setStartBoardNo(startBoardNo){
		this.startBoardNo = startBoardNo;
	}
	
	getEndBoardNo(){
		return this.endBoardNo;
	}
	setEndBoardNo(endBoardNo){
		this.endBoardNo = endBoardNo;
	}
	
}


/* 자바스크립트로 클래스 사용법 1 */
class Xhr_param {
    constructor(url, method, responseType, contentType, jsonObject) {
    	this.url = url;
		this.method = method || "GET";
		this.responseType = responseType || "json";
		this.contentType = contentType || "application/x-www-form-urlencoded; charset=utf-8";	// get 방식 디폴트 == 쿼리스트링
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












// 비동기 전송용 자바스크립트 클래스
// 생성자 인자전달에 http 설정값 전달.
// 메소드 : 기본적인 get방식, post방식 구현, url >> base64 코드로 받는 메서드도 구현 예정  
class Xhr {
	// 생성자
    constructor(url, method, responseType, contentType, jsonObject) {	// == (JAVA) public Xhr(url, method, responseType, contentType, jsonObject){}
    	// #1
    	this.xhr = new XMLHttpRequest();			// 자바스크립트의 비동기를 지원하는 객체
    	this.url = url;								// this.url == (JAVA) private String url = url 
		this.method = method || "GET";
		this.responseType = responseType || "json";
		this.contentType = contentType || "application/x-www-form-urlencoded; charset=utf-8";	// get 방식 디폴트 == 쿼리스트링
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
	
	// 일반적인 비동기 GET. POST 요청후 응답 
	async_DataLoad(callBack){
		const xhr = this.xhr
		
		// #4 onreadystatechange : 준비상태(200, 404, 500...)가 바뀌었을떄 트리거 되는 이벤트
		xhr.onreadystatechange = function() { 
			if(xhr.readyState === xhr.DONE){		// xhr.DONE는 서버에서 정상 응답되었을때
				if (xhr.status === 200 || xhr.status === 201) {
					// 응답시 받은 데이터 확인
					console.log("성공 데이터 확인 : " + JSON.stringify(xhr.response));
					
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
		/*
	 	request후에 웹에서 response가 트리거되는 순서
	 
		     onreadystatechange
		 readyState === 4, xhr.DONE
		                              ⇓
		 onload / onerror / onabort
		                              ⇓
		         onloadend 
	  
	 */
	}
	
	// 비동기 GET방식 >> XMLHTTpRequest()	: url오픈 > 헤더설정 >> 전송 >> 값을 받아온다(응답)
	async_GET(callBack){
		
		callBack = callBack || false;
		
		// #4 응답 >>> 응답 데이터 받기
		this.async_DataLoad(callBack);
		
		if(this.getMethod() === "GET" || this.getMethod() === "get") {
			if(this.getContentType() === "application/x-www-form-urlencoded; charset=utf-8"
				|| this.getContentType() === "application/x-www-form-urlencoded"){
				
				if(this.getJsonObject() === {}){ 
					// #2. 열기 : 쿼리스트링 없음
					this.xhr.open(this.getMethod(), this.getUrl());				
					console.log("GET 방식 전송 : 쿼리스트링 없음")
				} else {
					let query = "";
					let params = this.getJsonObject();
					for (let key in params){
						query += key + "=" + params[key] + "&";
					}
					// #2.  열기 : 쿼리스트링 있음 >>> 요청할 url + 쿼리스트링
					this.xhr.open(this.getMethod(), this.getUrl() + "?" + query);		
					console.log("GET 방식 전송 : 쿼리스트링 있음 >> " + this.getUrl() + "?" + query)
				}
				
				// #2. 응답 받을 타입 설정
				this.xhr.responseType = this.getResponseType();				
				
			} else {
				console.log("Content-Type이 맞지 않습니다. >>> get방식이면 'application/x-www-form-urlencoded; charset=utf-8' 으로 입력하세요. ")
			} 
			console.log("GET 방식으로 요청하세요")
		}
		// #3. 전송 : get방식 전송	
		this.xhr.send();
	}
	
	// 비동기 POST방식
	async_POST(callBack){
		
		callBack = callBack || false;
		
		// #4 응답 >>> 응답 데이터 받기
		this.async_DataLoad(callBack);
		
		if(this.getMethod() === "POST" || this.getMethod() === "post"){
			if(this.getContentType() === "application/json; charset=utf-8"
				|| this.getContentType() === "application/json"){		// Content-type가 쿼리스트링이 아니라면
				
				// #2. 열기 : 메소드 설정, 요청할 url 설정 
				this.xhr.open(this.getMethod(), this.getUrl());		
				// #2. 응답 받을 타입 설정
				this.xhr.responseType = this.getResponseType();		
				// #2. 전송 방식 설정 : Content-type = application/json
				this.xhr.setRequestHeader('Content-type', this.getContentType());		
			
			} else {
				console.log("Content-Type가 맞지 않습니다. >>> post방식이면 'application/json; charset=utf-8'으로 입력하세요. ")
			}
		} else {
			console.log("POST 방식으로 요청하세요")
		}
		
		if(this.getJsonObject() === {}){
			// #3. 전송  : body없음
			this.xhr.send();										
		} else {
			console.log(JSON.stringify(this.getJsonObject()));
			// #3. 전송 : body있음 >>> post 방식일때는 보낼 데이터를 send(body)에 인자로 전달한다 >> Request payload. 
			//  이때 만약 JSON.stringify()를 생략한다면 Request payload를 사용하는 것이 아닌 query String parameters를 사용하는 것이다.
			this.xhr.send(JSON.stringify(this.getJsonObject()));
		}
		
	}
	
	// 정적자원 uri요청 >> base64 받아서 file 객체로 변환  
	async_toDataURL(callBack){
		
	}
	
	//이미지 변환 : base64 >> new File(데이터, 이름 , 타입)
	base64toFile(){
		
	}
		
	
	static func01(){
		return "자바스크립트 클래스의 static이 붙은 함수는 자바의 static함수와 동일하게 사용됩니다. " +
				"즉 클래스를 new로 생성하지 않고 사용이 가능합니다. " +
				" ex) Xhr_param1.func01() ";
		
	}
}


