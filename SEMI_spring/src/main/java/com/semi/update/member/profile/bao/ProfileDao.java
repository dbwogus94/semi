package com.semi.update.member.profile.bao;

import java.util.List;

import com.semi.update.member.dto.MentorDto;
import com.semi.update.member.profile.dto.ProfileDto;


public interface ProfileDao {
	
    String NANESPACE = "com.semi.update.mapper.Profile.";
	
// 멘토 ====================================================================
    
    // 멘토dto에 selectOne  
    public MentorDto MentorSelectOne(String joinEmail);

    // profile테이블에 멘토Dto insert
    public int MentorProfileInsert(MentorDto mentorDto);
    
    // profile테이블에 멘토Dto로 update
    public int MentorProfileUpdate(MentorDto mentorDto);
    
	
}
