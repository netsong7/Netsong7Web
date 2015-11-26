package com.netsong7.board.multiboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netsong7.board.multiboard.service.Service;
import com.netsong7.board.multiboard.service.ServiceImpl;

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
		
		if("CREATE_BOARD".equals(cmd)){
			String boardName = req.getParameter("boardName");
			String boardTitle = req.getParameter("boardTitle");
			String[] chkOption = req.getParameterValues("chkOption");
			
			service.createBoard(boardName, boardTitle, chkOption);
			nextPage = "/WEB-INF/views/board/createBoard.jsp";
		}
		else if("LIST_BOARD".equals(cmd)){
			String boardNum = req.getParameter("boardNum");
			nextPage = "/WEB-INF/views/board/list.jsp";
		}
		
		req.setAttribute("tableList", service.getTables());
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}
}
