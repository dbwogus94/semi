/**
* 부트트스랩에서 사용할 이미지 파일 업로드
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
			console.log("3 : " + output.img)
        	//항상 업로드된 파일의 url이 있어야 한다.
			//editor.insertImage(welEditable, "");
			$(editor).summernote('insertImage', "/update/resources/img/board/gym.png");
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
//				onImageUpload : function(files, editor) {
//					for(var i = files.length -1; i >= 0; i--){
//						console.log("1")
//						uploadSummernoteImageFile(files[i], editor);
//					}
//					
//				}
//          }
    });
 });




//=======================================================================================================================================

/*
 @ summernote에서는 글내용에 이미지를 포함 시키기 위해 2가지 방법을 지원한다. 
   1. 디폴트 : 추가한 이미지를 base64코드로 변환하여 img태그의 src에 적용
   2. callback() : 이미지 추가시  file객체를 비동기로 서버에 전송 이미지를 업로드후 업로드 완료된 경로를 콜백으로 전달받아 적용한다.  
  
  >>> 위 의 두가지 방법 모두 불편함과 문제가 존재하여 조금 복잡하지만 나름의 방법으로 코드를 구성하였음

  # ES5에서 ajax를 구현한 함수를 호출하고 리턴값을 다음코드에 사용하는 동기식으로 코드를 구현 할 수는 없는것 같다
         그렇기 때문에 ajax안에서 결과를 받고 다음 실행할 동작을 구현한 함수를 호출하여 사용하게 코드를 구현하였음

  	
  # 코드 기본 로직 설명
  		1) 추가(base64) : summernote의 디폴트 업로드 방식으로 사용자의 이미지를 본문에 추가합니다.
  		2) 호출 : 사용자가 글을 작성 후 저장하기 클릭, 서버로 전송전에 전처리를 합니다.
  		3) 가공 : 본문에 추가된 img태그의 src에서 base64코드를 받아와 file객체들로 변환합니다.
  		4) input : 변환된 파일객체를 비동기로 서버에 전송하여 이미지 업로드를 실행하고 업로드된 경로들을 결과로 받아옵니다.
  		5) output : 결과로 받아온 이미지 경로를 본문의 img태그에 src넣어 줍니다.
  		6) 이벤트 확산 : 마지막으로 submit를 실행하여 form태그의 테이터를 서버로 전송 저장합니다. 
  		

 # 문제 발생 : img태그의 업로드 후 경로로 값을 변경 했음에도 base64코드로 서버에 전송되어 DB에 저장되었음
 	
 	1차 삽질. onsubmit를 사용하여 구현
 	   	# 문제 발생(img태그의 업로드 후 경로로 값을 변경 했음에도 base64코드로 서버에 전송되어 저장되었음) 
 	
 	2차 삽질. 2차시도 html의 캐시를 제거
 	    # 실패
 	    
 	3차 삽질. onsubmit발생 하면서 기존 form태그를 데이터를 가지고 있나 싶어서 onsubmit를 제거
 		# 실패 >>> onsubmit과 상관없음
 	
 	4차 삽질. src의 쿼리스트링에 버전을 부여하여 값을 캐시를 회피
 		# 실패 >>> 이후 부터 썸머노트가 캐시을 하고 있다는 가정을 하게되었음.
 				 + console.log()나 Html을 확인했을때는 변경된 src를 가지고 있음
 		
 	5차 성공. 다른 input 태그의 값으로 전달
 		#성공 >>> img의 src 확인하면  변경된 데이터를 가지고 있음 그래서 
 				submit를 발생전에  summernote의 내용을 input:hidden태그의 value로 보내어 form태그의 데이터에 포함하여 서버에 보내면 될 것이라 가정하였음
  
 */
var cnt = 0; 	// 코드 재실행 카운트 3번 호출되면 종료 >> 알림창 
function AjaxFileUpload(){	// fileList 파일 배열
	//2) 호출
	var content = document.getElementsByClassName("note-editable")[0]			// img를 가지고 있는 content(부모태그)
	var imgArr = content.getElementsByTagName("img"); 							// content에서 img태그를 배열로 가져온다.
	
	console.log("2) AjaxFileUpload >>>>> imgArr : " + imgArr[0])
	//3) 가공
	if(imgArr.length > 0){														// img가 있을경우
		var fileList = new Array()				
		for(var i = 0; i<imgArr.length; i++){
			let filename = imgArr[i].getAttribute('data-filename')
			// dataURLtoFile(base64 문자열, 이름.확장자) : base64 >> new File() 변환
			let file = dataURLtoFile(imgArr[i].src, + i +"_" + filename)
			fileList[i] = file
		}																		// base64로 되어있는 img를 file객체로 만들어 배열에 담는다. 
		console.log("3) AjaxFileUpload >>>>> date : " + fileList);
		
		//4) input
		let fileDate = new FormData();											// 자바스크립트에서 제공하는 전송용 객체에 만들기		
		
		for(let i = 0; i<fileList.length; i++){									// 전송용객체에 데이터를 담는다.
			// 같은이름으로 보내면 서버에서 배열로 받을 수 있다
			fileDate.append("fileArr", fileList[i]);	
		}
		
		console.log("4) AjaxFileUpload >>>>> input : " + fileDate);
		
		$.ajax({
			url : "AjaxFileUplod.do",
			type : "POST",
			data : fileDate,
			enctype: 'multipart/form-data',
			cache : false,
			contentType : false,				// 서버에 데이터를 보낼 때 사용되는 내용 유형
			processData : false,				// DOMDocument 또는 처리되지 않은 데이터 파일을 보내려면 false
			success : function(output) {	
				//5) output
				let boardNo = output.boardNo
				console.log("5) AjaxFileUpload >>>>>> output : " + output)
				if(output.msg === "success"){
					console.log("이미지 저장 성공")
					
					if(boardConfirm()){
						console.log("유효성검사 통과 >>> img.src 제거")
						
						// 이미지 업로드 성공시 받아온 boardNo를 view에 적용
						document.getElementById("boardNo").value = boardNo
						
						var date = new Date();	
						for(let i = 0; i<imgArr.length; i++){
							imgArr[i].setAttribute("class", "input_img")
							// 파일업로드후 결과로 받아온 경로를 img태그에 적용 
							imgArr[i].setAttribute('src', output.imgSrcArr[i] + "?" + date.getTime())
							
							if(i == imgArr.length-1){
								// 문제 해결 코드 : 썸머노트의 캐시를 피하기위해 썸머노트의 본문 내용을 hidden태그의 value 넣어서 submit 실행
								let summernoteContent = document.getElementsByClassName("note-editable")[0]
								let content = document.getElementsByName("boardContent")[0]
								content.value = summernoteContent.innerHTML		// div의 내용은 innerHTML을 통해 가져올 수 있다.
							}
						}
						// for문 종료 >> 6) 서브밋 발생
						console.log("6) AjaxFileUpload >>>>>> submit")
						document.getElementById("submit").click()
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
