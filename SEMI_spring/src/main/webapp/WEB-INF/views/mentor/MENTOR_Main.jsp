<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/mentor/MENTOR_Main.css" rel="stylesheet">
<title>Insert title here</title>
<%@ include file="../mentor/mentorHeader.jsp"%>
<body>
	<br>
	<hr>
	<br>
	<h3>MENTOR MAIN</h3>
	<br>
	<hr>
	<br>
	<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${mentorDto.memberName } 님 PROFILE</h3>
	<br>
	<hr>
	<div id="mentor_pro">
		<div id="img_pro">
			<img alt="" src="${mentorDto.memberContent}">
			<button onclick="location.href='profile.do'" class="edit_profile">
				 프로필수정
			</button>
		</div>
		<div id="li_pro">
			<ul>
				<li>${mentorDto.memberOneIntro }</li>
				<li>${mentorDto.joinEmail }</li>
				<li>${mentorDto.memberPhone }</li>
				<li>${mentorDto.memberPhone }</li>
			</ul>
		</div>
	</div>
	<br>
	<br>
	<hr>

	<div id="mentor_detail">
		<fieldset>
			<legend>수익</legend>
			<ul>
				<li id="1st"><span id="total">${mentorDto.memberCoin }</span>원</li>
				<li><span id="2ndtotal">${mentorDto.memberCoin }</span>을 출금할 수 있습니다.</li>
				<li><button onclick="location.href='profile.do?command=mentor_profit'">상세보기</button></li>
			</ul>
		</fieldset>

		<fieldset>
			<legend>관리 중인 멘티</legend>
			<ul>
				<li id="1st"><span id="total"></span>명</li>
				<li><span id="2ndtotal">명</span>의 멘티를 관리 중입니다.</li>
				<li><button onclick="location.href='profile.do?command=mentor_menteeList'">상세보기</button></li>
			</ul>
		</fieldset>
	</div>


	<footer>@ 2020 all copyrights reserved by 운토티</footer>

</body>
</html>