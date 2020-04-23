<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 체크</title>
<link rel="stylesheet" href="../resources/css/LOGIN_css/emailChk.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../resources/js/login/emailChk.js"></script>
</head>
<%
int RandomNumber=(int)(Math.floor(Math.random() * (9999-1000+1)) + 1000);
%>
<body>
	<form name="a" action="../join/mailSend.do" method="post" target="iframe2">
	<input type="hidden" id="number" name="number" value="<%=RandomNumber%>" >
	
	<fieldset>
	<legend>이메일 작성</legend>
	<div>
	<input type="text" id="emailCheck" placeholder="email@google.com" >
	<input type="button" id="validateID" value="유효성검사" onclick="validate();" >
	</div>
	</fieldset>
	
	
	<fieldset id="feildeID" style="display: none;">
	<legend>이메일입력</legend>
	<div>
	<input type="email" id="EmailID" name="EmailName" >
	<input type="submit" value="인증코드전송"  id="submit"><br>
	<input type="text" id="EmailCode" placeholder="인증코드 작성" >
	<input type="button" value="확인하기" id="chk2" onclick="EmailChk2(<%=RandomNumber%>);">
	<input type="button"  id="chk" value="인증성공" onclick="EmailChk();" style="display: none;" ><br>
	</div>
	</fieldset>
	
	</form>
</body>
<iframe name="iframe2" style="display: none;"></iframe>
</html>