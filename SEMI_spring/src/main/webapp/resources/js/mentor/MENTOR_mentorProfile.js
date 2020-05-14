// 데이트 피커
function openDate(inputText) {
	// 자바스크립트 Date객체로 년/월/일.. 가져오는법
	let today = new Date(); // 오늘 날짜
	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1; // 월
	let date = today.getDate(); // 날짜
	let day = today.getDay(); // 요일
	console.log(year + month + date) // 오늘 년월일

	const fp = flatpickr(inputText, { // flatpickr 생성 json바디에는 옵션이 들어간다 >>>
		// api참고
		// defaultDate:"1990-01-01", // 시작날짜 지정
		dateFormat : "Y-m-d", // 기본 달력 표시형식
		altInput : true, // 클릭후 달력 형식 변환 >>> 이 속성을 넣어야 피커가 유지됨
		altFormat : "Ymd", // 클릭후 변환할 달력 형식 지정 ex) "F j, Y" >>> May 15, 2020
		maxDate : "today" // 생년월일 이기 때문에 최대 날짜 오늘로 지정
	// enableTime: true, // 시간선택 활성화
	// noCalendar: true, // 달력 지우기, 시계만 사용할때 사용한다.
	// time_24hr: true, // 24시간제로 변경
	// inline: true, // 달력이 인라인으로 적용
	});
}

// 도로명 주소
function addressPopup() {
	window.open("AddressPopup.do", "", "width=500px,height=500px");
}

// 특기 태그 추가, 삭제
function tagInsert(select) {
	let div = document.getElementsByClassName("panel-body")[0]
	let spanList = document.getElementsByClassName("label label-default")
	let h5List = document.getElementsByClassName("keyword_h5")

	let value = select.value
	// 처음은 생성
	if (spanList.length < 1) {
		console.log("처음")
		tagCreate(value)
	} else {
		for (let i = 0; i < spanList.length; i++) {
			// span의 크기가 4이상인 경우 >> 중복있으면 삭제 >>> 모두 검사했을때 alert
			if (spanList.length >= 4) {
				if (value === spanList[i].innerHTML) {
					div.removeChild(h5List[i]) // 선택한 자식 노드 삭제
					return true;
				} else if (i === spanList.length - 1) {
					alert("최대 4개 선택 가능합니다.")
				}
				// span의 크기가 4이하 >>> 중복있으면 삭제 >>> 모두 검사했을때 생성
			} else if (spanList.length <= 4) {
				if (value === spanList[i].innerHTML) {
					div.removeChild(h5List[i]) // 선택한 자식 노드 삭제
					return true;
				} else if (i === spanList.length - 1) {
					console.log("생성 이전")
					tagCreate(value)
					// tagInputText(value)
					console.log("생성 이후")
					return true;
					// 의문? : return을 사용하지 않으면 tagCreate(value)함수의 명령이 실행되지 않는다.
				}
			}
		}
	}
}

// 값 복사
function tagInputText(value) {
	let inputText = document.getElementById("tagInput")
	inputText.value = value
}

// 태그 생성
function tagCreate(value) {
	let div = document.getElementsByClassName("panel-body")[0]
	// 버튼 생성 h5 + span
	let h5 = document.createElement("h5")
	h5.setAttribute("class", "keyword_h5")
	div.append(h5)

	let label = document.createElement("label")
	label.setAttribute("id", "memberCareer")
	label.setAttribute("name", "memberCareer")
	label.setAttribute("class", "label label-default")
	h5.appendChild(label)
	label.textContent = value + ""
	console.log("함수 호출")
}

// 서브밋 유효성 검사
function validateForm(form){
	
	//console.log(form)
	//alert("확인")
	return true;
}


// 이미지 미리보기
function imgUpload() {
	// 파일 업로드 태그 >> 파일 업로드시 해당 파일을 가지고 있는 객체이다.
	let upload = document.querySelector("#imgInsertbut") 
	// 추가될 이미지 영역 
	let preview = document.querySelector("#imgdiv") 

	
	/* #1 자바스트립트의 FileReader 객체 생성 */
	let reader = new FileReader()
	console.log(1)
	
	/* #2 addEventListener를 사용하여 change 이벤트 적용  */
	upload.addEventListener('change', function(e) {
		
		/* #3 이벤트를 발생시킨 타겟의 파일객체를 가져온다(배열)  */
		let get_file = e.target.files
		console.log(2)
		console.log(get_file)

		if (get_file) { // 자바스크립트의 undefined는 false를 의미 즉 값이 있으면 true
			/* #4 - 2 */
			reader.readAsDataURL(get_file[0]);
			/*
			  # readAsDataURL(파일)  	
			  >>> FileReader객체의 함수로 파일의 읽어 base64 스트링을 받아오는 메서드이다.  
			  1) 읽어오는 read 행위가 종료되는 경우에, readyState 의 상태가 DONE이 됨   
			  2) loadend 이벤트가 트리거 됩니다. 
			  3) 이와 함께, base64 인코딩 된 스트링 데이터가 result 속성(attribute)에 담아지게 됩니다.
			 */
			
			console.log(4 + " : 파일 읽기 완료")
		}
	})
	
	/* #4 - 1 reader 파일을 읽기 시작하면 시작시 함수 구현 */
	reader.onload = (function() {
		console.log(3 + " : 파일 읽기 시작")
		// 새로운 img태그
		let profileImg = document.createElement("img")
		profileImg.setAttribute("id", "mentorImg")
		
		/* #5 파일을 다 읽었을때 loadend 이벤트가 발생 >>> 이후 return 발생한다. */
		return function(event) {
			console.log(5 + " : 파일 읽기 완료후 이벤트")
			/* #7 base64 인코딩 된 스트링 데이터 == result */
			profileImg.src = event.target.result	// event.target = loadend 함수가 발생시킨 이벤트

			preview.innerHTML = "";
			preview.appendChild(profileImg);
			
			document.querySelector("#hidden_memberContent").value = ""
			
			console.log(6 + " : 완료")
		}
	})()
}

/*
 * # "EventTarget.addEventListener('이벤트 type', 이벤트)"에 대하여 
  
    > 사용법 : 
	var taget = document.querySelector("#taget") 
	
	// 1) 함수를 구현하여 이벤트 적용 
	function myEvent(event){ 
		console.log("event를 파라미터로 받는 함수를 구현") 
	}
    taget.addEventListener('click', myEvent) 
    
    
    // 2) 익명함수를 사용하여 이벤트 적용
    taget.addEventListener('이벤트 type', function(event){ 
    console.log("event를 파라미터로 받는 익명함수를 사용해 이벤트 구현")
    }) 
    
    > 기존방식과 다른 장점 : 
	기존 태그에 onclick=""에 함수를 등록하거나 window.onload를 이용하여 등록할 경우 
	하나의 타겟에 중복되는 이벤트 적용시 기존 적용된 이벤트는 제거하고 새로 적용한 이벤트만 적용되었음 
	하지만 addEventListener을 사용시에는 여러이벤트를 적용시 모두 적용되며 순차적으로 실행이 된다.
	즉, 하나의 태그에 여러함수를 적용할 수 있다.
	
	
	> 응용한 사용법 :
	var t1 = document.getElementById('target1') 
	var t2 = document.getElementById('target2') 
	
	// 1) 사용할 하나의 함수 구현
	function btn_listener(event){
		switch(event.target.id){ 	// 이벤트를 발생시킨 타겟의 id 
			case 'target1' :
				alert(1) 
				break
			case 'target2' : 
				alert(2) 
				break 
		}
	} 
	// 2) 위의 함수를 사용하여 이벤트 적용 
	t1.addEventListener('click', btn_listener) 
	t2.addEventListener('click', btn_listener)
 */

