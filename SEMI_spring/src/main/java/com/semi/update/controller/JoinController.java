package com.semi.update.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semi.update.All.mail.SMTPDto;
import com.semi.update.join.biz.JoinBiz;
import com.semi.update.join.dto.JoinDto;
import com.semi.update.member.dto.MentorDto;
import com.semi.update.member.profile.biz.ProfileBiz;

@Controller
@RequestMapping("/join")
public class JoinController {
	private Logger logger = LoggerFactory.getLogger(JoinController.class);

	@Autowired
	private JoinBiz joinBiz;

	@Autowired
	private ProfileBiz profileBiz;
	
	@Autowired			// servlet-content.xml의 빈을 통하여 값을 주입 
	private SMTPDto smtpDto;

	// 메인
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main() {
		logger.info("main"); // .info() 앱 흐름 정보
		return "Main";
	}

	// 아이디 중복확인
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(String id) {
		logger.info("ajax idCheck : " + id);

		if (joinBiz.idCheck(id)) { // =true 아이디 존재함
			return "fail";
		} else {
			return "ok";
		}
	}

	// 이메일 인증 팝업
	@RequestMapping(value = "/emailCheckPopUp.do", method = RequestMethod.GET)
	public String emailCheckPopUp() {
		logger.info("emailCheckPopUp");
		return "login/emailChk";
	}

	// 이메일 중복확인
	@RequestMapping(value = "/emailCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public String emailCheck(String email) {
		logger.info("emailCheck");

		if (joinBiz.emailCheck(email)) { // =true 이메일 존재함
			return "fail";
		} else {
			return "ok";
		}
	}

	// 인증번호 이메일 전송
	@RequestMapping(value = "/mailSend.do", method = RequestMethod.POST)
	@ResponseBody
	protected String sendmail(@ModelAttribute("joinemail") String joinemail, HttpServletRequest requset) throws Exception {

		String code = smtpDto.createAuthNo().toString().trim();// 랜덤번호 생성 후 담아줌
		logger.info("smtp 랜덤 번호 : " + code);

		try {
			smtpDto.sendAuthNo(joinemail, code); // 만들어둔 dto의 메서드에 값을 주입 : 수신자에게 번호 전송
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		HttpSession AuthNo = requset.getSession(); // 세션 소환
		AuthNo.setAttribute("AuthNo", code); // session에 값을 담아서 클라가 적은 값과 비교하기 위해 전송하자

		/*
		 * //이메일만 보내기 try { smtpDto.sendMail(sduemail, "인증번호 전송 ", "인증번호 입니다"); } catch
		 * (Exception e) { e.printStackTrace(); return "fail"; }
		 */
		// 첨부파일 보내기
		// 첨부되어서 전송은 되지만, 첨부주소에 접속이 불가능하다고 나온다 수정해야할 사항 !!!C:\\Users\\강지원\\Desktop,
		// a.jpg라고 적었을시에
//	      try {
//	         smtpDto.sendMail(sduemail, "이것은 제목", "스프링으로 구현해서 보내본다.","C:\\Users\\강지원\\Desktop\\a.jpg","파일이름.txt");//a.jpg
//	      } catch (Exception e) {
//	         e.printStackTrace();
//	         return "fail";         
//	      }

		return "succ";
	}

	// 이메일 인증번호 확인
	@RequestMapping(value = "/validate.do", method = RequestMethod.POST)
	@ResponseBody
	protected String AuthNoChk(@ModelAttribute("code") String code, HttpServletRequest req) {
		String AuthNo = (String) req.getSession().getAttribute("AuthNo");// session으로 담아뒀던 인증번호를 가지고옴

		// view에서 받아온 인증번호와 발송한 인증번호가 같은지 비교함
		if (code.equals(AuthNo)) {
			return "succ";
		}
		return "fail";
	}

	
	// 회원가입 화면으로
	@RequestMapping(value = "/loginSignUp.do", method = RequestMethod.GET)
	public String loginSignUp() {
		logger.info("loginSignUp");
		return "login/LOGIN_LoginSignUp";
	}

	
	// 회원가입
	@RequestMapping(value = "/loginSingUpRes.do", method = RequestMethod.POST)
	public void loingSingUpRes(@ModelAttribute JoinDto joinDto, HttpServletResponse response) {
		logger.info("SingUpRes");

		int res = joinBiz.insertMember(joinDto);
		if (res > 0) { // 성공
			logger.info("SingUp success");
			try {
				jsPrint("가입성공! 로그인해주세요.", "main.do", response); // 메인으로 >> 로그인
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // 실패
			logger.info("SingUp fail");
			try {
				jsPrint("가입실패! 다시 회원가입 해주세요.", "loginSignUp.do", response); // 다시 회원가입 화면으로..
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// kakao계정 회원가입 페이지
	@RequestMapping(value = "/kakaoSingUp.do", method = RequestMethod.GET)
	public String kakaoSingUp() {
		return "login/LOGIN_kakao_LoginSignUp";
	}

	// kakao계정 회원가입 페이지
	@RequestMapping(value = "/googleSingUp.do", method = RequestMethod.GET)
	public String googleSingUp() {
		return "login/LOGIN_google_LoginSignUp";
	}

	// sns계정 회원가입
	@RequestMapping(value = "/snsSingUpRes.do", method = RequestMethod.POST)
	public void snsSingUpRes(@ModelAttribute JoinDto joinDto, @RequestParam String API, HttpServletResponse response) {
		logger.info("snsSingUpRes.do");

		// 이메일에서 @앞부분 id로 사용
		String id = joinDto.getJoinEmail().split("@")[0];
		joinDto.setId(id);

		// 회원가입 실행
		int res = joinBiz.insertMember(joinDto);
		if (res > 0) { // 성공
			logger.info("SingUp success");
			try {
				jsPrint("가입성공! 로그인해주세요.", "main.do", response); // 메인으로 >> 로그인
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // 실패
			logger.info("SingUp fail");
			try {
				if (API.equals("google")) {
					jsPrint("가입실패! 다시 회원가입 해주세요.", "googleSingUp.do", response); // 다시 회원가입 화면으로..
				} else {
					jsPrint("가입실패! 다시 회원가입 해주세요.", "kakaoSingUp.do", response); // 다시 회원가입 화면으로..
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 로그인
	@RequestMapping(value = "/loginPopUp.do", method = RequestMethod.GET)
	public String loginPopUp() {
		logger.info("loginPopUp");
		return "login/LOGIN_LoginPopUp";
	}

	// 로그인 실행
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(Model model, @RequestParam String id, @RequestParam String joinPw,
			HttpServletResponse response, HttpSession session) {
		logger.info("login");

		JoinDto joinDto = joinBiz.Login(id, joinPw);
		// 로그인 성공시
		if (joinDto != null) {
			logger.info("login 성공 => " + joinDto);
			if (joinDto.getJoinRole().equals("관리")) {
				session.setAttribute("login", joinDto); // 관리자는 joinDto를 session에 사용
				logger.info("==========================[관리자 로그인]==========================");

				String search_type = "All_Y"; // 셀렉트 값
				// List<ProfileDto> profineDto = profileBiz.selectList_Profile();
				return ""; // 관리자 페이지

			} else if (joinDto.getJoinRole().equals("멘토")) {
				logger.info("==========================[멘토 로그인]==========================");
				// 최초 로그인 인경우 == 프로필을 작성하지 않은경우
				if (joinDto.getJoinRegisterYn().equals("N")) { // 프로필이 작성되어 있지 않다면 >> 멘토 첫 프로필 작성으로
					session.setAttribute("login", joinDto);		// 프로필을 작성하지 않았기 때문에 임시 세션 저장
					logger.info("[멘토 최초 로그인 session 임시설정] => " + joinDto);
					return "redirect:/mentor/firstProfile.do";
				// 최초 로그인이 아닌경우 == 프로필을 작성한경우
				} else if(joinDto.getJoinRegisterYn().equals("Y")) {
					MentorDto mentorDto = profileBiz.MentorSelectOne(id);
					session.setAttribute("login", mentorDto);
					logger.info("[멘토 로그인 session 설정 => " + mentorDto);
					return "redirect:/mentor/main.do";
				}
			} else if (joinDto.getJoinRole().equals("멘티")) {
				logger.info("==========================[멘티 로그인]==========================");

			} else {
				return "loginPopUp.do";
			}
		} else {
			try {
				jsPrint("로그인 실패", "loginPopUp.do", response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "";
	}
	
	

	// sns로그인 실행
	@RequestMapping(value = "/snsLogin.do", method = RequestMethod.POST)
	public String snsLoginRes(Model model, @RequestParam String id, @RequestParam String joinPw, HttpSession sessionm, HttpServletRequest res) {
		logger.info("snsLogin");

		HttpSession s = res.getSession();

		return "";
	}

	public void jsPrint(String msg, String url, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); // 해당 코드가 없으면 글자가 깨진다 >>> web.xml에 filter를 했음에도 깨지는 이유는를 모르겠음
		String s = "<script type='text/javascript'>" + " alert('" + msg + "'); " + "location.href ='" + url + "';"
				+ "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
}
