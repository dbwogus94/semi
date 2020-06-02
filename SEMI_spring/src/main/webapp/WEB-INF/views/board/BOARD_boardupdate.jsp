<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='Content-type' content='text/html; charset=utf-8'>
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/BOARD_boardupdate.css">
<title>게시글수정</title>
<%@ include file="../mentor/mentorHeader.jsp"%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/BOARD_boardupdate.js"></script>
</head>

<body>
<form action="updateRes.do" method="post" id="form" enctype="multipart/form-data">
	<input type="hidden" name="boardNo" value="${board.boardNo}" id="boardNo">
	<input type="hidden" name="id" value="${login.id }"/>
	<input type="hidden" name="memberName" value="${login.memberName }"/>
	<input type="hidden" name="boardContent"/>
	<div class="row" >
		<div class="col-md-12" id="headTitle">
			<hr>
			<br>
			<h2>자유게시판</h2>
			<!-- onclick="location.href='writeCancel.do'" -->
			<input type="button" class="button" value="수정취소" onclick="location.href='detail.do?boardNo=${board.boardNo}'"/>
			<input type="button" class="button" value="수정완료" onclick="AjaxFileUpdate();"/>
			<input type="submit" style="display: none" id="submit"/>
	    	
			<br>
			<hr>
		</div>
	
	</div>
	<div id="container">
		<div id="board">
			<div id="content">
				<div id="cdetail">
			 
		  			<input type="text" id="title" name="boardTitle"  value="${board.boardTitle }"/>
					<br>
					<div id="cdetail">
						<span><img src="${login.memberContent}"></span><span>${login.memberName }</span>
						<input type="file" id="file" multiple="multiple" name="file" onclick="return warning();"/>
					</div>
					<br>
					<br>
					<hr>
					<div class="cdetail2">
						<div class="blank"></div>
						<div id="ctext">
							<textarea rows="100" cols="100" id="summernote">${board.boardContent }</textarea>
							
						</div>
						<div id="btn2">
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
</form>
</body>
</html>