package com.semi.update.join.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.update.All.join.LoginProfile.dto.LoginProfileDto;
import com.semi.update.join.bao.JoinDao;
import com.semi.update.join.dto.JoinDto;


@Service
public class JoinBizImpl implements JoinBiz{
	
	
	/*
	  	스프링 적용 변경사항
	  	1) @Service 어노테이션 추가
	  	2) JoinDao @Autowired를 통해 자동 di

	 */
	
	@Autowired
	private JoinDao dao;
	
	@Override
	public JoinDto Login(String id, String joinPw) {
		return dao.Login(id, joinPw);
	}

	@Override
	public boolean idCheck(String id) {
		return dao.idCheck(id);
	}

	@Override
	public boolean emailCheck(String joinEmail) {
		return dao.emailCheck(joinEmail);
	}

	@Override
	public int insertMember(JoinDto joinDto) {
		return dao.insertMember(joinDto);
	}

	
	// 가입자 프로필 작성 유무 칼럼 "Y"로 수정
	@Override
	public int updateJoinRegister(String id, String joinRegisterYn) {
		// TODO Auto-generated method stub
		return dao.updateJoinRegister(id, joinRegisterYn);
	}

	// 전체 리스트 출력
	@Override
	public List<JoinDto> selectList_join() {
		return dao.selectList_join();
	}

	// 미승인 멘토 검색
	@Override
	public List<JoinDto> selsList_Mentor_reN() {
		// TODO Auto-generated method stub
		return dao.selsList_Mentor_reN();
	}

	// 프로필 미작성 멘티 검색
	@Override
	public List<JoinDto> selsList_Mentee_reN() {
		// TODO Auto-generated method stub
		return dao.selsList_Mentee_reN();
	}

	//전체에서 아이디으로 검색
	@Override
	public List<JoinDto> selectList_join(String id) {
		// TODO Auto-generated method stub
		return dao.selectList_join(id);
	}

	// 미승인 멘토에서 아이디로 검색
	@Override
	public List<JoinDto> selsList_Mentor_reN(String id) {
		// TODO Auto-generated method stub
		return dao.selsList_Mentor_reN(id);
	}
	// 프로필 미작성 멘티 아이디로 검색
	@Override
	public List<JoinDto> selsList_Mentee_reN(String id) {
		// TODO Auto-generated method stub
		return dao.selsList_Mentee_reN(id);
	}


	
	
	
	//id를 통해 멘토 정보 가져오기
	@Override
	public LoginProfileDto getLoginProfileDto_mentor(String id) {
		// TODO Auto-generated method stub
		return dao.getLoginProfileDto_mentor(id);
	}

	//id를 통해 멘티 정보 가저오기
	@Override
	public LoginProfileDto getLoginProfileDto_mentee(String id) {
		// TODO Auto-generated method stub
		return dao.getLoginProfileDto_mentee(id);
	}

	//패스워드 변경용 id, pw 가져오기
	@Override
	public JoinDto passwordChange(String id) {
		return dao.passwordChange(id);
	}
	//패스워드 변경
	@Override
	public int updatePassword(String id, String pw) {
		// TODO Auto-generated method stub
		return dao.updatePassword(id, pw);
	}
	//회원탈퇴
	@Override
	public int updateLeave(String id) {
		// TODO Auto-generated method stub
		return dao.updateLeave(id);
	}
	//아이디찾기
	@Override
	public JoinDto IdSearch(String email) {
		return dao.IdSearch(email);
	}

	@Override
	public boolean PwCheck(String id, String email) {
		// TODO Auto-generated method stub
		return dao.PwCheck(id, email);
	}

	@Override
	public JoinDto PwInfo(String id) {
		// TODO Auto-generated method stub
		return dao.PwInfo(id);
	}

	@Override
	public List<LoginProfileDto> selectList_MentorList() {
		// TODO Auto-generated method stub
		return dao.selectList_MentorList();
	}

	@Override
	public List<LoginProfileDto> search_MentorName(String memberName) {
		// TODO Auto-generated method stub
		return dao.search_MentorName(memberName);
	}

	
	

}
