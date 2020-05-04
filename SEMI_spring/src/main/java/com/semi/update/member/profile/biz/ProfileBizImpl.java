package com.semi.update.member.profile.biz;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.update.join.bao.JoinDaoImpl;
import com.semi.update.member.dto.MentorDto;
import com.semi.update.member.profile.bao.ProfileDao;
import com.semi.update.member.profile.dto.ProfileDto;

@Service
public class ProfileBizImpl implements ProfileBiz{
	private Logger logger = LoggerFactory.getLogger(ProfileBizImpl.class);
	
	@Autowired
	private ProfileDao profileDao;

	
// 멘토 ====================================================================

	// 멘토dto에 selectOne
	@Override
	public MentorDto MentorSelectOne(String joinEmail) {
		logger.info("ProFile BIZ =================>> MentorSelectOne : " + joinEmail);
		return profileDao.MentorSelectOne(joinEmail);
	}

	// profile테이블에 멘토Dto insert
	@Override
	public int MentorProfileInsert(MentorDto mentorDto) {
		logger.info("ProFile BIZ =================>> MentorProfileInsert : " + mentorDto);
		return profileDao.MentorProfileInsert(mentorDto);
	}

	// profile테이블에 멘토Dto로 update
	@Override
	public int MentorProfileUpdate(MentorDto mentorDto) {
		logger.info("ProFile BIZ =================>> MentorProfileUpdate : " + mentorDto);
		return profileDao.MentorProfileUpdate(mentorDto);
	}
	
	

}

