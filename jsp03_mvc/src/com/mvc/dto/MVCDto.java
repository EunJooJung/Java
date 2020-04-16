package com.mvc.dto;

import java.util.Date;

public class MVCDto {
	
	private int seq;
	private String writer;
	private String title;
	private String content;
	private Date regdate;
	
	public MVCDto() {
		
	}
	
	public MVCDto(int seq, String writer, String title, String content, Date regdate) {
		this.seq = seq;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public String getwriter() {
		return writer;
	}
	
	public void setwriter(String writer) {
		this.writer = writer;
	}
	
	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}
	
	public String getcontetn() {
		return content;
	}
	
	public void setcontent(String content) {
		this.content= content;
	}
	
	public Date getregdate() {
		return regdate;
	}
	
	public void setregdate(Date regdate) {
		this.regdate = regdate;
	}
	

}
