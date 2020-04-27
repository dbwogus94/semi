<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="414765760234-41pmr1ehu69sp6j7qrntkdq9fkulbg0h.apps.googleusercontent.com">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login/google_LoginSignUp.css">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login/LOGIN_google_LoginSignUp.js"></script>
<title>회원가입 팝업</title>
</head>
<body>

<header>
	<div id="logo"><img src="${pageContext.request.contextPath}/resources/images/logo_white.png" onclick="location.href='main.do'"/></div>
	<h1 id="untoti">운토티</h1>
	<div class="container" onclick="myFunction(this)">
  		<div class="bar1"></div>
  		<div class="bar2"></div>
 		 <div class="bar3"></div>
	</div>
</header>
	<div id="main_container">
		<div id="join_container">
		
			<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark" id="google"></div>
			<br />
			<br />
			<form action="snsSingUpRes.do" method="post" onsubmit="return confirmSubmit()">
				<input type="hidden" name="command" value="loingSingUpRes_API" />
				<input type="hidden" name="API" value="google" />

				<div id="chk">
					<input type="checkbox" name="joinRole" value="멘토" onclick="oneCheckbox(this)"/>멘토
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input type="checkbox" name="joinRole" value="멘티" onclick="oneCheckbox(this)" />멘티
				</div>

				<span class="join_span"> 
					<input type="password" class="input_text" name="joinPw" placeholder="구글 인증번호"  id="id"
						readonly="readonly" required="required" maxlength="20"/>
				</span>
				<span class="check_res" style="display: none"></span> 
				
				<br /> 
				<br />
				<br /> 

				<span class="join_span"> 
					<input type="text" class="input_text" id="EmailID" name="joinEmail" placeholder="Email"
						required="required" readonly="readonly" onclick="emailChkConfirm();" /> <!-- 필수 정보 입니다. -->
				</span> 
				<span class="check_res" style="display: none"></span>
				 
				<br /> 
				<br /> 
				 
				<div class="span_text" id="chk">
					  <br /> <input type="checkbox"
						name="check" required="required" onclick="consent(this);"/> 운토티이용약관 및 개인정보취급방침에 동의합니다.
				</div>
				
				<br /> 
				<br /> 

				<br /> <input type="submit" class="input_button" value="신규회원으로 가입" /> <br />
				<br />

				<div class="span_text" id="chk">
					이미 운토티 회원이신가요? <a href="loginPopUp.do">로그인하기</a>
				</div>
			</form>
		</div>
	</div>
<footer>@ 2020 all copyrights reserved by 운토티</footer>
</body>
</html>