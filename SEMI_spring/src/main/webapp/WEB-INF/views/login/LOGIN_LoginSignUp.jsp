<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
  
  <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login/LOGIN_LoginSignUp.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login/LOGIN_LoginSignUp.js"></script>
<title>SignUP</title>
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
			<h1>회원가입</h1>
			
			<button onclick="googleSignUp();" name="facebookRegister" id="google">
				<img alt="" src="${pageContext.request.contextPath}/resources/images/google.png">&nbsp;&nbsp;&nbsp;&nbsp;google계정으로 회원가입
			</button>
			
			<button onclick="kakaoSignUp();" id="kakao">
				<img alt="" src="${pageContext.request.contextPath}/resources/images/kakao2.png">&nbsp;&nbsp;&nbsp;&nbsp;카카오 계정으로 회원가입
			</button>
			
			
			<br>

			<br />
			<form action="loginSingUpRes.do" method="post" onsubmit="return confirmSubmit()">

				<div id="check">
					<input type="checkbox" name="joinRole" value="멘토" onclick="oneCheckbox(this)"/>멘토 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="joinRole" value="멘티" onclick="oneCheckbox(this)" />멘티
				</div>

	
					<input type="text" class="input_text" name="id" placeholder="아이디" 
						required="required" id="join_span"/>
			
				<span class="check_res" style="display: none"></span> 
				
				<br /> 
				<br />

					<input type="email"  id="join_span" class="input_text" id="EmailID" value="" name="joinEmail" placeholder="Email"
						required="required" readonly="readonly" onclick="emailChkConfirm();" /> <!-- 필수 정보 입니다. -->
		
				<span class="check_res" style="display: none"></span>
				 
				<br /> 
				<br />
				 
				
					<input type="password" id="join_span" class="input_text" name="joinPw"
						placeholder="password" required="required" /> <!-- 필수 정보 입니다. -->
				
				 <span class="check_res" style="display: none"></span>
				 
				<br /> 
				<br /> 
			
					<input type="password" id="join_span" class="input_text" name="joinPw_chk"
						placeholder="ConfirmPassword" required="required"/> <!-- 필수 정보 입니다. -->
				<span class="check_res" style="display: none"></span> 
				<br /> 
				<br />
				<h4>아이디는 영문 숫자조합 5자리 이상 15자리 이하</h4>
				<br />
				

				<div class="span_text">
					<input type="checkbox"
						name="check" required="required" onclick="consent(this);"/> 
						운토티이용약관 및 개인정보취급방침에<br />	동의합니다.
				</div>

				<br /> <input type="submit" class="join"
					 value="신규회원으로 가입"> <br />
				<br />

				<div class="span_text">
					이미 운토티 회원이신가요? <a href="loginPopUp.do">로그인하기</a>
				</div>
			</form>
		</div>
	</div>


<footer>@ 2020 all copyrights reserved by 운토티</footer>

</body>
</html>