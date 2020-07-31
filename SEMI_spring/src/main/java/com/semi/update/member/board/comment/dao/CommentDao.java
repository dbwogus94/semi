package com.semi.update.member.board.comment.dao;

import java.util.List;

import com.semi.update.member.board.comment.dto.CommentDto;

public interface CommentDao {
	String NANESPACE= "com.semi.update.mapper.Comment.";
	
	// 글하나의 댓글 총개수
	public int count_commentList(CommentDto dto);
	
	// 글 하나의 댓글만 가져오기 + 페이징
	public List<CommentDto> commentList(CommentDto dto);
	
	// 글하나의 댓글 총개수
	public int count_re_commentList(CommentDto dto);
	
	// 글하나의 댓글에 해당 대댓글 + 페이징
	public List<CommentDto> re_commentList(CommentDto dto);
	
	// 댓글 작성
	public int commentInsert(CommentDto dto);
	
	// 댓글 수정
	public int commentUpdate(CommentDto dto);
	
	// 대댓글 작성
	public int re_commentInsert(CommentDto dto);
		
	// 대댓글 수정
	public int re_commentUpdate(CommentDto dto);
		
	// 자신이 가장 최근에 작성한 댓글 하나 
	public CommentDto selectLatestInsertOne(CommentDto dto);
	
	// 댓글 삭제
	public int commentDelete(CommentDto dto);
}
