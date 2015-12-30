package com.netsong7.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netsong7.board.multiboard.spring.service.ServiceImpl;

public class AppFrontController extends HttpServlet {
	private ServiceImpl service;
	
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
		String reqUri = req.getRequestURI(); 
		String ctPath = req.getContextPath(); 
		String cmd = reqUri.substring(ctPath.length()); 

		String nextPage = null;
		
		service = new ServiceImpl();
		
		if(cmd.equals("/main.app")){
			nextPage = "/WEB-INF/views/index.jsp";
		}
		else if(cmd.equals("/board.app")){
			req.setAttribute("tableList", service.getTables());
			nextPage = "/WEB-INF/views/board/createBoard.jsp";
		}
		else if(cmd.equals("/login.app")){
			nextPage = "/login.jsp";
		}
		else if(cmd.equals("/shop.app")){
			nextPage = "/list.shop";
		}
		else if(cmd.equals("/chatting.app")){
			nextPage = "/WEB-INF/views/chat/chat.xhtml";
		}
		
		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, res);
	}
}