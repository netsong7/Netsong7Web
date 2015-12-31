package com.netsong7.board.multiboard.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netsong7.board.dto.MasterBoardDto;
import com.netsong7.board.multiboard.spring.service.Service;

@Controller
public class CreateBoardController {
	@Autowired
	private Service service;
	
	@RequestMapping("/createBoard.spr")
	public String pageHandler(HttpServletRequest req){
		req.setAttribute("tableList", service.getTables());
		return "createBoard";
	}
	
	@RequestMapping(value="/createBoard.spr", method=RequestMethod.POST)
	public String pageSubmit(@ModelAttribute MasterBoardDto masterBoardDto, HttpServletResponse resp) throws IOException{	
		// 중복된 테이블명이 있다면
		if(service.getDuplicatedTableName(masterBoardDto.getBoard_tab_name())){
			// AJAX로 값을 돌려주기 위해 true.jsp를 만들어 이동하면 true문자열을 리턴하게 하였다.
			return "true";
		}
		else{
			service.createBoard(masterBoardDto);
			return "createBoard";
		}
	}
}
