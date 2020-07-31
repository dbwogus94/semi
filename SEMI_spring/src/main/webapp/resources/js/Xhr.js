"use strict"
//비동기 전송용 자바스크립트 클래스
//생성자 인자전달에 http 설정값 전달.
//메소드 : 기본적인 get방식, post방식 구현, url >> base64 코드로 받는 메서드도 구현 예정  
class Xhr {
	// 생성자
	 constructor(url, method, responseType, contentType, jsonObject) {	// == (JAVA) public Xhr(url, method, responseType, contentType, jsonObject){}
	 	// #1
	 	this.xhr = new XMLHttpRequest();		// 자바스크립트의 비동기를 지원하는 객체
	 	this.url = url;							// this.url == (JAVA) private String url = url 
		this.method = method;
		this.responseType = responseType;		// responseType || "json" 가능은 하지만 권장하지 않음 set를 사용하자
		this.contentType = contentType;			 
		this.jsonObject = jsonObject 
		
	 }
	 /*
	  	# get, set
  			1. set를 정의하면 해당 클래스를 생성할때 작동한다.	
  			2. get set는 정의하지 않아도 자동을 생성되어 있다.
  			3. get사용은  this.url
  			4. set사용은  this.url = value
	 */
 	get url() {
	    return this._url;
	}    
	set url(url){
		this._url = url;
	}
	
	get method(){
		//console.log("get method() 실행")
		return this._method;
	}
	set method(method){
		//console.log("set method(value) 실행")
		this._method = (method === undefined)? "GET" : method;
	}
	
	get responseType(){
		//console.log("get responseType() 실행")
		return this._responseType;
	}
	set responseType(responseType){
		//console.log(`set responseType(${this.responseType}) 실행`)
		if(responseType === undefined || responseType === null || responseType === ""){
			this._responseType = "json";
		} else {
			this._responseType = responseType;
		} 
	}
	
	get contentType(){
		//console.log("get contentType() 실행")
		return this._contentType;
	}
	set contentType(contentType){
		//console.log(`set contentType(${this.contentType}) 실행`)
		if(contentType === undefined || contentType === null || contentType === ""){
			if(this.method === "get" ||  this.method === "GET"){
				this._contentType = "application/x-www-form-urlencoded; charset=utf-8";
			}
			if(this.method === "post" ||  this.method === "POST"){
				this._contentType = "application/json; charset=utf-8";
			}
		} else {
			this._contentType = contentType;
		}
	}
	
	get jsonObject(){
		//console.log("get jsonObject() 실행")
		return this._jsonObject;
	}
	set jsonObject(jsonObject){
		//console.log(`set jsonObject(${this.jsonObject}) 실행`)
		this._jsonObject = (jsonObject === undefined)? {} : jsonObject;
	}
	
	// 일반적인 비동기 GET. POST 요청후 응답 
	async_DataLoad(callBack){
		const xhr = this.xhr		// #4 응답  ,   #1 url오픈 >> #2 헤더설정 >> #3 요청 
		
		// #4 onreadystatechange : 준비상태(200, 404, 500...)가 바뀌었을떄 트리거 되는 이벤트
		xhr.onreadystatechange = function() { 
			if(xhr.readyState === xhr.DONE){		// xhr.DONE는 서버에서 정상 응답되었을때
				if (xhr.status === 200 || xhr.status === 201) {
					// 응답시 받은 데이터 확인
					console.log("[Xhr] 응답 성공 데이터 확인 : " + JSON.stringify(xhr.response));
					
					let res = xhr.response;
					// 데이터 콜백에 전달
					if(callBack){
						callBack(xhr.response)					
					}
				} else {
					console.error("서버 에러 : " + xhr.response);
					return false;
				}
			} else if(xhr.readyState === 4){		// 네트워크 문제 같은것으로 서버에 가지 못했을때 404 
				console.error("네트워크 에러 : " + xhr.responseText);
				return false;
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
		const xhr = this.xhr;
		
		callBack = callBack || false;
		
		// #4 응답 >>> 응답 데이터 받기
		this.async_DataLoad(callBack);
		
		if(this.method === "GET" || this.method === "get") {
			if(this.contentType === "application/x-www-form-urlencoded; charset=utf-8"
				|| this.contentType === "application/x-www-form-urlencoded"){
				
				if(this.jsonObject === {}){ 
					// #2. 열기 : 쿼리스트링 없음
					xhr.open(this.method, this.url);				
					console.log("GET 방식 전송 : 쿼리스트링 없음")
				} else {
					let query = "";
					let params = this.jsonObject;
					for (let key in params){
						query += key + "=" + params[key] + "&";
					}
					// #2.  열기 : 쿼리스트링 있음 >>> 요청할 url + 쿼리스트링
					xhr.open(this.method, this.url + "?" + query);		
					console.log("GET 방식 전송 : 쿼리스트링 있음 >> " + this.url + "?" + query)
				}
				
				// #2. 응답 받을 타입 설정
				xhr.responseType = this.responseType;				
				
			} else {
				console.log("Content-Type이 맞지 않습니다. >>> get방식이면 'application/x-www-form-urlencoded; charset=utf-8' 으로 입력하세요. ")
			} 
				console.log("GET 방식으로 요청하세요")
		}
		// #3. 전송 : get방식 전송	
		xhr.send();
	}
	
	// 비동기 POST방식
	async_POST(callBack){
		const xhr = this.xhr;
		
		callBack = callBack || false;
		
		// #4 응답 >>> 응답 데이터 받기
		this.async_DataLoad(callBack);
		
		if(this.method === "POST" || this.method === "post"){
			if(this.contentType === "application/json; charset=utf-8"
				|| this.contentType === "application/json"){		// Content-type가 쿼리스트링이 아니라면
				
				// #2. 열기 : 메소드 설정, 요청할 url 설정 
				xhr.open(this.method, this.url);		
				// #2. 응답 받을 타입 설정
				xhr.responseType = this.responseType;		
				// #2. 전송 방식 설정 : Content-type = application/json
				xhr.setRequestHeader('Content-type', this.contentType);		
			
			} else {
				console.log("Content-Type가 맞지 않습니다. >>> post방식이면 'application/json; charset=utf-8'으로 입력하세요. ")
			}
		} else {
			console.log("POST 방식으로 요청하세요")
		}
		
		if(this.jsonObject === {}){
			// #3. 전송  : body없음
			console.log("POST 전송,  body 없음") 
			xhr.send();										
		} else {
			console.log("POST 전송,  body 있음 :" +  JSON.stringify(this.jsonObject));
			// #3. 전송 : body있음 >>> post 방식일때는 보낼 데이터를 send(body)에 인자로 전달한다 >> Request payload. 
			//  이때 만약 JSON.stringify()를 생략한다면 Request payload를 사용하는 것이 아닌 query String parameters를 사용하는 것이다.
			xhr.send(JSON.stringify(this.jsonObject));
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

















/* 자바스크립트로 클래스 사용법 1 */
class Xhr_param {
    constructor(url, method, responseType, contentType, jsonObject) {
    	this.url = url;
		this.method = method || "GET";
		this.responseType = responseType || "json";
		this.contentType = contentType || "application/x-www-form-urlencoded; charset=utf-8";	// get 방식 디폴트 == 쿼리스트링
		this.jsonObject = jsonObject || {}
    }
    
    
    // get set은 만들지 않아도 사용이 가능하다.
    get url(){
    	console.log("getter 사용됨")
    	return this._url;
    }
    set url(url){
    	console.log("세터 사용됨")
    	this._url = (url === undefined)? "기본 url" : url ;
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
	},
	this.getResponseType = function(){
		return this.responseType;
	},
	this.setResponseType = function(responseType){
		this.responseType = responseType
	}
};






