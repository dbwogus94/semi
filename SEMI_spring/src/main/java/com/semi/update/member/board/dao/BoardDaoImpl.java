package com.semi.update.member.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semi.update.member.board.dto.BoardDto;


@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
	private SqlSessionTemplate session;

	private Logger logger = LoggerFactory.getLogger(BoardDaoImpl.class);
	
	// 이미지 추가
	@Override
	public int insertImg(BoardDto dto) {
		logger.info("[BoardDaoImpl] >>>>>>>>>>>>>>>> insertImg : " + dto);
		return session.insert(NANESPACE + "insertImg", dto);
	}

	// id와 썸내일 명으로 boardNo 가져오기
	@Override
	public String getBoardNo(BoardDto dto) {
		logger.info("[BoardDaoImpl] >>>>>>>>>>>>>>>> getBoardNo : " + dto);
		BoardDto res = session.selectOne(NANESPACE +"getBoardNo", dto);
		return Integer.toString(res.getBoardNo());
	}

	// 이미지 추가후 나머지 내용 update
	@Override
	public int updateRestContent(BoardDto dto) {
		logger.info("[BoardDaoImpl] >>>>>>>>>>>>>>> updateRestContent : " + dto);
		return session.update(NANESPACE + "updateRestContent", dto);
	}

	// 이미지 추가 안했을때 board insert
	@Override
	public int insertNoImgBoard(BoardDto dto) {
		logger.info("[BoardDaoImpl] >>>>>>>>>>>>>> insertNoimgBoard : " + dto);
		return session.insert(NANESPACE + "insertNoImgBoard", dto);
	}

	//전체 선택(페이징) + 검색
	@Override
	public List<BoardDto> boardList(BoardDto dto) {
		logger.info("[BoardDaoImpl] >>>>>>>>>>>>>>> boardList : " + dto);
		return session.selectList(NANESPACE + "boardList" , dto);
	}

	// select one (작성자 프로필 사진경로 포함)
	@Override
	public BoardDto selectOne(int boardNo) {
		logger.info("[BoardDaoImpl] >>>>>>>>>>>>>> selectOne : " + boardNo);
		return session.selectOne(NANESPACE + "selectOne", boardNo);
	}
	
}
