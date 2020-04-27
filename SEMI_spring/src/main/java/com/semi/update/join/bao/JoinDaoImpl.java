package com.semi.update.join.bao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semi.update.All.join.LoginProfile.dto.LoginProfileDto;
import com.semi.update.join.dto.JoinDto;

import oracle.jdbc.OracleConnection.CommitOption;

@Repository
public class JoinDaoImpl implements JoinDao {

	/*
	  	스프링 적용 변경사항
	 	1. @Repository 어노테이션 적용
	 	2. SqlSessionTemplate객체 @Autowired를 통해 생성 : 해당객체가 SqlSession을 인스턴스화, 오픈 클로즈 등을 지원한다.
	 	3. 각각 메서드에 전역변수 SqlSessionTemplate을 적용 
	 	4. 메서드에 System.out.println() >>> Logger로 변경 
	 */
	
	@Autowired
	private SqlSessionTemplate session;

	private Logger logger = LoggerFactory.getLogger(JoinDaoImpl.class);

	// 로그인
	@Override
	public JoinDto Login(String id, String joinPw) {
		JoinDto dto = null;

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("joinPw", joinPw);

		try {
			dto = session.selectOne(NANESPACE + "Login", map);
		} catch (Exception e) {
			logger.info("[Error] Login"); // .info(); 서비스 동작상태 / .info() 개발자 필요(Dev 존에서만 사용)
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] Login");
			
		}
		return dto;
	}

	// 아이디 체크 >>> id가 있냐? > 있음. 그럼 중복 사용불가
	@Override
	public boolean idCheck(String id) {
		String res_id = "";
		boolean b = false;

		try {
			res_id = session.selectOne(NANESPACE + "idCheck", id);
			if (!(res_id == null||res_id.equals(""))) { // 빈값이 아니라면 >> 중복
				b = true; // true면 중복
			}
		} catch (Exception e) {
			logger.info("[Error] idCheck");
			//logger.debug("[Error] idCheck");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] idCheck");
		}
		return b;
	}

	@Override
	public boolean emailCheck(String joinEmail) {
		String res_email = "";
		boolean b = false;

		try {
			res_email = session.selectOne(NANESPACE + "emailCheck", joinEmail);
			if (!(res_email == null || res_email.equals(""))) {
				b = true;
			}
		} catch (Exception e) {
			logger.info("[Error] emailCheck");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] emailCheck");
		}

		return b;
	}

	// 회원가입
	@Override
	public int insertMember(JoinDto joinDto) {
		int res = 0;

		try {
			res = session.insert(NANESPACE + "insertMember", joinDto);
		} catch (Exception e) {
			logger.info("[Error] insertMember");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] insertMember");
		}
		return res;
	}

	// 전체 출력
	@Override
	public List<JoinDto> selectList_join() {
		List<JoinDto> list = null;

		try {
			list = session.selectList(NANESPACE + "selectList_join");
		} catch (Exception e) {
			logger.info("[Error] selectList_join");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] selectList_join");
		}
		return list;
	}

	// 미승인 멘토리스트 출력
	@Override
	public List<JoinDto> selsList_Mentor_reN() {
		List<JoinDto> list = null;

		try {
			list = session.selectList(NANESPACE + "selsList_Mentor_reN");
		} catch (Exception e) {
			logger.info("[Error] selsList_Mentor_reN");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] selsList_Mentor_reN");
		}

		return list;
	}

	// 프로필 미작성 멘티 출력
	@Override
	public List<JoinDto> selsList_Mentee_reN() {
		List<JoinDto> list = null;

		try {
			list = session.selectList(NANESPACE + "selsList_Mentee_reN");
		} catch (Exception e) {
			logger.info("[Error] selsList_Mentee_reN");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] selsList_Mentee_reN");
		}

		return list;
	}

	// 전체에서 아이디으로 검색
	@Override
	public List<JoinDto> selectList_join(String id) {
		List<JoinDto> list = null;

		try {
			list = session.selectList(NANESPACE + "selectList_join_id", id);
		} catch (Exception e) {
			logger.info("[Error] selectList_join");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] selectList_join");
		}

		return list;
	}

	// 미승인 멘토 이름으로 검색
	@Override
	public List<JoinDto> selsList_Mentor_reN(String id) {
		List<JoinDto> list = null;

		try {
			list = session.selectList(NANESPACE + "selsList_Mentor_reN_id", id);
		} catch (Exception e) {
			logger.info("[Error] selsList_Mentor_reN");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] selsList_Mentor_reN_id");
		}

		return list;
	}

	// 프로필 미작성 멘티 이름으로 검색
	@Override
	public List<JoinDto> selsList_Mentee_reN(String id) {
		List<JoinDto> list = null;

		try {
			list = session.selectList(NANESPACE + "selsList_Mentee_reN_id", id);
		} catch (Exception e) {
			logger.info("[Error] selsList_Mentee_reN");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] selsList_Mentee_reN_id");
		}

		return list;
	}

	// 가입자 프로필 작성유무 칼럼 'Y'수정
	@Override
	public int updateJoinRegister(String id, String joinRegisterYn) {
		int res = 0;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("joinRegisterYn", joinRegisterYn);

		try {
			res = session.update(NANESPACE + "updateJoinRegister", map);
		} catch (Exception e) {
			logger.info("[Error] updateJoinRegister");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] updateJoinRegister");
		}

		return res;
	}

	// 패스워드 변경용 id, pw 가져오기
	@Override
	public JoinDto passwordChange(String id) {
		JoinDto joinDto = null;

		try {
			joinDto = session.selectOne(NANESPACE + "passwordChange", id);
		} catch (Exception e) {
			logger.info("[error] passwordChange");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] passwordChange");
		}

		return joinDto;
	}

	@Override
	public int updatePassword(String id, String pw) {
		int res = 0;

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);

		try {
			res = session.update(NANESPACE + "updatePassword", map);
		} catch (Exception e) {
			logger.info("[error] updatePassword");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] updatePassword");
		}

		return res;
	}

	@Override
	public int updateLeave(String id) {
		int res = 0;

		try {
			res = session.update(NANESPACE + "updateLeave", id);
		} catch (Exception e) {
			logger.info("[error] updateLeave");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] updateLeave");
		}

		return res;
	}

	// 아이디 찾기
	@Override
	public JoinDto IdSearch(String email) {
		JoinDto joinDto = null;

		try {
			joinDto = session.selectOne(NANESPACE + "idSearch", email);
		} catch (Exception e) {
			logger.info("[error] IdSearch");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] IdSearch");
		}

		return joinDto;
	}

	// 비밀번호 찾기용(id, email) 정보 확인
	@Override
	public boolean PwCheck(String id, String email) {
		String res_email = "";
		boolean b = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("email", email);

		try {
			res_email = session.selectOne(NANESPACE + "PwCheck", map);
			if (!(res_email.equals(""))) { // 결과가 있다면 등록된 이메일인 것
				b = true;
			}
		} catch (Exception e) {
			logger.info("[Error] emailCheck");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] PwCheck");
		} 
		return b;
	}

//비밀번호 찾기용 정보 불러오기
	@Override
	public JoinDto PwInfo(String id) {
		JoinDto joinDto = null;

		try {
			joinDto = session.selectOne(NANESPACE + "PwInfo", id);
		} catch (Exception e) {
			logger.info("[error] PwInfo");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] PwInfo");
		} 
		
		return joinDto;
	}

	// join테이블 profile테이블 조인 Dao========================

	// id를 이용해 LoginProfileDto의 멘토 정보 접근
	@Override
	public LoginProfileDto getLoginProfileDto_mentor(String id) {
		LoginProfileDto loginProfileDto = null;

		try {
			loginProfileDto = session.selectOne(NANESPACE + "getLoginProfileDto_mentor", id);
		} catch (Exception e) {
			logger.info("[Error] getLoginProfileDto_mentor");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] getLoginProfileDto_mentor");
		} 
		return loginProfileDto;
	}

	// id를 이용해 LoginProfileDto의 멘티 정보 접근
	@Override
	public LoginProfileDto getLoginProfileDto_mentee(String id) {
		LoginProfileDto loginProfileDto = null;

		try {
			loginProfileDto = session.selectOne(NANESPACE + "getLoginProfileDto_mentee", id);
		} catch (Exception e) {
			logger.info("[Error] getLoginProfileDto_mentee");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] getLoginProfileDto_mentee");
		}

		return loginProfileDto;
	}

	@Override
	public List<LoginProfileDto> selectList_MentorList() {
		List<LoginProfileDto> list = null;

		try {
			list = session.selectList(NANESPACE+"loginProfile_mentorList");
		} catch (Exception e) {
			logger.info("[Error] join searchMentorList");
			e.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public List<LoginProfileDto> search_MentorName(String memberName) {
		List<LoginProfileDto> list = null;

		try {
			list = session.selectList(NANESPACE + "loginProfile_mentorSearch", memberName);
		} catch (Exception e) {
			logger.info("[Error] join searchMentorMemberName");
			e.printStackTrace();
			//throw new GlobalExceptionHandler("[Dao Error] search_MentorName");
		}

		return list;
	}

}
