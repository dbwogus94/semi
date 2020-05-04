package com.semi.update.All.join.LoginProfile.dto;

import java.util.Date;

public class LoginProfileDto {
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
	
	
	// 프로필 dto(중복은 제거함)
	
    // 회원이름 
    private String memberName;
    // 생년월일(YYYY/MM/DD) 
    private String memberBirth;
    // 키 
    private double memberHeight;
    // 몸무게 
    private double memberWeight;
    // 주소 
    private String memberAddr;
    // 전화번호 
    private String memberPhone;
    // 한 줄 소개 
    private String memberOneIntro;
    // 코인(기본0, 멘토) 
    private String memberCoin;
    // 특기(경력) 
    private String memberCareer;
    // 내용(추가설명) 
    private String memberContent;
    // 총 별점 
    private double memberScore;
    // 성별(M,F) 
    private String memberGender;
	// 활동량(1~4) 
    private int memberActivity;
    // 기초대사량 
    private double memberBasal;
    // BMI 
    private double memberBmi;
	
    
    
    public LoginProfileDto() {
	}
    
    //멘티용
    public LoginProfileDto(int joinNo, String id, String joinPw, String joinEmail, String joinRole, Date joinDate,
			String joinJoined, String joinRegisterYn, String memberName, String memberBirth, double memberHeight,
			double memberWeight, String memberAddr, String memberPhone, String memberOneIntro, String memberGender,
			int memberActivity, double memberBasal, double memberBmi) {
		this.joinNo = joinNo;
		this.id = id;
		this.joinPw = joinPw;
		this.joinEmail = joinEmail;
		this.joinRole = joinRole;
		this.joinDate = joinDate;
		this.joinJoined = joinJoined;
		this.joinRegisterYn = joinRegisterYn;
		this.memberName = memberName;
		this.memberBirth = memberBirth;
		this.memberHeight = memberHeight;
		this.memberWeight = memberWeight;
		this.memberAddr = memberAddr;
		this.memberPhone = memberPhone;
		this.memberOneIntro = memberOneIntro;
		this.memberGender = memberGender;
		this.memberActivity = memberActivity;
		this.memberBasal = memberBasal;
		this.memberBmi = memberBmi;
	}
    
	// 멘토 용
    public LoginProfileDto(int joinNo, String id, String joinPw, String joinEmail, String joinRole, Date joinDate,
			String joinJoined, String joinRegisterYn, String memberName, String memberBirth, double memberHeight,
			double memberWeight, String memberAddr, String memberPhone, String memberOneIntro, String memberCoin,
			String memberCareer, String memberContent, double memberScore) {
		this.joinNo = joinNo;
		this.id = id;
		this.joinPw = joinPw;
		this.joinEmail = joinEmail;
		this.joinRole = joinRole;
		this.joinDate = joinDate;
		this.joinJoined = joinJoined;
		this.joinRegisterYn = joinRegisterYn;
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
	}
	//전체 
    public LoginProfileDto(
    		int joinNo, String id, String joinPw, String joinEmail, String joinRole, Date joinDate,
			String joinJoined, String joinRegisterYn, String memberName, String memberBirth, double memberHeight,
			double memberWeight, String memberAddr, String memberPhone, String memberOneIntro, String memberCoin,
			String memberCareer, String memberContent, double memberScore, String memberGender, int memberActivity,
			double memberBasal, double memberBmi
		) {
		this.joinNo = joinNo;
		this.id = id;
		this.joinPw = joinPw;
		this.joinEmail = joinEmail;
		this.joinRole = joinRole;
		this.joinDate = joinDate;
		this.joinJoined = joinJoined;
		this.joinRegisterYn = joinRegisterYn;
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
		this.memberGender = memberGender;
		this.memberActivity = memberActivity;
		this.memberBasal = memberBasal;
		this.memberBmi = memberBmi;
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
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public double getMemberHeight() {
		return memberHeight;
	}
	public void setMemberHeight(double memberHeight) {
		this.memberHeight = memberHeight;
	}
	public double getMemberWeight() {
		return memberWeight;
	}
	public void setMemberWeight(double memberWeight) {
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
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public int getMemberActivity() {
		return memberActivity;
	}
	public void setMemberActivity(int memberActivity) {
		this.memberActivity = memberActivity;
	}
	public double getMemberBasal() {
		return memberBasal;
	}
	public void setMemberBasal(double memberBasal) {
		this.memberBasal = memberBasal;
	}
	public double getMemberBmi() {
		return memberBmi;
	}
	public void setMemberBmi(double memberBmi) {
		this.memberBmi = memberBmi;
	}
	@Override
	public String toString() {
		return "LoginProfileDto [joinNo=" + joinNo + ", id=" + id + ", joinPw=" + joinPw + ", joinEmail=" + joinEmail
				+ ", joinRole=" + joinRole + ", joinDate=" + joinDate + ", joinJoined=" + joinJoined
				+ ", joinRegisterYn=" + joinRegisterYn + ", memberName=" + memberName + ", memberBirth=" + memberBirth
				+ ", memberHeight=" + memberHeight + ", memberWeight=" + memberWeight + ", memberAddr=" + memberAddr
				+ ", memberPhone=" + memberPhone + ", memberOneIntro=" + memberOneIntro + ", memberCoin=" + memberCoin
				+ ", memberCareer=" + memberCareer + ", memberContent=" + memberContent + ", memberScore=" + memberScore
				+ ", memberGender=" + memberGender + ", memberActivity=" + memberActivity + ", memberBasal="
				+ memberBasal + ", memberBmi=" + memberBmi + "]";
	}
   
  
	

}
