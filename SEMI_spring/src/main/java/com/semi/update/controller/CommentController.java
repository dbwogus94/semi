package com.semi.update.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.update.All.pagination.OraclePagination;
import com.semi.update.member.board.comment.biz.CommentBiz;
import com.semi.update.member.board.comment.dto.CommentDto;

@Controller
@RequestMapping("/board/comment/")
public class CommentController {
	
	private Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentBiz commentBiz;
	
	// ALL 비동기
	
	/* **비동기 사용 주의점** 
	 * 
	 * 	# view 로직
	 *  	1. view ajax(비동기) post 방식으로 전송 방식 선택
	 * 		2. 전송시 타입 "Content-type = application/json"으로 추가
	 *  	3. 전송시 body(request payload)에 JSON.stringify()를 이용하여 json형태의 스트링으로 변환하여 전송
	 *  	>>> json을 스트링으로 변환하지 않고 전송하면 request body(request payload)를 사용하는것이 아니라
	 *  		쿼리스트링 방식(request parameter)을 사용하는 것이다. 
	 *  
	 *  # 서버 로직 
	 *		1. 스프링에서 @ResponseBody을 사용하여 비동기 통신을 수행할 수 있다.
	 *		2. view에서 post방식으로 jsonObject를 json스트링으로 변환하여 전송(request payload를 사용한 것)
	 *			이럴때는 @RequestParam 또는  @ModelAttribute을 사용할 수 없다. 
	 *		   	즉, @RequestBody만 사용이 가능
	 *	
	 *		3. 문제는 @RequestBody는 jackson을 사용하여 매핑을 받는다. 
	 *			더 정확히 말하면 jackson은 직열화를 사용하여 json형태의 스트링을 메핑한다.
	 *			즉, 직열화가 되지 못하는 단일 값에는 @RequestBody를 사용하지 못한다. 
	 *			(@RequestBody String boardNo >> 이런 경우 안됨.)
	 *			단일의 값을 받고 싶다면 dto Class를 만들어서 받던가 또는 
	 *			map 컬렉션 클래스를 사용하여 받으면 된다.
	 *
	 *		결론 : json형태의 스트링을 서버에서 받을때 단일 값 OR dto가 없는 다중값을 받고 싶다면 
	 *			dto Class를 새로 만들던가, HashMap를 사용해서 받는다.
	 *			ex) @RequsetBody HashMap<String, String> input   
	 *
	 *
	 *  # 서버에서 비동기전송된 데이터 받는법
	 *		1. 일반적으로 get방식으로 받을때는 @RequestParam을 사용해서 받으면 된.
	 *		2. form태그에서 post로 전송이 될경우 @ModelAttribute를 통하여 받으면 된다. 
	 *		3. {}(jsonObject)가 아닌 단일 값을 post방식으로 보낸다면 @ModelAttribute를 사용하면 된다.    	
	 *		4. {}(jsonObject)로 전송된 값에 대응하는 dto가 있다면 @RequestBody를 사용하여 받으면 된다.
	 * 		>>> 이는  jackson에 의해 jsonObject에 대응하는 dto를 찾아서 메핑해주기 때문에 가능하다 
	 * 		>>> 더 정확하게 말하면 dto Class를 직렬화 하여 json에 대응하는 dto를 찾아서 메핑이 되는것이다. 
	 * 
	 */
	
	// 댓글 리스트 보기  >>> 뷰변경
	@RequestMapping(value = "loadCommentList.do", method = RequestMethod.POST) // produces = "application/json; charset=utf8", headers = "content-type=application/json"
	@ResponseBody
	public Map<String, List<CommentDto>> loadCommentList(@RequestBody HashMap<String, Integer> input, CommentDto commentDto){
		//@ModelAttribute("jsonObject") HashMap<String, Integer> jsonObject
		logger.info("[ajax] load Comment List input : " + input);
		Map<String, List<CommentDto>> output = new HashMap<String, List<CommentDto>>();
		
		// 답글 dto에 부모글 시퀀스 추가
		commentDto.setBoardNo(input.get("boardNo"));
		// 부모글의 답글 총개수 
		int totalComment = commentBiz.count_commentList(commentDto); 
				
		// 페이징 객체 생성(화면에 출력할 답글수(10, 출력될 페이지(여기서 필요없음), 총개수, 현재 페이지(1)
		OraclePagination pagination = new OraclePagination(10, 10, totalComment, input.get("currentPage"));
				
		// 페이징 시작번호
		commentDto.setStartBoardNo(pagination.getStartBoardNo());
		// 페이징 끝번호
		commentDto.setEndBoardNo(pagination.getEndBoardNo());
				
		// 현재 게시물의 댓글 가져오기
		List<CommentDto> commentList = commentBiz.commentList(commentDto);
				
		output.put("commentList", commentList);
		logger.info("[ajax] load Comment List output : " + output);
		return output;
	}
	
	// 댓글 리스트 보기  >>> 뷰변경
	@RequestMapping(value = "loadCommentList_GET.do", method = RequestMethod.GET) // produces = "application/json; charset=utf8", headers = "content-type=application/json"
	@ResponseBody
	public Map<String, List<CommentDto>> loadCommentList_GET(int boardNo, int currentPage, CommentDto commentDto){
		//@ModelAttribute("jsonObject") HashMap<String, Integer> jsonObject
		logger.info("[ajax] load Comment List input : " + boardNo);
		Map<String, List<CommentDto>> output = new HashMap<String, List<CommentDto>>();
		
		// 답글 dto에 부모글 시퀀스 추가
		commentDto.setBoardNo(boardNo);
		// 부모글의 답글 총개수 
		int totalComment = commentBiz.count_commentList(commentDto); 
				
		// 페이징 객체 생성(화면에 출력할 답글수(10, 출력될 페이지(여기서 필요없음), 총개수, 현재 페이지(1)
		OraclePagination pagination = new OraclePagination(10, 10, totalComment, currentPage);
					
		// 페이징 시작번호
		commentDto.setStartBoardNo(pagination.getStartBoardNo());
		// 페이징 끝번호
		commentDto.setEndBoardNo(pagination.getEndBoardNo());
					
		// 현재 게시물의 댓글 가져오기
		List<CommentDto> commentList = commentBiz.commentList(commentDto);
					
		output.put("commentList", commentList);
		logger.info("[ajax] load Comment List output : " + output);
		return output;
	}
	
	// 대댓글 리스트 보기
	
	// 댓글 작성
	
	// 댓글 수정
	
	// 대댓글 작성
	
	// 대댓글 수정
	
	
}
