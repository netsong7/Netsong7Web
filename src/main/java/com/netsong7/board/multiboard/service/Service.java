package com.netsong7.board.multiboard.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netsong7.board.multiboard.dto.MasterBoardDto;

public interface Service {
	public void createBoard(String boardName, String boardTitle, String[] chkOption);  
	public List getTables();
	public List getBoardList(int boardNum);
	public MasterBoardDto getMasterTable(int boardNum);
}