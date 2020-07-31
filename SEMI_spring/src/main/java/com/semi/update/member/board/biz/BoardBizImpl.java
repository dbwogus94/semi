package com.semi.update.member.board.biz;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.update.member.board.dao.BoardDao;
import com.semi.update.member.board.dto.BoardDto;

@Service
public class BoardBizImpl implements BoardBiz {

	@Autowired
	private BoardDao dao;
	
	private Logger logger = LoggerFactory.getLogger(BoardBizImpl.class);
	
	// 이미지 추가
	@Override
	public int insertImg(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> insertImg : " + dto);
		return dao.insertImg(dto);
	}

	// id와 썸내일 명으로 boardNo 가져오기
	@Override
	public String getBoardNo(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> getBoardNo : " + dto);
		return dao.getBoardNo(dto);
	}

	@Override
	public int updateRestContent(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> updateRestContent : " + dto);
		return dao.updateRestContent(dto);
	}

	@Override
	public int insertNoImgBoard(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> insertNoImgBoard : " + dto);
		return dao.insertNoImgBoard(dto);
	}

	// 게시물 개수 가져오기(검색 포함
	@Override
	public int getTotalBoard(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> getTotalBoard : " + dto);
		return dao.getTotalBoard(dto);
	}
	
	//전체 선택(페이징) + 검색
	@Override
	public List<BoardDto> boardList(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>> boardList : " + dto);
		return dao.boardList(dto);
	}

	// select one (작성자 프로필 사진경로 포함)
	@Override
	public BoardDto selectOne(int boardNo) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>> boardList : " + boardNo);
		return dao.selectOne(boardNo);
	}

	@Override
	public int updateImg(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>> updateImg : " + dto);
		return dao.updateImg(dto);
	}
	
	@Override
	public int updateNoImgBoard(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>> updateNoImgBoard : " + dto);
		return dao.updateNoImgBoard(dto);
	}

	@Override
	public int updateBoard(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>>> updateBoard : " + dto);
		return dao.updateBoard(dto);
	}

	// 유저가 작성한 게시글 총 개
	@Override
	public int getMyTotalBoard(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>> getMyTotalBoard : " + dto);
		return dao.getMyTotalBoard(dto);
	}
	
	// 유저가 작성한 게시물 리스트
	@Override
	public List<BoardDto> myBoardList(BoardDto dto) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>> myBoardList : " + dto);
		return dao.myBoardList(dto);
	}

	@Override
	public int multiBoardDelete(int[] boardNoArr) {
//		logger.info("[BoardBizImpl] >>>>>>>>>>>>> multiBoardDelete : " + Arrays.toString(boardNoArr));
		return dao.multiBoardDelete(boardNoArr);
	}

}
