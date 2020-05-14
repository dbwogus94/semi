<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mentor/mentorHeader.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mentor/mentorHeader.js"></script>
<!-- include bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- fontawesome 헤더 동작 스타일 -->
<!-- <script src="https://kit.fontawesome.com/599e2aa924.js" crossorigin="anonymous"></script> -->
</head>
<body>
		<header>
		<div id="logo">
			<img src="${pageContext.request.contextPath}/resources/images/logo_white.png" onclick="location.href='../join/main.do'"/>
		</div>
		<h1>운토티</h1>
		<div class="header_container" onclick="myFunction(this)">
			<div class="bar1"></div>
			<div class="bar2"></div>
			<div class="bar3"></div>
		</div>
	   <div id="nav">
			<ul>
				<a href="../mentor/main.do"><li>멘토 메인페이지</li></a>
				<a href="../mentor/profile.do"><li>프로필 확인</li></a>
				<a href="../board/main.do"><li>게시판</li></a>
				<a href="" onclick="chatPopup()"><li>채팅</li></a>
		   </ul>
		</div>
	
	</header>
</body>
</html>