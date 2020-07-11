function myFunction(x) {
	x.classList.toggle("change");		// 기존 class속성에 값이 있다면 바뀌는게 아니라 추가됨
	$("#nav").toggle(800);				// 제이쿼리의 함수로 선택한 객체를 보이거나 안보이게 해준다.
	
	/*
	 * 자바스크립트에서 제공하는 함수 "dom객체.classList.함수()"는 클래스 속성을 제어하는 함수이다. 
	  1) element.classList.add(클래스 String)
	  	>>> 지정한 클래스 값을 추가한다. 만약 추가하려는 클래스의 엘리먼트 class속성이 이미 존재한다면 무시한다.
	  2) element.classList.remove(String)
	  	>>> 지정한 클래스 값을 제거한다.
	  3) element.classList.item(Number)
	  	>>> 콜렉션의 인덱스를 이용하여 클래스 값을 반환한다.
	  4) element.classList.toggle(클래스 String)
	  	>>> 하나의 인수만 있을 때 : 클래스 값을 토글링 한다. 즉, 클래스가 존재한다면 제거하고 false를 반환하며 존재하지 않으면 클래스를 추가하고 true를 반환한다.
	  		두번째 인수가 있을 때 : true로 평가되면 지정한 클래스 값을 추가하고 false로 평가되면 제거한다.
	  5) element.classList.contains(클래스 String)
	   	>>> 지정한 클래스 값이 엘리먼트의 class 속성에서 존재하는지 확인한다.
	  6) element.classList.replace(oldClass, newClass)
	  	>>> 존재하는 클래스를 새로운 클래스로 교체한다.
	  
	 */
	
}

function chatPopup() {
	var url = 'join.do?command=chat_mentor';
	window.open(url, "", "width=400px, height=500px");
}
