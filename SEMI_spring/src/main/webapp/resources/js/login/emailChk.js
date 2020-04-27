/*부모 veiw에게 값 전달*/

function EmailChk() {
	console.log(opener.window.document.getElementsByName("joinEmail")[0]);
	opener.window.document.getElementsByName("joinEmail")[0].value = document
			.getElementById("EmailID").value;
	window.close();
}

// 이메일 유효성검사
function emailValidate() {
	var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	var email = document.getElementById("emailCheck");
	if (email.value == "") {
		alert("이메일을 입력해 주세요");
		email.focus();
		return false;
	}
	if (!re2.test(email.value)) {
		alert("이메일 형식이 아닙니다.");
		return false;
	} else {
		$.ajax({
			url : "emailCheck.do",
			type : "post",
			async : true,
			data : {
				email : email.value
			},
			dataType : "text",
			success : function(res) {
				if (res === "fail") {
					alert("이메일이 중복되었습니다.");
					return false;
				} else if (res == "ok") {
					alert("사용가능한 이메일 입니다.");
					$("#feildeID").show();
					document.getElementById("EmailID").value = document
							.getElementById("emailCheck").value;
				}
			},
			error : function() {
				alert("통신 실패");
			}
		})
	}
}

	// 인증번호 전송
	function Emailsend() {
		var joinemail = document.getElementById("emailCheck")
		joinemail = {
			joinemail : joinemail.value
		}
		$.ajax({
			url : "mailSend.do",
			type : "post",
			async : true,
			data : joinemail,
			dataType : "text",
			success : function(res) {
				if (res == "succ") {
					alert("인증번호 전송 완료")
				} else {
					alert("인증번호 전송 실패 다시 시도 해주세요")
				}
			},
			error : function() {
				alert("통신 실패");
			}
		});
	}

	// 인증번호 확인
	function validateCode() {
		var code = document.getElementById("EmailCode")
		code = {
			code : code.value
		}
		$.ajax({
			url : "validate.do",
			type : "post",
			data : code,
			dataType : "text",
			success : function(res) {
				if (res == "succ") {
					alert("인증되었습니다.");
					$("#chk").show();
					$("#chk2").hide();
					$("#EmailCode").hide();
				} else {
					alert("인증번호가 틀렸습니다.");
				}
			},
			error : function() {
				alert("통신실패")
			}
		})
	}

