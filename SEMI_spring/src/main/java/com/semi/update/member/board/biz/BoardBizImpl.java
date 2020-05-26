package com.semi.update.member.board.biz;

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
		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> insertImg : " + dto);
		return dao.insertImg(dto);
	}

	// id와 썸내일 명으로 boardNo 가져오기
	@Override
	public String getBoardNo(BoardDto dto) {
		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> getBoardNo : " + dto);
		return dao.getBoardNo(dto);
	}

	@Override
	public int updateRestContent(BoardDto dto) {
		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> updateRestContent : " + dto);
		return dao.updateRestContent(dto);
	}

	@Override
	public int insertNoImgBoard(BoardDto dto) {
		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>>> insertNoImgBoard : " + dto);
		return dao.insertNoImgBoard(dto);
	}

	//전체 선택(페이징) + 검색
	@Override
	public List<BoardDto> boardList(BoardDto dto) {
		logger.info("[BoardBizImpl] >>>>>>>>>>>>>>> boardList : " + dto);
		return dao.boardList(dto);
	}

	// select one (작성자 프로필 사진경로 포함)
	@Override
	public BoardDto selectOne(int boardNo) {
		logger.info("[BoardBizImpl] >>>>>>>>>>>>>> boardList : " + boardNo);
		return dao.selectOne(boardNo);
	}

}
