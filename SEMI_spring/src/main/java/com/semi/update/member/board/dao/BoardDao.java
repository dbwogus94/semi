package com.semi.update.member.board.dao;

import java.util.List;

import com.semi.update.member.board.dto.BoardDto;

public interface BoardDao {
	String NANESPACE= "com.semi.update.mapper.Board.";
	
	// 이미지 추가
	public int insertImg(BoardDto dto);
	
	// id와 썸내일명으로 boardseq 가져오기
	public String getBoardNo(BoardDto dto);
	
	// 이미지 추가후 나머지 내용 update
	public int updateRestContent(BoardDto dto);
	
	// 이미지 추가 안했을때 board insert
	public int insertNoImgBoard(BoardDto dto);
	
	//전체 선택(페이징) + 검색
	public List<BoardDto> boardList(BoardDto dto);
}
