// 전역변수 : 요청할 다음페이지 
let currentPage = 0;

/* 스크롤 페이징 */
window.onscroll = function(){
	// 현재 보이는 창 높이
	let window_innerHeight = window.innerHeight;
	// 스크롤 위치
	let nowScroll = window.scrollY || document.documentElement.scrollTop;
	// 보여지는 html body 높이
	let fullHeight = document.body.scrollHeight;
	
	// 스크롤이 가장 바닦에 다았을때
	if((window_innerHeight + nowScroll) >= fullHeight){
		let boardNo = document.getElementById("boardNo").value
		console.log(boardNo)
		
		currentPage++;
		
		// boardNo =	Number(boardNo) or board *= 1
		// 비동기로 서버에 요청시 사용할 데이터
		let jsonObject = {boardNo:Number(boardNo), currentPage:currentPage}
		
		//post
		const xhr = new Xhr("comment/loadCommentList.do", "POST", "json", "application/json; charset=utf-8", jsonObject)
		xhr.async_POST(loadCommentList)
		
		//get
		//const xhr = new Xhr("comment/loadCommentList_GET.do", undefined, "json", undefined, jsonObject)
		//xhr.async_GET(loadCommentList)
		
		//xhr.url = "새로운 url"		// setter
		//console.log(xhr.method);	// getter
		
	}
}

// 콜백에 사용된 함수
function loadCommentList(response){	
	console.log(response.commentList)
	let commentList = response.commentList 

	for(let i = 0; i<commentList.length; i++){
		
		// 그룹번호가 0이면 댓글 그 이상이면 대댓글
		if(commentList[0].commentGroupSeq <= 0){
			// 댓글 생성
			makeComment(commentList[i])
			if(i == (commentList.length-1)){
				noneRecomment()	 // 댓글 0이면 답글 보기 숨기기
				format_time()	 // 작성, 수정날짜
			}
		} else {
			// 대댓글 생성
			makeReComment(commentList[i])
			if(i == (commentList.length-1)){
				
			}
		}
	}

}


//댓글 0이면 답글 보기 숨기기
function noneRecomment(){
	let reCommentCount = document.getElementsByClassName("reCommentCount");
	let comment_bottom = document.getElementsByClassName("comment_bottom");
	for(i in reCommentCount){
		
		if(reCommentCount[i].value == 0){
			console.log(comment_bottom[i])
			comment_bottom[i].classList.toggle("hidden");
		}
	}
}


//스크립트 Date객체를 받아서 날짜 포멧 변경
function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '/' + month + '/' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}


//작성일과 수정일 일치하지않으면 수정일 붙이고 수정일로 변경
function format_time(){
	const commentBody = document.getElementById("comment_body"); 
	const insert_time = commentBody.getElementsByClassName("insert_time");
	const update_time = commentBody.getElementsByClassName("update_time");
	const time = commentBody.getElementsByClassName("time");
	
	for(i in time){
		if(insert_time[i].innerHTML == update_time[i].innerHTML){
			let date = new Date(Number(insert_time[i].innerHTML));		// 타입스텝프를 이용해 js의 날짜 객체 변환 
			time[i].innerHTML = "작성일 : " + getFormatDate(date);
		} else {
			let date = new Date(Number(update_time[i].innerHTML));
			time[i].innerHTML = "수정일 : " + getFormatDate(date);
		} 
	}
}

//본문 글자 일정글자수 보다 크면 자세히보기 만들기 
function commnet_contentCut(){
	//let comment_content = 
	
	`<a class="comment_content_aTag">자세히 보기</a>`
}

//추가 클릭 페이징




/* 대댓글 보이기 이벤트 */
function show_ReComment(){
	// 이벤트를 발생시킨 주체 => a태그
	let target = event.target;
	// 필요한 요소가 전부 들어있는 div
	let comment_body = document.querySelector("#comment_body");
	// 필요한 요소
	let comment_aTag = comment_body.querySelectorAll(".comment_aTag");
	let comment_aTag_close = comment_body.querySelectorAll(".comment_aTag_close"); 
	let div_reComment = comment_body.querySelectorAll(".div_reComment");
	
	// 값조회에 필요한 현재 글 번호, 현재 댓글그룹번호
	let boardNo = document.getElementById("boardNo").value
	let commentGroupNo = comment_body.querySelectorAll(".commentGroupNo")
	
	for (i in comment_aTag){
		// 현재 이벤트 발생시킨 a태그의 번지 찾기
		if(target === comment_aTag[i] || target === comment_aTag_close[i]){
			
			// comment_aTag(대댓글 개수) <-> comment_aTag_close(답글 숨기기) 
			comment_aTag[i].classList.toggle("hidden");
			comment_aTag_close[i].classList.toggle("hidden");
			
			
			// 비동기로 서버에 요청시 사용할 데이터 : 현재 글 번호, 요청할 페이지, 현재 댓글그룹번호
			let currentPage = 1;
			let jsonObject = {boardNo:Number(boardNo), currentPage:currentPage, commentGroupNo:commentGroupNo[i].value}
			console.log("대댓글 요청")
			console.log(jsonObject)
			
			// 비동기 대댓글 가져오기  : 조회 > 결과 받기 > 콜백함수 loadCommentList(response)를 이용해서 그린다
			console.log(div_reComment[i].querySelectorAll('.row')[0])
			
			if(div_reComment[i].querySelectorAll('.row')[0] === undefined){
				const xhr = new Xhr("comment/loadReCommentList.do", "POST", "json", "application/json; charset=utf-8", jsonObject)
				xhr.async_POST(loadCommentList)
			}
			
			// 대댓글 보기기
			div_reComment[i].classList.toggle("show");
			
			break;
		}
	}
}


//댓글
function makeComment(commentDto){
	// 생성 위치
	const commentBody = document.getElementById("comment_body"); 
	commentBody.innerHTML += `
			<div class="row">
			 	
				 <!-- 댓글 -->
				<div class="div_comment">
					<input type="hidden" class="commentGroupNo" name="commentGroupNo" value="${commentDto.commentGroupNo}"/>
				 	<div class="row">
						<div class="col-md-1">
							<div class="profile_top">
								<div class="profile_mid">
									<img alt=""	src="${commentDto.profileImg}" onerror="this.src='/update/resources/img/user.png';">
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class ="comment_top">
								<span class="writer">${commentDto.commentName}&nbsp;&nbsp;&nbsp;</span><span class="time"></span>
								<span class="insert_time hidden">${commentDto.commentGegdate}</span><span class="update_time hidden">${commentDto.commentUpdateRegDate}</span>
							</div>
							<div class ="comment_content">
								${commentDto.commentContent}<br>
								<a class="comment_content_aTag">자세히 보기</a>
							</div>
							<div class ="comment_mid">추천, <a>답글</a></div>
							<div class ="comment_bottom">
								<a class="comment_aTag" onclick="show_ReComment()">답글 ${commentDto.reCommentCount}개 보기</a>
								<input type="hidden" class="reCommentCount" value="${commentDto.reCommentCount}"/>
								<a class="comment_aTag_close hidden" onclick="show_ReComment()">답글 숨기기</a>
							</div>
							
						</div>
					</div>
				</div>	
				
				<div class="div_reComment"></div> `
}

//대댓글 
function makeReComment(commentDto){
	// 자신이 그려져야 하는 위치? >> 그룹번호 >> commentDto안에 있음
	let comment_body = document.querySelector("#comment_body");
	let commentGroupNo_List = comment_body.querySelectorAll(".commentGroupNo");
	let div_reComment_List = comment_body.querySelectorAll(".div_reComment");
	
	for (i in commentGroupNo_List){
		if(commentDto.commentGroupNo == commentGroupNo_List[i].value){
			div_reComment_List[i].innerHTML += `
				<div class="row">
						<div class="col-md-1">
							<div class="profile_top">
								<div class="profile_mid">
									<img alt=""	src="">
								</div>
							</div>
						</div>
							
						<div class="col-md-11">
							<div class="col-md-1">
								<div class="profile_top">
									<div class="profile_mid">
										<img alt=""	src="${commentDto.profileImg}" onerror="this.src='/update/resources/img/user.png';">
									</div>
								</div>
							</div>
							<div class="col-md-11">
								<div class ="comment_top">
									<span class="writer">${commentDto.commentName}&nbsp;&nbsp;&nbsp;</span><span class="time"></span>
									<span class="insert_time hidden">${commentDto.commentGegdate}</span><span class="update_time hidden">${commentDto.commentUpdateRegDate}</span>
								</div>
								<div class ="reComment_content">
									${commentDto.commentContent}<br>
									<a class="comment_content_aTag">자세히 보기</a>
								</div>
								<div class ="comment_mid">추천, <a>답글</a></div>
							</div>
						</div>
					</div>
				 </div> `
			break;
		}
	}
}








