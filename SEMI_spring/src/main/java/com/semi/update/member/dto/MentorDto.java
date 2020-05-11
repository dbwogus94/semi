package com.semi.update.member.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.semi.update.join.dto.JoinDto;

public class MentorDto {

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
	
	
// 멘토
	//멤퍼 no
	private int memberNo;
	// 이름
	@NotEmpty(message = "이름을 입력해주세요")
	@Pattern(regexp = "[가-힣]*$", message = "이름은 한글만 사용해주세요")
	private String memberName;
	
	// 생년월일
	@NotEmpty(message = "생년월일을 입력해주세요")
	private String memberBirth;
	// 키
	@NotEmpty(message = "키를 숫자로 입력해주세요.")
	private String memberHeight;
	// 몸무게
	@NotEmpty(message = "몸무게를 숫자로 입력해주세요.")
	private String memberWeight;
	// 주소
	@NotEmpty(message = "주소를 입력해주세요.")
	private String memberAddr;
	// 전화번호
	@NotEmpty(message = "전화번호를 입력해주세요.")
	private String memberPhone;
	// 한 줄 소개
	@NotEmpty(message = "한 줄 소개를 입력해주세요.")
	private String memberOneIntro;
	// 코인
	private String memberCoin;
	// 특기(경력)
	//@NotEmpty(message = "특기를 1개이상 클릭하세요.")
	private String memberCareer = "기본 특기";
	// 내용(프로필 사진)
	private String memberContent;
	// 총 별점
	private double memberScore;
	
	
	// 스프링에서 제공하는 MultipartFile
	private MultipartFile memberFile;
	
	

	public MentorDto() {
		
	}
	
	public MentorDto(int joinNo, String id, String joinPw, String joinEmail, String joinRole, Date joinDate,
			String joinJoined, String joinRegisterYn, int memberNo,
			@Pattern(regexp = "[가-힣]*$", message = "이름은 한글만 사용해주세요") String memberName, String memberBirth,
			String memberHeight, String memberWeight, String memberAddr, String memberPhone, String memberOneIntro,
			String memberCoin, String memberCareer, String memberContent, double memberScore,
			MultipartFile memberFile) {
		super();
		this.joinNo = joinNo;
		this.id = id;
		this.joinPw = joinPw;
		this.joinEmail = joinEmail;
		this.joinRole = joinRole;
		this.joinDate = joinDate;
		this.joinJoined = joinJoined;
		this.joinRegisterYn = joinRegisterYn;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.memberHeight = memberHeight;
		this.memberWeight = memberWeight;
		this.memberAddr = memberAddr;
		this.memberPhone = memberPhone;
		this.memberOneIntro = memberOneIntro;
		this.memberCoin = memberCoin;
		this.memberCareer = memberCareer;
		this.memberContent = memberContent;
		this.memberScore = memberScore;
		this.memberFile = memberFile;
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

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberHeight() {
		return memberHeight;
	}

	public void setMemberHeight(String memberHeight) {
		this.memberHeight = memberHeight;
	}

	public String getMemberWeight() {
		return memberWeight;
	}

	public void setMemberWeight(String memberWeight) {
		this.memberWeight = memberWeight;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberOneIntro() {
		return memberOneIntro;
	}

	public void setMemberOneIntro(String memberOneIntro) {
		this.memberOneIntro = memberOneIntro;
	}

	public String getMemberCoin() {
		return memberCoin;
	}

	public void setMemberCoin(String memberCoin) {
		this.memberCoin = memberCoin;
	}

	public String getMemberCareer() {
		return memberCareer;
	}

	public void setMemberCareer(String memberCareer) {
		this.memberCareer = memberCareer;
	}

	public String getMemberContent() {
		return memberContent;
	}

	public void setMemberContent(String memberContent) {
		this.memberContent = memberContent;
	}

	public double getMemberScore() {
		return memberScore;
	}

	public void setMemberScore(double memberScore) {
		this.memberScore = memberScore;
	}

	public MultipartFile getMemberFile() {
		return memberFile;
	}

	public void setMemberFile(MultipartFile memberFile) {
		this.memberFile = memberFile;
	}

	// 로그인시 사용되는 joinDto를 사용하여 MentorDto에 값 전달
	public void setDto(JoinDto joinDto) {
		this.joinNo = joinDto.getJoinNo();
		this.id = joinDto.getId();
		this.joinPw = joinDto.getJoinPw();
		this.joinEmail = joinDto.getJoinEmail();
		this.joinRole = joinDto.getJoinRole();
		this.joinDate = joinDto.getJoinDate();
		this.joinJoined = joinDto.getJoinJoined();
		this.joinRegisterYn = joinDto.getJoinRegisterYn();
	}

	@Override
	public String toString() {
		return "MentorDto [joinNo=" + joinNo + ", id=" + id + ", joinPw=" + joinPw + ", joinEmail=" + joinEmail
				+ ", joinRole=" + joinRole + ", joinDate=" + joinDate + ", joinJoined=" + joinJoined
				+ ", joinRegisterYn=" + joinRegisterYn + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", memberBirth=" + memberBirth + ", memberHeight=" + memberHeight + ", memberWeight=" + memberWeight
				+ ", memberAddr=" + memberAddr + ", memberPhone=" + memberPhone + ", memberOneIntro=" + memberOneIntro
				+ ", memberCoin=" + memberCoin + ", memberCareer=" + memberCareer + ", memberContent=" + memberContent
				+ ", memberScore=" + memberScore + ", memberFile=" + memberFile + "]";
	}

	
	
}


