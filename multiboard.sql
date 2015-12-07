use netsong7



CREATE TABLE tblBoardBasic
(
	wr_num               INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	wr_title             VARCHAR(50) NULL,
	wr_writer            VARCHAR(30) NULL,
	wr_contents          TEXT NULL,
	wr_email             VARCHAR(50) NULL,
	wr_home              VARCHAR(100) NULL,
	wr_counter           MEDIUMINT NULL,
	wr_ip                VARCHAR(30) NULL,
	wr_pass              VARCHAR(20) NULL,
	wr_date              DATETIME NULL,
	board_num            INTEGER NOT NULL
);


CREATE TABLE tblBoardComment
(
	co_num               INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	wr_num               INTEGER NOT NULL,
	co_comment           TEXT NULL,
	co_name              VARCHAR(30) NULL,
	co_date              DATETIME NULL
);



CREATE TABLE tblBoardMaster
(
	board_num            INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	board_disp_name      VARCHAR(30) NULL,
	board_tab_name       VARCHAR(30) NULL,
	board_create_date    DATETIME NULL
);

-- 1차 수정된 tblBoardMaster
CREATE TABLE tblBoardMaster
(
	board_num            INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	board_disp_name      VARCHAR(30) NULL,
	board_tab_name       VARCHAR(30) NULL,
	board_create_date    DATETIME NULL,
	board_reply			 char(1),
	board_comment		 char(1),
	board_upload		 char(1)
);


CREATE TABLE tblBoardReply
(
	re_num               INTEGER NOT NULL  PRIMARY KEY AUTO_INCREMENT,
	wr_pos               TINYINT NULL,
	wr_depth             TINYINT NULL,
	wr_group             TINYINT NULL,
	wr_num               INTEGER NOT NULL
);



CREATE TABLE tblBoardUpload
(
	up_num               INTEGER NOT NULL  PRIMARY KEY AUTO_INCREMENT,
	wr_num               INTEGER NOT NULL,
	wr_file              VARCHAR(70) NULL
);



ALTER TABLE tblBoardBasic
ADD FOREIGN KEY R_3 (board_num) REFERENCES tblBoardMaster (board_num);

ALTER TABLE tblBoardComment
ADD FOREIGN KEY R_6 (wr_num) REFERENCES tblBoardBasic (wr_num);

ALTER TABLE tblBoardReply
ADD FOREIGN KEY R_4 (wr_num) REFERENCES tblBoardBasic (wr_num);

ALTER TABLE tblBoardUpload
ADD FOREIGN KEY R_5 (wr_num) REFERENCES tblBoardBasic (wr_num);

/*
 * 회원 테이블 추가 2015.12.5
 * ----------------------
 * mem_birth는 yyyymmdd
 * mem_hobby는 나중에 취미별로 커뮤니티나 기타 서비스를 제공하기 위해 준비(코드값으로 입력받음)
 */
CREATE TABLE tblMember(
	mem_num			INTEGER		NOT	NULL	PRIMARY KEY AUTO_INCREMENT,
	mem_id			VARCHAR(20)	NOT NULL,
	mem_name		VARCHAR(20)	NOT NULL,
	mem_pass		VARCHAR(20)	NOT NULL,
	mem_nick		VARCHAR(20) NULL,
	mem_birth		CHAR(8)		NOT	NULL,	
	mem_sex			CHAR(1)		NOT	NULL,
	mem_email		VARCHAR(30)	NOT	NULL,
	mem_addr		VARCHAR(50)	NOT	NULL,
	mem_company		VARCHAR(50)	NULL,
	mem_phone		VARCHAR(30)	NOT	NULL,
	mem_hobby		CHAR(2),
	mem_intro		VARCHAR(200)	NOT	NULL,
	mem_pic			VARCHAR(50)	NOT	NULL,
	mem_regdate		TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
