<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/Board_Main.css">
<%@ include file="../mentor/mentorHeader.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/BOARD_BoardMain.js"></script>
<title>자유게시판</title>
</head>
<body>

<div class="row" >
	<div class="col-md-12" id="headTitle">
		<hr>
		<br>
		<h2>자유게시판</h2>
		
		<input type="button" class="button" value="게시글 관리" onclick="location.href='.do'">
    	<input type="button" class="button" value="글쓰기" onclick="location.href='write.do'">
    	
		<br>
		<hr>
	</div>

</div>
<div id="container">
<form action="main.do" method="post">
    <div id="search1">
    	<select name = "category">
    		<option value="0">---선택---</option>
    		<option value="content">내용</option>
    		<option value="title">제목</option>
    		<option value="name">작성자</option>
    	</select>
    	<input type="text" name = "keyword" placeholder="검색할 내용을 입력하고 엔터키를 눌러주세요" onkeypress = "if(event.keyCode == 13) {submit();}">
    </div>
    <div id="button">
    </div>
</form>
	<div id="board">
    <ul>
		<c:choose>
			<c:when test="${empty list }">
				<li>
					<div class="body">
						============글이 없습니다============
					</div>
				</li>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="dto">
					<li>
			           <a href="">
			           		 <div class="top">
			           		 	<img class="board_img" alt="" src="${dto.thumbnail }" onerror="this.src='/update/resources/img/board/board.jpeg';">
			           		 </div>
			           		 <div class="body">
								 <div class="title" name=""><b>${dto.boardTitle}</b></div>
								 <div class="content" name="">${dto.boardContent }</div>
								 <div class="date" name="">${dto.boardRegdate }</div>
							 </div>
						</a>	 
							 <div class="footer">
							 	<a href=""><div class="writer">${dto.memberName}(클릭시 유저 메인으로)</div></a>
							 	<div class="like">${dto.boardLike }</div>
							 </div>
			        </li>
				</c:forEach>		
			</c:otherwise>
		</c:choose>
    </ul>
    </div>
	
	<!-- 페이징 영역 -->
	<footer id="paging">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<ul class="pagination">
					<c:if test="${pagination.curRange ne 1 }">
						<li onClick="paging(1)">
							<a href="#" aria-label="Previous">
								<span aria-hidden="true">처음</span>
							</a>
						</li>
					</c:if>
					<c:if test="${pagination.curPage ne 1}">
						<li onClick="paging('${pagination.prevPage }')">
							<a href="#" aria-label="Previous"> 
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					<c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
						<c:choose>
							<c:when test="${pageNum eq  pagination.curPage}">
								<li onClick="paging('${pageNum }')">
									<a href="#" class="paging-focus">${pageNum }</a>
								</li>
							</c:when>
							<c:otherwise>
								<li onClick="paging('${pageNum }')">
									<a href="#">${pageNum }</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
						<li onClick="paging('${pagination.nextPage }')">
							<a href="#" aria-label="Next"> 
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
					<c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
						<li onClick="paging('${pagination.pageCnt }')">
						<a href="#" aria-label="Next"> 
							<span aria-hidden="true">마지막</span>
						</a></li>
					</c:if>
				</ul>
			</div>
			<div class="col-md-2"></div>
	</footer>
	<!-- 페이징 영역 끝 -->
</div>

</body>
</html>