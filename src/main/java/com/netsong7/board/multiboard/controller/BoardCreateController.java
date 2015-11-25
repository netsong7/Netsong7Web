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

public class BoardCreateController extends HttpServlet {
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
		// TODO Auto-generated method stub
		String cmd = req.getParameter("cmd");
		String nextPage = null;
		Service service = null;
		
		if("CREATE_BOARD".equals(cmd)){
			String boardName = req.getParameter("boardName");
			String boardTitle = req.getParameter("boardTitle");
			String[] chkOption = req.getParameterValues("chkOption");
			
			service = new ServiceImpl();
			nextPage = (String)service.createBoard(boardName, boardTitle, chkOption);
			req.setAttribute("tableList", service.getTables());
		}
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}
}
