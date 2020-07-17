// Date 객체나, 타임스템프를  받아서 날짜 문자열로 변환
function getFormatDate(input, shape){
	shape = shape || "/";
	let date
	if(typeof input == "number"){
		date = new Date(input)
	}else if(typeof input == 'string'){
		date = new Date(Number(input))
	}else if(input instanceof Date){
		date = input
	}
    let year = date.getFullYear();              //yyyy
    let month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    let day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    
    return  year + shape + month + shape + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}



// form태그 생성 전송할 키:값을 만들어 전송 요청 
//    => 파라미터 (경로, 배열 or json, 전송방식, 배열의경우 서버에서 받을 이름) 
function postSend(path, params, method, ArrName) {
	method = method || "post";		// 파라미터로 들어온 값이 없으면 디폴트값
	
	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);
	
	// 배열이면?
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
	// json이면?
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