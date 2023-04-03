-- 시퀀스
DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ NOCACHE;

-- 테이블
DROP TABLE BOARD;
CREATE TABLE BOARD (
	BOARD_NO 		NUMBER NOT NULL,
	TITLE 			VARCHAR2(1000 BYTE) NOT NULL,
	CONTENT 		CLOB, -- CLOB : VARCHAR2보다 위, VARCHAR2 : 4000 BYTE
	MODIFIED_DATE 	DATE,
	CREATED_DATE 	DATE NOT NULL,
	
	CONSTRAINT PK_BOARD PRIMARY KEY(BOARD_NO)

);

-- 행(ROW)
INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, '[공지]월요일알림', 'DBCP는 DataBase Connection Poll을 의미한다', NULL, SYSDATE);
INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, '[협조]내일준비물', '물감, 리코더, 공책 가져오기', NULL, SYSDATE);
-- 디벨로퍼를 이용하면 위까지 끝인데, 이클립스에서 작업하면 커밋 후 ;(세미콜론) 하지 않는다. 더 나아가면 커밋을 안적어도 된다. 왜냐 이클립스가 오토커밋이라서 알아서함.
-- 디벨로퍼에서 할때 꼭 커밋!!!!!!!!!!