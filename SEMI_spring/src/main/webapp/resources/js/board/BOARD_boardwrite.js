
/**
* 부트트스랩에서 사용할 이미지 파일 업로드
*/
function uploadSummernoteImageFile(file, editor, welEditable) {
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
			console.log("3 : " + output.img)
        	//항상 업로드된 파일의 url이 있어야 한다.
			editor.insertImage(welEditable, "/update/resources/img/board/img/1994dbwogus/2020/05/18/s/s_cb191bf3-b22a-419f-aa34-e72e3d238ac3_js.png");
		}
	});
}

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
//				onImageUpload : function(files, editor, welEditable) {
//					for(var i = files.length -1; i >= 0; i--){
//						console.log("1")
//						uploadSummernoteImageFile(files[i], editor, welEditable);
//					}
//					
//				}
//          }
    });
 });




//=======================================================================================================================================

// file배열 객체 비동기 전송 == onsubmit
/*
	# ES5에서 ajax를 구현한 함수를 호출하고 리턴값을 다음코드에 사용하는 동기식으로 코드를 구현 할 수는 없는것 같다
	    그렇기 때문에 ajax안에서 결과를 받고 다음 실행할 동작을 구현한 함수를 호출하여 사용하게 코드를 구현하였음
	 
	공통로직 : 
	 	1) form태그에서 submit를 통작 하였을때 onsubmit에 적용된 함수를 먼저 실행
	 	2) base64로 된 img를 file객체로 만들어 서버에 전송하여 저장한다.
	 	3) 그 후 subbit를 정상 동작시켜서 게시판 내용을 서버에 저장 저장한다.
	     
	    
	1. 원래 구상한 코드 구성
		1) onsumbit을 동작하여 썸머노트의 base64를 file객체로 변환한다.
		2) (ajax)파일 배열을 인자로 받는 비동기 파일업로드 함수를 실행하고 성공시 결과를 리턴시킨다./ 실패코드가 왔을시 재실행 시킨다.(일정시간이상 실패시 정지)
		3) 위의 리턴 결과가 성공시 유효성검사를 실시한다 통과시 썸머노트의 img.src를 제거한다 / 실패시에 실패를 알리고 재실행을 유도한다.
		4) 모두 정상 통과시 서브밋을 정상 동작시켜 서버에 form의 데이터를 전달한다.
	 	
	2. 변환한 코드 구성
	 	1) 호출 : onsumbit의 이벤트로 ajax를 구현한 함수를 호출한다.
	 	2) 가공 : (ajax)ajax를 구현한 함수에서 썸머노트의 base64를 file객체로 변환한다.
	 	3) input : (ajax)변환한 파일배열을 서버에 전달한다. 그 후 서버에서  결과를 받아온다. 
	 	4) output : (ajax)결과에 따라서 서버에서 성공코드가 왔다면 게시판 유효성 검사를 실행하고 통과시 썸머노트의 img.src를 제거한다. / 해당 함수를 실패시 재실행 시킨다.(일정시간이상 실패시 정지)
	 	5) 이벤트 확산 : 모두 정상 통과시 서브밋을 정상 동작시켜 서버에 form의 데이터를 전달한다.
	
	3. 변환 코드
	
  
 */
// 재실행 카운트
var cnt = 0; 
function AjaxFileUpload(){	// fileList 파일 배열
	//1) 호출
	var content = document.getElementsByClassName("note-editable")[0]		// img를 가지고 있는 content(부모태그)
	var imgArr = content.getElementsByTagName("img"); 						// content에서 img태그를 배열로 가져온다.
	
	console.log("1) AjaxFileUpload >>>>> imgArr : " + imgArr[0])
	//2) 가공
	if(imgArr.length > 0){													// img가 있을경우
		var fileList = new Array()				
		for(var i = 0; i<imgArr.length; i++){
			// dataURLtoFile(base64 문자열, 이름.확장자) : base64 >> new File() 변환
			let file = dataURLtoFile(imgArr[i].src, "board_" + i)
			fileList[i] = file
		}																		// base64로 되어있는 img를 file객체로 만들어 배열에 담는다. 
		console.log("2) AjaxFileUpload >>>>> date : " + fileList);
		
		//3) input
		let fileDate = new FormData();											// 자바스크립트에서 제공하는 전송용 객체에 만들기		
		
		for(let i = 0; i<fileList.length; i++){									// 전송용객체에 데이터를 담는다.
			// 같은이름으로 보내면 서버에서 배열로 받을 수 있다
			fileDate.append("fileArr", fileList[i]);	
		}
		
		console.log("3) AjaxFileUpload >>>>> input : " + fileDate);
		
		$.ajax({
			url : "AjaxFileUplod.do",
			type : "POST",
			data : fileDate,
			enctype: 'multipart/form-data',
			cache : false,
			contentType : false,				// 서버에 데이터를 보낼 때 사용되는 내용 유형
			processData : false,				// DOMDocument 또는 처리되지 않은 데이터 파일을 보내려면 false
			success : function(output) {	
				//4) output
				let boardNo = output.boardNo
				console.log("4) AjaxFileUpload >>>>>> output : " + output)
				if(output.msg === "success"){
					console.log("이미지 저장 성공")
					
					if(boardConfirm()){
						console.log("유효성검사 통과 >>> img.src 제거")
						// 이미지 업로드 성공시 받아온 boardNo를 view에 적용
						document.getElementById("boardNo").value = boardNo
						
						var date = new Date();
						for(let i = 0; i<imgArr.length; i++){
							
							// 이미지의 상위 부모 태그 가져오기 > p태그
							let parent = imgArr[i].parentNode 
							// 부모태그 하위 첫번째 자식 노드 삭제
							parent.removeChild(parent.firstChild)
							// 새로운 img 생성
							let newImg = document.createElement("img")
							newImg.setAttribute('src', output.imgArr[i] + "?" + date.getTime())
							parent.appendChild(newImg)
							
							//imgArr[i].setAttribute('src', output.imgArr[i] + "?" + date.getTime())
							
							if(i == imgArr.length-1){
								//5) 서브밋 발생
								console.log("5) AjaxFileUpload >>>>>> submit")
								document.getElementById("submit").click()
								
							}
						}
					}
				} else {
					console.log("이미지 저장 실패")
					if(cnt < 3){
						cnt++
						console.log("재실행) AjaxFileUpload >>>>>> cnt : " + cnt)
						AjaxFileUpload()
					} else {
						alert("서비스 문제로 저장 실패 관리자에게 요청하세요.")
					}
				}
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
			document.getElementById("submit").click()
		}
	}
}


//이미지 변환 : base64 >> new File()
const dataURLtoFile = (dataurl, fileName) => {
    var arr = dataurl.split(',') 
    var	mime = arr[0].match(/:(.*?);/)[1]   // base64에서 미디어 타입을 가져온다. 
    var fileType = mime.split("/")[1]		// 확장자 가져오기
    var	bstr = atob(arr[1]) 				// 미디어 타입이 제거된 base64코드를 가져와서 16진수 byte코드로 변환한다.
    										// atob("ASCII to binary") base64로 인코딩 된 문자열을 디코딩 : base64문자열 >> 16진수 byte
        									// btoa("binary to ASCII") 이진 데이터의 "문자열"을 base64로 인코딩된  "ASCII문자열"을 만든다 : byte문자열 >> base64문자열
    var	n = bstr.length 
    var u8arr = new Uint8Array(n)			// Uint8Array 해당 어레이는 8 비트 부호없는 정수의 배열을 나타낸다. 
    
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);		// 16진수를 담은 배열.charCodeAt(index) : 배열에서 주어진 index에 대한 UTF-16 코드를 나타내는 0부터 65535 사이의 정수를 반환
    }
    
    return new File([u8arr], fileName + "." + fileType, {type:mime});
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

//========================================================================================================================================



//onsubmit
function validateForm_fail(){
	var content = document.getElementsByClassName("note-editable")[0]
	var imgArr = content.getElementsByTagName("img"); 
// #1 >> 실패
	//1. form 태그에 input file 태그를 만들고, multiple와  name 속성 부어한다
	//2. 해당 태그의 files 속성을 가져온다
	//3. files.push(파일객체)로 파일 객체 추가 	>> // 보안상 파일 리스트에 파일을 넣는것은 지원되지 않음 
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
//		imgFileList.push(file)			
//										// 드래그인 드랍은 DataTransfer라는 것을 사용해서 추가할수 있음
//	}
	return false;
}
