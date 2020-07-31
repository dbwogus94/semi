package com.semi.update.member.dto;

import java.util.Date;

public class MenteeDto {

	// 공용
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
		
		
		// 이름
		private String memberName;
		
		
		public MenteeDto() {
			
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


		public String getMemberName() {
			return memberName;
		}


		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}


		@Override
		public String toString() {
			return "MenteeDto [joinNo=" + joinNo + ", id=" + id + ", joinPw=" + joinPw + ", joinEmail=" + joinEmail
					+ ", joinRole=" + joinRole + ", joinDate=" + joinDate + ", joinJoined=" + joinJoined
					+ ", joinRegisterYn=" + joinRegisterYn + ", memberName=" + memberName + "]";
		}
		
		
}
