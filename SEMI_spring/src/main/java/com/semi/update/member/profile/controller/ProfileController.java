package com.semi.update.member.profile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.semi.update.join.controller.JoinController;
import com.semi.update.member.profile.biz.ProfileBiz;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileBiz profileBiz;
	
	private Logger logger = LoggerFactory.getLogger(ProfileController.class);


//멘토 컨트롤러
	
	// 맨토메인
	public String mentorMain() {
		
		return "";
	}
	
	
	public String mentorProfile(){
		return "";
	}
//멘티 컨트롤러
	
	
}
