
/*
섬머노트는 
이미지 업로드시 
크게 2가지 방법으로 사용이 가능하다
1. 이미지를 base64코드로 변환하여 내용에 바로 적용
	>>> 바디의 내용이 너무나 커짐 
	>>> 이미지 편집등에 사용이 힘들어진다.

2. 썸머노트에서 제공하는 callback() : {onImageUpload : function(files) {}을 사용
	1) 파일을 서버로 보내어 저장하고 경로를 결과로 받아온다. 
	2) 받아온 경로를 썸머노트 내용에 적용한다.
	>>> 서버 DB커넥션이 이미지 개수만큼 발생한다.
	>>> 게시판 테이블에 이미지를 직접 넣으면 안될거 같음(글을 작성하다 취소하는 경우 때문에)
*/


// 부트스트랩 로드
$(document).ready(function() {
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
          ],
//          callbacks: {					// 이미지가 추가될때마다 처리하는 콜백 함수  	>> ajax >> 저장 >> url리턴 >> 적용
//				onImageUpload : function(files) {
//					for(var i = files.length -1; i >= 0; i--){
//						console.log("1")
//						uploadSummernoteImageFile(files[i],this);
//					}
//					
//				}
//          }
    });
 });


/**
* 부트트스랩과 사용할 이미지 파일 업로드
*/
function uploadSummernoteImageFile(file, editor) {
	/**
	 # new FormData()
	 >>> 데이터를 담는 객체 :
	 	1) .append(key, value)를 사용하여 key/value 형태로 데이터를 넣을 수 있다.
	 	2) 담아진 입력 내용을 인코딩한다.
	 */
	console.log("2")
	var img_data = new FormData();		
	img_data.append("file", file);
	$.ajax({
		url : "imgUplod.do",
		type : "POST",
		data : img_data,
		enctype: 'multipart/form-data',
		cache: false,
		contentType : false,
		processData : false,
		success : function(output) {
			console.log("3 : " + output)
        	//항상 업로드된 파일의 url이 있어야 한다.
			$(editor).summernote('insertImage', output);
		}
	});
}


// base64 >> new File()
const dataURLtoFile = (dataurl, fileName) => {
    var arr = dataurl.split(',') 
    var	mime = arr[0].match(/:(.*?);/)[1]   // base64에서 미디어 타입을 가져온다. 
    var	bstr = atob(arr[1]) 				// 미디어 타입이 제거된 base64코드를 가져와서 16진수 byte코드로 변환한다.
    										// atob("ASCII to binary") base64로 인코딩 된 문자열을 디코딩 : base64문자열 >> 16진수 byte
        									// btoa("binary to ASCII") 이진 데이터의 "문자열"을 base64로 인코딩된  "ASCII문자열"을 만든다 : byte문자열 >> base64문자열
    var	n = bstr.length 
    var u8arr = new Uint8Array(n)			// Uint8Array 해당 어레이는 8 비트 부호없는 정수의 배열을 나타낸다. 
    
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);		// 16진수를 담은 배열.charCodeAt(index) : 배열에서 주어진 index에 대한 UTF-16 코드를 나타내는 0부터 65535 사이의 정수를 반환
    }
    
    return new File([u8arr], fileName, {type:mime});
    /*
      	js의 File객채 생성자 
      	new File(bits, name[, options]);
      	
      	=> bits : 데이터 (배열에 넣어서 전달)
      	   name : 파일명이나 파일의 경로를 나타내는 USVString.
      	   options : {type:"미디어 타입"}  
      	
      	ex)
      	new File(["foo"], "foo.txt", {type:"text/plain"})
     */
}


// file배열 객체 비동기 전송
function AjaxFileUpload(fileList){	// fileList 파일 배열
	
	let board_seq = 0;
	
	// 파일 전송용 객체 FormData() 생성
	let fileDate = new FormData();		
	// 객체 담기
	for(let i = 0; i<fileList.length; i++){
		fileDate.append("fileArr", fileList[i]);
	}
	
	
	$.ajax({
		url : "AjaxFileUplod.do",
		type : "POST",
		data : fileDate,
		enctype: 'multipart/form-data',
		cache: false,
		contentType : false,			// 서버에 데이터를 보낼 때 사용되는 내용 유형
		processData : false,			// DOMDocument 또는 처리되지 않은 데이터 파일을 보내려면 false
		success : function(output) {
        	
			if(output.msg === "success"){
				console.log("이미지 저장 성공")
				board_seq = output.board_seq
				return board_seq;
			} else {
				console.log("이미지 저장 실패")
				return board_seq;			// 0 == false
			}
		},
		fail : function(){
			alert("서버 통신 실패")
		}
	});
}


// onsubmit
function validateForm(){
	var content = document.getElementsByClassName("note-editable")[0]
	var imgArr = content.getElementsByTagName("img"); 
	
	console.log(imgArr)
	
	if(imgArr.length > 0){
// #2 
	// 1. file을 배열로 만들어 객체를 비동기로 서버에 전달하여 업로드 실행 
	// 2. 업로드가 정상으로 실행됬을 경우 summernote의 바디의 img.src를 전부 날린다.
	// 3. 서브밋 동작 
	var fileList = new Array()
	for(var i = 0; i<imgArr.length; i++){
		let file = dataURLtoFile(imgArr[i].src, i + "_img.png")
		console.log(file)
		fileList[i] = file
	}
	console.log(fileList)
	
	// 파일 업로드 실행 
	var fileUploading = AjaxFileUpload(fileList)
	
	//성공시 썸머노트 img src 삭제
	if(fileUploading){
		for(var i = 0; i<imgArr.length; i++){
			imgArr[i].src = ""
			if(i == imgArr.length-1){
				return true					// 서브밋 정상 실행
			}
		}
	}else{
		alert("저장 실패 재시도 해주세요")
		return false;
	}
	
	alert("저장 실패 재시도 해주세요")
	return false;
	
	} else {
		return true
	}
}





//onsubmit
function validateForm_fail(){
	var content = document.getElementsByClassName("note-editable")[0]
	var imgArr = content.getElementsByTagName("img"); 
// #1 >> 실패
	//1. form 태그에 input file 태그를 만들고, multiple와  name 속성 부어한다
	//2. 해당 태그의 files 속성을 가져온다
	//3. files.push(파일객체)로 파일 객체 추가 
	//4. 서브밋
	
//	var form = document.getElementById("form");
//	
//	var fileTag = document.createElement("input")
//	fileTag.setAttribute("type", "file")
//	fileTag.setAttribute("name", "")
//	fileTag.setAttribute("id", "imgFile")
//	fileTag.setAttribute("multiple", "multiple")
//	
//	var div = document.createElement("div")
//	div.style.display = "none";
//	
//	form.appendChild(div)
//	div.appendChild(fileTag)
//	
//	var imgFileList = document.getElementById("imgFile").files;
//	console.log(imgFileList)
//	for(var i = 0; i<imgArr.length; i++){
//		let file = dataURLtoFile(imgArr[i].src, i + "_img.png")
//		imgFileList.push(file)			// 보안상 파일 리스트에 파일을 넣는것은 지원되지 않음 
//										// 드래그인 드랍은 DataTransfer라는 것을 사용해서 추가할수 있음
//	}
	return false;
}
