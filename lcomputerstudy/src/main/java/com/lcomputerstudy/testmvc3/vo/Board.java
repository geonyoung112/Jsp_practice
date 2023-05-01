package com.lcomputerstudy.testmvc3.vo;

import java.sql.Timestamp;

import com.lcomputerstudy.testmvc.vo.User;

public class Board {
	
	private int b_idx;
	private int u_idx;
	private String b_title;
	private String b_content;
	private Timestamp b_date;
	private int b_readcount;
	private int b_ref;
	private int b_restep;
	private int b_relevel;
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public Timestamp getB_date() {
		return b_date;
	}
	public void setB_date(Timestamp b_date) {
		this.b_date = b_date;
	}
	public int getB_readcount() {
		return b_readcount;
	}
	public void setB_readcount(int b_readcount) {
		this.b_readcount = b_readcount;
	}
	public int getB_ref() {
		return b_ref;
	}
	public void setB_ref(int b_ref) {
		this.b_ref = b_ref;
	}
	public int getB_restep() {
		return b_restep;
	}
	public void setB_restep(int b_restep) {
		this.b_restep = b_restep;
	}
	public int getB_relevel() {
		return b_relevel;
	}
	public void setB_relevel(int b_relevel) {
		this.b_relevel = b_relevel;
	}

	@Override
	public String toString() {
		return "Board [b_idx=" + b_idx + ", u_idx= " + u_idx + ", b_title=" + b_title + ", b_content=" + b_content + ", b_Date=" + b_date
				+ ", b_readCount=" + b_readcount + ", b_ref=" + b_ref + ", b_restep="
				+ b_restep + ", b_relevel=" + b_relevel + "]";
	}
}
