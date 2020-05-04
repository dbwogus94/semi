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
	if(spanList.length < 1){
		console.log("처음")
		tagCreate(value)
	} else {
		for(let i = 0; i<spanList.length; i++){
			// span의 크기가 4이상인 경우 >> 중복있으면 삭제 >>> 모두 검사했을때 alert
			if(spanList.length >= 4) {
				if(value === spanList[i].innerHTML){
					div.removeChild(h5List[i]) // 선택한 자식 노드 삭제
					return true;
				} else if(i === spanList.length-1) {
					alert("최대 4개 선택 가능합니다.")
				}
			// span의 크기가 4이하 >>> 중복있으면 삭제 >>> 모두 검사했을때 생성 
			} else if(spanList.length <= 4) {
				if(value === spanList[i].innerHTML){
					div.removeChild(h5List[i]) // 선택한 자식 노드 삭제
					return true;
				} else if(i === spanList.length-1) {
					console.log("생성 이전")
					tagCreate(value)
					//tagInputText(value)
					console.log("생성 이후")
					return true; 			
					// 의문? : return을 사용하지 않으면 tagCreate(value)함수의 명령이 실행되지 않는다.
				}
			}
		}
	}
}

// 값 복사 
function tagInputText(value){
	let inputText = document.getElementById("tagInput")
	inputText.value = value
}

// 태그 생성 
function tagCreate(value){
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

 