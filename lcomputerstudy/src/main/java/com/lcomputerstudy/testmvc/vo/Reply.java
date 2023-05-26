package com.lcomputerstudy.testmvc.vo;

import java.sql.Timestamp;

public class Reply {
	private int re_idx;
	private int u_idx;
	private String re_content;
	private Timestamp re_date;
	private int b_idx;
	private User2 user;
	private Board board;
	
	public User2 getUser() {
		return user;
	}
	public void setUser(User2 user) {
		this.user = user;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getRe_idx() {
		return re_idx;
	}
	public void setRe_idx(int re_idx) {
		this.re_idx = re_idx;
	}
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public Timestamp getRe_date() {
		return re_date;
	}
	public void setRe_date(Timestamp re_date) {
		this.re_date = re_date;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	
	@Override
	public String toString() {
		return "Reply [re_idx=" + re_idx + ", u_idx=" + u_idx + ", re_content=" + re_content + ", re_date=" + re_date
				+ ", b_idx=" + b_idx + "]";
	}
	
}
