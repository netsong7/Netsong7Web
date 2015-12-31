package com.netsong7.board.multiboard.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netsong7.board.multiboard.spring.service.Service;

@Component
@Controller
public class BoardListController {
	@Autowired
	private Service service;
	
	@RequestMapping(value="/listBoard.spr")
	public String pageHandler(@RequestParam("board_num") int board_num, HttpServletRequest req){
		req.setAttribute("boardList", service.getBoardList(board_num));
		req.setAttribute("board_num", board_num);
		return "boardList";
	}
}
