// 값을 post방식으로 보내기
/*
 * path : 전송 URL
 * params : 전송 데이터 {'q':'a','s':'b','c':'d'...}으로 키:값 묶어서 사용  (name : value)
 * method : 전송 방식(생략가능)
 */
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

// 구글 로그인
function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	var userID = profile.getId();
	var userEmail = profile.getEmail()
	userEmail = userEmail.split("@")[0]

	// 로그인
	var path = "login.do"
	var params = {
		"id" : userEmail,
		"joinPw" : userID,
		"sns" : "google"
	};

	// 로그아웃
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
	});
	auth2.disconnect();

	// 토큰(id), 이메일 포스트 방식으로 보낸다.
	postSend(path, params);

}

function out01() {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.signOut().then(function() {
	});
	auth2.disconnect();
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

					var userID = res.id; // 유저의 카카오톡 고유 토큰
					var userEmail = res.kakao_account.email; // 유저의 이메일
					userEmail = userEmail.split("@")[0]
					// 로그인
					var path = "login.do"
					var params = {
						"id" : userEmail,
						"joinPw" : userID
					};

					// 토큰(id), 이메일 포스트 방식으로 보낸다.
					postSend(path, params);

					// 로그아웃
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

function IdSearch() {
	window.open("join.do?command=IdSearch", "", "width=400px,height=350px;");

}

function PwSearch() {
	window.open("join.do?command=PwSearch", "", "width=400px,height=350px;");
}
