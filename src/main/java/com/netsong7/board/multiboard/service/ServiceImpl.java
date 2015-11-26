package com.netsong7.board.multiboard.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import com.netsong7.board.multiboard.dto.MasterBoardDto;
import com.netsong7.dao.DAO_Singleton;

public class ServiceImpl implements Service {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DAO_Singleton dao;
	
	public ServiceImpl(){
		try{
			dao =  DAO_Singleton.newInstance();
		}
		catch(Exception err){
			System.out.println("ServiceImpl() : " + err);
		}
		finally{
			dao.freeCon(con, pstmt, rs);
		}
	}
	
	public void createBoard(String boardName, String boardTitle, String[] chkOption) {
		String sql = ""; 

		try{
			con = dao.getConnection();
			sql = "insert into tblBoardMaster(board_disp_name, board_tab_name, board_create_date, board_reply, board_comment, board_upload) values(?, ?, now(), ?, ?, ?)";
			
			if(con != null){
				// 일단 개별적으로 테이블이 생성되는지에 대한 테스트
				//String sql = "create table " + boardName + " (id int)";
				
				String reply="n", comment="n", upload="n";
				try{
					if(chkOption != null){
						for(int i=0; i<chkOption.length; i++){
							if("reply".equals(chkOption[i])){
								reply="y";
							}
							else if("comment".equals(chkOption[i])){
								comment="y";
							}
							else if("upload".equals(chkOption[i])){
								upload="y";
							}
						}
					}
				}
				catch(NullPointerException err){
					
				}
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, boardName);
				pstmt.setString(2, boardTitle);
				pstmt.setString(3, reply);
				pstmt.setString(4, comment);
				pstmt.setString(5, upload);
				
				pstmt.executeUpdate();
			}	
		}
		catch(Exception err){
			System.out.println("createBoard() : " + err);
		}
		finally{
			dao.freeCon(con, pstmt, rs);
		}
	}
	
	public List getTables(){
		Vector tableList = new Vector();
		
		try{
			con = dao.getConnection();
			
			if(con != null){
				// 실제 테이블 목록을 받아올 때
				//String sql = "select table_name from INFORMATION_SCHEMA.TABLES where  TABLE_SCHEMA = 'netsong7'";
				
				String sql = "select * from tblBoardMaster";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					MasterBoardDto dto = new MasterBoardDto();
					dto.setBoard_create_date(rs.getString("board_create_date"));
					dto.setBoard_disp_name(rs.getString("board_disp_name"));
					dto.setBoard_num(rs.getInt("board_num"));
					dto.setBoard_tab_name(rs.getString("board_tab_name"));
					dto.setBoard_reply(rs.getString("board_reply"));
					dto.setBoard_comment(rs.getString("board_comment"));
					dto.setBoard_upload(rs.getString("board_upload"));
					
					tableList.add(dto);
				}
			}	
		}
		catch(Exception err){
			System.out.println("createBoard() : " + err);
		}
		finally{
			dao.freeCon(con, pstmt, rs);
		}
		
		return tableList;
	}
	
	public MasterBoardDto getMasterTable(int boardNum){
		try{
			con = dao.getConnection();
			
			if(con != null){
				String sql = "select * from tblBoardMaster where board_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, boardNum);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					MasterBoardDto dto = new MasterBoardDto();
					dto.setBoard_create_date(rs.getString("board_create_date"));
					dto.setBoard_disp_name(rs.getString("board_disp_name"));
					dto.setBoard_num(rs.getInt("board_num"));
					dto.setBoard_tab_name(rs.getString("board_tab_name"));
					dto.setBoard_reply(rs.getString("board_reply"));
					dto.setBoard_comment(rs.getString("board_comment"));
					dto.setBoard_upload(rs.getString("board_upload"));
					
					return dto;
				}
			}	
		}
		catch(Exception err){
			System.out.println("getBoard() : " + err);
		}
		finally{
			dao.freeCon(con, pstmt, rs);
		}
		return null;
	}
	
	public List getBoardList(int boardNum){
		return null;
	}
}
