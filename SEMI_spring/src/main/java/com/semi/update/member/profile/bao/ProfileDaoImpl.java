package com.semi.update.member.profile.bao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semi.update.join.dao.JoinDaoImpl;
import com.semi.update.member.dto.MentorDto;
import com.semi.update.member.profile.dto.ProfileDto;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	
	@Autowired
	private SqlSessionTemplate session;

	private Logger logger = LoggerFactory.getLogger(JoinDaoImpl.class);

	
//  멘토 ====================================================================
	// 멘토dto에 selectOne  
	@Override
	public MentorDto MentorSelectOne(String joinEmail) {
		logger.info("ProFile dao =================>> MentorSelectOne : " + joinEmail);
		return session.selectOne(NANESPACE + "MentorSelectOne", joinEmail);
	}

	// profile테이블에 멘토Dto insert
	@Override
	public int MentorProfileInsert(MentorDto mentorDto) {
		logger.info("ProFile dao =================>> MentorProfileInsert : " + mentorDto);
		return session.insert(NANESPACE + "MentorProfileInsert", mentorDto);
	}
	
	// profile테이블에 멘토Dto로 update
	@Override
	public int MentorProfileUpdate(MentorDto mentorDto) {
		logger.info("ProFile dao =================>> MentorProfileUpdate : " + mentorDto);
		return session.update(NANESPACE +"MentorProfileUpdate", mentorDto);
	}

	

}
