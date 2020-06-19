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
<title>게시글확인</title>
<%@ include file="../mentor/mentorHeader.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board/BOARD_boardDetail.js"></script>
</head>

<body>
	<input type="hidden" name="boardNo" value="${board.boardNo }" id="boardNo">
	<input type="hidden" name="id" value="${board.id }"/>
	<div class="row" >
		<div class="col-md-12" id="headTitle">
			<hr>
			<br>
			<h2>${board.boardNo}번 글 상세</h2>
			<c:if test="${board.id eq login.id}">
				<input type="button" class="button" value="글 삭제" onclick="deleteOne('${board.boardNo}')"/>
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
		<table class="table table-bordered" class="shadow p-3 mb-5 bg-white rounded">
			<tr>
				<td colspan="4" class="reply-label">댓 글</td>
			</tr>
			<tr class="reply-area">
				<td colspan="4">
					<div class="reply-insert-area">
						<input type="text" class="form-control" placeholder="바르고 고운말을 사용합시다." aria-label="Recipient's username" aria-describedby="button-addon2"
								onkeydown="onKeyDown(${boardDetail.boardseq }, ${boardDetail.groupno });">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="replyInsert(${boardDetail.boardseq });">입  력</button>
						</div>
					</div>
				</td>
			</tr>

		<c:forEach items="${replyList }" var="reply">
			<tr class="reply">
				<th class="reply-email">${reply.joinemail }</th>
				<td class="reply-content">${reply.content }</td>
				<td class="reply-update-delete">
			<c:choose>		
				<c:when test="${reply.joinemail eq login.joinemail }">
					<span><a href="#" onclick="deleteAlert('댓글', ${reply.boardseq });">삭제</a></span>
				</c:when>
				<c:otherwise>
					<span><a href="#" onclick="rereply('${reply.joinemail }', ${reply.boardseq }); " >답글</a></span>
				</c:otherwise>
			</c:choose>
				</td>
				${comment.List }
				<td class="reply-date"><fmt:formatDate value="${reply.regdate}" pattern="yy-MM-dd HH:mm" /></td>
			</tr>
		</c:forEach>	
		</table>
	</div>
	
	
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