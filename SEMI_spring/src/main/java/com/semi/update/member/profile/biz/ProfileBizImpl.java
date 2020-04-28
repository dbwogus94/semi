package com.semi.update.member.profile.biz;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.update.member.profile.bao.ProfileDao;
import com.semi.update.member.profile.dto.ProfileDto;

@Service
public class ProfileBizImpl implements ProfileBiz{
	
	@Autowired
	private ProfileDao profileDao;
	
	@Override
	public ProfileDto getProfile(String id) {
		// TODO Auto-generated method stub
		return profileDao.getProfile(id);
	}

	// 전체 프로필 출력
	@Override
	public List<ProfileDto> selectList_Profile() {
		// TODO Auto-generated method stub
		return profileDao.selectList_Profile();
	}

	// 멘토들 프로필 전체 출력 
	@Override
	public List<ProfileDto> selectList_Mentor() {
		// TODO Auto-generated method stub
		return profileDao.selectList_Mentor();
	}

	// 멘티들  프로필 전체 출력
	@Override
	public List<ProfileDto> selectList_Mentee() {
		// TODO Auto-generated method stub
		return profileDao.selectList_Mentee();
	}

	// 해당 이름을 가진 회원 전체에서 출력
	@Override
	public List<ProfileDto> search_Member(String memberName) {
		// TODO Auto-generated method stub
		return profileDao.search_Member(memberName);
	}

	// 해당 이름을 가진 멘토들 출력 
	@Override
	public List<ProfileDto> search_Mentor(String memberName) {
		// TODO Auto-generated method stub
		return profileDao.search_Mentor(memberName);
	}

	// 해당 이름을 가진 멘티들 출력
	@Override
	public List<ProfileDto> search_Memtee(String memberName) {
		// TODO Auto-generated method stub
		return profileDao.search_Memtee(memberName);
	}
	// 해당 멘토 아이디를 가진 멘티들 검색
	@Override
	public List<ProfileDto> search_Mentor_Mentee(String id) {
		// TODO Auto-generated method stub
		return profileDao.search_Mentor_Mentee(id);
	}
	
	
	
	
	
	
	@Override
	public ProfileDto selectOne_Mentor(String id) {
		// TODO Auto-generated method stub
		return profileDao.selectOne_Mentor(id);
	}

	@Override
	public ProfileDto selectOne_Mentee(String id) {
		// TODO Auto-generated method stub
		return profileDao.selectOne_Mentee(id);
	}

	@Override
	public int insertProfile_Mentor(ProfileDto profileDto) {
		// TODO Auto-generated method stub
		return profileDao.insertProfile_Mentor(profileDto);
	}

	@Override
	public int insertProfile_Mentee(ProfileDto profileDto) {
		// TODO Auto-generated method stub
		return profileDao.insertProfile_Mentee(profileDto);
	}

	@Override
	public int updateProfile_Mentor(ProfileDto profileDto) {
		// TODO Auto-generated method stub
		return profileDao.updateProfile_Mentor(profileDto);
	}

	@Override
	public int updateProfile_Mentee(ProfileDto profileDto) {
		// TODO Auto-generated method stub
		return profileDao.updateProfile_Mentee(profileDto);
	}

	@Override
	public List<ProfileDto> getMentorList() {
		// TODO Auto-generated method stub
		return profileDao.getMentorList();
	}

	@Override
	public int updateProfilePay(String id) {
		// TODO Auto-generated method stub
		return profileDao.updateProfilePay(id);
	}

	@Override
	public List<ProfileDto> searchMentor(String name) {
		// TODO Auto-generated method stub
		return profileDao.searchMentor(name);
	}
	//핸드폰 번호 체크
	@Override
	public boolean phoneCheck(String profilePhoneNumber) {
		// TODO Auto-generated method stub
		return profileDao.phoneCheck(profilePhoneNumber);
	}
	
	//멘토 이미지 경로 추가
		@Override
		public int UpdateSrc_Mentor(ProfileDto profileDto) {
			// TODO Auto-generated method stub
			return profileDao.UpdateSrc_Mentor(profileDto);
		}


}

