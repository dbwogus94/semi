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

<title>자유게시판</title>
<%@ include file="../mentor/mentorHeader.jsp"%>
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
<form action="board.do" method="post">
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
    
    
    </div>
</form>
	<div id="board">
    <ul>

		<li>
           <a href="">
           		 <div class="top">
           		 	<img class="board_img" alt="" src="https://macinjune.com/wp-content/uploads/2018/06/th_google_image_search_2.jpg">
           		 </div>
           		 <div class="body">
					 <div class="title" name="">제목</div>
					 <div class="content" name="">이곳은 글의  내용이 들어가는 곳입니다.</div>
					 <div class="date" name="">2020년 05월 11일</div>
				 </div>
			</a>	 
				 <div class="footer">
				 	<a href=""><div class="writer">작성자(클릭시 유저 메인으로)</div></a>
				 	<div class="like">좋아요(보여주기만)</div>
				 </div>
        </li>
        
        
		
		<li>
           <a href="">
           		 <div class="top">
           		 	<img class="board_img" alt="" src="https://macinjune.com/wp-content/uploads/2018/06/th_google_image_search_2.jpg">
           		 </div>
           		 <div class="body">
					 <div class="title" name="">제목</div>
					 <div class="content" name="">이곳은 글의  내용이 들어가는 곳입니다.</div>
					 <div class="date" name="">2020년 05월 11일</div>
				 </div>
			</a>	 
				 <div class="footer">
				 	<a href=""><div class="writer">작성자(클릭시 유저 메인으로)</div></a>
				 	<div class="like">좋아요(보여주기만)</div>
				 </div>
        </li>
        
        <li>
           <a href="">
           		 <div class="top">
           		 	<img class="board_img" alt="" src="https://macinjune.com/wp-content/uploads/2018/06/th_google_image_search_2.jpg">
           		 </div>
           		 <div class="body">
					 <div class="title" name="">제목</div>
					 <div class="content" name="">이곳은 글의  내용이 들어가는 곳입니다.</div>
					 <div class="date" name="">2020년 05월 11일</div>
				 </div>
			</a>	 
				 <div class="footer">
				 	<a href=""><div class="writer">작성자(클릭시 유저 메인으로)</div></a>
				 	<div class="like">좋아요(보여주기만)</div>
				 </div>
        </li>
	
		<li>
           <a href="">
           		 <div class="top">
           		 	<img class="board_img" alt="" src="https://macinjune.com/wp-content/uploads/2018/06/th_google_image_search_2.jpg">
           		 </div>
           		 <div class="body">
					 <div class="title" name="">제목</div>
					 <div class="content" name="">이곳은 글의  내용이 들어가는 곳입니다.</div>
					 <div class="date" name="">2020년 05월 11일</div>
				 </div>
			</a>	 
				 <div class="footer">
				 	<a href=""><div class="writer">작성자(클릭시 유저 메인으로)</div></a>
				 	<div class="like">좋아요(보여주기만)</div>
				 </div>
        </li>
        <li>
           <a href="">
           		 <div class="top">
           		 	<img class="board_img" alt="" src="https://macinjune.com/wp-content/uploads/2018/06/th_google_image_search_2.jpg">
           		 </div>
           		 <div class="body">
					 <div class="title" name="">제목</div>
					 <div class="content" name="">이곳은 글의  내용이 들어가는 곳입니다.</div>
					 <div class="date" name="">2020년 05월 11일</div>
				 </div>
			</a>	 
				 <div class="footer">
				 	<a href=""><div class="writer">작성자(클릭시 유저 메인으로)</div></a>
				 	<div class="like">좋아요(보여주기만)</div>
				 </div>
        </li>
        <li>
           <a href="">
           		 <div class="top">
           		 	<img class="board_img" alt="" src="https://macinjune.com/wp-content/uploads/2018/06/th_google_image_search_2.jpg">
           		 </div>
           		 <div class="body">
					 <div class="title" name="">제목</div>
					 <div class="content" name="">이곳은 글의  내용이 들어가는 곳입니다.</div>
					 <div class="date" name="">2020년 05월 11일</div>
				 </div>
			</a>	 
				 <div class="footer">
				 	<a href=""><div class="writer">작성자(클릭시 유저 메인으로)</div></a>
				 	<div class="like">좋아요(보여주기만)</div>
				 </div>
        </li>
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