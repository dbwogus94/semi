	var chk1 = false; 
	var chk2 = false;
	var chk3 = false; 
	var chk4 = false;
	var chk5 = false;
		 
	// 멘토 멘티 체크
	function oneCheckbox(obj) {
		var chk = document.getElementsByName("joinRole");
		for(var i=0; i<chk.length; i++){
            if(chk[i] != obj){
            	chk[i].checked = false;
            }
		}
		if(chk[0].checked || chk[1].checked){
			chk4 = true;
    		console.log("확인"+chk4);
    	}
	} 
	
	window.onload = function(){
		
		// 아이디 유효성 검사
		$(".input_text").eq(0).keyup(function(){
			var idChecked = /^[0-9a-zA-Z]/;
			var id = document.getElementsByClassName("input_text")[0]; 
			var res = document.getElementsByClassName("check_res")[0]; 
		
			if(id.value){  // id가 작성되어 있다면 
				if(!(idChecked.test(id.value))){  // 정규표현식으로 영어 숫자 검사
					chk1 = false;
					res.style.display="block";
					res.style.color = "red";
					res.textContent = "아이디는 영어+숫자를 사용해 주세요";
					return false;
				
				} else if((id.value.length < 5) || (id.value.length > 15)){ // 길이 검사 
					res.style.display="block";
					res.style.color = "red";
					res.textContent = "아이디는 5 ~ 15자리 사이로 만들어주세요";
					chk1 = false;
					return false;
				
				} else {  		// DB에서 아이디 중복검사 
					$.ajax({
						url:"../join/idCheck.do", 
						type:"post",
						async: true,
						data:{
							id:id.value
						},
						dataType:"text",
						success: function(value){
							if(value === "fail"){
								chk1 = false;
								res.style.display="block";
								res.style.color = "red";
								res.textContent="이미 존재하는 아이디 입니다.";
							} else if(value === "ok") {
								chk1 = true;  				// id 최종적으로 사용가능하면 true
								res.style.display="block";
								res.style.color="blue"
								res.textContent="사용가능한 아이디 입니다.";
							}
						},
						error: function(){
							alert("통신 실패");
						}
					});
				} 
			} else {
				//alert("아이디를 입력 하세요.");
				res.textContent = "아이디는 필수입니다.";
				res.style.display="block";
				chk1 = false;
				return false;
			}
		});
	
		// 이메일 텍스트에 값이 추가되었다면 >>> true로
		$(".input_text").eq(1).keyup(function(){
			var email = document.getElementsByClassName("input_text")[1];
			if(email.value){
				chk2 = true;
			}
		});
		
		$(".input_text").eq(3).keyup(function(){
			var pw = document.getElementsByClassName("input_text")[2];
			var pwChk = document.getElementsByClassName("input_text")[3];
			var res = document.getElementsByClassName("check_res")[3];
			
			if(pw.value == pwChk.value){
				chk3 = true;
				res.style.display="block";
				res.style.color = "blue";
				res.textContent="비빌번호가 일치합니다."
			} else {
				chk3 = false;
				res.style.display="block";
				res.style.color = "red";
				res.textContent="비빌번호가 일치하지 않습니다."
				
			}
		});
		
	} //window.onload 바디  끝
	
	// 이메일 인증 팝업
	function emailChkConfirm() {
		window.open("../join/emailCheckPopUp.do","","width=500px,height=500px");
		chk2 = true;
	}
	
	// 약관동의
	function consent(obj){
		if(obj){
			chk5 = true;
		} else {
			chk5 = false;	
		}
	}
	
	
	// 서브밋 유효성 검사
	function  confirmSubmit() {
		if(chk1 && chk2 && chk3 && chk4 && chk5){
			var con = confirm("작성내용으로 회원가입 하시겠습니까?")
			if(con){
				return true;
			} else {
				return false;
			}
		} else if(!chk1) {
			alert("아이디를 확인하세요");
			return false;
		} else if(!chk2) {
			alert("이메일을 확인하세요")
			return false;
		} else if(!chk3) {
			alert("비밀번호를 확인하세요");
			return false;
		} else if(!chk4){
			alert("멘토 또는 멘티를 체크하세요");
			return false;
		} else if(!chk5){
			alert("약관에 동의하세요");
			return false;
		}
		return false;
	}
	
	
	//카카오 회워가입 페이지
	function kakaoSignUp() {
		location.href="../join/kakaoSingUp.do";		
	}
	// 구글 회원가입 페이지
	function googleSignUp() {
		location.href="../join/googleSingUp.do";
	}
	
	 function myFunction(x) {
		  x.classList.toggle("change");
	}
