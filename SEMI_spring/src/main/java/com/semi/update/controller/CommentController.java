package com.semi.update.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
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
	
	// 댓글 리스트 보기  >>> 뷰변경
	@RequestMapping(value = "loadCommentList.do", method = {RequestMethod.GET, RequestMethod.POST}) // produces = "application/json; charset=utf8", headers = "content-type=application/json"
	@ResponseBody
	public Map<String, List<CommentDto>> loadCommentList(@RequestParam("boardNo") int boardNo, @RequestParam("currentPage") int currentPage, CommentDto commentDto){
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
