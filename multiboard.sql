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
