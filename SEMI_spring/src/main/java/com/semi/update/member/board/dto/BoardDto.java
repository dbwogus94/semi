package com.semi.update.member.board.dto;

import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardDto {
	// 게시판 기능들 

	    // 전체 글번호 
	    private int boardNo;

	    // 아이디
	    private String id;   
	    
	    // 작성자 
	    private String memberName;

	    // 제목
	    private String boardTitle;

	    // 내용
	    private String boardContent;

	    // 작성일
	    private Date boardRegdate;

	    // 좋아요
	    private int boardLike;
	    
	    // 파일경로 (&& 구분)
	    private String filePath;
	    
	    // 이미지 경로(&& 구분)
	    private String imgPath;
	    
	    // 썸내일 경로
	    private String thumbnail;
	    
	    // 스프링에서 지원하는 multipart
	    private MultipartFile[] file;
	    
	    /*---------- 검색 ----------*/
	    private String category;
	    private String keyword;
	    
	    // 페이징
	    

	    // 기본생성자
		public BoardDto() {
			super();
		}

		public int getBoardNo() {
			return boardNo;
		}

		public void setBoardNo(int boardNo) {
			this.boardNo = boardNo;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public String getBoardTitle() {
			return boardTitle;
		}

		public void setBoardTitle(String boardTitle) {
			this.boardTitle = boardTitle;
		}

		public String getBoardContent() {
			return boardContent;
		}

		public void setBoardContent(String boardContent) {
			this.boardContent = boardContent;
		}

		public Date getBoardRegdate() {
			return boardRegdate;
		}

		public void setBoardRegdate(Date boardRegdate) {
			this.boardRegdate = boardRegdate;
		}

		public int getBoardLike() {
			return boardLike;
		}

		public void setBoardLike(int boardLike) {
			this.boardLike = boardLike;
		}

		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public String getImgPath() {
			return imgPath;
		}

		public void setImgPath(String imgPath) {
			this.imgPath = imgPath;
		}

		public String getThumbnail() {
			return thumbnail;
		}

		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}

		public MultipartFile[] getFile() {
			return file;
		}

		public void setFile(MultipartFile[] file) {
			this.file = file;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		@Override
		public String toString() {
			return "BoardDto [boardNo=" + boardNo + ", id=" + id + ", memberName=" + memberName + ", boardTitle="
					+ boardTitle + ", boardContent=" + boardContent + ", boardRegdate=" + boardRegdate + ", boardLike="
					+ boardLike + ", filePath=" + filePath + ", imgPath=" + imgPath + ", thumbnail=" + thumbnail
					+ ", file=" + Arrays.toString(file) + ", category=" + category + ", keyword=" + keyword + "]";
		}


}
	  
	    
	    
