var chk1 = false;
var chk2 = false;
var chk3 = false;
var chk4 = false;

//사용 안함
function idChkAjax(value) {
	var idRes = document.getElementsByClassName("check_res")[0];
	$.ajax({
		url : "idCheck.do",
		type : "post",
		async : true,
		data : {
			command : "idCheck",
			id : value
		},
		dataType : "text",
		success : function(text) {
			if (text === "fail") {
				idRes.style.display = "block";
				idRes.textContent = "이미 존재하는 아이디 입니다.";
			} else if (text === "ok") { // 사용가능한 아이디
				//chk1 = true;
				idRes.style.display = "block";
				idRes.style.color = "blue";
				idRes.textContent = "사용가능한 아이디 입니다.";
			}
		},
		error : function() {
			alert("통신 실패");
		}
	});
}

// 이메일 중복 확인
function emailChkAjax(value) {
	var idRes = document.getElementsByClassName("check_res")[0];
	var emailRes = document.getElementsByClassName("check_res")[1];
	$.ajax({
		url : "emailCheck.do",
		type : "post",
		async : true,
		data : {
			email : value
		},
		dataType : "text",
		success : function(text) {
			if (text === "fail") {
				chk1 = true;
				idRes.style.display = "block";
				idRes.style.color = "blue";
				idRes.textContent = "인증완료 되었습니다.";
				emailRes.style.display = "block";
				emailRes.textContent = "이미 존재하는 이메일 입니다.";
			} else if (text === "ok") {
				chk1 = true;
				chk2 = true;
				idRes.style.display = "block";
				idRes.style.color = "blue";
				idRes.textContent = "인증완료 되었습니다.";
				emailRes.style.display = "block";
				emailRes.style.color = "blue";
				emailRes.textContent = "사용가능한 이메일 입니다.";
			}
		},
		error : function() {
			alert("통신 실패");
		}
	});
}

window.onload = function() {
	$(".input_text").eq(0).click(function() {
		var id = document.getElementsByClassName("input_text")[0];
		var idRes = document.getElementsByClassName("check_res")[0];

		if (id.value === "") {
			idRes.style.display = "block";
			idRes.textContent = "구글 로그인을 눌러주세요";
		}
	});

	$(".input_text").eq(1).click(function() {
		var email = document.getElementsByClassName("input_text")[1];
		var emailRes = document.getElementsByClassName("check_res")[1];

		if (email.value === "") {
			emailRes.style.display = "block";
			emailRes.textContent = "구글 로그인을 눌러주세요";
			return false;
		}
	});

} // window.onload 바디 끝

// 멘토 멘티 체크
function oneCheckbox(obj) {
	var chk = document.getElementsByName("joinRole");
	for (var i = 0; i < chk.length; i++) {
		if (chk[i] != obj) {
			chk[i].checked = false;
		}
	}
	if (chk[0].checked || chk[1].checked) {
		chk4 = true;
	}
}

// 약관동의
function consent(obj) {
	if (obj) {
		chk3 = true;
	} else {
		chk3 = false;
	}
}

// 서브밋 유효성 검사
function confirmSubmit() {
	if (chk1 && chk2 && chk3 && chk4) {
		var con = confirm("해당내용 회원가입 하시겠습니까?")
		if (con) {
			return true;
		} else {
			return false;
		}
	} else if (!chk1) {
		alert("구글 인증을 완료하세요");
		return false;
	} else if (!chk2) {
		alert("구글 로그인으로 이메일 인증을 해주세요")
		return false;
	} else if (!chk3) {
		alert("약관에 동의하세요");
		return false;
	} else if (!chk4) {
		alert("멘토 또는 멘티를 체크하세요");
		return false;
	} else {
		return true;
	}
	return false;
}
// 구글 로그인
function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	document.getElementById('id').value = profile.getId();
	document.getElementById('EmailID').value = profile.getEmail();

	// id, email 중복검사
	//idChkAjax(profile.getId());
	emailChkAjax(profile.getEmail());

	// 로그아웃
	logout()
}

function logout() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.disconnect(); 				// 구글 강제 로그아웃   #1  >> 먼저사용할 경우 다시 로그인클릭시 계정입력창이 뜬다
	auth2.signOut().then(function() { 	// 구글 로그아웃 실행   #2  >> 먼저 사용할 경우 다시 로그인클릭시 이전 계정으로 자동 로그인된다.
		console.log('Usersigned out.');
	});
}

function myFunction(x) {
	x.classList.toggle("change");
}
