<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="414765760234-41pmr1ehu69sp6j7qrntkdq9fkulbg0h.apps.googleusercontent.com">
   
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />

<link rel="stylesheet" href="../resources/css/LOGIN_css/LOGIN_LoginPopUp.css">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript" src="<c:url value="../resources/js/jquery-3.4.1.js"/>"></script>
<script type="text/javascript" src="../resources/js/login/LOGIN_LoginPopUp.js"></script>

<title>Login</title>
</head>
<body>
	<header>
		<div id="logo">
			<img src="../resources/images/main/logo_white.png" onclick="location.href='join.do?command=main'"/>
		</div>
		<h1 id="untoti">운토티</h1>
		<div class="container" onclick="myFunction(this)">
			<div class="bar1"></div>
			<div class="bar2"></div>
			<div class="bar3"></div>
		</div>
	</header>

	<div id="main_container">
		<h1>로그인</h1>
		<div id="join_container">
			<br>
			<br>
			<button onclick="" name="" data-width="320" data-height="40"
				class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"
				id="google">
				<img alt="" src="../resources/images/login/google.png">&nbsp;&nbsp;&nbsp;&nbsp;google
				로그인
			</button>
			<button onclick="kakaoLogin();" id="kakao">
				<img alt="" src="../resources/images/login/kakao2.png">&nbsp;&nbsp;&nbsp;&nbsp;카카오
				로그인
			</button>

			<br />
			<form action="join.do" method="post">
				<input type="hidden" name="command" value="loginCheck" /> <span
					class="join_span"> <input type="text" class="input_text"
					name="id" placeholder="아이디" id="id" required="required"
					maxlength="20" />
				</span> <br /> <span class="join_span"> <input type="password"
					class="input_text" name="joinPw" placeholder="PassWord"
					required="required" onclick="emailChkConfirm();" /> <!-- 필수 정보 입니다. -->
				</span> <br /> <br /> <br /> <br /> <input type="submit"
					class="input_button" value="Login"> <br /> <br />

				<div class="span_text" id="chk">
					아이디가 없으신가요? 
					<a href="join.do?command=loginSingUp">회원가입 </a><br>
					까먹었어요ㅜㅜ?
					<a href="join.do?command=loginPopUp" onclick="IdSearch();">아이디찾기</a>
					<a href="join.do?command=loginPopUp" onclick="PwSearch();">비밀번호찾기</a>
				</div>
			</form>
		</div>
	</div>
	<footer>@ 2020 all copyrights reserved by 운토티</footer>
</body>
</html>