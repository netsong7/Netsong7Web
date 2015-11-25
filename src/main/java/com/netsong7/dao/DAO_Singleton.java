package com.netsong7.dao;

public class DAO_Singleton {
	private static DAO_Singleton dao = new DAO_Singleton(); 
	
	private DAO_Singleton(){}
	
	public DAO_Singleton newInstance(){
		return dao;
	}
	
	
}
