package com.netsong7.board.multiboard.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netsong7.board.multiboard.dto.BasicBoardDto;
import com.netsong7.board.multiboard.dto.MasterBoardDto;

public interface Service {
	public void createBoard(String boardName, String boardTitle, String[] chkOption);  
	public List getTables();
	public List getBoardList(int board_num);
	public MasterBoardDto getMasterTable(int boardNum);
	public void writeBoard(BasicBoardDto basicDto);
	public BasicBoardDto getBoard(int wr_num, String board_upload);
	public boolean getDuplicatedTableName(String tableName);
	public void removeBoard(int board_num);
}
