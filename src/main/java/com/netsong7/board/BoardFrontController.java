package com.netsong7.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netsong7.board.multiboard.service.Service;
import com.netsong7.board.multiboard.service.ServiceImpl;

public class BoardFrontController extends HttpServlet {
	private Service service;
	
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
		service = new ServiceImpl();
		
		if(cmd.equals("MVC_MULTI")){
			nextPage = "/WEB-INF/views/board/createBoard.jsp";
		}
		else if(cmd.equals("SPRING_MULTI")){
			System.out.println("create_board");
		}
		
		req.setAttribute("tableList", service.getTables());
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, res);
	}
}
