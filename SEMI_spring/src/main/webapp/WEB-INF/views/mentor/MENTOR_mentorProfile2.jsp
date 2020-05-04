<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<fieldset class="container" id="main_container">
	<legend id="title">멘토 프로필</legend>
	<div class="row">	
		<div class="col-md-4"></div>
		<div class="col-md-4" id="imgdiv">
			<img alt="" src="${pageContext.request.contextPath}/resources/img/mentor/mentor01.png" id="mentorImg">
		</div>
		<div class="col-md-4"></div>
	</div>
	<div class="row">	
		<div class="col-md-4"></div>
		<div class="col-md-4" id="imgInsertdiv">
			<input type="button" id="imgInsertbut" value="프로필사진">
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
				<input type="text" class="form-control">
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<input type="text" class="form-control" id="birthdate" placeholder="클릭!" readonly="readonly" onclick="openDate(this)">
			</div>
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
				<input type="text" class="form-control" placeholder="cm">
			</div>
		</div>
		<div class="col-md-4">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="kg">
			</div>
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
				<input type="text" class="form-control" id="AddressID" placeholder="주소 입력을 위해 클릭하세요" readonly="readonly" onclick="addressPopup()">
			</div>
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
				<input type="text" class="form-control" readonly="readonly" onclick="">
			</div>
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
				<input type="text" class="form-control" readonly="readonly" onclick="" value="010">
			</div>
		</div>
		<div class="col-md-5">
			<div class="form-group">
				<input type="text" class="form-control" onclick="" placeholder="'-'제외  8자리 입력 하세요.">
			</div>
		</div>
		<div class="col-md-1">
			<div class="form-group">
				<input type="button" class="btn btn-success" id="overlapBtn" value="중복확인">
			</div>
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
		<div class="row">	
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-body">
					</div>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>	
	</div>
	
	<br/>
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
				<input type="text" class="form-control" onclick="">
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
	
	<!-- 저장하기 -->
	<br/>
	<div class="row" id="save">	
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<div class="form-group">
				<input type="button" class="btn btn-success" value="저장하기">
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