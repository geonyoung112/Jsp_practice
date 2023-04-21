package com.lcomputerstudy.testmvc3.vo;

public class Board {
	private int b_idx;
	private String b_title;
	private String b_content;
	private String b_date;
	private String b_write;
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
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
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public String getB_write() {
		return b_write;
	}
	public void setB_write(String b_write) {
		this.b_write = b_write;
	}
	@Override
	public String toString() {
		return "Board [b_idx=" + b_idx + ", b_title=" + b_title + ", b_content=" + b_content + ", b_date=" + b_date
				+ ", b_write=" + b_write + "]";
	}
	
}
