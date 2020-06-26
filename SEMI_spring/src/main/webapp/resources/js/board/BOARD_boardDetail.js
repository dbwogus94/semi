function authorityChk(id, boardId, boardNo){
	if(id === boardId){
		location.href='update.do?boardNo=' + boardNo
	} else {
		alert("자신이 쓴 글만 수정할 수 있습니다.")
	}
} 

// 글 삭제
function deleteOne(loginId, boardId ,boardNo){
	
	if(loginId === boardId){
		warningMsg("","","location.href='deleteOne.do?boardNo="+boardNo + "'")
	} else {
		alert("자신이 쓴 글만 삭제할 수 있습니다.")
	}
	
	
}

// #경고 모달 모듈 >>> 파라미터 (타이틀, 내용, 확인에 사용할 함수명()(문자열))
function warningMsg(title_msg, msg, confirmFunc){
	// 기본값
	title_msg = title_msg || " 경고!";
	msg = msg || "글을 삭제 하시겠습니까?</br> 주의! : 삭제된 글은 복구하지 못합니다.";
	
	let btn = document.getElementById("warningMsg-yes-btn")
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
	let modal = document.getElementById("warningMsg")
	let title = modal.getElementsByClassName("modal-title")[0]
	title.classList.add("glyphicon")		// 부트스트랩에서 제공하는
	title.classList.add("glyphicon-alert")	// 경고표시 icon
	title.innerHTML = title_msg
	let body = modal.getElementsByClassName("modal-body")[0]
	body.innerHTML = msg 

	$('#warningMsg').modal('show');
}



/*=============================================[파일 다운로드 시작]=======================================================*/

//모달 팝업에 파일 표시
function fileDetail(boardNo){
	let modal_body = document.getElementsByClassName("modal-body")[0]
	modal_body.innerHTML =""
	$.ajax({
		url : "fileDetail.do",
		type : "POST",
		data : {boardNo : boardNo},
		dataType : "json",	// 디폴트
		success : function(output) {
			if(output.msg == "success"){
				let fileName = output.fileName
				let modal_body = document.getElementsByClassName("modal-body")[0]
				
				if(typeof fileName == 'string'){
					createCheckbox(fileName)
				} else if(typeof fileName == 'object'){
					let nameArr = output.fileName;
					for(let i = 0; i<nameArr.length ;i++ ){
							createCheckbox(fileName[i])
					}
				}
				$('#boardModal').modal('show');
			} else {
				alert("첨부된 파일이 없습니다.")
			}
		},
		fail : function(){
			alert("서버 연결 실패")
		}
	})
}

//모달팝업에 파일명과 체크박스 생성
function createCheckbox(text){
	let modal_body = document.getElementsByClassName("modal-body")[0]
	let div = document.createElement("div")
	div.setAttribute("class", "checkbox")
	 
	let label = document.createElement("label")
	 
	let input = document.createElement("input")
	input.setAttribute("type","checkbox")
	input.setAttribute("name","file")
	input.setAttribute("value", text)		// 파일명
	 
	modal_body.appendChild(div)
	div.appendChild(label)
	label.appendChild(input)
	
	label.innerHTML += text
}


/* 
* @ 파일 다운로드 관하여 

# 파일 다운요청은 ajax가 안된다. (굳이 하자면 가능은 하다)
		서버에 파일 다운을 요청하면 서버에서 파일 데이터와 함께 파일다운용으로 응답을 날린다. 
		그렇게 되면 응답에 따른 프롬프트로 이동해야 하기 때문에 AJAX 요청에서는 다운로드 프롬프트를 열 수 없다. 

# ajax로 굳이 하고 싶다면
		대신 ajax의 성공 함수에  window.location = "다운로드 url"로 서버에 요청을 날린다
		그러면 다운로드 프롬프트가 열리지 만 현재 페이지는 변경되지 않기때문에 가능하다.

	결론은  window.location나 form태그를 사용하고 AJAX 요청을 완전히 피하는 것이 좋다.
	
# 여러개의 파일을 한번에 다운이 가능한가?
	결론부터 말하면 한번의 요청에 여러 파일을 다운받는것은 불가능하다.
	그렇다면?
	다운받을 파일의 많큼 서버에 다운 요청을 하면된다.
	이때 재귀 함수와 setTimeout()함수를 활용해서 하는것이 효율적이다. 

*/

//성공 코드 : 재귀함수를 사용한 다중파일 다운로드 요청
var index = 0; 
function fileDownChk(){
	let checkbox = document.getElementsByName("file")
	let boardNo = document.getElementById("boardNo").value
	if(checkbox[index].checked){
		let fileName = checkbox[index].value
		let path = "fileDown.do"
		let params = {fileName:fileName, boardNo:boardNo}
		postSend(path, params, "post")
	}
	
	if(index >= checkbox.length-1){
		console.log("초기화: " + index)
		index = 0;
		return false
	} else {
		setTimeout(function(){
			console.log("재귀 실행: " + index)
			index++
			fileDownChk()
		}, 1000)
	}
}


//#form태그 생성 전송 모듈 >>> 파라미터 (경로, 배열 or json, 전송방식, 배열의경우 서버에서 받을 이름) 
function postSend(path, params, method, ArrName) {
	method = method || "post";		// 파라미터로 들어온 값이 없으면 디폴트값
	
	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);
	
	// 히든으로 값을 넣는다.
	if(Array.isArray(params)){
		// Array
		ArrName = ArrName || "arr";		// 파라미터로 배열이 들어온 경우 디폴트 name 
		for(let i = 0; i<params.length; i++){
			let input_tag = document.createElement("input");
			input_tag.setAttribute("type", "hidden");
			input_tag.setAttribute("name", ArrName) 
			input_tag.setAttribute("value", params[i])
			
			form.appendChild(input_tag);
		}
	} else {
		// json
		for (let key in params) { // {'name1':'var1','name2':'var2','name3':'var3'}
			let input_tag = document.createElement("input");
			input_tag.setAttribute("type", "hidden");
			input_tag.setAttribute("name", key) // name1, name2, name3
			input_tag.setAttribute("value", params[key]) // var1, var2, var3

			form.appendChild(input_tag);
		}
	}
	document.body.appendChild(form);
	form.submit();
}
/*=============================================[파일 다운로드 끝]=======================================================*/






