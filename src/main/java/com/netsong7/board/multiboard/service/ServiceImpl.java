package com.netsong7.board.multiboard.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import com.netsong7.board.multiboard.dto.BasicBoardDto;
import com.netsong7.board.multiboard.dto.MasterBoardDto;
import com.netsong7.dao.DAO_Singleton;
import com.netsong7.dao.DBConnectionMgr;

public class ServiceImpl implements Service {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	//private DAO_Singleton dao;
	private DBConnectionMgr dao;
	
	public ServiceImpl(){
		try{
			//dao =  DAO_Singleton.newInstance();
			dao = DBConnectionMgr.getInstance();
		}
		catch(Exception err){
			System.out.println("ServiceImpl() : " + err);
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
			//dao.freeCon(con, pstmt);
			dao.freeConnection(con, pstmt, rs);
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
			//dao.freeCon(con, pstmt, rs);
			dao.freeConnection(con, pstmt, rs);
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
			//dao.freeCon(con, pstmt, rs);
			dao.freeConnection(con, pstmt, rs);
		}
		return null;
	}
	
	public List getBoardList(int board_num){
		Vector boardList = new Vector();
		try{
			con = dao.getConnection();
			if(con != null){
				String sql = "select wr_num, wr_title, wr_writer, wr_date, wr_counter from tblBoardBasic where board_num=" + board_num;
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					BasicBoardDto dto = new BasicBoardDto();
					dto.setWr_num(rs.getInt("wr_num"));
					dto.setWr_title(rs.getString("wr_title"));
					dto.setWr_writer(rs.getString("wr_writer"));
					dto.setWr_date(rs.getString("wr_date"));
					dto.setWr_counter(rs.getInt("wr_counter"));
					
					boardList.add(dto);
				}
			}	
		}
		catch(Exception err){
			System.out.println("createBoard() : " + err);
		}
		finally{
			//dao.freeCon(con, pstmt, rs);
			dao.freeConnection(con, pstmt, rs);
		}
		return boardList;
	}
	
	public void writeBoard(BasicBoardDto basicDto){
		try{
			con = dao.getConnection();
			if(con != null){
				String sql = "insert into tblBoardBasic(wr_title, wr_writer, wr_contents, wr_email, wr_home, wr_counter, wr_ip, wr_pass, wr_date, board_num) values(?, ?, ?, ?, ?, 0, ?, ?, now(), ?)";
				pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, basicDto.getWr_title());
				pstmt.setString(2, basicDto.getWr_writer());
				pstmt.setString(3, basicDto.getWr_contents());
				pstmt.setString(4, basicDto.getWr_email());
				pstmt.setString(5, basicDto.getWr_home());
				pstmt.setString(6, basicDto.getWr_ip());
				pstmt.setString(7, basicDto.getWr_pass());
				pstmt.setInt(8, basicDto.getBoard_num());
				
				pstmt.executeUpdate();  
				ResultSet keys = pstmt.getGeneratedKeys();    
				keys.next();  
				int key = keys.getInt(1);
				
				if(basicDto.getWr_file() != null && !basicDto.getWr_file().isEmpty()){
					sql = "insert into tblBoardUpload(wr_num, wr_file) values(?, ?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, key);
					pstmt.setString(2, basicDto.getWr_file());
					pstmt.executeUpdate();
				}
			}	
		}
		catch(Exception err){
			System.out.println("writeBoard() : " + err);
		}
		finally{
			//dao.freeCon(con, pstmt);
			dao.freeConnection(con, pstmt, rs);
		}
	}
	
	public BasicBoardDto getBoard(int wr_num){
		BasicBoardDto dto = new BasicBoardDto();
		try{
			con = dao.getConnection();
			if(con != null){
				String sql = "select wr_num, wr_title, wr_writer, wr_email, wr_home, wr_contents from tblBoardBasic where wr_num=" + wr_num;
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					dto.setWr_num(rs.getInt("wr_num"));
					dto.setWr_title(rs.getString("wr_title"));
					dto.setWr_writer(rs.getString("wr_writer"));
					dto.setWr_email(rs.getString("wr_email"));
					dto.setWr_home(rs.getString("wr_home"));
					dto.setWr_contents(rs.getString("wr_contents"));
				}
			}	
		}
		catch(Exception err){
			System.out.println("createBoard() : " + err);
		}
		finally{
			//dao.freeCon(con, pstmt, rs);
			dao.freeConnection(con, pstmt, rs);
		}
		return dto;
	}
}
