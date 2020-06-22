package com.semi.update.member.board.comment.dto;

import java.util.Date;

public class CommentDto {

	// 시퀀스
	private int commentNo;

	// 부모글 시퀀스
	private int boardNo;

	// 댓글의 그룹번호
	private int commentGroupNo;

	// 대글 그룹에서 대댓글 순서
	private int commentGroupSeq;

	// 유저 프로필 사진
	private String profileImg;
	
	// 유져 id
	private String id;

	// 작성사
	private String commentName;

	// 내용
	private String commentContent;

	// 작성일
	private Date commentGegdate;

	// 수정일
	private Date commentUpdateRegDate;

	// 댓글이 가지고 있는 대댓글 갯수 따로 조회해서 추가
	private int reCommentCount;
	
	
	/* 페이징  시작번호 끝 번호 */
	private int startBoardNo;
	private int endBoardNo;

	
	
	public CommentDto() {
		
	}

	public CommentDto(int commentNo, int boardNo, int commentGroupNo, int commentGroupSeq, String profileImg, String id,
			String commentName, String commentContent, Date commentGegdate, Date commentUpdateRegDate,
			int reCommentCount, int startBoardNo, int endBoardNo) {
		super();
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		this.commentGroupNo = commentGroupNo;
		this.commentGroupSeq = commentGroupSeq;
		this.profileImg = profileImg;
		this.id = id;
		this.commentName = commentName;
		this.commentContent = commentContent;
		this.commentGegdate = commentGegdate;
		this.commentUpdateRegDate = commentUpdateRegDate;
		this.reCommentCount = reCommentCount;
		this.startBoardNo = startBoardNo;
		this.endBoardNo = endBoardNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCommentGroupNo() {
		return commentGroupNo;
	}

	public void setCommentGroupNo(int commentGroupNo) {
		this.commentGroupNo = commentGroupNo;
	}

	public int getCommentGroupSeq() {
		return commentGroupSeq;
	}

	public void setCommentGroupSeq(int commentGroupSeq) {
		this.commentGroupSeq = commentGroupSeq;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentName() {
		return commentName;
	}

	public void setCommentName(String commentName) {
		this.commentName = commentName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentGegdate() {
		return commentGegdate;
	}

	public void setCommentGegdate(Date commentGegdate) {
		this.commentGegdate = commentGegdate;
	}

	public Date getCommentUpdateRegDate() {
		return commentUpdateRegDate;
	}

	public void setCommentUpdateRegDate(Date commentUpdateRegDate) {
		this.commentUpdateRegDate = commentUpdateRegDate;
	}

	public int getReCommentCount() {
		return reCommentCount;
	}

	public void setReCommentCount(int reCommentCount) {
		this.reCommentCount = reCommentCount;
	}

	public int getStartBoardNo() {
		return startBoardNo;
	}

	public void setStartBoardNo(int startBoardNo) {
		this.startBoardNo = startBoardNo;
	}

	public int getEndBoardNo() {
		return endBoardNo;
	}

	public void setEndBoardNo(int endBoardNo) {
		this.endBoardNo = endBoardNo;
	}

	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", boardNo=" + boardNo + ", commentGroupNo=" + commentGroupNo
				+ ", commentGroupSeq=" + commentGroupSeq + ", profileImg=" + profileImg + ", id=" + id
				+ ", commentName=" + commentName + ", commentContent=" + commentContent + ", commentGegdate="
				+ commentGegdate + ", commentUpdateRegDate=" + commentUpdateRegDate + ", reCommentCount="
				+ reCommentCount + ", startBoardNo=" + startBoardNo + ", endBoardNo=" + endBoardNo + "]";
	}
	

}
