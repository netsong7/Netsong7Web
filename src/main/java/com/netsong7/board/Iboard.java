package com.netsong7.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @author netsong7
 *  @since 2015.11.25
 *	 
 */

public interface Iboard {
	/**
	 * 
	 * @param req
	 * @param resp
	 * @return Object
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException; 
}
