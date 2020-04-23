package com.semi.update.join.biz;

import java.util.List;

import com.semi.update.All.join.LoginProfile.dto.LoginProfileDto;
import com.semi.update.join.dto.JoinDto;


public interface JoinBiz {
	// 로그인  아이디 체크
	public JoinDto Login(String id, String joinPw);
	
	// 아이디 중복체크
	public boolean idCheck(String id); 
	
	// 이메일 중복체크 
	public boolean emailCheck(String joinEmail);
	
	// 회원가입
	public int insertMember(JoinDto joinDto);
	
	// 프로필 작성시 JOIN_REGISTER_YN 칼럼의 값 'Y'로 수정
	public int updateJoinRegister(String id, String joinRegisterYn);
	
	// 전체 리스트 출력
	public List<JoinDto> selectList_join();
	
	// 미승인 멘토리스트 츨력
	public List<JoinDto> selsList_Mentor_reN();
	
	// 프로필 미작성 멘티 리스트 출력	
	public List<JoinDto> selsList_Mentee_reN();

	// 전체에서 아이디로 검색
	public List<JoinDto> selectList_join(String id);
	
	// 미승인 멘토 아이디로 검색
	public List<JoinDto> selsList_Mentor_reN(String id);
	
	// 프로필 미작성 멘티 아이디로 검색
	public List<JoinDto> selsList_Mentee_reN(String id);
	
	
	// --혜린 멘토 리스트 출력(Login Profile join Table)
	public List<LoginProfileDto> selectList_MentorList();

	// --혜린 멘토에서 이름으로 검색(Login Profile join Table)
	public List<LoginProfileDto> search_MentorName(String memberName);

	
	
	
	//패스워드 변경용 id, pw 가져오기
	public JoinDto passwordChange(String id);
	
	//패스워드 변경 하기
	public int updatePassword(String id, String pw);
	
	//회원탈퇴
	public int updateLeave(String id);
	
	//아이디 찾기
	public JoinDto IdSearch(String email);
	
	// 비밀번호 찾기용 아이디, 이메일 확인
	public boolean PwCheck(String id, String email);
	
	// 비밀번호 찾기용 정보 불러오기
	public JoinDto PwInfo(String id);
	
	
	
	
//join테이블 profile테이블 조인 Dao		
	// 로그인 성공시 해당 id 프로필과 join한 Dto 리턴  - 멘토용 멘티용 따로 구현
	public LoginProfileDto getLoginProfileDto_mentor(String id);
	
	public LoginProfileDto getLoginProfileDto_mentee(String id);


}