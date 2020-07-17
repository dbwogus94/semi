package com.semi.update.member.board.comment.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semi.update.member.board.comment.dto.CommentDto;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	private Logger logger = LoggerFactory.getLogger(CommentDaoImpl.class); 
	
	/* 글하나의 댓글 총개수 */
	@Override
	public int count_commentList(CommentDto dto) {
		logger.info("[CommentDaoImpl] count_commentList argument : " + dto);
		return session.selectOne(NANESPACE + "count_commentList", dto);
	}
	/* 글 하나의 댓글 리스트 가져오기(+ 페이징, 대댓글x */
	@Override
	public List<CommentDto> commentList(CommentDto dto) {
		logger.info("[CommentDaoImpl] commentList argument : " + dto);
		return session.selectList(NANESPACE + "commentList", dto);
	}
	/* 글하나의 댓글의 대댓글 총개수  */
	@Override
	public int count_re_commentList(CommentDto dto) {
		logger.info("[CommentDaoImpl] count_re_commentList argument : " + dto);
		return session.selectOne(NANESPACE + "count_re_commentList", dto);
	}
	/* 글 하나의 댓글에 달린 대댓글 리스트 가져오기(+ 페이징) */
	@Override
	public List<CommentDto> re_commentList(CommentDto dto) {
		logger.info("[CommentDaoImpl] re_commentList argument " + dto);
		return session.selectList(NANESPACE + "re_commentList", dto);
	}
	/* 댓글 추가 */
	@Override
	public int commentInsert(CommentDto dto) {
		logger.info("[CommentDaoImpl] commentInsert argument : " + dto);
		return session.insert(NANESPACE + "commentInsert", dto);
	}
	/* 댓글 수정 */
	@Override
	public int commentUpdate(CommentDto dto) {
		logger.info("[CommentDaoImpl] commentUpdate argument : " + dto);
		return session.update(NANESPACE + "commentUpdate", dto);
	}
	/* 대댓글 추가 */
	@Override
	public int re_commentInsert(CommentDto dto) {
		logger.info("[CommentDaoImpl] re_commentInsert argument : " + dto);
		return session.insert(NANESPACE + "re_commentInsert", dto);
	}
	/* 대댓글 수정 */
	@Override
	public int re_commentUpdate(CommentDto dto) {
		logger.info("[CommentDaoImpl] re_commentUpdate argument : " + dto);
		return session.update(NANESPACE + "re_commentUpdate", dto);
	}
	@Override
	public CommentDto selectLatestInsertOne(CommentDto dto) {
		logger.info("[CommentDaoImpl] selectLatestInsertOne argument : " + dto);
		return session.selectOne(NANESPACE + "selectLatestInsertOne", dto);
	}
	
	

}
