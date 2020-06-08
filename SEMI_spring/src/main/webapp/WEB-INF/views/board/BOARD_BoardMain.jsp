<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8");%>
<%response.setContentType("text/html; charset=UTF-8");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv='Content-type' content='text/html; charset=utf-8'>
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">

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
			           <a href="detail.do?boardNo=${dto.boardNo }">
			           		 <div class="top">
			           		 	<img class="board_img" alt="" src="${dto.thumbnail }" onerror="this.src='/update/resources/img/board/board.jpeg';">
			           		 </div>
			           		 <div class="body">
								 <div class="title"><b>${dto.boardTitle}</b></div>
								 <div class="content">${dto.boardContent }</div>
								 <div class="date">${dto.boardRegdate }</div>
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
				
					<!-- << : 10 페이지 뒤로-->
					<c:if test="${pagination.startPage >= 11 }">
						<li onClick="paging()">
							<a href="main.do?currentPage=${pagination.currentPage -10}" aria-label="Previous">
								<span aria-hidden="true">&lt;&lt;</span>
							</a>
						</li>
					</c:if>
					
					<!-- < -->
					<c:if test="${pagination.currentPage ne 1 }">
						<li onClick="paging(${pagination.prevPage })">
							<a href="main.do?currentPage=${pagination.prevPage }" aria-label="Previous">
								<span aria-hidden="true">&nbsp;&lt;&nbsp;</span>
							</a>
						</li>
					</c:if>
					
					<!-- 처음 : ... 1 -->
					<c:if test="${pagination.currentPage > 6 }">
						<li onClick="paging(1)">
							<a href="main.do?currentPage=1" aria-label="Previous">
								<span aria-hidden="true">1</span>
							</a>
						</li>
						<li class="none">
							<span aria-hidden="true">...</span>
						</li>
					</c:if>
					
					<!-- 번호 출력 -->
					<c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
						<li class="page-item  <c:out value="${pagination.currentPage == pageNum ? 'active' : ''}"/>" id="<c:out value="${pagination.currentPage == pageNum ? 'none' : ''}"/>" onClick="paging('${pageNum }')">
							<a class="page-link" href="main.do?currentPage=${pageNum }">${pageNum }</a>
						</li>
					</c:forEach>
					
					<!-- 끝 : ... N  -->
					<c:choose>
						<c:when test="${pagination.endPage ne pagination.totalPage && pagination.totalPage > 10}">
							<li class="none">
								<span aria-hidden="true">...</span>
							</li>
							<li onClick="paging(${pagination.totalPage })">
								<a href="main.do?currentPage=${pagination.totalPage }" aria-label="Next"> 
									<span aria-hidden="true">${pagination.totalPage }</span>
								</a>
							</li>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					
					<!-- > -->
					<c:if test="${pagination.currentPage ne pagination.totalPage }">
						<li onClick="paging(${pagination.nextPage })">
							<a href="main.do?currentPage=${pagination.nextPage }" aria-label="Next"> 
								<span aria-hidden="true">&nbsp;&gt;&nbsp;</span>
							</a>
						</li>
					</c:if>
					
					<!-- >> : 10 페이지 앞으로-->
					<c:if test="${(pagination.currentPage +10) <= pagination.totalPage }">
						<li onClick="paging(${pagination.nextPage })">
							<a href="main.do?currentPage=${pagination.currentPage +10 }" aria-label="Next"> 
								<span aria-hidden="true">&gt;&gt;</span>
							</a>
						</li>
					</c:if>
					
				</ul>
			</div>
			<div class="col-md-2"></div>
	</footer>
	<!-- 페이징 영역 끝 -->
</div>

</body>
</html>