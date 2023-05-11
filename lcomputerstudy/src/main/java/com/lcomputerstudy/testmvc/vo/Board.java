package com.lcomputerstudy.testmvc.vo;

import java.sql.Timestamp;

public class Board {
	
	private int b_idx;
	private int u_idx;
	private String b_title;
	private String b_content;
	private Timestamp b_date;
	private int b_readcount;
	private int b_group;
	private int b_order;
	private int b_depth;
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
	public int getB_group() {
		return b_group;
	}
	public void setB_group(int b_group) {
		this.b_group = b_group;
	}
	public int getB_order() {
		return b_order;
	}
	public void setB_order(int b_order) {
		this.b_order = b_order;
	}
	public int getB_depth() {
		return b_depth;
	}
	public void setB_depth(int b_depth) {
		this.b_depth = b_depth;
	}

	@Override
	public String toString() {
		return "Board [b_idx=" + b_idx + ", u_idx= " + u_idx + ", b_title=" + b_title + ", b_content=" + b_content + ", b_Date=" + b_date
				+ ", b_readCount=" + b_readcount + ", b_group=" + b_group + ", b_order="
				+ b_order+ ", b_depth=" + b_depth + "]";
	}
}
