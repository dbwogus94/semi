<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Board_Main.css">

<title>자유게시판</title>
<%@ include file="../mentor/mentorHeader.jsp"%>

<style type="text/css">
.pageBar{
	text-align: center;
}
</style>
</head>
<body>

<hr><br><h3>자유게시판</h3><br><hr>

<form action="board.do" method="post">
<input type = "hidden" name = "command" value = "boardSearch"/>
<div id="container">
    <div id="search1">
    	<select name = "category">
    		<option value="0">---선택---</option>
    		<option value="id">아이디</option>
    		<option value="name">이름</option>
    		<option value="addr">제목</option>
    	</select>
    	<input type="text" name = "search2" placeholder="검색할 내용을 입력하고 엔터키를 눌러주세요" onkeypress = "if(event.keyCode == 13) {submit();}">
    </div>
    <div id="button">
    
    <input type="button" class="button" value="게시글관리" onclick="location.href=''">
    <input type="button" class="button" value="글쓰기" onclick="location.href=''">
    </div>
	</form>
    <ul class="board">
		<li>
           <a href="board.do">
                 <div id="writer">아이디</div>
				 <div id="date">작성시간</div>
 				 <div id="img"><img src="images/conimg.jpg" alt=""></div>
				 <div id="title">제목</div>
            </a>
        </li>
    </ul>
    <div id = "pageBar">

	</div>
</div>
<footer>@ 2020 all copyrights reserved by 운토티</footer>
</body>
</html>