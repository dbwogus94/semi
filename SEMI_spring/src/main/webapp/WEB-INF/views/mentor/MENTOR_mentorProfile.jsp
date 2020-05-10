<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mentor/MENTOR_mentorProfile.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mentor/MENTOR_mentorProfile.js"></script>
<!-- flatpickr 데이트 피커  -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<!-- flatpickr 피커 추가 테마 중 dark.css 사용 -->
<link rel="stylesheet" type="text/css" href="https://npmcdn.com/flatpickr/dist/themes/dark.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 헤더 -->
<%@ include file="../mentor/mentorHeader.jsp"%>

</head>

	
<body>
<f:form action="profileInsert.do" method="post" modelAttribute="mentorDto" enctype="multipart/form-data" onsubmit="return confirmSubmit()">	
	<fieldset class="container" id="main_container">
		<legend id="title">멘토 프로필</legend>
		<f:hidden path="id"/>
		<f:hidden path="memberContent"/>
		<div class="row">	
			<div class="col-md-4"></div>
			<div class="col-md-4" id="imgdiv">
				<c:choose>
					<c:when test="${empty mentorDto.memberContent}">
						<img alt="" src="${pageContext.request.contextPath}/resources/img/mentor/mentor01.png" id="mentorImg">
					</c:when>
					<c:otherwise>
						<img alt="" src="${mentorDto.memberContent}" id="mentorImg">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-md-4"></div>
		</div>
		<div class="row">	
			<div class="col-md-4"></div>
			<div class="col-md-4" id="imgInsertdiv">
				<f:input path="memberFile" id="imgInsertbut" multiple="multiple"  type="file" onclick="imgUpload()" accept="image/gif, image/jpeg, image/png" value="${mentorDto.memberContent}"/>
			</div>
			<div class="col-md-4"></div>
		</div>
		
		
		<!-- 이름  생년월일-->
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8"><h4>이 름 / 생년월일</h4></div>
			<div class="col-md-2"></div>
		</div>		
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<div class="form-group">
 					<f:input path="memberName" cssClass="form-control" placeholder="이름을 입력하세요"/> 
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<f:input path="memberBirth" cssClass="form-control" id="birthdate" placeholder="클릭!" readonly="true" onclick="openDate(this)"/>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>	
		<!-- 이름 / 생년월일 에러메세지 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<f:errors path="memberName" class="errorMsg"/>
			</div>
			<div class="col-md-4">
				<f:errors path="memberBirth" class="errorMsg"/>
			</div>
			<div class="col-md-2"></div>
		</div>
		
		<!-- 키, 몸무게-->
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8"><h4>키 / 몸무게</h4></div>
			<div class="col-md-2"></div>
		</div>		
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<div class="form-group">
					<f:input type="number" path="memberHeight" cssClass="form-control" placeholder="cm"/>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<f:input type="number" path="memberWeight" cssClass="form-control" placeholder="kg"/>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
		
		<!-- 키 / 몸무게 에러메세지 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<f:errors path="memberHeight" cssClass="errorMsg"/>
			</div>
			<div class="col-md-4">
				<f:errors path="memberWeight" cssClass="errorMsg"/>
			</div>
			<div class="col-md-2"></div>
		</div>
			
		
		<!-- 주소 -->
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8"><h4>주 소</h4></div>
			<div class="col-md-2"></div>
		</div>		
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="form-group">
					<f:input path="memberAddr" cssClass="form-control" id="AddressID" placeholder="주소 입력을 위해 클릭하세요" readonly="true" onclick="addressPopup()"/>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
		<!-- 주소 에러메세지 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<f:errors path="memberAddr" cssClass="errorMsg"/>
			</div>
			<div class="col-md-2"></div>
		</div>	
		
		<!-- 이메일 -->
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8"><h4>Email</h4></div>
			<div class="col-md-2"></div>
		</div>		
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="form-group">
					<f:input path="joinEmail" cssClass="form-control" readonly="true" onclick=""/>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
		<!-- 이메일 에러메세지 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<f:errors path="joinEmail" cssClass="errorMsg"/>
			</div>
			<div class="col-md-2"></div>
		</div>	
		
		<!-- 전화번호 -->
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8"><h4>전화번호</h4></div>
			<div class="col-md-2"></div>
		</div>		
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-2">
				<div class="form-group">
					<f:input path="" cssClass="form-control" readonly="true" value="010"/>
				</div>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<f:input path="memberPhone" cssClass="form-control" placeholder="'-'제외  8자리 입력 하세요."/>
				</div>
			</div>
			<div class="col-md-1">
				<div class="form-group">
					<input type="button" class="btn btn-success" id="overlapBtn" value="중복확인">
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
		<!-- 전화번호 에러메세지 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<f:errors path="memberPhone" cssClass="errorMsg"/>
			</div>
			<div class="col-md-2"></div>
		</div>	
		
		<!-- 태그 -->
		<div id="specialty">
			<div class="row">	
				<div class="col-md-2"></div>
				<div class="col-md-8"><h4>특기 태그(최대 4개 선택)</h4></div>
				<div class="col-md-2"></div>
			</div>		
			<div class="row">	
				<div class="col-md-2"></div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="홈트레이닝" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="맨 몸 운 동" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="머 슬 업" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="웨 이 트" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2"></div>
			</div>
			<div class="row">	
				<div class="col-md-2"></div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="요 가" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="필 라 테 스" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="다이어트" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<input type="button" class="btn btn-success" value="스 피 닝" onclick="tagInsert(this);">
					</div>
				</div>
				<div class="col-md-2"></div>
			</div>
			<!-- 태그 결과  -->
			<div class="row">	
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<div class="panel panel-default">
						<div class="panel-body">
<%--  							<h5 class="keyword_h5"><f:label path="memberCareer" class="label label-default"></f:label></h5>  --%>
						</div>
					</div>
				</div>
				<div class="col-md-2"></div>
			</div>	
		</div>
		<!-- 특기태그 에러메세지 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<f:errors path="memberCareer" cssClass="errorMsg"/>
			</div>
			<div class="col-md-2"></div>
		</div>	
		
		<!-- 한줄소개 -->
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8"><h4>한 줄 소개 하기</h4></div>
			<div class="col-md-2"></div>
		</div>		
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="form-group">
					<f:input path="memberOneIntro" cssClass="form-control" onclick=""/>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
		<!-- 한줄소개 에러메세지 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<f:errors path="memberOneIntro" cssClass="errorMsg"/>
			</div>
			<div class="col-md-2"></div>
		</div>	
		
		<!-- 저장하기 -->
		<div class="row" id="save">	
			<div class="col-md-2"></div>
			<div class="col-md-4">
				<div class="form-group">
					<input type="submit" class="btn btn-success" value="저장하기">
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<input type="button" class="btn btn-success" value="탈퇴하기">
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</fieldset>
</f:form>

<!-- 모달 -->
	<div class="modal fade" role="dialog" id="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" align="center">도로명 주소</h3>
				</div>
				<div class="modal-body" align="center">
					
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
</body>
</html>