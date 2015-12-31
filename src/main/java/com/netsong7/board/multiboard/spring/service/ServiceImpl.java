package com.netsong7.board.multiboard.spring.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.netsong7.board.dto.BasicBoardDto;
import com.netsong7.board.dto.CommentBoardDto;
import com.netsong7.board.dto.MasterBoardDto;
import com.netsong7.dao.DBConnectionMgr;
import com.netsong7.mybatis.BoardManager;

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
	
	public void createBoard(@ModelAttribute MasterBoardDto masterBoardDto) {
		if(masterBoardDto.getBoard_comment() == null)
			masterBoardDto.setBoard_comment("n");
		else
			masterBoardDto.setBoard_comment("y");
		
		if(masterBoardDto.getBoard_reply() == null)
			masterBoardDto.setBoard_reply("n");
		else
			masterBoardDto.setBoard_reply("y");
		
		if(masterBoardDto.getBoard_upload() == null)
			masterBoardDto.setBoard_upload("n");
		else
			masterBoardDto.setBoard_upload("y");
		
		BoardManager.createBoard(masterBoardDto);
	}
	
	// 화면에 테이블 목록 뿌려주기 위한 기능
	public List getTables(){
		return BoardManager.getTables();
	}
	
	// 마스터 테이블 정보 알아내기
	public MasterBoardDto getMasterTable(int boardNum){
		return BoardManager.getMasterTable(boardNum);
	}
	
	public List getBoardList(int board_num){
		return BoardManager.getBoardList(board_num);
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
	
	// 글 읽기 페이지 기능
	public BasicBoardDto getBoard(int wr_num, String board_upload){
		BasicBoardDto dto = new BasicBoardDto();
		String sql = null;
		try{
			con = dao.getConnection();
			if(con != null){
				if(board_upload.equals("y")){
					sql = "select basic.wr_num, wr_title, wr_writer, wr_email, wr_home, wr_contents, wr_file from tblBoardBasic basic inner join tblBoardUpload upload on basic.wr_num=upload.wr_num and basic.wr_num=" + wr_num;
				}
				else{
					sql = "select wr_num, wr_title, wr_writer, wr_email, wr_home, wr_contents from tblBoardBasic where wr_num=" + wr_num;
				}
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					dto.setWr_num(rs.getInt("wr_num"));
					dto.setWr_title(rs.getString("wr_title"));
					dto.setWr_writer(rs.getString("wr_writer"));
					dto.setWr_email(rs.getString("wr_email"));
					dto.setWr_home(rs.getString("wr_home"));
					dto.setWr_contents(rs.getString("wr_contents"));
					if(board_upload.equals("y")){
						dto.setWr_file(rs.getString("wr_file"));
					}
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
		return dto;
	}
	
	// 테이블 이름 중복검사
	public boolean getDuplicatedTableName(String tableName){		
		return BoardManager.getDuplicatedTableName(tableName);
	}
	
	// 테이블 삭제
	public void removeBoard(int board_num){
		/*
		try{
			con = dao.getConnection();
			if(con != null){
				String sql = "select wr_num from tblBoardBasic where board_num=" + board_num;
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			
				if(rs.next()){
					System.out.println(rs.getInt("wr_num"));
					pstmt = con.prepareStatement("delete from tblBoardComment where wr_num=" + rs.getInt("wr_num"));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement("delete from tblBoardReply where wr_num=" + rs.getInt("wr_num"));
					pstmt.executeUpdate();
					pstmt = con.prepareStatement("delete from tblBoardUpload where wr_num=" + rs.getInt("wr_num"));
					pstmt.executeUpdate();
				}
				
				pstmt = con.prepareStatement("delete from tblBoardBasic where board_num=" + board_num);
				pstmt.executeUpdate();
				pstmt = con.prepareStatement("delete from tblBoardMaster where board_num=" + board_num);
				pstmt.executeUpdate();
			}	
		}
		catch(Exception err){
			System.out.println("removeBoard() : " + err);
		}
		finally{
			//dao.freeCon(con, pstmt, rs);
			dao.freeConnection(con, pstmt, rs);
		}
		*/
		BoardManager.removeBoard(board_num);
	}
	
	@Override
	public void commentBoard(CommentBoardDto commentBoardDto) {
		try{
			con = dao.getConnection();
			if(con != null){
				String sql = "insert into tblBoardComment(co_name, co_comment, co_date, wr_num) values(?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, commentBoardDto.getCo_name());
				pstmt.setString(2, commentBoardDto.getCo_comment());
				pstmt.setString(3, commentBoardDto.getCo_date());
				pstmt.setInt(4, commentBoardDto.getWr_num());
	
				pstmt.executeUpdate();  
			}	
		}
		catch(Exception err){
			System.out.println("commentBoard() : " + err);
		}
		finally{
			//dao.freeCon(con, pstmt);
			dao.freeConnection(con, pstmt);
		}
	}
	
	public List getCommentList(int wr_num){
		Vector commentList = new Vector();
		try{
			String sql = "select * from tblBoardComment where wr_num = " + wr_num;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CommentBoardDto dto = new CommentBoardDto();
				dto.setWr_num(rs.getInt("wr_num"));
				dto.setCo_comment(rs.getString("co_comment"));
				dto.setCo_date(rs.getString("co_date"));
				dto.setCo_num(rs.getInt("co_num"));
				dto.setCo_name(rs.getString("co_name"));
				
				commentList.add(dto);
			}
		}
		catch(Exception err){
			dao.freeConnection(con, pstmt, rs);
		}
		return commentList;
	}
}
