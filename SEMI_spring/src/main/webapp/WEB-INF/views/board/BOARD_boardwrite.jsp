<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/BOARD_boardwrite.css">
<title>게시글작성</title>
<%@ include file="../mentor/mentorHeader.jsp"%>
<!-- summernotr 부트스트랩4 연동 버전/ 3버전과 사용하면 css 충돌이 발생한다. -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"> -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script> -->
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script> -->
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css" rel="stylesheet"> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script> -->

<!-- summernotr 부트스트랩 없이 단독사용 라이트 버전 -->
<!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/BOARD_boardwrite.js"></script>
</head>

<body>
<form action="" method="post" onsubmit ="return validateForm();" id="form">
	<div class="row" >
		<div class="col-md-12" id="headTitle" onsubmit="">
			<hr>
			<br>
			<h2>자유게시판</h2>
			<!-- onclick="location.href='writeCancel.do'" -->>
			<input type="button" class="button" value="작성취소" onclick="validateForm();"/>
			<input type="submit" class="button" value="저장하기"/>

	    	
			<br>
			<hr>
		</div>
	
	</div>
	<div id="container">
		<div id="board">
			<div id="content">
				<div id="cdetail">
			 
		  			<input type="text" id="title" name="title" placeholder="제목을 입력해주세요"/>
					<br>
					<div id="cdetail">
						<span><img src="${pageContext.request.contextPath}/resources/img/mentor/mentor01.png"></span><span>작성자</span>
						<input type="file" id="file" multiple="multiple"/>
					</div>
					<br>
					<br>
					<hr>
					<div class="cdetail2">
						<div class="blank"></div>
						<div id="ctext">
							<textarea rows="100" cols="100" id="summernote"></textarea>
							<!-- <input type="text" id="context" name = "content" placeholder="내용을 입력해주세요"/> -->
						</div>
						<div id="btn2">
							<input type = "hidden" name = "name" value=""/>
							<input type = "hidden" name = "id" value=""/>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
</form>
</body>
</html>