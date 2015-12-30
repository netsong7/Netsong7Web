package com.netsong7.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.netsong7.board.dto.MasterBoardDto;

public class BoardManager {
	private static SqlSessionFactory sqlFactory;
	static{
		try{
			Reader reader = 
				Resources.getResourceAsReader("com/netsong7/mybatis/sqlmapConfig.xml");
			sqlFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		catch(IOException err){
			throw new RuntimeException("SqlSessionFactory 인스턴스 생성 실패 : " + err);
		}
	}
	
	public static List getTables(){
		List list = null;
		SqlSession session = sqlFactory.openSession();
		list = session.selectList("getTables");
		return list;
	}
	
	public static void createBoard(MasterBoardDto masterBoardDto){
		SqlSession session = sqlFactory.openSession();
		session.insert("createBoard", masterBoardDto);
		session.commit();
	}
	
	public static boolean getDuplicatedTableName(String table_name){
		SqlSession session = sqlFactory.openSession();
		String tname = session.selectOne("getDuplicatedTableName", table_name);

		try{
			if(tname.equals(table_name))
				return true;
			else
				return false;
		}
		catch(NullPointerException err){
			return false;
		}
	}
	
	public static void removeBoard(int board_num){
		SqlSession session = sqlFactory.openSession();
		System.out.println(board_num);
		List list = session.selectList("getBoardBasic", board_num);
		System.out.println(list.size());
	}
}
