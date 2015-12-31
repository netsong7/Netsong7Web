package com.netsong7.board.multiboard.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.netsong7.board.dto.BasicBoardDto;
import com.netsong7.board.dto.MasterBoardDto;
import com.netsong7.board.multiboard.spring.service.Service;

@Component
@Controller
public class BoardWriteController {
	@Autowired
	private Service service;
	
	@RequestMapping("/boardWrite.spr")
	public ModelAndView pageHandler(@RequestParam("board_num") int board_num){
		ModelAndView view = new ModelAndView("boardWrite");
		
		MasterBoardDto dto = service.getMasterTable(board_num);
		view.addObject("master", dto);

		return view;
	}
	
	@RequestMapping(value="/boardWrite.spr", params={"cmd=WRITE_PROC_BOARD"},method=RequestMethod.POST)
	public ModelAndView pageSubmit(@ModelAttribute BasicBoardDto basicBoardDto){
		ModelAndView view = new ModelAndView("redirect:listBoard.spr");
		view.addObject("board_num", basicBoardDto.getBoard_num());
		service.writeBoard(basicBoardDto);
		return view;
	}
	
	@RequestMapping(value="/boardWrite.spr", params={"cmd=WRITE_PROC_UPLOAD_BOARD"},method=RequestMethod.POST)
	public ModelAndView pageUploadSubmit(){
		ModelAndView view = new ModelAndView("boardList");
		//view.addObject("board_num", );
		return view;
	}
}
