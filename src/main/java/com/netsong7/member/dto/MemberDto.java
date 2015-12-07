package com.netsong7.member.dto;

import java.sql.Timestamp;

/*
java.util.Date date = getItSomehow();
 
Timestamp timestamp = new Timestamp(date.getTime());
preparedStatement = connection.prepareStatement("SELECT * FROM tbl WHERE ts > ?");
preparedStatement.setTimestamp(1, timestamp);
*/

public class MemberDto {
	private	int			mem_num;
	private	String		mem_id;
	private	String		mem_name;
	private	String		mem_pass;
	private	String		mem_nick;
	private	String		mem_birth;	
	private	String		mem_sex;
	private	String		mem_email;
	private	String		mem_addr;
	private	String		mem_company;
	private	String		mem_phone;
	private	String		mem_hobby;
	private	String		mem_intro;
	private	String		mem_pic;
	private	Timestamp 	mem_regdate;
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMem_birth() {
		return mem_birth;
	}
	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}
	public String getMem_sex() {
		return mem_sex;
	}
	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_company() {
		return mem_company;
	}
	public void setMem_company(String mem_company) {
		this.mem_company = mem_company;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_hobby() {
		return mem_hobby;
	}
	public void setMem_hobby(String mem_hobby) {
		this.mem_hobby = mem_hobby;
	}
	public String getMem_intro() {
		return mem_intro;
	}
	public void setMem_intro(String mem_intro) {
		this.mem_intro = mem_intro;
	}
	public String getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(String mem_pic) {
		this.mem_pic = mem_pic;
	}
	public Timestamp getMem_regdate() {
		return mem_regdate;
	}
	public void setMem_regdate(Timestamp mem_regdate) {
		this.mem_regdate = mem_regdate;
	}
}
