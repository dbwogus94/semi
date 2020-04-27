<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
  
  <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login/kakao_LoginSignUp.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login/LOGIN_kakao_LoginSignUp.js"></script>
<title>회원가입 팝업</title>
</head>
<body>

<header>
	<div id="logo"><img src="${pageContext.request.contextPath}/resources/images/logo_white.png" onclick="location.href='join.do?command=main'"/></div>
	<h1 id="untoti">운토티</h1>
	<div class="container" onclick="myFunction(this)">
  		<div class="bar1"></div>
  		<div class="bar2"></div>
 		 <div class="bar3"></div>
	</div>
</header>

	<div id="main_container">
		<div id="join_container">
			<br><br>
			<button onclick="kakaoLogin();" id="kakao">
				<img alt="" src="images/kakao2.png">&nbsp;&nbsp;&nbsp;&nbsp;카카오 로그인
			</button>
			<br>

			<br />
			<br /> 
			<form action="snsSingUpRes.do" method="post" onsubmit="return confirmSubmit()">
				<input type="hidden" name="command" value="loingSingUpRes_API" />
				<input type="hidden" name="API" value="kakao" />
					
				<div id="chk">
					<input type="checkbox" name="joinRole" value="멘토" onclick="oneCheckbox(this)"/>멘토 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="joinRole" value="멘티" onclick="oneCheckbox(this)" />멘티
				</div>

				<span class="join_span"> 
					<input type="password" class="input_text" name="joinPw" placeholder="카카오 인증번호"  id="id" 
					 readonly="readonly" required="required" maxlength="20"/>
				</span>
				<span class="check_res" style="display: none"></span> 
				
				<br /> 
				<br />
				<br /> 

				<span class="join_span"> 
					<input type="text" class="input_text" id="EmailID" name="joinEmail" placeholder="Email"
						required="required" readonly="readonly" onclick="emailChkConfirm();" />
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
				
				<input type="submit" class="input_button" value="신규회원으로 가입"> <br />
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