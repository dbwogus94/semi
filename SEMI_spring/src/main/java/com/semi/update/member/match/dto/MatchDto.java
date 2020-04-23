package com.semi.update.member.match.dto;

public class MatchDto {
	private int matchNo;
	private String matchMenteeId;
	private String matchMentorId;
	
	public MatchDto() {
		
	}

	public MatchDto(int matchNo, String matchMenteeId, String matchMentorId) {
		this.matchNo = matchNo;
		this.matchMenteeId = matchMenteeId;
		this.matchMentorId = matchMentorId;
	}

	public int getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}

	public String getMatchMenteeId() {
		return matchMenteeId;
	}

	public void setMatchMenteeId(String matchMenteeId) {
		this.matchMenteeId = matchMenteeId;
	}

	public String getMatchMentorId() {
		return matchMentorId;
	}

	public void setMatchMentorId(String matchMentorId) {
		this.matchMentorId = matchMentorId;
	}


	@Override
	public String toString() {
		return "MatchDto [matchNo=" + matchNo + ", matchMenteeId=" + matchMenteeId + ", matchMentorId=" + matchMentorId
				+ ", memberRole=" + "]";
	}
	
	

}

