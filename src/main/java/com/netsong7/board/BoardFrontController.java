package com.netsong7.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(req, resp);
	}
	
	protected void doService(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		String nextPage = null;

		if(cmd.equals("MVC_MULTI")){
			nextPage = "/WEB-INF/views/board/createBoard.jsp";
		}
		else if(cmd.equals("SPRING_MULTI")){
			System.out.println("create_board");
		}
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, res);
	}
}
