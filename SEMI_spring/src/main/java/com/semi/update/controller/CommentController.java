package com.semi.update.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.update.member.board.comment.biz.CommentBiz;

@Controller
@RequestMapping("/board/comment/")
public class CommentController {
	
private Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentBiz commentBiz;
	
	// ALL 비동기
	
	// 댓글 리스트 보기
	
	// 대댓글 리스트 보기
	
	// 댓글 작성
	
	// 댓글 수정
	
	// 대댓글 작성
	
	// 대댓글 수정
	
	
}
