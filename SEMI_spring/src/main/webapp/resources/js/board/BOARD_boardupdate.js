function createSummernote(){
	$('#summernote').summernote({
        height: 350,					// 에디터 높이
        minHeight: null,				// 최소 높이
        maxHeight: null,				// 최대 높이
        focus: false,					// 에디터 로딩후 포커스를 맞출지 여부
        lang : "ko_KR",				// 언어설정
        placeholder: "",				
        disableDragAndDrop: true,
        toolbar: [
            ['style', ['style']],							// ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
            ['font', ['bold', 'italic', 'underline']],  	// + , 'clear'
            //['fontface', ['fontname']],
            ['textsize', ['fontsize']],
            ['color', ['color']],
            ['height', ['height']],
            ['alignment', ['ul', 'paragraph', 'lineheight', 'hr']], 	// + 'ol' 
            ['table', ['table']],
            ['insert', ['picture']],						// + 'link',
            ['view', ['fullscreen', 'codeview']],			// + 'help'
            ['Misc',['undo','redo']]
        ]
  });
}

/*
 # window.load = function(){} vs $(document).ready(function() {})
	
	1. $(window).load()

		- DOM의 standard 이벤트
		- html의 로딩이 끝난 후에 시작
		- 화면에 필요한 모든 요소(css, js, image, iframe etc..)들이 웹 브라우저 메모리에 모두 올려진 다음에 실행됨
		- 화면이 모두 그려진 다음의 메세징이나 이미지가 관련 요소가 모두 올려진 다음의 애니메이션에 적합함
		- 전체 페이지의 모든 외부 리소스와 이미지가 브러우저에 불려운 이후 작동하게 되어 이미지가 안뜨너가 딜레이가 생길 때에는 그만큼의 시간을 기다려야 함
		- 외부 링크나 파일 인크루트시 그 안에 window.load 스크립트가 있으면 둘 중 하나만 적용됨
		- body onload 이벤트와 같이 body에서 onload 이벤트를 쓰게 되면 모든 window.load() 가 실행되지 않는 현상이 발생함

	2. $(document).ready(function() {});	==		document.addEventListener("DOMContentLoaded", function(){}
	
   		- 외부 리소스. 이미지와는 상관 없이 브라우저가 DOM (document object model) 트리를 생성한 직후 실행
   		- window.load() 보다 더 빠르게 실행되고 중복 사용하여 실행해도 선언한 순서대로 실행됨

		출처: https://diaryofgreen.tistory.com/96 [vida valiente]
*/

/* 추가 : DOMContentLoaded 이벤트는 초기 HTML 문서를 완전히 불러오고 분석했을 때 발생합니다. 스타일 시트, 이미지, 하위 프레임의 로딩은 기다리지 않습니다. */


//1. $(document).ready() 로딩 1순위
document.addEventListener("DOMContentLoaded", function(){	//  == $(document).ready(function() {}
	createSummernote()
});

//2. $(document).ready() 로딩 2순위
document.addEventListener("DOMContentLoaded", function(){
	urlToBase64();
	//let img = document.getElementsByClassName("input_img")[0]
	//console.log(img.getAttribute('data-filename'))
});



//================================================================[이미지 url을  base64로 변환]=========================================================================

// 실제 실행되는 함수
function urlToBase64(){
	let img = document.getElementsByClassName("input_img")
	for(var i = 0; i<img.length; i++){
		console.log(img[i].src)
		let target = img[i]
		toDataURL(target, target.src, setImgFunc)
	}
}

//비동기 응답 결과에 사용되는 콜백에 사용될 함수 >> 기존 이미지에 base64를 적용
function setImgFunc(imgTag, base64String){
	imgTag.src = base64String;
}

// 자바스크립트 비동기 : 이미지 url을 blob
function toDataURL(target, targetUrl, callback) {
	const xhr = new XMLHttpRequest();	
	xhr.onload = function() {
		if (xhr.status === 200 || xhr.status === 201) {
			const reader = new FileReader();		// 파일을 읽어오는 reader객체 생성, *프로필 사진 미리보기 참고
			
			// 'blob'타입의 결과 받아오기 : xhr.response		// >>> XHR2(XMLHttpRequest Level 2)부터 지원되며 url의 응답 내용을 Blob으로 다운로드 하는 방법을 지원한다(내부적으로 작동, 브라우저마다 지원여부가 다르다)
			/* 1. 읽어달라고 요청 */
			reader.readAsDataURL(xhr.response);		// #1 비동기 결과를 xhr.response통해 'blob'타입의 데이터를 받아온다
		    										// .readAsDataURL(blob); >> blob : 읽고자 하는 Blob 또는 File.을 읽어서 
													// 읽기가 완료 됬을때 base64코드를 .result 통해 가져올수 있다.
			
			/* 2. reader.onload(읽는동안 해줘)  >>  3. reader.onloadend(읽는게 끝나면 해줘)  */
			reader.onloadend = function() {
				let base64String = reader.result	// #2 reader.result는 #1에서 실행된 메서드가 데이터를 다 읽고 종료되면 base64 반환함.
				callback(target, base64String);			
		    }
			
		} else {
			 console.error(xhr.responseText);
		}
	};
	// 전송방식과, 전송할url 설정하고 스트림 열기
 	xhr.open('GET', targetUrl);	// **중요 open은 보내는 스트림을 여는것이다. 항상 스트림을 연후에  응답 받는 타입[responseType], 헤더 설정[setRequestHeader('Content-type', 'application/json');]등을 해야합니다.
 	// 응답받는 데이터 타입설정 
	xhr.responseType = 'blob';	// Blob 객체는 파일류의 불변하는 미가공 데이터를 나타냅니다.(텍스트와 이진 데이터의 형태)
	// 전송
	xhr.send();
}
/*
 * # XMLHttpRequest 참고 : 
 * https://developer.mozilla.org/ko/docs/Web/API/XMLHttpRequest
 * https://kamang-it.tistory.com/entry/RESTfulajaxajax%EB%9E%80-XMLHttpRequest%EC%82%AC%EC%9A%A9%EB%B2%95-1?category=693873
 * 
 * 
 * # bolb 수신 참고 :
 * https://heropy.blog/2019/02/28/blob/
 */


//=============================================================================================================================================================================


//==========================================[글 수정하기 클릭시 발생 이벤트]============================================================================================================

var cnt = 0; 	// 코드 재실행 카운트 3번 호출되면 종료 >> 알림창 
function AjaxFileUpdate(){	// fileList 파일 배열
	//2) 호출
	var content = document.getElementsByClassName("note-editable")[0]			// img를 가지고 있는 content(부모태그)
	var imgArr = content.getElementsByTagName("img"); 							// content에서 img태그를 배열로 가져온다.
	
	console.log("2) AjaxFileUpdate >>>>> imgArr : " + imgArr[0])
	//3) 가공
	if(imgArr.length > 0){														// img가 있을경우
		var fileList = new Array()				
		for(var i = 0; i<imgArr.length; i++){
			let filename = imgArr[i].getAttribute('data-filename')
			// dataURLtoFile(base64 문자열, 이름.확장자) : base64 >> new File() 변환
			let file = dataURLtoFile(imgArr[i].src, + i +"_" + filename)
			fileList[i] = file
		}																		// base64로 되어있는 img를 file객체로 만들어 배열에 담는다. 
		console.log("3) AjaxFileUpdate >>>>> date : " + fileList);
		
		//4) input
		let formDate = new FormData();											// 자바스크립트에서 제공하는 전송용 객체에 만들기		
		
		for(let i = 0; i<fileList.length; i++){									// 전송용객체에 데이터를 담는다.
			formDate.append("fileArr", fileList[i]);	
		}
		let boardNo = document.getElementById("boardNo").value
		formDate.append("boardNo", boardNo);	// 업데이트를 위해 pk를 추가
		
		console.log("4) AjaxFileUpdate >>>>> input : " + formDate);
		
		$.ajax({
			url : "AjaxFileUpdate.do",
			type : "POST",
			data : formDate,
			enctype: 'multipart/form-data',
			cache : false,
			contentType : false,				// 서버에 데이터를 보낼 때 사용되는 내용 유형
			processData : false,				// DOMDocument 또는 처리되지 않은 데이터 파일을 보내려면 false
			timeout: 10000,						// 10000만큼 지연시 에러 발생
			success : function(output) {
				//5) output
				console.log("5) AjaxFileUpdate >>>>>> output : " + output)
				if(output.msg === "success"){
					console.log("이미지 수정 성공")
					
					if(boardConfirm()){
						console.log("유효성검사 통과 >>> img.src 제거")
						
						var date = new Date();	
						for(let i = 0; i<imgArr.length; i++){
							// 파일업로드후 결과로 받아온 경로를 img태그에 적용 
							imgArr[i].setAttribute("class", "input_img")
							imgArr[i].setAttribute('src', output.imgSrcArr[i] + "?" + date.getTime())
							
							if(i == imgArr.length-1){
								// 문제 해결 코드 : 썸머노트의 캐시를 피하기위해 썸머노트의 본문 내용을 hidden태그의 value 넣어서 submit 실행
								let summernoteContent = document.getElementsByClassName("note-editable")[0]
								let content = document.getElementsByName("boardContent")[0]
								content.value = summernoteContent.innerHTML		// div의 내용은 innerHTML을 통해 가져올 수 있다.
							}
						}
						// for문 종료 >> 6) 서브밋 발생
						console.log("6) AjaxFileUpdate >>>>>> submit")
						document.getElementById("submit").click()
					}
				} else {
					console.log("이미지 수정 실패")
					if(cnt < 3){
						cnt++
						console.log("재실행) AjaxFileUpdate >>>>>> cnt : " + cnt)
						AjaxFileUpload()
					} else {
						alert("서비스 문제로 저장 실패 관리자에게 요청하세요.")
					}
				}
			},
			// ajax에러 서버 통신후 발생시 응답 코드
			error : function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	            var err=JSON.parse(request.responseText);
	            
	            alert(err.resData[0].errorMsg);
			},
			fail : function(){
				alert("서버 통신 실패")
			}
		});
	// 본문에 이미지 없음
	} else {
		console.log("이미지 없음 >> 유효성 검사 실행")
		// 유효성검사
		if(boardConfirm()){
			let summernoteContent = document.getElementsByClassName("note-editable")[0]
			let content = document.getElementsByName("boardContent")[0]
			content.value = summernoteContent.innerHTML
			document.getElementById("submit").click()
		}
	}
}

//이미지 변환 : base64 >> new File(데이터, 이름 , 타입)
const dataURLtoFile = (dataurl, fileName) => {
    var arr = dataurl.split(',') 
    var	mime = arr[0].match(/:(.*?);/)[1]   // base64에서 미디어 타입을 가져온다. 
    var fileType = mime.split("/")[1]		// 확장자 가져오기
    var	bstr = atob(arr[1]) 				// 미디어 타입이 제거된 base64코드를 가져와서 16진수 byte코드로 변환한다.
    										// atob("ASCII to binary") base64로 인코딩 된 문자열을 디코딩 : base64문자열(==16진수 byte) >> 8진수 byte
        									// btoa("binary to ASCII") 이진 데이터의 "문자열"을 base64로 인코딩된  "ASCII문자열"을 만든다 : byte문자열 >> base64문자열
    var	n = bstr.length 
    var u8arr = new Uint8Array(n)			// Uint8Array 해당 어레이는 8 비트 부호없는 정수의 배열을 나타낸다. 
    
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);		// 16진수를 담은 배열.charCodeAt(index) : 배열에서 주어진 index에 대한 UTF-16 코드를 나타내는 0부터 65535 사이의 정수를 반환
    }
    return new File([u8arr], fileName, {type:mime});
}

// 유효성 검사
function boardConfirm(){
	console.log("유효성검사!!")
	var title = document.getElementById("title").value
	var content = document.getElementById('summernote').value
	
	if(title == "" || title == undefined){
		console.log("title 유효성 검사 실행")
		alert("제목을 입력하세요")
		return false
	} else if(content == "" || content == undefined) {
		console.log("content 유효성 검사 실행")
		alert("글 내용을 입력사세요")
		return false
	} else {
		return true
	}
}

//============================================================================================================================================================================

//file태그 클릭시 confirm
function warning(){
	var text = "첨부파일 추가시 기존 첨부파일은 삭제 됩니다. \n첨부파일 추가를 하시겠습니까?";
	if(confirm(text)){return true}
	else{return false}
}
















// HTMLCanvasElement를 사용해서 img url을 base64로 새로그리는 방법도 있음
function toDataURL_CANVAS(src, callback, outputFormat) {
	  var img = new Image();
	  img.crossOrigin = 'Anonymous';
	  img.onload = function() {
	    var canvas = document.createElement('CANVAS');
	    var ctx = canvas.getContext('2d');
	    var dataURL;
	    canvas.height = this.naturalHeight;
	    canvas.width = this.naturalWidth;
	    ctx.drawImage(this, 0, 0);
	    dataURL = canvas.toDataURL(outputFormat);
	    callback(dataURL);
	  };
	  img.src = src;
	  if (img.complete || img.complete === undefined) {
	    img.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
	    img.src = src;
	  }
}


