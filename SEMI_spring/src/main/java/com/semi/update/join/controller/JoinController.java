package com.semi.update.join.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.update.join.biz.JoinBiz;
import com.semi.update.join.dto.JoinDto;

@Controller
public class JoinController {
	private Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Autowired
	private JoinBiz joinBiz;
	
	
	// 메인
	@RequestMapping(value="/main.do", method = RequestMethod.GET)
	public String main() {
		logger.info("main");	// .info() 앱 흐름 정보
		return "Main";
	}
	
	
	/*
		# controller의 Url매핑의 주의점 
		
		컨트롤러의 URL을 기능적으로 나누고 싶을때 "/기능/매핑 url"이런 식으로 나눌수 있다
		 - 1) 해당 url에 응답되는 veiw에서 서블릿을 호출할 때 반듯이 ../를 붙여야 한다.     
		 - 2) 해당 url에 응답되는 veiw에서 정적자원을 사용시 해당 경로에 앞에 반듯이  ../를 붙여야 정상적인 요청이 된다.
	*/	 
	
	// 로그인 
	@RequestMapping(value="/join/loginPopUp.do", method=RequestMethod.GET)
	public String loginPopUp() {
		logger.info("loginPopUp");
		return "login/LOGIN_LoginPopUp";
	}
	
	
	// 회원가입 화면으로
	@RequestMapping(value="/join/loginSignUp.do",  method=RequestMethod.GET)
	public String loginSignUp() {
		logger.info("loginSignUp");
		return "login/LOGIN_LoginSignUp";
	}
	
	// 아이디 중복확인
	@RequestMapping(value="/join/idCheck.do", method=RequestMethod.POST)
	@ResponseBody
	public String idCheck(String id) {
		logger.info("ajax idCheck : " + id);
		
		if(joinBiz.idCheck(id)) { // =true 아이디 존재함 
			return "fail"; 
		} else {
			return "ok"; 
		}
	}
	
	// 이메일 인증 팝업
	@RequestMapping(value="/join/emailCheckPopUp.do", method=RequestMethod.GET)
	public String emailCheckPopUp() {
		logger.info("emailCheckPopUp");
		return "login/emailChk";
	}
	
	
	// 이메일 중복확인
	@RequestMapping(value="/join/emailCheck.do", method=RequestMethod.POST)
	@ResponseBody
	public String emailCheck(String email) {
		logger.info("emailCheck");
		
		if(joinBiz.emailCheck(email)) {  // =true 이메일 존재함
			return "fail";
		} else {
			return "ok";
		}
	}
	
	//이메일 전송 화면으로
	@RequestMapping(value="/join/mailSend.do", method=RequestMethod.POST)
	public String mailSend(Model model, String EmailName) {
		logger.info("mailSend");
		model.addAttribute("EmailName",EmailName);
		return "login/mailSend";
	}
	
	
	// 회원가입 
	@RequestMapping(value="join/loingSingUpRes.do", method=RequestMethod.POST)
	public void loingSingUpRes(@ModelAttribute JoinDto joinDto, HttpServletResponse response) {
		logger.info("SingUpRes");
		
		int res = joinBiz.insertMember(joinDto); 
		if(res > 0) { 	// 성공
			logger.info("SingUp success");
			try {
				jsPrint("가입성공! 로그인해주세요.", "../main.do", response);			// 메인으로 >> 로그인
			} catch (IOException e) {
				e.printStackTrace();
			}		
		} else {		// 실패 	
			logger.info("SingUp fail");
			try {
				jsPrint("가입실패! 다시 회원가입 해주세요.", "../join/loginSignUp.do", response);			// 다시 회원가입 화면으로..
			} catch (IOException e) {
				e.printStackTrace();
			}  		
		}
	}
	
	
	//kakao계정 회원가입 페이지
	@RequestMapping(value="/join/kakaoSingUp.do", method=RequestMethod.GET)
	public String kakaoSingUp() {
		return "";
	}
	
	
	
	
	public void jsPrint(String msg, String url, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");	// 해당 코드가 없으면 글자가 깨진다 >>> web.xml에 filter를 했음에도 깨지는 이유는를 모르겠음
		String s = "<script type='text/javascript'>"
				+ " alert('"+ msg +"'); "
				+ "location.href ='"+ url + "';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
}
