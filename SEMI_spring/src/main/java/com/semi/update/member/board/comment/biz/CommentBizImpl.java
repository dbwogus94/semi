package com.semi.update.member.board.comment.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.update.member.board.comment.dao.CommentDao;
import com.semi.update.member.board.comment.dto.CommentDto;

@Service
public class CommentBizImpl implements CommentBiz {
	
	@Autowired
	private CommentDao dao;
	
	//private Logger logger = LoggerFactory.getLogger(CommentBizImpl.class);
	
	@Override
	public int count_commentList(CommentDto dto) {
		return dao.count_commentList(dto);
	}
		
	@Override
	public List<CommentDto> commentList(CommentDto dto) {
		return dao.commentList(dto);
	}

	@Override
	public int count_re_commentList(CommentDto dto) {
		return dao.count_re_commentList(dto);
	}
	
	@Override
	public List<CommentDto> re_commentList(CommentDto dto) {
		return dao.re_commentList(dto);
	}

	@Override
	public int commentInsert(CommentDto dto) {
		return dao.commentInsert(dto);
	}

	@Override
	public int commentUpdate(CommentDto dto) {
		return dao.commentUpdate(dto);
	}

	@Override
	public int re_commentInsert(CommentDto dto) {
		return dao.re_commentInsert(dto);
	}

	@Override
	public int re_commentUpdate(CommentDto dto) {
		return dao.re_commentUpdate(dto);
	}

	@Override
	public CommentDto selectLatestInsertOne(CommentDto dto) {
		return dao.selectLatestInsertOne(dto);
	}

	@Override
	public int commentDelete(CommentDto dto) {
		return dao.commentDelete(dto);
	}

	

}
