window.onload = function(){
	imgTagDelete()
}

//원하는 위치의 img 태그를 찾아 css로 이미지 제거
function imgTagDelete(){
	var content = document.getElementsByClassName("content")
	
	for(var i = 0; i<content.length; i++){
		var imgTag = content[i].getElementsByTagName("img")
		for(let j = 0; j<imgTag.length; j++){
			imgTag[j].style.display = "none"
		}
		 
	}
}

// img를 포함한 부모태그를 삭제
function imgTagParentDelete(){
	var content = document.getElementsByClassName("content")
	
	// 썸머노트로 작성된 body에서 img를 포함하고 있는 p태그와 자손 삭제
	console.log(content)
	for(let i = 0; i<content.length; i++){
		let pArr = content[i].getElementsByTagName("p")
		for(let j = 0; j<pArr.length; j++){
			// p태그가 img태그를 포함한다면?
			if(pArr[j].getElementsByTagName("img")){
				pArr[j].parentNode.removeChild(pArr[j])		// 부모태그를 찾고 해당 부모태그에서 자식 요소 선택하여 삭제
			}
		}
	}
}

// 페이징에 사용
function paging(pageNum){
	console.log(pageNum)
//	var url = "main.do?currentPage=" + pageNum
//	location.href = url
}

// 글 선택 >> 체크박스 보이기
function deleteChk_show(){
	var btn = document.getElementsByClassName("button")[2]
	btn.value = "선택 취소"
	btn.setAttribute("onclick", "deleteChk_hide()");
	
	// 보이기
	var span = document.getElementsByClassName("deleteSpan")
	for(var i = 0; i<span.length; i++){
		span[i].style.display = "block"
	}
}

// 글 선택 해제, 체크박스 클릭 해제, 숨기기
function deleteChk_hide(){
	var btn = document.getElementsByClassName("button")[2]
	btn.value = "글 선택"
	btn.setAttribute("onclick", "deleteChk_show()");
	
	// 체크박스 체크 제거
	var chk = document.getElementsByClassName("deleteChk");
	for(var i = 0; i<chk.length; i++){
		chk[i].checked = false
	}
	
	// 보이기
	var span = document.getElementsByClassName("deleteSpan")
	for(var i = 0; i<span.length; i++){
		span[i].style.display="none"
	}
}

//삭제 클릭
function deleteBoard() {
	//체크여부 확인
	var chk = document.getElementsByClassName("deleteChk");
	var isChk = false;
	for(var i = 0; i<chk.length; i++){
		if(chk[i].checked){
			isChk = true;
			break;
		}
	}
	if(!isChk){
		warningMsg(" 알림!" , "글을 먼저 선택해 주세요")
	} else {
		warningMsg('','', "deleteSend()")	
		
		/*	# 인자로 함수를 전달할때 주의점	
		 	- deleteSend() 함수에 ()릁 붙여서 전달할경우 함수가 실행된다
		  		그렇기 때문에 실행된 함수의 리턴이 있다면 그 리턴된 값을 msgWarning함수에서 사용이 가능하지만
		  		리턴값이 없은 함수인 경우는 undefined를 인자에 전달한 것과 같다
		  	- 인자로 넣는 함수에 ()를 붙일 경우 실행이된다 즉 실행순서가 바뀐다 
		  		예상하는 실행 순서		1)deleteBoard() 2)deleteSend   3)deleteSend()
		  		실행순서가 바뀌게 된다.	1)deleteBoard() 2)deleteSend() 3)deleteSend() 
		  	- 인자로 전달되는 함수를 html에서 적용하고 싶을때는 함수를 인자에 넣는것이 아닌 함수명을 스크립트로 인자에 전송해야 한다
		*/ 
	}
}


//글 삭제 실행 >> 서버로 전송
function deleteSend(){
	var chk = document.getElementsByClassName("deleteChk");
	var arr = new Array();
	for(var i = 0; i<chk.length; i++){
		if(chk[i].checked){
			arr.push(chk[i].value)
		}
	}
	postSend("multiDelete.do", arr, "post", "boardNoArr")
}


//#경고 모달 모듈 >>> 파라미터 (타이틀, 내용, 확인에 사용할 함수명()(문자열))
function warningMsg(title_msg, msg, confirmFunc){
	// 기본값
	title_msg = title_msg || " 경고!";
	msg = msg || "글을 삭제 하시겠습니까?</br> 주의! : 삭제된 글은 복구하지 못합니다.";
	
	var btn = document.getElementById("warningMsg-yes-btn")
	// 확인 버튼 함수 있을 때
	console.log(confirmFunc)
	if(confirmFunc){
		console.log(1)
		btn.removeAttribute("data-dismiss")
		btn.setAttribute("onclick", confirmFunc)
	} else {
		console.log(2)
		if(btn.getAttribute("onclick")){
			btn.setAttribute("data-dismiss", "modal")
			btn.removeAttribute("onclick")
		}
	}
	// 모달 팝업 내용추가
	var modal = document.getElementById("warningMsg")
	var title = modal.getElementsByClassName("modal-title")[0]
	title.classList.add("glyphicon")		// 부트스트랩에서 제공하는
	title.classList.add("glyphicon-alert")	// 경고표시 icon
	title.innerHTML = title_msg
	var body = modal.getElementsByClassName("modal-body")[0]
	body.innerHTML = msg 

	$('#warningMsg').modal('show');
	//modal.classList.add("in")
	//modal.style="display: block; padding-right: 16px;"
}


// #form태그 생성 전송 모듈 >>> 파라미터 (경로, 배열 or json, 전송방식, 배열의경우 서버에서 받을 이름) 
function postSend(path, params, method, ArrName) {
	method = method || "post";		// 파라미터로 들어온 값이 없으면 디폴트값
	
	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);
	
	// 히든으로 값을 넣는다.
	if(Array.isArray(params)){
		// Array
		ArrName = ArrName || "arr";		// 파라미터로 배열이 들어온 경우 디폴트 name 
		for(var i = 0; i<params.length; i++){
			var input_tag = document.createElement("input");
			input_tag.setAttribute("type", "hidden");
			input_tag.setAttribute("name", ArrName) 
			input_tag.setAttribute("value", params[i])
			
			form.appendChild(input_tag);
		}
	} else {
		// json
		for ( var key in params) { // {'name1':'var1','name2':'var2','name3':'var3'}
			var input_tag = document.createElement("input");
			input_tag.setAttribute("type", "hidden");
			input_tag.setAttribute("name", key) // name1, name2, name3
			input_tag.setAttribute("value", params[key]) // var1, var2, var3

			form.appendChild(input_tag);
		}
	}
	document.body.appendChild(form);
	form.submit();
}






