package com.netsong7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO_Singleton {
	private static DAO_Singleton dao = new DAO_Singleton(); 
	
	private DAO_Singleton(){}
	
	public static DAO_Singleton newInstance(){
		return dao;
	}
	
	public static Connection getConnection(){
		try{
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/netsong7");
			return ds.getConnection();
		}
		catch(Exception err){
			System.out.println("SawonDao() : " + err);
		}
		
		return null;
	}
	
	public static void freeCon(Connection con){
		if(con!=null){ try{ con.close(); }catch(Exception err){} }
	}
	
	public static void freeCon(Connection con, PreparedStatement pstmt){
		if(pstmt!=null){ try{ pstmt.close(); }catch(Exception err){} }
		if(con!=null){ try{ con.close(); }catch(Exception err){} }
	}
	
	public static void freeCon(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(rs!=null){ try{ rs.close(); }catch(Exception err){} }
		if(pstmt!=null){ try{ pstmt.close(); }catch(Exception err){} }
		if(con!=null){ try{ con.close(); }catch(Exception err){} }
	}
}
