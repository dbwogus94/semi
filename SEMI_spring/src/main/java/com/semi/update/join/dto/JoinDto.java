package com.semi.update.join.dto;

import java.util.Date;

public class JoinDto {
	// 회원가입(관리자계정 디폴트로 포함)

	// 시퀀스
	private int joinNo;
	// 아이디
	private String id;
	// P W
	private String joinPw;
	// 이메일
	private String joinEmail;
	// 멘토 OR 멘티
	private String joinRole;
	// 가입일
	private Date joinDate;
	// 가입유무
	private String joinJoined;
	// 멘티 멘토 프로필 작성에 따른 활성화 
	private String joinRegisterYn;
	
	public JoinDto() {
		
	}
	
	public JoinDto(String id, String joinPw) {
		this.id = id;
		this.joinPw = joinPw;
	}
	
	public JoinDto(String id, String joinPw, String joinEmail, String joinRole) {
		this.id = id;
		this.joinPw = joinPw;
		this.joinEmail = joinEmail;
		this.joinRole = joinRole;
	}
	
	public JoinDto(int joinNo, String id, String joinPw, String joinEmail, String joinRole, Date joinDate,
			String joinJoined, String joinRegisterYn) {
		this.joinNo = joinNo;
		this.id = id;
		this.joinPw = joinPw;
		this.joinEmail = joinEmail;
		this.joinRole = joinRole;
		this.joinDate = joinDate;
		this.joinJoined = joinJoined;
		this.joinRegisterYn = joinRegisterYn;
	}

	public int getJoinNo() {
		return joinNo;
	}

	public void setJoinNo(int joinNo) {
		this.joinNo = joinNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJoinPw() {
		return joinPw;
	}

	public void setJoinPw(String joinPw) {
		this.joinPw = joinPw;
	}

	public String getJoinEmail() {
		return joinEmail;
	}

	public void setJoinEmail(String joinEmail) {
		this.joinEmail = joinEmail;
	}

	public String getJoinRole() {
		return joinRole;
	}

	public void setJoinRole(String joinRole) {
		this.joinRole = joinRole;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getJoinJoined() {
		return joinJoined;
	}

	public void setJoinJoined(String joinJoined) {
		this.joinJoined = joinJoined;
	}

	public String getJoinRegisterYn() {
		return joinRegisterYn;
	}

	public void setJoinRegisterYn(String joinRegisterYn) {
		this.joinRegisterYn = joinRegisterYn;
	}

	@Override
	public String toString() {
		return "JoinDto [joinNo=" + joinNo + ", id=" + id + ", joinPw=" + joinPw + ", joinEmail=" + joinEmail
				+ ", joinRole=" + joinRole + ", joinDate=" + joinDate + ", joinJoined=" + joinJoined
				+ ", joinRegisterYn=" + joinRegisterYn + "]";
	}

	

	
}
