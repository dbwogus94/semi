class Xhr {
	// 생성자
    constructor(url, method, responseType, contentType, jsonObject) {
    	// #1
    	this.xhr = new XMLHttpRequest();			// 자바스크립트의 비동기를 지원하는 객체
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
	}
	
	// 비동기 GET방식
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
			this.xhr.send(JSON.stringify(this.getJsonObject()));
		}
		
	}
	
	
	async_toDataURL(callBack){
		
	}
	
	
	static func01(){
		return "자바스크립트 클래스의 static이 붙은 함수는 자바의 static함수와 동일하게 사용됩니다. " +
				"즉 클래스를 new로 생성하지 않고 사용이 가능합니다. " +
				" ex) Xhr_param1.func01() ";
		
	}
}