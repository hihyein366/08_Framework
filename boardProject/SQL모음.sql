/* 계정 생성 (관리자 계정으로 접속) */
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

CREATE USER SPRING_JHI IDENTIFIED BY SPRING1234;

GRANT CONNECT, RESOURCE TO SPRING_JHI;

ALTER USER SPRING_JHI DEFAULT TABLESPACE USERS QUOTA 20M ON USERS;

--> 계정 생성 후 접속방법(새 데이터데이스) 추가

-------------------------------------------------------------

/* SPRING 계정 접속 */

-- "" : 내부에 작성된 글(모양) 그대로 인식 -> 대소문자 구분
--> "" 작성 권장

-- CHAR(10)		 : 고정 길이 문자열 10바이트 (최대 2000바이트)
-- VARCHAR(10) : 가변 길이 문자열 10바이트 (최대 4000바이트)
-- NVARCHAR2(10) : 가변 길이 문자열 10글자 (유니코드, 최대 4000바이트)
-- CLOB : 가변 길이 문자열 (최대 4GB)

/* MEMBER 테이블 생성 */
CREATE TABLE "MEMBER" (
	"MEMBER_NO" 			NUMBER CONSTRAINT "MEMBER_PK" PRIMARY KEY,
	"MEMBER_EMAIL" 		NVARCHAR2(50) NOT NULL,
	"MEMBER_PW" 			NVARCHAR2(100) NOT NULL,
	"MEMBER_NICKNAME" NVARCHAR2(10) NOT NULL,
	"MEMBER_TEL" 			CHAR(11) NOT NULL,
	"MEMBER_ADDRESS" 	NVARCHAR2(150),
	"PROFILE_IMG"			VARCHAR2(300),
	"ENROLL_DATE"			DATE DEFAULT SYSDATE NOT NULL,
	"MEMBER_DEL_FL"		CHAR(1) DEFAULT 'N' CHECK("MEMBER_DEL_FL" IN ('Y', 'N')),
	"AUTHORITY"				NUMBER DEFAULT 1 CHECK("AUTHORITY" IN (1, 2))
);


-- 회원 번호 시퀀스 만들기
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE;

-- 샘플 회원 데이터 삽입
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL,
			 'member01@kh.or.kr',
			 '$2a$10$zlLWoMIOyjbMtsYCEe5Fr.s1uPNGN7tNEp/QUEenS2Hd51hFR0ydC',
			 '샘플1',
			 '01012341234',
			 NULL,
			 NULL,
			 DEFAULT,
			 DEFAULT,
			 DEFAULT
);

COMMIT;

SELECT * FROM "MEMBER";

DELETE FROM "MEMBER" m WHERE MEMBER_EMAIL = 'hi@hi.com';
-- 로그인
-- -> BCrypt 암호와 사용 중 DB에서 비번 비교 불가능 그래서 MEMBER_PW를 조회
-- --> 이메일이 일치하는 회원 + 탈퇴 안한 회원 조건만 검색
SELECT MEMBER_NO, MEMBER_EMAIL, MEMBER_NICKNAME, MEMBER_PW, MEMBER_TEL,
			 MEMBER_ADDRESS, PROFILE_IMG, AUTHORITY, 
			 TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일" HH24"시" MI"분" SS"초"' )
FROM "MEMBER"
WHERE MEMBER_EMAIL = ?
AND MEMBER_DEL_FL = 'N'

-- 이메일 중복 검사 (탈퇴 안한 회원 중 같은 이메일 있는지 조회)
SELECT COUNT(*)
FROM "MEMBER" 
WHERE MEMBER_DEL_FL = 'N'
AND MEMBER_EMAIL = 'member01*kh.or.kr';

DELETE FROM "MEMBER" WHERE MEMBER_EMAIL = 'qqq366@naver.com';


/* 이메일, 인증키 저장 테이블 생성 */
CREATE TABLE "TB_AUTH_KEY"(
	"KEY_NO" 			NUMBER PRIMARY KEY,
	"EMAIL" 			NVARCHAR2(50) NOT NULL,
	"AUTH_KEY" 		CHAR(6) NOT NULL,
	"CREATE_TIME" DATE DEFAULT SYSDATE NOT NULL
);

COMMENT ON COLUMN "TB_AUTH_KEY"."KEY_NO" IS '인증키 구분 번호(시퀀스)';
COMMENT ON COLUMN "TB_AUTH_KEY"."EMAIL" IS '인증 이메일';
COMMENT ON COLUMN "TB_AUTH_KEY"."AUTH_KEY" IS '인증 번호';
COMMENT ON COLUMN "TB_AUTH_KEY"."CREATE_TIME" IS '인증 번호 생성 시간';

CREATE SEQUENCE SEQ_KEY_NO NOCACHE;

SELECT * FROM "TB_AUTH_KEY";

SELECT COUNT(*) FROM "TB_AUTH_KEY"
WHERE EMAIL = #{가입하려는 이메일 입력값}
AND AUTH_KEY = #{위 이메일로 보낸 인증번호}
;

UPDATE "MEMBER" SET 
MEMBER_ADDRESS = '123^^^가오길^^^465호'
WHERE MEMBER_NO = 8;

UPDATE MEMBER SET 
MEMBER_DEL_FL = 'N'
WHERE MEMBER_NO = '18';

COMMIT;

------------------------------------------------------

-- 파일 업로드 테스트용 테이블
CREATE TABLE "UPLOAD_FILE" (
	FILE_NO NUMBER PRIMARY KEY,
	FILE_PATH VARCHAR2(500) NOT NULL,
	FILE_ORIGINAL_NAME VARCHAR2(300) NOT NULL,
	FILE_RENAME VARCHAR2(100) NOT NULL,
	FILE_UPLOAD_DATE DATE DEFAULT SYSDATE,
	MEMBER_NO NUMBER REFERENCES "MEMBER"
);


COMMENT ON COLUMN "UPLOAD_FILE".FILE_NO IS '파일 번호(PK)';
COMMENT ON COLUMN "UPLOAD_FILE".FILE_PATH IS '클라이언트 요청 경로';
COMMENT ON COLUMN "UPLOAD_FILE".FILE_ORIGINAL_NAME IS '파일 원본명';
COMMENT ON COLUMN "UPLOAD_FILE".FILE_RENAME IS '변경된 파일명';
COMMENT ON COLUMN "UPLOAD_FILE".FILE_UPLOAD_DATE IS '업로드 날짜';
COMMENT ON COLUMN "UPLOAD_FILE".MEMBER_NO 
IS 'MEMBER 테이블의 PK(MEMBER_NO) 참조';

CREATE SEQUENCE SEQ_FILE_NO NOCACHE;

SELECT * FROM "UPLOAD_FILE";

-- 파일 목록 조회
SELECT FILE_NO, FILE_PATH, FILE_ORIGINAL_NAME, FILE_RENAME,
	TO_CHAR(FILE_UPLOAD_DATE, 'YYYY-MM-DD') FILE_UPLOAD_DATE,
	MEMBER_NICKNAME
FROM UPLOAD_FILE
JOIN MEMBER USING(MEMBER_NO)
ORDER BY FILE_NO DESC; 


----------------------------------------------------------------------------------------------------------------------------------


COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원번호(PK)';

COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원 이메일(ID 역할)';

COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원 비밀번호(암호화)';

COMMENT ON COLUMN "MEMBER"."MEMBER_NICKNAME" IS '회원 닉네임';

COMMENT ON COLUMN "MEMBER"."MEMBER_TEL" IS '회원 전화번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_ADDRESS" IS '회원 주소';

COMMENT ON COLUMN "MEMBER"."PROFILE_IMG" IS '프로필 이미지';

COMMENT ON COLUMN "MEMBER"."ENROLL_DATE" IS '회원가입일';

COMMENT ON COLUMN "MEMBER"."MEMBER_DEL_FL" IS '탈퇴 여부(Y/N)';

COMMENT ON COLUMN "MEMBER"."AUTHORITY" IS '권한(1:일반, 2:관리자)';


/* 게시판 테이블 생성 */
CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"BOARD_TITLE"	NVARCHAR2(100)		NOT NULL,
	"BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"BOARD_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"BOARD_UPDATE_DATE"	DATE		NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"BOARD_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"BOARD_CODE"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글 번호(PK)';

COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';

COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';

COMMENT ON COLUMN "BOARD"."BOARD_WRITE_DATE" IS '게시글 작성일';

COMMENT ON COLUMN "BOARD"."BOARD_UPDATE_DATE" IS '게시글 마지막 수정일';

COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';

COMMENT ON COLUMN "BOARD"."BOARD_DEL_FL" IS '게시글 삭제 여부(Y, N)';

COMMENT ON COLUMN "BOARD"."BOARD_CODE" IS '게시판 종류 코드 번호';

COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '작성한 회원 번호(FK)';

/* 게시판 종류 생성 */
CREATE TABLE "BOARD_TYPE" (
	"BOARD_CODE"	NUMBER		NOT NULL,
	"BOARD_NAME"	NVARCHAR2(20)		NOT NULL
);

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_CODE" IS '게시판 종류 코드 번호';

COMMENT ON COLUMN "BOARD_TYPE"."BOARD_NAME" IS '게시판명';

CREATE TABLE "BOARD_LIKE" (
	"MEMBER_NO"	NUMBER		NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD_LIKE"."MEMBER_NO" IS '회원번호';

COMMENT ON COLUMN "BOARD_LIKE"."BOARD_NO" IS '게시글 번호(PK)';

CREATE TABLE "BOARD_IMG" (
	"IMG_NUMBER"	NUMBER		NOT NULL,
	"IMG_PATH"	VARCHAR2(200)		NOT NULL,
	"IMG_ORIGINAL_NAME"	NVARCHAR2(50)		NOT NULL,
	"IMG_RENAME"	NVARCHAR2(50)		NOT NULL,
	"IMG_ORDER"	NUMBER		NULL,
	"BOARD_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "BOARD_IMG"."IMG_NUMBER" IS '이미지 번호(PK)';

COMMENT ON COLUMN "BOARD_IMG"."IMG_PATH" IS '이미지 요청 경로';

COMMENT ON COLUMN "BOARD_IMG"."IMG_ORIGINAL_NAME" IS '이미지 원본명';

COMMENT ON COLUMN "BOARD_IMG"."IMG_RENAME" IS '이미지 변경명';

COMMENT ON COLUMN "BOARD_IMG"."IMG_ORDER" IS '이미지 순서';

COMMENT ON COLUMN "BOARD_IMG"."BOARD_NO" IS '게시글 번호(PK)';

CREATE TABLE "COMMENT" (
	"COMMENT_NO"	NUMBER		NOT NULL,
	"COMMENT_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"COMMENT_WRITE_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"COMMENT_DEL_FL"	CHAR(1)	DEFAULT 'N'	NOT NULL,
	"BOARD_NO"	NUMBER		NOT NULL,
	"MEMBER_NO"	NUMBER		NOT NULL,
	"PARENT_COMMENT_NO"	NUMBER		NOT NULL
);

COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호(PK)';

COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';

COMMENT ON COLUMN "COMMENT"."COMMENT_WRITE_DATE" IS '댓글 작성일';

COMMENT ON COLUMN "COMMENT"."COMMENT_DEL_FL" IS '댓글 삭제 여부(Y/N)';

COMMENT ON COLUMN "COMMENT"."BOARD_NO" IS '게시글 번호(PK)';

COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '회원번호(PK)';

COMMENT ON COLUMN "COMMENT"."PARENT_COMMENT_NO" IS '부모 댓글 번호';


----------------- PK -----------------

-- 1
ALTER TABLE "BOARD" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY (
	"BOARD_NO"
);

-- 2
ALTER TABLE "BOARD_TYPE" ADD CONSTRAINT "PK_BOARD_TYPE" PRIMARY KEY (
	"BOARD_CODE"
);

-- 3
ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "PK_BOARD_LIKE" PRIMARY KEY (
	"MEMBER_NO",
	"BOARD_NO"
);

-- 4
ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "PK_BOARD_IMG" PRIMARY KEY (
	"IMG_NUMBER"
);

-- 5
ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY (
	"COMMENT_NO"
);

----------------- FK -----------------

ALTER TABLE "BOARD" ADD CONSTRAINT "FK_BOARD_TYPE_TO_BOARD_1" FOREIGN KEY (
	"BOARD_CODE"
)
REFERENCES "BOARD_TYPE" (
	"BOARD_CODE"
);


ALTER TABLE "BOARD" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);


ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_MEMBER_TO_BOARD_LIKE_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);


ALTER TABLE "BOARD_LIKE" ADD CONSTRAINT "FK_BOARD_TO_BOARD_LIKE_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
);


ALTER TABLE "BOARD_IMG" ADD CONSTRAINT "FK_BOARD_TO_BOARD_IMG_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
);


ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_BOARD_TO_COMMENT_1" FOREIGN KEY (
	"BOARD_NO"
)
REFERENCES "BOARD" (
	"BOARD_NO"
);


ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_MEMBER_TO_COMMENT_1" FOREIGN KEY (
	"MEMBER_NO"
)
REFERENCES "MEMBER" (
	"MEMBER_NO"
);


ALTER TABLE "COMMENT" ADD CONSTRAINT "FK_COMMENT_TO_COMMENT_1" FOREIGN KEY (
	"PARENT_COMMENT_NO"
)
REFERENCES "COMMENT" (
	"COMMENT_NO"
);


----------------- CHECK -----------------

-- 게시글 삭제 여부
ALTER TABLE "BOARD" ADD
CONSTRAINT "BOARD_DEL_CHECK"
CHECK("BOARD_DEL_FL" IN ('Y', 'N'));

-- 댓글 삭제 여부 (COMMENT는 예약어라 쌍따옴표 꼭 써주세요)
ALTER TABLE "COMMENT" ADD
CONSTRAINT "COMMENT_DEL_CHECK"
CHECK("COMMENT_DEL_FL" IN ('Y', 'N'));


--------------------------------------------------------------------------------------------------------------
/* 책 관리 프로젝트 (연습용) */

CREATE TABLE "BOOK" (
	"BOOK_NO"	NUMBER		NOT NULL,
	"BOOK_TITLE"	NVARCHAR2(50)		NOT NULL,
	"BOOK_WRITER"	NVARCHAR2(20)		NOT NULL,
	"BOOK_PRICE"	NUMBER		NOT NULL,
	"REG_DATE"	DATE	DEFAULT SYSDATE	NOT NULL
);

COMMENT ON COLUMN "BOOK"."BOOK_NO" IS '책 번호';

COMMENT ON COLUMN "BOOK"."BOOK_TITLE" IS '책 제목';

COMMENT ON COLUMN "BOOK"."BOOK_WRITER" IS '글쓴이';

COMMENT ON COLUMN "BOOK"."BOOK_PRICE" IS '가격';

COMMENT ON COLUMN "BOOK"."REG_DATE" IS '등록일';

ALTER TABLE "BOOK" ADD CONSTRAINT "PK_BOOK" PRIMARY KEY (
	"BOOK_NO"
);

CREATE SEQUENCE SEQ_BOOK_NO NOCACHE;

INSERT INTO "BOOK"
VALUES(SEQ_MEMBER_NO.NEXTVAL,
			 '안내문자제목',
			 '산림청',
			 5000,
			 DEFAULT
);

INSERT INTO "BOOK"
VALUES(SEQ_MEMBER_NO.NEXTVAL,
			 '집에 가기 좋은 날씨',
			 '청계천',
			 8000,
			 DEFAULT
);

SELECT * FROM BOOK;

COMMIT;
--------------------------------------------------------------------------------------------------------------












