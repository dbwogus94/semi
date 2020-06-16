--JOIN TABLE
DROP SEQUENCE MEMBER_JOIN_SEQ;
DROP TABLE MEMBER_JOIN;

CREATE SEQUENCE MEMBER_JOIN_SEQ;
CREATE TABLE MEMBER_JOIN(
   JOIN_NO           NUMBER            NOT NULL,
   ID                VARCHAR2(200)       NOT NULL,
   JOIN_PW           VARCHAR2(200)       NOT NULL,
   JOIN_EMAIL        VARCHAR2(200)       NOT NULL,
   JOIN_ROLE         CHAR(6)            NOT NULL,
   JOIN_DATE         DATE              NOT NULL,
   JOIN_JOINED       CHAR(1)           NOT NULL,
   JOIN_REGISTER_YN    CHAR(1)          NOT NULL, 
   CONSTRAINT JOIN_PK PRIMARY KEY (ID),
   CONSTRAINT JOIN_UQ01 UNIQUE (JOIN_NO),
   CONSTRAINT JOIN_UQ02 UNIQUE (JOIN_EMAIL),
   CONSTRAINT JOIN_CH01_ROLE CHECK (JOIN_ROLE IN('멘토', '멘티', '관리')),
   CONSTRAINT JOIN_CH02_JOINED CHECK (JOIN_JOINED IN('Y','N')),
   CONSTRAINT JOIN_CH03_REGISTER CHECK (JOIN_REGISTER_YN IN('Y','N','W'))
);

--MENTEE01 
INSERT INTO MEMBER_JOIN VALUES (
   MEMBER_JOIN_SEQ.NEXTVAL,
   'MENTEE01',
   '1234',
   'MENTEE01@GMAIL.COM',
   '멘티',
   SYSDATE,
   'Y',
   'Y'
);

--MENTEE02
INSERT INTO MEMBER_JOIN VALUES (
   MEMBER_JOIN_SEQ.NEXTVAL,
   'MENTEE02',
   '1234',
   'MENTEE02@GMAIL.COM',
   '멘티',
   SYSDATE,
   'Y',
   'Y'
);

--MENTOR01
INSERT INTO MEMBER_JOIN VALUES (
   MEMBER_JOIN_SEQ.NEXTVAL,
   'MENTOR01',
   '1234',
   'MENTOR01@GMAIL.COM',
   '멘토',
   SYSDATE,
   'Y',
   'Y'
);
--ADMIN
INSERT INTO MEMBER_JOIN VALUES (
   MEMBER_JOIN_SEQ.NEXTVAL,
   'ADMIN',
   '1234',
   'ADMIN@GMAIL.COM',
   '관리',
   SYSDATE,
   'Y',
   'N'
);

SELECT * FROM MEMBER_JOIN ORDER BY JOIN_NO DESC;

DELETE 
FROM MEMBER_JOIN
WHERE JOIN_NO = 23;

--MEMBER PROFILE
DROP SEQUENCE MEMBER_PROFILE_SEQ;
DROP TABLE MEMBER_PROFILE;

CREATE SEQUENCE MEMBER_PROFILE_SEQ;
CREATE TABLE MEMBER_PROFILE
(
    MEMBER_NO             NUMBER            NOT NULL, 
    ID                    VARCHAR2(200)     NOT NULL, 
    MEMBER_ROLE           CHAR(6)           NOT NULL, 
    MEMBER_REGISTER_YN    CHAR(1)           NOT NULL, 
    MEMBER_NAME           VARCHAR2(20)      NOT NULL, 
    MEMBER_BIRTH          CHAR(10)          NOT NULL, 
    MEMBER_HEIGHT         VARCHAR2(20)      NOT NULL, 
    MEMBER_WEIGHT         VARCHAR2(20)      NOT NULL, 
    MEMBER_ADDR           VARCHAR2(1000)    NOT NULL, 
    MEMBER_PHONE          VARCHAR2(200)     NOT NULL, 
    MEMBER_ONE_INTRO      VARCHAR2(2000)    NOT NULL, 
    MEMBER_COIN           NUMBER,
    MEMBER_CAREER         VARCHAR2(4000), 
    MEMBER_CONTENT        VARCHAR2(4000), 
    MEMBER_SCORE          NUMBER, 
    MEMBER_GENDER         CHAR(1), 
    MEMBER_ACTIVITY       NUMBER, 
    MEMBER_BASAL          NUMBER, 
    MEMBER_BMI            NUMBER, 
    CONSTRAINT MEMBER_PROFILE_PK PRIMARY KEY (MEMBER_NO),
    CONSTRAINT MEMBER_PROFILE_UQ01 UNIQUE (ID),
    CONSTRAINT MEMBER_PROFILE_UQ02 UNIQUE (MEMBER_PHONE),
    CONSTRAINT MEMBER_PROFILE_CH01 CHECK (MEMBER_REGISTER_YN IN('Y','N')),
    CONSTRAINT MEMBER_PROFILE_CH02 CHECK (MEMBER_GENDER IN('F','M')),
    CONSTRAINT MEMBER_PROFILE_FK01 FOREIGN KEY(ID) REFERENCES MEMBER_JOIN(ID)
);

--MENTEE01 PROFILE
INSERT INTO MEMBER_PROFILE VALUES(
   MEMBER_PROFILE_SEQ.NEXTVAL, 
   'MENTEE01', 
   '멘티', 
   'Y',  
   '김멘티', 
   '20000104', 
   '170', 
   '56', 
   '서울특별시 강남구', 
   '010-1111-1111', 
   '저는 홈트를 좋아해요.', 
   0, 
   'NULL', 
   'NULL', 
   0,                 
   'M', 
   '2', 
   '0', 
   '0'); 

--MENTEE02 PROFILE
INSERT INTO MEMBER_PROFILE VALUES(
   MEMBER_PROFILE_SEQ.NEXTVAL, 
   'MENTEE02', 
   '멘티', 
   'Y',  
   '최자바', 
   '19960829', 
   '170', 
   '56', 
   '경기도 남양주시', 
   '010-0000-1111', 
   '건강하게 다이어트 하고 싶어요!', 
   0, 
   'NULL', 
   'NULL', 
   0,                 
   'F', 
   '3', 
   '0', 
   '0'); 

--MENTOR01 PROFILE
INSERT INTO MEMBER_PROFILE
VALUES(
MEMBER_PROFILE_SEQ.NEXTVAL, 'MENTOR01', '멘토', 
'Y',  '김코치', '19940104', '184', '83', 
'경기도 안양시', '010-0000-0000', '저는 헬스를 잘합니다.', 
2000, '다이어트/헬스/건강', '경력은...',0,   --멘토
'', 1, 0, 0);          

SELECT * FROM MEMBER_PROFILE

--JOIN테이블과 PROFILE테이블 join하여 ID로 맨토 정보 가져오기
SELECT
JOIN_NO, ID, JOIN_PW, JOIN_EMAIL, JOIN_ROLE, JOIN_DATE, JOIN_JOINED, JOIN_REGISTER_YN,
MEMBER_NO, MEMBER_NAME, MEMBER_BIRTH, MEMBER_HEIGHT, MEMBER_WEIGHT,
MEMBER_ADDR, MEMBER_PHONE, MEMBER_ONE_INTRO, 
MEMBER_COIN, MEMBER_CAREER, MEMBER_CONTENT, MEMBER_SCORE
FROM MEMBER_JOIN JOIN MEMBER_PROFILE USING(ID)
WHERE ID = '1994dbwogus';


--멘토 추가
INSERT INTO 
MEMBER_PROFILE(
	MEMBER_NO, ID, MEMBER_ROLE, MEMBER_REGISTER_YN, 
	MEMBER_NAME, MEMBER_BIRTH, MEMBER_HEIGHT, MEMBER_WEIGHT, MEMBER_ADDR, MEMBER_PHONE, 
	MEMBER_ONE_INTRO, MEMBER_COIN, MEMBER_CAREER, MEMBER_CONTENT, MEMBER_SCORE) 
VALUES(
	MEMBER_PROFILE_SEQ.NEXTVAL, 'ADMIN', '관리', 'Y', 
	'이름', 'yyyyMMdd', '180', '75', 'ㅁㅁ시 OO구 ㅁㅁ동', '1234-5678',
	'한 줄 자기소개', 0,'특기1,특기2,특기3', '프로필 사진', 0);
--멘토 수정
UPDATE MEMBER_PROFILE 
SET MEMBER_NAME = '이름수정', MEMBER_BIRTH = 'yyyyMMdd', MEMBER_HEIGHT = '180', MEMBER_WEIGHT = '75',
	MEMBER_ADDR = '사랑시 고백구 행복동', MEMBER_PHONE = '1234-5678', MEMBER_ONE_INTRO = '한 줄 소개 수정',
	MEMBER_CAREER = '특기1,특기2,특기3', MEMBER_CONTENT = '프로필 사진'
WHERE ID = 'ADMIN'


UPDATE MEMBER_PROFILE
SET MEMBER_COIN = 2000
WHERE ID = 'MENTOR01';

DELETE FROM MEMBER_PROFILE
WHERE ID = 'ADMIN';



--BOARD =========================================================================================================================
DROP SEQUENCE FREE_BOARD_SEQ;
DROP TABLE FREE_BOARD;

CREATE SEQUENCE FREE_BOARD_SEQ;

CREATE TABLE FREE_BOARD
(
    BOARD_NO          NUMBER             NOT NULL, 
    ID                VARCHAR2(200)      NOT NULL, 
    BOARD_NAME        VARCHAR2(200)      NULL, 
    BOARD_TITLE       VARCHAR2(2000)     NULL, 
    BOARD_CONTENT     CLOB               NULL, 
    BOARD_REGDATE     DATE               NULL, 
    BOARD_LIKE        NUMBER             NULL, 
    BOARD_FILEPATH    VARCHAR2(3000)	 NULL,
    BOARD_IMGPATH     VARCHAR2(3000)	 NULL,
    BOARD_THUMBNAIL   VARCHAR2(1000)	 NULL,
    CONSTRAINT FREE_BOARD_PK PRIMARY KEY (BOARD_NO),
    CONSTRAINT FREE_BOARD_FK01 FOREIGN KEY(ID) REFERENCES MEMBER_JOIN(ID)
);

--전채 선택
SELECT * FROM FREE_BOARD
ORDER BY BOARD_NO DESC;

--SELECT ONE
-- 게시판 불러오기
SELECT * FROM FREE_BOARD
WHERE ID = 'MENTEE01'
AND BOARD_NO = ;

--시퀀스를 사용한 최종 페이징 쿼리
SELECT X.RN, X.BOARD_NO, X.ID, X.BOARD_NAME, X.BOARD_TITLE, X.BOARD_REGDATE, X.BOARD_LIKE
FROM(
	SELECT ROWNUM as RN, A.BOARD_NO, A.ID, A.BOARD_NAME, A.BOARD_TITLE, A.BOARD_REGDATE, A.BOARD_LIKE 
	FROM (
		SELECT BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE 
		FROM FREE_BOARD
		ORDER BY BOARD_NO DESC
		) A
	WHERE ROWNUM <= 20		-- 핵심
	) X
WHERE X.RN >= 11;			-- 핵심


--user 프로필 img 가져오기
SELECT f.BOARD_NO, f.ID, f.BOARD_NAME, f.BOARD_TITLE, f.BOARD_CONTENT, f.BOARD_LIKE ,f.BOARD_FILEPATH, m.MEMBER_CONTENT 
FROM FREE_BOARD f, MEMBER_JOIN J, MEMBER_PROFILE m
WHERE f.ID = j.ID
AND j.ID = m.ID
AND f.BOARD_NO = '2';

-- 전체 추가
INSERT INTO FREE_BOARD(BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_REGDATE, BOARD_LIKE, BOARD_FILEPATH, BOARD_IMGPATH, BOARD_THUMBNAIL) 
VALUES(FREE_BOARD_SEQ.NEXTVAL, 'MENTEE01', '김멘티', '글제목', '글내용', SYSDATE, 0, null, null, null);


-- 반복문으로 여러개 추가========================================================================================================================================================
DECLARE v_cnt NUMBER := 1;

BEGIN
    LOOP
    	EXIT WHEN v_cnt = 100;
			INSERT INTO FREE_BOARD(BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_REGDATE, BOARD_LIKE, BOARD_FILEPATH, BOARD_IMGPATH, BOARD_THUMBNAIL) 
			VALUES(FREE_BOARD_SEQ.NEXTVAL, '1994dbwogus', '유재현', '반복추가:'||to_char(v_cnt), '글내용', SYSDATE, ROUND(DBMS_RANDOM.VALUE (1, 100)), null, null, null);
			v_cnt := v_cnt+1;
	END LOOP;
END;
-- ========================================================================================================================================================




-- 1) 이미지 insert : id, 썸내일, 이미지 추가
INSERT INTO FREE_BOARD(BOARD_NO, ID, BOARD_IMGPATH, BOARD_THUMBNAIL) 
VALUES(FREE_BOARD_SEQ.NEXTVAL, 'MENTEE01', '이미지경로1,이미지경로2,이미지경로3', '썸내일 이미지');

-- 2) 이미지 insert이후 나머지 내용 update 
UPDATE FREE_BOARD
SET BOARD_NAME = '', BOARD_TITLE = '', BOARD_CONTENT = '', BOARD_REGDATE = SYSDATE, BOARD_LIKE = 0, BOARD_FILEPATH = ''
WHERE ID = ''
AND BOARD_NO = '';

-- 1) 이미지 없을때  나머지 insert : board 내용 insert
INSERT INTO FREE_BOARD(BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_REGDATE, BOARD_LIKE, BOARD_FILEPATH) 
VALUES(FREE_BOARD_SEQ.NEXTVAL, 'MENTEE01', '김멘티', '글제목', '글내용', SYSDATE, 0, '첨부파일');


-- 썸내일 명과 id로 보드 seq 찾기 
SELECT BOARD_NO FROM FREE_BOARD
WHERE ID = ''
AND BOARD_IMGPATH = '';


-- 이미지 수정 (썸내일 이미지, 본문 이미지)
UPDATE FREE_BOARD
SET BOARD_IMGPATH = '', BOARD_THUMBNAIL=''
WHERE ID =''
AND BOARD_NO = '';

-- 게시판 수정
UPDATE FREE_BOARD
SET BOARD_TITLE = '', BOARD_CONTENT = '', BOARD_FILEPATH = '', BOARD_REGDATE = SYSDATE
WHERE ID = ''
AND BOARD_NO = '';



-- 삭제
DELETE FREE_BOARD
WHERE ID = 'MENTEE01';


--COMMENT BOARD ===================================================================================================================

/*
 부모글 3

댓글1				부모글 : 3,  대댓글 그룹 1번(시퀀스), 	대댓글 그룹의 순서 0번(댓글 부모 : 0)
 ㄴ 대댓글1			부모글 : 3,  대댓글 그룹 1번, 			대댓글 그룹의 순서 1번
 ㄴ 대댓글2			부모글 : 3,  대댓글 그룹 1번, 			대댓글 그룹의 순서 2번
 ㄴ 대댓글3			부모글 : 3,  대댓글 그룹 1번, 			대댓글 그룹의 순서 3번

댓글2				부모글 : 3,  대댓글 그룹 2번(시퀀스), 	대댓글 그룹의 순서 0번(댓글 부모 : 0)
ㄴ 대댓글1			부모글 : 3,  대댓글 그룹 2번, 			대댓글 그룹의 순서 1번

댓글3				부모글 : 3,  대댓글 그룹 3번(시퀀스), 	대댓글 그룹의 순서 0번(댓글 부모 : 0)

댓글4				부모글 : 3,  대댓글 그룹 4번(시퀀스), 	대댓글 그룹의 순서 0번(댓글 부모 : 0)
ㄴ 대댓글1			부모글 : 3,  대댓글 그룹 4번, 			대댓글 그룹의 순서 1번
ㄴ 대댓글2			부모글 : 3,  대댓글 그룹 4번, 			대댓글 그룹의 순서 2번  


 ##1 작성일 수정일 표시  
 if(수정일자 == 작성일자){
 	(작성일)yyyy-MM-dd hh:mm:ss		// 작성날짜
 } else {
 	(수정일)yyyy-MM-dd hh:mm:ss		// 수정날짜
 }
 
 ##2 대댓들은 클릭하여 대댓글 작성하게 만들것(좀더 고민하자)
 ex) </a>@클릭한 닉네임<a> 대댓글 내용~ 
 
*/


DROP SEQUENCE COMMENT_BOARD_SEQ;		
DROP SEQUENCE COMMENT_GROUP_NO;
DROP TABLE COMMENT_BOARD;

CREATE SEQUENCE COMMENT_BOARD_SEQ;	-- 글의 시퀀스
CREATE SEQUENCE COMMENT_GROUP_NO;	-- 댓글 그룹 넘버(시퀀스)
	
CREATE TABLE COMMENT_BOARD									-- 댓글용 테이블
(
    COMMENT_NO         NUMBER             NOT NULL, 		-- 시퀀스
    BOARD_NO           NUMBER             NOT NULL, 		-- 부모글, join할 외래키
    COMMENT_GROUPNO    NUMBER             NOT NULL, 		-- 댓글 그룹 넘버(시퀀스), 대댓글시 같은 글인지 확인용
    COMMENT_GROUPSEQ   NUMBER			  NOT NULL,			-- 댓글 그룹내의 순서, 	댓글 부모글이면 : 0
    ID                 VARCHAR2(200)      NOT NULL, 		-- 아이디
    COMMENT_NAME       VARCHAR2(200)      NOT NULL, 		-- 작성자 이름
    COMMENT_CONTENT    VARCHAR2(2000)     NOT NULL, 		-- 내용
    COMMENT_REGDATE    DATE               NOT NULL, 		-- 작성일
    COMMENT_UPDATE_REGDATE    DATE               NOT NULL, 		-- 수정일
    CONSTRAINT COMMENT_BOARD_PK PRIMARY KEY (COMMENT_NO),
    CONSTRAINT COMMENT_BOARD_FK01 FOREIGN KEY(ID) REFERENCES MEMBER_JOIN(ID),
    CONSTRAINT COMMENT_BOARD_FK02 FOREIGN KEY(BOARD_NO) REFERENCES FREE_BOARD(BOARD_NO)
);

-- 전채 선택
SELECT * FROM COMMENT_BOARD
ORDER BY COMMENT_GROUPNO, COMMENT_GROUPSEQ;

-- 글하나의 댓글들 확인
SELECT * FROM COMMENT_BOARD
WHERE BOARD_NO = 301
ORDER BY COMMENT_GROUPNO, COMMENT_GROUPSEQ;

-- 글하나의 1개 댓글의 대댓글  확인
SELECT * FROM COMMENT_BOARD
WHERE BOARD_NO = 301
AND COMMENT_GROUPNO = 1;


-- 내가 작성한 전체 댓글확인
SELECT * FROM COMMENT_BOARD
WHERE ID = '1994dbwogus';

-- 댓글 추가 
INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301,COMMENT_GROUP_NO.NEXTVAL, 0, 'MENTEE01', '김멘티', '01 댓글입니다.', SYSDATE, SYSDATE);

INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301,COMMENT_GROUP_NO.NEXTVAL, 0, 'MENTEE02', '최자바', '02 댓글입니다.', SYSDATE, SYSDATE);

INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301,COMMENT_GROUP_NO.NEXTVAL, 0, 'MENTOR01', '김코치', '03 댓글입니다.', SYSDATE, SYSDATE);

INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301,COMMENT_GROUP_NO.NEXTVAL, 0, '1994dbwogus', '유재현', '03 댓글입니다.', SYSDATE, SYSDATE);



-- 그룹 1 대댓글 추가
INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301, 1, 1, '1994dbwogus', '유재현', '01 유재현 대댓글입니다.', SYSDATE, SYSDATE);

INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301, 1, 2, 'MENTOR01', '김코치', '01 김코치 대댓글입니다.', SYSDATE, SYSDATE);

INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301, 1, 3, 'MENTEE02', '최자바', '01 최자바 대댓글입니다.', SYSDATE, SYSDATE);

INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301, 1, 4, 'MENTEE01', '김멘티', '01 김멘티 대댓글입니다.', SYSDATE, SYSDATE);

-- 그룹 2 대댓글 추가

-- 그룹 3 대댓글 추가
INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301, 3, 1, 'MENTEE02', '최자바', '02 최자바 대댓글입니다.', SYSDATE, SYSDATE);

INSERT INTO COMMENT_BOARD(COMMENT_NO, BOARD_NO, COMMENT_GROUPNO, COMMENT_GROUPSEQ, ID, COMMENT_NAME, COMMENT_CONTENT, COMMENT_REGDATE, COMMENT_UPDATE_REGDATE)
VALUES(COMMENT_BOARD_SEQ.NEXTVAL, 301, 3, 2, '1994dbwogus', '유재현', '02 최자바 대댓글입니다.', SYSDATE, SYSDATE);



-- 댓글 수정
UPDATE COMMENT_BOARD
SET COMMENT_CONTENT = '(수정)01 댓글을 수정했습니다.', COMMENT_UPDATE_REGDATE = SYSDATE
WHERE BOARD_NO = 301
AND COMMENT_GROUPNO = 1		-- 변경할 댓글 그룹의 번호
AND COMMENT_GROUPSEQ = 0	-- 댓글은 항상 0
AND ID = 'MENTEE01';


-- 대댓글 수정
UPDATE COMMENT_BOARD
SET COMMENT_CONTENT = '(수정)01 대댓글을 수정했습니다.', COMMENT_UPDATE_REGDATE = SYSDATE
WHERE BOARD_NO = 301
AND COMMENT_GROUPNO = 1		-- 변경할 대댓글 그룹의 번호
AND COMMENT_GROUPSEQ = 1	-- 변경할 대댓글의 그룹내 순서
AND ID = '1994dbwogus';


-- 댓들 삭제 (대댓글 달렸으면 삭제 못하게 하자)
DELETE COMMENT_BOARD
WHERE BOARD_NO = 301
AND COMMENT_GROUPNO = 1
AND COMMENT_GROUPSEQ = 0	-- 고정
AND ID = 'MENTEE01';

-- 대댓글 삭제 
DELETE COMMENT_BOARD
WHERE BOARD_NO = 301
AND COMMENT_GROUPNO = 1
AND COMMENT_GROUPSEQ = 1
AND ID = '1994dbwogus';














-- 사용안함
-- FILE_BOARD ====================================================================================================--=====
DROP SEQUENCE FILE_BOARD_SEQ;
DROP TABLE FILE_BOARD;

CREATE SEQUENCE FILE_BOARD_SEQ;
CREATE TABLE FILE_BOARD
(
    FILE_NO          NUMBER            NOT NULL, 
    FILE_GROUP_NO    NUMBER            NOT NULL, 
    FILE_CATEGORY    CHAR(2)     	   NOT NULL, 
    FILE_FILE_NO     NUMBER            NULL, 
    FILE_PATH        VARCHAR2(2000)    NOT NULL, 
    CONSTRAINT FILE_BOARD_PK PRIMARY KEY (FILE_NO),
    CONSTRAINT FILE_BOARD_CH01 CHECK (FILE_CATEGORY IN('I','F'))
);


SELECT * FROM FILE_BOARD;


-- 이미지 추가
INSERT INTO FILE_BOARD 
VALUES(FILE_BOARD_SEQ.NEXTVAL, 1, 'I', 1, '경로');

INSERT INTO FILE_BOARD 
VALUES(FILE_BOARD_SEQ.NEXTVAL, 1, 'I', 2, '경로2');

INSERT INTO FILE_BOARD 
VALUES(FILE_BOARD_SEQ.NEXTVAL, 1, 'I', 3, '경로3');


-- 파일 추가(일단은 멀티 업로드 구현X)
INSERT INTO FILE_BOARD 
VALUES(FILE_BOARD_SEQ.NEXTVAL, 2, 'F', 1, '파일경로');

-- 삭제
DELETE FROM FILE_BOARD
WHERE FILE_NO = 4;



--BOARD 버전2 =========================================================================================================================
DROP SEQUENCE FREE_BOARD_SEQ;
DROP TABLE FREE_BOARD;

CREATE SEQUENCE FREE_BOARD_SEQ;

CREATE TABLE FREE_BOARD
(
    BOARD_NO          NUMBER             NOT NULL, 
    ID                VARCHAR2(200)      NOT NULL, 
    BOARD_NAME        VARCHAR2(200)      NOT NULL, 
    BOARD_TITLE       VARCHAR2(2000)     NOT NULL, 
    BOARD_CONTENT     CLOB               NOT NULL, 
    BOARD_REGDATE     DATE               NOT NULL, 
    BOARD_LIKE        NUMBER             NULL, 
    BOARD_FILEPATH    VARCHAR2(2000)	 NULL,
    FILE_NO    		  NUMBER			 NULL,
    CONSTRAINT FREE_BOARD_PK PRIMARY KEY (BOARD_NO),
    CONSTRAINT FREE_BOARD_FK01 FOREIGN KEY(ID) REFERENCES MEMBER_JOIN(ID),
    CONSTRAINT FREE_BOARD_FK02 FOREIGN KEY(FILE_NO) REFERENCES FILE_BOARD(FILE_NO)
);

--전채 선택
SELECT * FROM FREE_BOARD;

--SELECT ONE
-- 게시판 불러오기
SELECT * FROM FREE_BOARD
WHERE ID = 'MENTEE01';

-- 추가
INSERT INTO FREE_BOARD(BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_CONTENT, BOARD_REGDATE, BOARD_LIKE, BOARD_FILEPATH, FILE_NO) 
VALUES(FREE_BOARD_SEQ.NEXTVAL, 'MENTEE01', '김멘티', '글제목', '글내용', SYSDATE, 0, '파일경로', 1);

-- 게시판 수정
UPDATE FREE_BOARD
SET BOARD_TITLE = '', BOARD_CONTENT = '', BOARD_REGDATE = SYSDATE
WHERE ID = 'MENTEE01';

-- 연결된 이미지 수정

UPDATE FILE_BOARD
SET 

-- 게시판에 속한 이미지 가져와 보기
-- 1) 게시판의 PK를 이용해 게시판에서 외래키인 FILE_NO를 가져온다 
-- 2) FILE_BOARD테이블에서 FILE_NO(PK)를 이용해 그룹번호를 가지고 온다
-- 3) 가져온 그룹번호와 일치하는 로우를 전부불러온다.
SELECT * FROM FILE_BOARD
WHERE FILE_GROUP_NO IN
	(SELECT FILE_GROUP_NO FROM FILE_BOARD 
		WHERE FILE_NO IN (SELECT FILE_NO FROM FREE_BOARD WHERE ID = 'MENTEE01'));


-- 삭제
DELETE FREE_BOARD
WHERE ID = 'MENTEE01';





-- 페이징============================================================================================================================================= 

-- # 용어 정리 
-- 서브쿼리 : WHERE 절/FROM 절/SELECT 절 안에 들어가는 쿼리를 통칭하여 서브쿼리라고 한다.
-- 중첩서브쿼리 : WHERE절 서브쿼리	- 특징 > ORDER BY를 사용하지 못한다.
-- 인라인뷰 : FROM절 서브쿼리		- 특징 > 뷰(VIEW)처럼 하나의 가상테이블이다.
-- 스칼라서브쿼리 : SELECT절 서브쿼리 - 특징 > 반드시 단일 값을 리턴하는 쿼리를 사용한다. 주로 SUM, COUNT, MIN, MAX 등과 같은 집계 함수와 사용한다.

-- ROWNUM은 오라클에서 제공하는 Top-N 쿼리 프로세싱 기능이다 : 쿼리문의 로우 데이터가 출력될때  결과에 순서내로 숫자를 매겨서 나오게 한다.    
--												응용으로는 넘버링된 숫자의 범위를 지정하여 데이터를 가져올 수 있다 

-- ROWNUM의 비밀 ==> SELECT ROWNUM : 가상 칼럼을 만들어서 넘버를 붙인다 / WHERE ROWNUM : 오라클에서 제공하는 TOP-N 쿼리 프로세싱의 핵심 기술이다 

-- # ROWNUM의 동작 원리
-- ROWNUM은 쿼리 내에서 사용 가능한 (실제 컬럼이 아닌) 가상 컬럼(pseudocolumn)입니다. 
-- ROWNUM에는 숫자 1, 2, 3, 4, ... N의 값이 할당됩니다. 여기서 N 은 ROWNUM과 함께 사용하는 로우의 수를 의미합니다. 
-- ROWNUM의 값은 로우에 영구적으로 할당되지 않습니다(이는 사람들이 많이 오해하는 부분이기도 합니다). 테이블의 로우는 숫자와 연계되어 참조될 수 없습니다. 
-- 따라서 테이블에서 "row 5"를 요청할 수 있는 방법은 없습니다. "row 5"라는 것은 존재하지 않기 때문입니다.
-- 또 ROWNUM 값이 실제로 할당되는 방법에 대해서도 많은 사람들이 오해를 하고 있습니다. 
-- ROWNUM 값은 쿼리의 조건절이 처리되고 난 이후, 그리고 sort, aggregation이 수행되기 이전에 할당됩니다.

-- # 실행 순서
--1. FROM/WHERE 절이 먼저 처리됩니다.
--2. ROWNUM이 할당되고 FROM/WHERE 절에서 전달되는 각각의 출력 로우에 대해 증가(increment) 됩니다.
--3. SELECT가 적용됩니다.
--4. GROUP BY 조건이 적용됩니다.
--5. HAVING이 적용됩니다.
--6. ORDER BY 조건이 적용됩니다.

--출처: https://5dol.tistory.com/127 [5dol Story]


-- 페이징 쿼리 >>> 정렬된 테이블 번호를 붙이고 범위를 잘라온다.


-- ROWNUM 연습1 -- 단순출력(잘못된 사용이다.)
SELECT ROWNUM as RN, BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE 
FROM FREE_BOARD




-- ROWNUM 연습2 -- 인라인뷰 없이 ROWNUM + ORDER BY와 함께 사용		>> 잘못된 방법을 알기 위한 예시

-- 2-1) 잘못된사용	>>> 번호를 붙인 테이블에 정렬이 되고 있음
SELECT ROWNUM as RN, BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE 
FROM FREE_BOARD
ORDER BY BOARD_NO DESC		


-- 결과 : 2-1)의 쿼리를 실행하면 아무문제 없이 출력이된다.
-- 왜 일까?	BOARD_NO는 시퀀스이다 시퀀스는 생성하는 순서대로 작성이된다. 
--			그렇기 때문에 BOARD_NO를 오름차순 하든 내림차순 하든 아무런 문제 없이 정렬된 값에 넘버가 붙는다.
--			이러한 쿼리는 잘못 사용하고 있지만, 결과는 의도한 대로 나오는 것처럼 보여 잘못을 인지하기 힘들게 한다.

-- 2-2) 잘못된 사용 예시 : 시퀀스가 아닌 BOARD_LIKE를 기준으로 정렬 
SELECT ROWNUM as RN, BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE 
FROM FREE_BOARD
ORDER BY BOARD_LIKE DESC;	

-- 결과 : 2-2)의 쿼리를 출력하면 의도 하지 않는 결과를 출력한다. ROUNUM에 의해 넘버링이 먼저 되고 그 후 BOARD_LIKE를 기준으로 내림차순 정렬된 결과가 나오게 된다.

-- 왜 일까?	결론은 실행되는 순서 때문이다. ROUNUM에 의해 넘버링이 먼저 되며, 그후에 정렬(ORDER BY)연산이 실행된다. 
--			그렇기 때문에 최종적으로    RN의 순서는 BOARD_LIKE를 기준으로 뒤죽박죽 출력이 된다.



-- ROWNUM 연습3 -- 인라인뷰 + SELECT문의 ROWNUM + ORDER BY 함께 사용		>> 정확한 사용법을 알기 위한 예시
SELECT ROWNUM AS RN, A.BOARD_NO, A.BOARD_NAME, A.BOARD_TITLE, A.BOARD_REGDATE, A.BOARD_LIKE
FROM (
	SELECT BOARD_NO, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE
	FROM FREE_BOARD
	ORDER BY BOARD_LIKE DESC) A

-- 결과 : 정상적으로 의도한 결과를 출력한다. BOARD_LIKE를 기준으로 내림차순 정렬이 되고, 그 후 넘버링이 붙은 결과를 출력한다.
-- 왜 일까?	위에 연습2에서 설명을 했듯이 문제는 쿼리의 실행 순서이다. 
--			핵심순서는 '정렬을 먼저하고 넘버링을 붙여라'이다. 
--			이를 위해서 서브쿼리(인라인뷰, 가상테이블)을 사용하여 정렬한 테이블을 먼저 만들고, 해당 결과에 ROWNUM을 사용하여 넘버링을 한다.
	
	
	
-- ROWNUM 연습4 -- 가상테이블 + ROWNUM + ORDER BY를 사용한 결과에서 ROWNUM넘버 5 ~ 10을 가져오자		>>> TOP N 쿼리 사용 예시(페이징)	

-- 4-1) > 결과가 나오지 않는 잘못된 사용
SELECT ROWNUM AS RN, A.BOARD_NO, A.BOARD_NAME, A.BOARD_TITLE, A.BOARD_REGDATE, A.BOARD_LIKE
FROM (
	SELECT BOARD_NO, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE
	FROM FREE_BOARD
	ORDER BY BOARD_LIKE DESC) A
WHERE ROWNUM BETWEEN 5 AND 10; 	-- 또는   WHERE RN BETWEEN 5 AND 10;

-- 결과 : WHERE에 ROWNUM을 사용하면 아무것도 나오지 않는다. / WHERE에 RN이라는 이름으로 사용하면 invalid identifier(잘못된 식별자) 에러를 발생한다. 
-- 왜 일까? 	1. 먼저 알아야 할 것은 WHERE의 'ROWNUM'과 SELECT의 'ROWNUM AS RN'은 다른 ROWNUM이다. 
-- 			2. WHERE ROWNUM의 기능은 1 ~ N개의 데이터를 가져오기 위해 사용된다.(상위 N 개의 로우만을 가져오도록 명령) 그렇기 때문에 범위를 지정하면 아무런 결과를 내보내지 않는다.
--			3. SELECT의 ROWNUM AS RN은 현재 가상테이블이기 때문에 위처럼 사용시에 조건에 참조하여 사용할 수 없기 때문에 잘못된 식별자 에러가 발생한다.
	
	
-- 4-2) SELECT의 'ROWNUM AS RN'를 참조하기 위해 인라인뷰(X)를 추가로 사용하여  5~10 범위의 데이터를 가져오자.	
SELECT X.RN, X.BOARD_NO, X.BOARD_NAME, X.BOARD_TITLE, X.BOARD_REGDATE, X.BOARD_LIKE
FROM (
	SELECT ROWNUM AS RN, A.BOARD_NO, A.BOARD_NAME, A.BOARD_TITLE, A.BOARD_REGDATE, A.BOARD_LIKE
	FROM (
		SELECT BOARD_NO, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE
		FROM FREE_BOARD
		ORDER BY BOARD_LIKE DESC) A
	)X	
WHERE X.RN BETWEEN 5 AND 10;

-- 결과 : 정상적으로 TOP N 쿼리를 내보낸다. 
--		1. BOARD_LIKE를 기준으로 정렬한다.
-- 		2. 정렬된 데이터에 넘버링을 한다.
--	 	3. 넘버링된 데이터에서 5 ~ 10번째의 데이터를 가지고 온다. 
-- 왜 일까 :	4-1)의 결과와 다르게 정상 작동되는 이유는 SELECT의 'ROWNUM AS RN'을 참조하여 사용할 수 있게 됬기 때문이다.
--			인라인뷰 A는 BOARD_LIKE를 기준으로 정렬이 되어있다. 거기에 ROWNUM(RN)으로 넘버링을 하였다.
--			여기까지는 A테이블과 RN이 각각 따로이다 그렇기 때문에 RN을 참조할 수 가 없다.(?)
-- 			그렇기 때문에  추가 인라인뷰 X를 사용하여 A테이블과 RN을 하나의 가상테이블로 합친것이다.
--			결과적으로 A테이블과 RN은 X테이블이라는 하나의 테이블이 되고 마지막 WHERE문에서 사용된 것처럼
--			X.RN이라는 이름으로 참조되어 5 ~ 10 범위의 데이터를 가져온다 



-- 4-2) [SELECT의 ROWNUM]과 [WHERE의 ROWNUM]을 사용한 효율적인 TOP N 쿼리 최종 코드
SELECT X.RN, X.BOARD_NO, X.BOARD_NAME, X.BOARD_TITLE, X.BOARD_REGDATE, X.BOARD_LIKE
FROM (
	SELECT ROWNUM AS RN, A.BOARD_NO, A.BOARD_NAME, A.BOARD_TITLE, A.BOARD_REGDATE, A.BOARD_LIKE
	FROM (
		SELECT BOARD_NO, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE
		FROM FREE_BOARD
		ORDER BY BOARD_LIKE DESC) A
	WHERE ROWNUM <= 10	
	)X	
WHERE X.RN >= 5

-- 결과 : 위의 4-2)와 같은 정상적으로 TOP N 쿼리를 내보낸다. 
-- 왜 효율적일까?
--		4-1)와 4-2)의 쿼리는 데이터가 적다면 차이가 없다. 하지만 데이터가 많아지면 많아질수록 큰 성능 차이를 보여준다. 
-- 		먼저 두 쿼리는 공통적으로 먼저 BOARD_LIKE를 기준으로 정렬을 수행한다. 그 후 넘버링을 하고 필요한 범위를 가져온다
--		여기서 생각해 볼것이 있다. 로우가 10만개 이상이라고 가정할 때 사용자가 요청하는 데이터는 정렬된 상위데이터 중 5 ~ 10 뿐이다.
--		즉, 상위 10개의 데이터를 가져오자고 10만개의 데이터를 전부 정렬하고 번호를 일일이 붙이는 것은 너무 비효율적일 것이다   	

--		그렇기 때문에 오라클DB에서는 이러한 Top-N 프로세싱을 위해 WHERE문에서 사용하는 ROWNUM을 만들었다.
-- 		사용자가 인라인뷰에 정렬을 한 테이블을 적용한 후 'WHERE ROWNUM < N'이란 조건문을 사용한 쿼리를 요청하면 
--		DB에서는 내부적으로 모든 정렬을 수행하지 않고 사용자가 요청한 N개의 데이터까지 정렬을 수행 후 순서대로 내보낸다.
--		즉, ROWNUM을 사용하고 안하고의 차이는 '모든 데이터의 정렬을 수행하는가?', '요청한 필요한 데이터만 정렬을 수행하는가?'라는 아주아주 큰 성능차이를 발생 시킨다.

--	   	'WHERE ROWNUM < N' 주의점
--		1. 상위 N개의 데이터를 요청하기 위해 사용되기 때문에 범위를 지정하여 쿼리를 요청할 수 없다(값이 안나옴)
-- 		2. WHERE문 조건절은 ORDER BY보다 빨리 수행되기 때문에 무조건! 인라인뷰로 정렬을 먼저수행한 가상테이블의 조건절에 사용한다.  
--		EX)
--			SELECT * 
--			FROM
--				(SELECT * FROM 테이블명	-- 주의점 2.
--				ORDER BY 컬럼) 
--			WHERE ROWNUM <= N;			-- 주의점 1.

-- 참고 출처: https://5dol.tistory.com/127 [5dol Story]






-- 2. WHERE문 조건절은 ORDER BY보다 빨리 수행되기 때문에 무조건! 인라인뷰로 정렬을 먼저수행한 가상테이블의 조건절에 사용한다.
 
-- 틀린 사용	>> WHERE ROWNUM <= 20이 먼저 실행 되고 그다음에 정렬이 됨  
--			내가 원한것은 정렬이 된후 상위 20개를 가져오는것 >> 실행된것은 랜덤하게 20개를 가져와서 정렬한것
SELECT BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE 
FROM FREE_BOARD
WHERE ROWNUM <= 20
ORDER BY BOARD_LIKE DESC

-- 틀린 사용 증명
SELECT BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE
FROM FREE_BOARD
WHERE BOARD_LIKE <= 93
AND BOARD_LIKE >= 87
ORDER BY BOARD_LIKE DESC;

-- 정상 사용
SELECT A.*
FROM
	(SELECT BOARD_NO, ID, BOARD_NAME, BOARD_TITLE, BOARD_REGDATE, BOARD_LIKE 
	FROM FREE_BOARD
	ORDER BY BOARD_LIKE DESC) A
WHERE ROWNUM <= 20

-- 페이징 끝 =============================================================================================================================================
