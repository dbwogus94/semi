<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://kit.fontawesome.com/599e2aa924.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main/main.css">
 <script type="text/javascript">
 function myFunction(x) {
	  x.classList.toggle("change");
	}
 </script>
 
</head>
<body>
<header>
	<div id="logo"><img src="${pageContext.request.contextPath}/resources/images/gym.png" /></div>
	<h1>운토티</h1>
	<div class="container" onclick="myFunction(this)">
  		<div class="bar1"></div>
  		<div class="bar2"></div>
 		 <div class="bar3"></div>
	</div>
</header><br>
<hr><br><h3>Welcome!</h3><br><hr><br>
<img id="female"alt="" src="${pageContext.request.contextPath}/resources/images/main2.jpg">

<section>
<div class="box">


<h2>혼자하기<span id="effect1"> 힘든</span> <span id="next">운동</span></h2>
<h2><span id="effect1">멘토</span><span id="next">와 함께하세요!</span></h2>

<button onclick="location.href='join/loginPopUp.do'"><span>로그인</span></button><button onclick="location.href='join/loginSignUp.do'"><span>회원가입</span></button>

</div>
</section>
<footer>@ 2020 all copyrights reserved by 운토티</footer>

</body>
</html>