package com.semi.update.member.profile.dto;


public class ProfileDto {
		
		private int memberNo;
	    // 아이디(공용) 
	    private String id;
	    // 멘티/멘토(부모중복) 
	    private String memberRole;
	    // 멘토or멘티활성화 
	    private String memberRegisterYn;
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
	    private int memberCoin;
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
	    
	    
	    //기본생성자
	    public ProfileDto() {
		}
	    
	    //멘토
		public ProfileDto(String id, String memberRole, String memberRegisterYn, String memberName, String memberBirth,
				double memberHeight, double memberWeight, String memberAddr, String memberPhone, String memberOneIntro,
				int memberCoin, String memberCareer, String memberContent, double memberScore) {
			this.id = id;
			this.memberRole = memberRole;
			this.memberRegisterYn = memberRegisterYn;
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
		
		//멘티
		public ProfileDto(String id, String memberRole, String memberRegisterYn, String memberName, String memberBirth,
				double memberHeight, double memberWeight, String memberAddr, String memberPhone, String memberOneIntro,
				String memberGender, int memberActivity, double memberBasal, double memberBmi, String memberContent) {
			this.id = id;
			this.memberRole = memberRole;
			this.memberRegisterYn = memberRegisterYn;
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
			this.memberContent = memberContent;
		}
		
		//전체
		public ProfileDto(int memberNo, String id, String memberRole, String memberRegisterYn, String memberName, String memberBirth,
				double memberHeight, double memberWeight, String memberAddr, String memberPhone, String memberOneIntro,
				int memberCoin, String memberCareer, String memberContent, double memberScore, String memberGender,
				int memberActivity, double memberBasal, double memberBmi) {
			this.memberNo = memberNo;
			this.id = id;
			this.memberRole = memberRole;
			this.memberRegisterYn = memberRegisterYn;
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
		


		public ProfileDto(String memberName, String memberBirth, double memberHeight, double memberWeight,
				String memberAddr, String memberPhone, String memberOneIntro, String memberCareer, String id) {
			this.memberName = memberName;
			this.memberBirth = memberBirth;
			this.memberHeight = memberHeight;
			this.memberWeight = memberWeight;
			this.memberAddr = memberAddr;
			this.memberPhone = memberPhone;
			this.memberOneIntro = memberOneIntro;
			this.memberCareer = memberCareer;
			this.id = id;
			
		}
		

		public ProfileDto(String memberName, String memberBirth, double memberHeight, double memberWeight,
				String memberAddr, String memberPhone, String memberOneIntro, String memberGender, int memberActivity, String id) {
			this.memberName = memberName;
			this.memberBirth = memberBirth;
			this.memberHeight = memberHeight;
			this.memberWeight = memberWeight;
			this.memberAddr = memberAddr;
			this.memberPhone = memberPhone;
			this.memberOneIntro = memberOneIntro;
			this.memberGender = memberGender;
			this.memberActivity = memberActivity;
			this.id = id;
		}
		
		public ProfileDto(String memberName, String memberBirth, double memberHeight, double memberWeight,
				String memberAddr, String memberPhone, String memberOneIntro, String memberGender, int memberActivity, String id, double memberBasal, double memberBmi) {
			this.memberName = memberName;
			this.memberBirth = memberBirth;
			this.memberHeight = memberHeight;
			this.memberWeight = memberWeight;
			this.memberAddr = memberAddr;
			this.memberPhone = memberPhone;
			this.memberOneIntro = memberOneIntro;
			this.memberGender = memberGender;
			this.memberActivity = memberActivity;
			this.id = id;
			this.memberBasal = memberBasal;
			this.memberBmi = memberBmi;
		}

		public int getMemberNo() {
			return memberNo;
		}

		public void setMemberNo(int memberNo) {
			this.memberNo = memberNo;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMemberRole() {
			return memberRole;
		}

		public void setMemberRole(String memberRole) {
			this.memberRole = memberRole;
		}

		public String getMemberRegisterYn() {
			return memberRegisterYn;
		}

		public void setMemberRegisterYn(String memberRegisterYn) {
			this.memberRegisterYn = memberRegisterYn;
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

		public int getMemberCoin() {
			return memberCoin;
		}

		public void setMemberCoin(int memberCoin) {
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
			return "ProfileDto [memberNo=" + memberNo + ", id=" + id + ", memberRole=" + memberRole
					+ ", memberRegisterYn=" + memberRegisterYn + ", memberName=" + memberName + ", memberBirth="
					+ memberBirth + ", memberHeight=" + memberHeight + ", memberWeight=" + memberWeight
					+ ", memberAddr=" + memberAddr + ", memberPhone=" + memberPhone + ", memberOneIntro="
					+ memberOneIntro + ", memberCoin=" + memberCoin + ", memberCareer=" + memberCareer
					+ ", memberContent=" + memberContent + ", memberScore=" + memberScore + ", memberGender="
					+ memberGender + ", memberActivity=" + memberActivity + ", memberBasal=" + memberBasal
					+ ", memberBmi=" + memberBmi + "]";
		}

	

	    
}
