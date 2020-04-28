package com.semi.update.member.profile.bao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semi.update.join.bao.JoinDaoImpl;
import com.semi.update.member.profile.dto.ProfileDto;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	
	@Autowired
	private SqlSessionTemplate session;

	private Logger logger = LoggerFactory.getLogger(JoinDaoImpl.class);

	
	
	// 전체 출력 --> 관리자 용
	@Override
	public ProfileDto getProfile(String id) {
		return null;
	}

	// 멘토만 출력(멘토 관련 정보만 뽑을것) --> 관리자 사용, 멘토찾기 사용
	@Override
	public List<ProfileDto> selectList_Profile() {
		return null;
	}

	// 멘티만 출력(멘티 관련 정보만 뽑을것) --> 관리자 사용
	@Override
	public List<ProfileDto> selectList_Mentor() {
		return null;
	}

	// 전체에서 이름으로 검색 >> 이름과 id를 가져오자
	@Override
	public List<ProfileDto> selectList_Mentee() {
		return null;
	}

	// 멘토에서 이름으로 검색 >>
	@Override
	public List<ProfileDto> search_Member(String memberName) {
		return null;
	}

	// 멘티에서 이름으로 검색 >>
	@Override
	public List<ProfileDto> search_Mentor(String memberName) {
		return null;
	}

	// 멘토에서 아이디로 멘티 검색
	@Override
	public List<ProfileDto> search_Memtee(String memberName) {
		return null;
	}


// 위에 가져온 id를 통해 유저정보 디테일 페이지에서는 join한 값을 가져온다.	
	
	@Override
	public List<ProfileDto> search_Mentor_Mentee(String id) {
		return null;
	}

	// 선택 하나 출력(id 사용) --> 각각 프로필에 사용(
	// 멘토용
	@Override
	public ProfileDto selectOne_Mentor(String id) {
		return null;
	}

	// 멘티용
	@Override
	public ProfileDto selectOne_Mentee(String id) {
		return null;
	}

	// 멘토 프로필 입력
	@Override
	public int insertProfile_Mentor(ProfileDto profileDto) {
		return 0;
	}
	
	// 멘티 프로필 입력
	@Override
	public int insertProfile_Mentee(ProfileDto profileDto) {
		return 0;
	}

	// 멘토 프로필 수정
	@Override
	public int updateProfile_Mentor(ProfileDto profileDto) {
		return 0;
	}

	// 멘티 프로필 수정
	@Override
	public int updateProfile_Mentee(ProfileDto profileDto) {
		return 0;
	}

	/* 박하 결제 */
	// 멘토찾기 페이지 (멘토 프로필 리스트)
	@Override
	public List<ProfileDto> getMentorList() {
		return null;
	}

	// 결제 후 해당 멘토의 코인++
	@Override
	public int updateProfilePay(String id) {
		return 0;
	}

	// 멘토찾기 페이지 (멘토 검색기능)
	@Override
	public List<ProfileDto> searchMentor(String name) {
		return null;
	}

	//핸드폰 번호 중복체크
	@Override
	public boolean phoneCheck(String profilePhoneNumber) {
		return false;
	}
	
	//멘티 이미지 경로 추가
	@Override
	public int UpdateSrc_Mentor(ProfileDto profileDto) {
		return 0;
	}



}
