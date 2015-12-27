package com.netsong7.board.multiboard.dto;

public class CommentBoardDto {
	private	int		co_num;
	private	int		wr_num;
	private	String	co_comment;
	private	String	co_name;
	private	String	co_date;
	
	public int getCo_num() {
		return co_num;
	}
	public void setCo_num(int co_num) {
		this.co_num = co_num;
	}
	public int getWr_num() {
		return wr_num;
	}
	public void setWr_num(int wr_num) {
		this.wr_num = wr_num;
	}
	public String getCo_comment() {
		return co_comment;
	}
	public void setCo_comment(String co_comment) {
		this.co_comment = co_comment;
	}
	public String getCo_name() {
		return co_name;
	}
	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}
	public String getCo_date() {
		return co_date;
	}
	public void setCo_date(String co_date) {
		this.co_date = co_date;
	}
}
