<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/BOARD_boardDetail.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board/BOARD_boardComment.css">
<title>게시글확인</title>
<%@ include file="../mentor/mentorHeader.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/BOARD_boardDetail.js"></script>

<!-- comment -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Xhr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/myUtil.js"></script>
<script defer type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/BOARD_boardComment.js"></script>

</head>

<body>
	<input type="hidden" name="boardNo" value="${board.boardNo }" id="boardNo">
	<input type="hidden" id="id" name="id" value="${board.id }"/>
	<input type="hidden" id="myId" name="myId" value="${login.id }"/>
	<div class="row" >
		<div class="col-md-12" id="headTitle">
			<hr>
			<br>
			<h2>${board.boardNo}번 글 상세</h2>
			<c:if test="${board.id eq login.id}">
				<input type="button" class="button" value="글 삭제" onclick="deleteOne('${login.id }', '${board.id }','${board.boardNo}')"/>
			</c:if>	
			<input type="button" class="button" value="목록으로" onclick="location.href='main.do'"/>
			<input type="button" class="button" value="수정하기" onclick="authorityChk('${login.id }', '${board.id }', ${board.boardNo })"/>
			<br>
			<hr>
		</div>
	</div>
	<div id="container">
		<div id="board">
			<div id="content">
				<div id="cdetail">
		  			<input type="text" id="title" name="boardTitle" value="${board.boardTitle }" readonly="readonly"/>
					<br>
					<div id="cdetail">
						<span><img src="${board.profileSrc }" onerror="this.src='/update/resources/img/user.png';"></span><span>${board.memberName }</span>
						<span id="fileDown">
							<c:if test="${empty board.filePath }">
								첨부파일 없음
							</c:if>
							<c:if test="${not empty board.filePath }">
								${board.filePath }
							</c:if>
							<input type="button" value="첨부파일 확인" class="button" onclick="fileDetail('${board.boardNo }');"/>
						</span>	
					</div>
					<br>
					<br>
					<hr>
					<div class="cdetail2">
						<div class="blank"></div>
						<div id="ctext">
							${board.boardContent }
						</div>
						<div id="btn2">
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
	
	<div class="container" id="comment-container">
		<input type="hidden" id="currentPage" name="currentPage" value="0"/>
		<div class="row">
			<div class="col-md-2">댓 글</div>
			<div class="col-md-10"></div>
		</div>
		
		<!-- 댓글 입력 -->
		<div class="row">
			<div class="col-md-11">
				<div id="commentContent" contenteditable="true" placeholder="바르고 고운말을 사용합시다." onkeydown="countWord();" onkeyup="countWord();"></div>
<!-- 				<input type="text" class="form-control" name="commentContent" placeholder="바르고 고운말을 사용합시다."  onkeypress="if(event.keyCode == 13) {inputComment();}" onkeydown="countWord();" onkeyup="countWord();"> -->
				<span id="countWord">0/500</span>
			</div>
			<div class="col-md-1">
				<button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="inputComment()">입  력</button>
			</div>
		</div>
		
	
		<!-- 댓글 view -->
		<div id="comment_body"></div>
 			<br><br>
	</div>
			
		
		
			
<!-- 			<div class="row"> -->
			 	
<!-- 				 댓글 -->
<!-- 				<div class="div_comment"> -->
<%-- 					<input type="hidden" class="commentGroupNo" name="commentGroupNo" value="${commentDto.commentGroupNo}"/> --%>
<!-- 				 	<div class="row"> -->
<!-- 						<div class="col-md-1"> -->
<!-- 							<div class="profile_top"> -->
<!-- 								<div class="profile_mid"> -->
<%-- 									<img alt=""	src="${commentDto.profileImg}" onerror="this.src='/update/resources/img/user.png';"> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-11"> -->
<!-- 							<div class ="comment_top"> -->
<%-- 								<span class="writer">${commentDto.commentName}&nbsp;&nbsp;&nbsp;</span><span class="time"></span> --%>
<%-- 								<span class="insert_time hidden">${commentDto.commentGegdate}</span><span class="update_time hidden">${commentDto.commentUpdateRegDate}</span> --%>
<!-- 							</div> -->
<!-- 							<div class ="comment_content"> -->
<%-- 								${commentDto.commentContent}<br> --%>
<!-- 								<a class="comment_content_aTag">자세히 보기</a> -->
<!-- 							</div> -->
<!-- 							<div class ="comment_mid">추천, <a>답글</a></div> -->
<!-- 							<div class ="comment_bottom"> -->
<%-- 								<a class="comment_aTag" onclick="show_ReComment()">답글 ${commentDto.reCommentCount}개 보기</a> --%>
<%-- 								<input type="hidden" class="reCommentCount" value="${commentDto.reCommentCount}"/> --%>
<!-- 								<a class="comment_aTag_close hidden" onclick="show_ReComment()">답글 숨기기</a> -->
<!-- 							</div> -->
							
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div>	 -->
				
					
<!-- 				대댓 -->
<!-- 				<div class="div_reComment"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-1"> -->
<!-- 							<div class="profile_top"> -->
<!-- 								<div class="profile_mid"> -->
<!-- 									<img alt=""	src=""> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
							
<!-- 						<div class="col-md-11"> -->
<!-- 							<div class="col-md-1"> -->
<!-- 								<div class="profile_top"> -->
<!-- 									<div class="profile_mid"> -->
<!-- 										<img alt=""	src=""> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-11"> -->
<!-- 								<div class ="comment_top"> -->
<!-- 									<span class="writer">작성자</span><span class="time">작성일</span> -->
<!-- 								</div> -->
<!-- 								<div class ="comment_content"> -->
<!-- 									대댓글 내용 -->
<!-- 									<br> -->
<!-- 									<br>	 -->
<!-- 									<a>자세히 보기</a> -->
<!-- 								</div> -->
<!-- 								<div class ="comment_mid">추천, 답글</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				 </div> -->
				
<!-- 			</div> -->

		
		
		
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${empty comment}"> --%>
<!-- 			<div class="row">	 -->
<!-- 				<div class="col-md-12"> -->
<!-- 						==========================댓글이 없습니다.========================== -->
<!-- 				</div> -->
<!-- 			</div> -->
<%-- 		</c:when> --%>
			
<%-- 		<c:otherwise> --%>
<%-- 			<c:forEach items="${comment }" var="commentDto"> --%>
<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-1"> -->
<!-- 						<div class="profile_top"> -->
<!-- 							<div class="profile_mid"> -->
<%-- 								<img alt=""	src="${commentDto.profileImg }" onerror="this.src='/update/resources/img/user.png';"> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-11"> -->
<!-- 						<div class ="comment_top"> -->
<%-- 							<span class="writer">${commentDto.commentName }</span><span class="date">${commentDto.commentGegdate }</span> --%>
<!-- 						</div> -->
<!-- 						<div class ="comment_content"> -->
<%-- 							${commentDto.commentContent } --%>
<!-- 							<br> -->
<!-- 							<br>	 -->
<!-- 							<a>자세히 보기</a> -->
<!-- 						</div> -->
<!-- 						<div class ="comment_mid">추천, 답글</div> -->
<%-- 						<div class ="comment_bottom"><a>대댓글 있을시 표시 : ${commentDto.reCommentCount } </a></div> --%>
<!-- 					</div> -->
<!-- 				</div>	 -->
<%-- 			</c:forEach> --%>
<%-- 		</c:otherwise> --%>
<%-- 	</c:choose> --%>
			
		
		

	
	<!-- 모달 영역 -->
	<div class="modal fade" id="boardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">첨부 파일 리스트</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="yes-btn"
						onclick="fileDownChk();">선택 다운로드</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 경고 메세지 모달 영역 -->
	<div class="modal fade" id="warningMsg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="warningMsg-yes-btn">확인</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>