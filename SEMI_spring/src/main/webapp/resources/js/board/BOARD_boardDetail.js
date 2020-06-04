function authorityChk(id, boardId, boardNo){
	if(id === boardId){
		location.href='update.do?boardNo=' + boardNo
	} else {
		alert("글 수정 권한이 없습니다.")
	}
} 


function postSend(path, params, method) {
	method = method || "post";
	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);

	// 히든으로 값을 넣는다.
	for ( var key in params) { // {'name1':'var1','name2':'var2','name3':'var3'}
		var input_tag = document.createElement("input");
		input_tag.setAttribute("type", "hidden");
		input_tag.setAttribute("name", key) // name1, name2, name3
		input_tag.setAttribute("value", params[key]) // var1, var2, var3

		console.log("name : " + key);
		console.log("value : " + params[key]);

		form.appendChild(input_tag);
	}
	document.body.appendChild(form);
	form.submit();
}



// 모달 팝업에 파일 표시
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

// 모달팝업에 파일명과 체크박스 생성
function createCheckbox(text){
	let modal_body = document.getElementsByClassName("modal-body")[0]
	var div = document.createElement("div")
	div.setAttribute("class", "checkbox")
	 
	var label = document.createElement("label")
	 
	var input = document.createElement("input")
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

// 성공 코드 : 재귀함수를 사용한 다중파일 다운로드 요청
var index = 0; 
function fileDownChk(){
	let checkbox = document.getElementsByName("file")
	var boardNo = document.getElementById("boardNo").value
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

// 실패 코드 : for문을 사용한 서버의 다중 요청 코드는 재요청 코드 부분이 잘못되어서 실패하였다
function fileDownChk1(){
	let checkbox = document.getElementsByName("file")
	var boardNo = document.getElementById("boardNo").value
	for(let i = 0; i<checkbox.length; i++){
		if(checkbox[i].checked){
			let fileName = checkbox[i].value
			let path = "fileDown.do"
			let params = {fileName:fileName, boardNo:boardNo}
			postSend(path, params, "post")
			setTimeout(function(){
				// 재요청이 들어가야함
			}, 1000)
		}
	}
} 
	








