package com.semi.update.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.semi.update.All.util.UploadFileUtils;
import com.semi.update.All.util.Util;
import com.semi.update.join.biz.JoinBiz;
import com.semi.update.join.dto.JoinDto;
import com.semi.update.member.dto.MentorDto;
import com.semi.update.member.profile.biz.ProfileBiz;

@Controller
@RequestMapping("/mentor")
public class MentorController {
	private Logger logger = LoggerFactory.getLogger(JoinController.class);

	@Autowired
	private ProfileBiz profileBiz;
	
	@Autowired
	private JoinBiz joinBiz;
	
	@Resource(name="uploadPath")	// 업로드 파일 경로
	private String uploadPath;

	// 멘토 메인
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String mentroMain(Model model, HttpSession session) {
		logger.info("mentor Main page");
		MentorDto login = (MentorDto) session.getAttribute("login");
		MentorDto mentorDto = profileBiz.MentorSelectOne(login.getId());
		model.addAttribute("mentorDto", mentorDto);
		return "mentor/MENTOR_Main";
	}

	// 멘토 최초 프로필작성 페이지
	@RequestMapping(value = "/firstProfile.do", method = RequestMethod.GET)
	public String mentorFirstProfile(Model model, HttpSession session, MentorDto mentorDto) {
		logger.info("mentor first profile page");
		JoinDto joinDto = (JoinDto) session.getAttribute("login");
		mentorDto.setDto(joinDto); // joinDto를 이용하여 mentorDto를 생성 
		// hibernate를 사용하여 객체 유효성검사를 하기위해 뷰에 빈 객체 전달
		model.addAttribute("mentorDto", mentorDto);
		return "mentor/MENTOR_mentorProfile";
	}

	// 멘토 프로필 보기
	@RequestMapping(value = "/profile.do", method = RequestMethod.GET)
	public String mentorProfile(Model model, HttpSession session) {
		logger.info("mentor profile page");
		MentorDto login = (MentorDto) session.getAttribute("login");
		
		//MentorDto dto = profileBiz.MentorSelectOne(login.getId());
		// form:form과 hibernate를 사용하기위해 전달
		model.addAttribute("mentorDto", login);
		return "mentor/MENTOR_mentorProfile";
	}

	// 프로필저장 : mentorDto insert, update
	@RequestMapping(value = "/profileInsert.do", method = RequestMethod.POST)
	public String mentorProfileInsert(Model model, HttpSession session, @ModelAttribute("mentorDto") @Valid MentorDto mentorDto,
			BindingResult result) throws IOException {
		logger.info("mentor profile insert");
		logger.info("===============MentorDto " + mentorDto);

		if (result.hasErrors()) {
			logger.info("유효성검사 >>>>>>>>>>>>>>>>>>>>> 실행");
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			logger.info("유효성검사 >>>>>>>>>>>>>>>>>>>>> 실패");
			model.addAttribute("mentorDto", mentorDto);
			return "mentor/MENTOR_mentorProfile";
		} else {
			logger.info("유효성검사  >>>>>>>>>>>>>>>>>>>>> 통과");
			// MEMBER_JOIN 테이블 MEMBER_PROFILE 테이블 join >>> MentorDto
			// id을 사용하여 profile 테이블에 추가할 멘토 정보가 있는지 확인
			MentorDto dto = profileBiz.MentorSelectOne(mentorDto.getId());

			// profile테이블에 없으면 insert >>> 세션 변경
			if (dto == null) {
				// 최초프로필 작성시  joinDto에서 정보를 추가로 가져온다 
				JoinDto login = (JoinDto) session.getAttribute("login");
			
			// 파일저장 : 최초 insert시에는 무조건 파일저장 실행	
				// 파일 데이터와 경로를 인지로 전달 >>> 파일 저장
				String UUIDfileName = UploadFileUtils.SP_ProfileUpload(mentorDto.getMemberFile(), uploadPath);	// 파일 저장 실행 리턴값으로 파일명 반환 
				// Dto에 경로 추가
				mentorDto.setMemberContent("/update/resources/img/mentor/" + UUIDfileName);

				// joinDto + 추가 프로필 정보 = mentorDto
				mentorDto.setDto(login);
				int isertRes = profileBiz.MentorProfileInsert(mentorDto);
				if (isertRes > 0) {
					logger.info("멘토 profile insert 성공");
					//프로필 작성여부 칼럼 JoinRegisterYn >> Y로 변경
					int updateJoinRegister = joinBiz.updateJoinRegister(mentorDto.getId(), "Y");
					if(updateJoinRegister > 0) {
						logger.info("멘토 profile 프로필 작성여부 Y로 변경 성공");
						// joinDto + 추가 프로필 정보를 불러와서 세션 재할당
						MentorDto newLogin = profileBiz.MentorSelectOne(mentorDto.getId());
						session.setAttribute("login", newLogin);
						return "redirect:/mentor/main.do";
					} else {
						logger.info("멘토 profile 프로필 작성여부 Y로 변경 실패!!!!");
						model.addAttribute("mentorDto",mentorDto);
						return "mentor/MENTOR_mentorProfile";
					}
				} else {
					logger.info("멘토 profile insert 실패");
					return "mentor/MENTOR_mentorProfile";
				}
				// profile테이블에 있으면 update >>> 세션 변경
			} else {
				
			// 파일저장 : 수정에서는 파일저장시  같은이름의 파일이 있다면 실행하지 말아야함
				String oldFileName = mentorDto.getMemberContent().replace("/update/resources/img/mentor/P_", "");
				String[] fileNames = Util.getFilesName(uploadPath);
				
				boolean chk = true;
				for(String name : fileNames) {
					if(name.equals(oldFileName)) {
						logger.info(">>>>>>>>>>>>>>>>>>> [파일 중복]");
						chk = false;
						break;
					}
				}
				
				if(chk) { // 같은 이름의 파일이 있다면 true + not = false
					logger.info("프로필 이미지 업로드 실행 >>>>>>>>>>>>>>>>>>>> " +  mentorDto.getMemberFile().getOriginalFilename());
					
					// 파일 데이터 받기
					MultipartFile file = mentorDto.getMemberFile();
					// 파일 저장 실행 >> 리턴값으로 파일명 반환
					String UUIDfileName = UploadFileUtils.SP_ProfileUpload(file, uploadPath);	 
					// Dto에 업로드된 경로 추가
					mentorDto.setMemberContent("/update/resources/img/mentor/" + UUIDfileName);
				}

				int updateRes = profileBiz.MentorProfileUpdate(mentorDto);
				if (updateRes > 0) {
					logger.info("멘토 profile update 성공");
					// 추가한 정보로 세션 재할당
					MentorDto newLogin = profileBiz.MentorSelectOne(mentorDto.getId());
					session.setAttribute("login", newLogin);
					return "redirect:/mentor/main.do";
				} else {
					logger.info("멘토 profile update 실패");
					model.addAttribute("mentorDto",mentorDto);
					return "mentor/MENTOR_mentorProfile";
				}
			}
		}
	}

	// 주소입력 팝업
	@RequestMapping(value = "/AddressPopup.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String AddressPopup() {
		logger.info("mentor profile Address PopUp");
		return "all/AddressAPI";
	}

}
