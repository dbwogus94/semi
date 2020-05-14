package com.semi.update.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.semi.update.member.board.dto.BoardDto;

@Controller
@RequestMapping("/board")
public class BoardController {

	private Logger logger = LoggerFactory.getLogger(BoardController.class);

	// 메인
	@RequestMapping(value = "/main.do")
	public String boardMain(Model model) {
		logger.info("board main page");

		// 게시글리스트 와 페이징 보내야함

		return "board/BOARD_BoardMain";
	}

	// 글쓰기
	@RequestMapping(value = "/write.do")
	public String boardWrite() {
		logger.info("board write page");
		return "board/BOARD_boardwrite";
	}

	// 글취소 >> 이미지 저장으로 인해 저장된 글이 있다면 삭제
	@RequestMapping(value = "/writeCancel.do")
	public String boardWriteCancel() {
		logger.info("board write Cancel");

		// 작성하던 이미지 가져와서 삭제?

		return "redirect:/board/main.do";
	}

	// 썸머노트 이미지
	@ResponseBody
	@RequestMapping(value = "/imgUplod.do")
	public String summernoteImgUpload(@ModelAttribute("file") MultipartFile file, BoardDto dto, HttpSession session) {
		logger.info("[ajax] summernote img Upload : >>>>>>>>>>>>>>>>>>>>>  " + file);
		return "/update/resources/img/mentor/mentor01.png";
	}

	// 비동기 멀티 이미지 업로드
	@ResponseBody
	@RequestMapping(value = "/AjaxFileUplod.do")
	public Map<String, String> AjaxFileUplod(@ModelAttribute("fileArr") MultipartFile[] fileArr, BoardDto dto, HttpSession session) {
		logger.info("[ajax] Ajax File Uplod : >>>>>>>>>>>>>>>>>>>>>  " + fileArr);
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileArr.length);
		Map<String, String> output = new HashMap<String, String>();
		output.put("msg", "fail");			// 디폴트 fail
		output.put("board_seq", "0");		// 디폴트 0
		
		// 파일 업로드(처음사진은 저장후 썸내일 생성) >> 파일명 배열로 리턴
		
		// 게시판 테이블 이미지 컬럼 + 썸내일 컬럼 저장
		
		// 방금추가한 보드 seq를 알아내야함

		return output;
	}

	// 글쓰기 완료    
	// >> 이미지가 추가되었을 경우 update
	// >> 이미지가 추가되지 않았을 경우 insert

	// 디테일
	// >> 
	
	// 좋아요

	// 수정하기

	// 자신이 쓴 글 리스트 보기

	// 자신이 쓴 글 멀티 삭제
}
