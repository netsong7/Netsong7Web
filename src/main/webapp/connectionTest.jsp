<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page import="javax.sql.*, javax.naming.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%
	DataSource cp = null;
	try{
		Context ctx = new InitialContext();
		cp = (DataSource)ctx.lookup("java:/comp/env/jdbc/netsong7");
		
		Connection con = cp.getConnection();
		
		if(con != null){
			out.println("연결 성공");
		}
	}
	catch(Exception err){
		out.println(err);
	}
%>
</body>
</html>