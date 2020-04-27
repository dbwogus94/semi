//var chk1 = false;
var chk2 = false;
var chk3 = false;
var chk4 = false;


//사용안함
function idChkAjax(value) {
	var idRes = document.getElementsByClassName("check_res")[0];
	$.ajax({
		url : "join.do",
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
			idRes.textContent = "카카오 로그인을 눌러주세요";
		}
	});

	$(".input_text").eq(1).click(function() {
		var email = document.getElementsByClassName("input_text")[1];
		var emailRes = document.getElementsByClassName("check_res")[1];

		if (email.value === "") {
			emailRes.style.display = "block";
			emailRes.textContent = "카카오 로그인을 눌러주세요";
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
		alert("카카오 인증을 완료하세요");
		return false;
	} else if (!chk2) {
		alert("이메일을 인증")
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

// 카카오톡 로그인
function kakaoLogin() {

	// Kakao.init('70d3537d1fcd39317f206eb8c09a486d');
	Kakao.init('9f7a7d3a273350b18a01ba15bdae8c67');

	// 카카오 로그인 버튼을 생성합니다.
	Kakao.Auth.loginForm({
		success : function(authObj) {
			Kakao.API.request({
				url : '/v2/user/me',
				success : function(res) {
					console.log(res);

					var userID = res.id; // 유저의 카카오톡 고유 id
					var userEmail = res.kakao_account.email; // 유저의 이메일

					document.getElementById('EmailID').value = userEmail;
					document.getElementById('id').value = userID;
					
					// id email 중복확인
					//idChkAjax(userID);
					emailChkAjax(userEmail);

					Kakao.Auth.logout();
				},
				fail : function(error) {
					alert(JSON.stringify(error));
				}
			});

		},
		fail : function(err) {
			alert(JSON.stringify(err));
		}
	});

}
function myFunction(x) {
	x.classList.toggle("change");
}
