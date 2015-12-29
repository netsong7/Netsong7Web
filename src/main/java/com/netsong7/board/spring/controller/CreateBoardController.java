package com.netsong7.board.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netsong7.board.multiboard.service.Service;
import com.netsong7.board.multiboard.service.ServiceImpl;

@Controller
@RequestMapping("/board.spr")
public class CreateBoardController {
	@RequestMapping(params={"cmd=CREATE_BOARD"})
	public String pageHandler(){
		return "createBoard";
	}
	
	@RequestMapping(params={"cmd=CREATE_BOARD"}, method=RequestMethod.POST)
	public String pageSubmit(@RequestParam("boardName") String name, @RequestParam("boardTitle") String title, @RequestParam("chkOption") String[] opt, HttpServletResponse resp) throws IOException{
		String nextPage = null;
		Service service = new ServiceImpl();
		int board_num = 0;
		
		// 중복된 테이블명이 있다면
		if(service.getDuplicatedTableName(name)){
			resp.getWriter().println("true");
		}
		else{
			service.createBoard(name, title, opt);
		}
		
		return "createBoard";
	}
}
