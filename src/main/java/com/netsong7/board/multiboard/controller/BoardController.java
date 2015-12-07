package com.netsong7.board.multiboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netsong7.board.multiboard.dto.BasicBoardDto;
import com.netsong7.board.multiboard.dto.MasterBoardDto;
import com.netsong7.board.multiboard.service.Service;
import com.netsong7.board.multiboard.service.ServiceImpl;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 *  @author netsong7
 *  @since 2015.11.25
 *	
 */

public class BoardController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	/**
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String cmd = req.getParameter("cmd");
		
		String nextPage = null;
		Service service = new ServiceImpl();
		int board_num = 0;

		if("CREATE_BOARD".equals(cmd)){
			String boardName = req.getParameter("boardName");
			String boardTitle = req.getParameter("boardTitle");
			String[] chkOption = req.getParameterValues("chkOption");
			
			// 중복된 테이블명이 있다면
			if(service.getDuplicatedTableName(boardName)){
				resp.getWriter().println("true");
				return;
				//nextPage = "/WEB-INF/views/board/createBoard.jsp";
			}
			else{
				service.createBoard(boardName, boardTitle, chkOption);
				nextPage = "/WEB-INF/views/board/createBoard.jsp";
			}
		}
		else if("LIST_BOARD".equals(cmd)){
			board_num = Integer.parseInt(req.getParameter("board_num"));
			nextPage = "/WEB-INF/views/board/list.jsp";
			req.setAttribute("board_num", board_num);
		}
		else if("WRITE_BOARD".equals(cmd)){ // move to write.jsp
			board_num = Integer.parseInt(req.getParameter("board_num"));
			MasterBoardDto dto = service.getMasterTable(board_num);
			req.setAttribute("master", dto);
			nextPage = "/WEB-INF/views/board/write.jsp";
		}
		else if("WRITE_PROC_BOARD".equals(cmd)){
			board_num = Integer.parseInt(req.getParameter("board_num"));
			String wr_title = req.getParameter("wr_title");
			String wr_writer = req.getParameter("wr_writer");
			String wr_email = req.getParameter("wr_email");
			String wr_home = req.getParameter("wr_home");
			String wr_pass = req.getParameter("wr_pass");
			String wr_contents = req.getParameter("wr_contents");
			
			BasicBoardDto basicDto = new BasicBoardDto();
			basicDto.setBoard_num(board_num);
			basicDto.setWr_writer(wr_writer);
			basicDto.setWr_title(wr_title);
			basicDto.setWr_email(wr_email);
			basicDto.setWr_home(wr_home);
			basicDto.setWr_pass(wr_pass);
			basicDto.setWr_contents(wr_contents);
			basicDto.setWr_ip(req.getRemoteAddr());
			service.writeBoard(basicDto);
						
			req.setAttribute("board_num", board_num);
			nextPage = "/WEB-INF/views/board/list.jsp";
		}
		else if("WRITE_PROC_UPLOAD_BOARD".equals(cmd)){
			String saveDir = getServletContext().getRealPath("upload");
			MultipartRequest multiReq = new MultipartRequest(req, saveDir, 50*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			
			board_num = Integer.parseInt(multiReq.getParameter("board_num"));

			String wr_title = multiReq.getParameter("wr_title");
			String wr_writer = multiReq.getParameter("wr_writer");
			String wr_email = multiReq.getParameter("wr_email");
			String wr_home = multiReq.getParameter("wr_home");
			String wr_pass = multiReq.getParameter("wr_pass");
			String wr_file = null;
			Enumeration enumer = multiReq.getFileNames();
			while(enumer.hasMoreElements()){
				String name = (String)enumer.nextElement();
				wr_file = multiReq.getFilesystemName(name);
			}
			String wr_contents = multiReq.getParameter("wr_contents");
			
			BasicBoardDto basicDto = new BasicBoardDto();
			basicDto.setBoard_num(board_num);
			basicDto.setWr_writer(wr_writer);
			basicDto.setWr_title(wr_title);
			basicDto.setWr_email(wr_email);
			basicDto.setWr_home(wr_home);
			basicDto.setWr_pass(wr_pass);
			basicDto.setWr_contents(wr_contents);
			basicDto.setWr_file(wr_file);
			basicDto.setWr_ip(req.getRemoteAddr());
			service.writeBoard(basicDto);
			
			req.setAttribute("board_num", board_num);
			nextPage = "/WEB-INF/views/board/list.jsp";
		}
		else if("READ_BOARD".equals(cmd)){
			int wr_num = Integer.parseInt(req.getParameter("wr_num"));
			board_num = Integer.parseInt(req.getParameter("board_num"));
			
			MasterBoardDto dto = service.getMasterTable(board_num);
			BasicBoardDto basicDto = service.getBoard(wr_num, dto.getBoard_upload());
			
			req.setAttribute("master", dto);
			req.setAttribute("board", basicDto);
			nextPage = "/WEB-INF/views/board/read.jsp";
		}
		else if("DOWNLOAD".equals(cmd)){
			String path = req.getParameter("path");
			String name = req.getParameter("name");
			
			nextPage = "/WEB-INF/util/download.jsp?path=" + path + "&name=" + name;
		}
		else if("DELETE_BOARD".equals(cmd)){
			board_num = Integer.parseInt(req.getParameter("board_num"));
			service.removeBoard(board_num);
			nextPage = "/WEB-INF/views/board/createBoard.jsp";
		}
		
		req.setAttribute("tableList", service.getTables());
		req.setAttribute("boardList", service.getBoardList(board_num));
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}
}
