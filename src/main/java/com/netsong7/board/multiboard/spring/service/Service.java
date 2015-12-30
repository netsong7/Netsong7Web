package com.netsong7.board.multiboard.spring.service;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.netsong7.board.dto.BasicBoardDto;
import com.netsong7.board.dto.CommentBoardDto;
import com.netsong7.board.dto.MasterBoardDto;

public interface Service {
	public void createBoard(@ModelAttribute MasterBoardDto masterBoardDto);  
	public List getTables();
	public List getBoardList(int board_num);
	public MasterBoardDto getMasterTable(int boardNum);
	public void writeBoard(BasicBoardDto basicDto);
	public BasicBoardDto getBoard(int wr_num, String board_upload);
	public boolean getDuplicatedTableName(String tableName);
	public void removeBoard(int board_num);
	public void commentBoard(CommentBoardDto commentBoardDto);
	public List getCommentList(int wr_num);
}
