<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=EUC-KR"%>
<%
	String virPath = request.getParameter("path");
	String fileName = request.getParameter("name");
	
	response.setContentType("Application/octet-stream");
	response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	
	String realPath = getServletContext().getRealPath(virPath);
	File f = new File(realPath + "/" + new String(fileName.getBytes("8859_1"), "euc-kr"));
	byte[] data = new byte[1024];
	
	try{
		BufferedInputStream bis = 
			new BufferedInputStream(new FileInputStream(f));
		
		BufferedOutputStream bos =
			new BufferedOutputStream(response.getOutputStream());
		
		int read = bis.read(data);
		while(read != -1){
			bos.write(data, 0, read);
			read = bis.read(data);
		}
		bos.flush();
		bos.close();
		bis.close();
	}
	catch(Exception err){
		err.printStackTrace();
	}
%>







