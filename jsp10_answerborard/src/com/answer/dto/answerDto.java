package com.answer.dto;

import java.util.Date;

public class answerDto {
	
	int boardno;
	int groupno;
	int groupseq;
	int titletab;
	String title;
	String content;
	String writer;
	Date regdate;
	
	
	public answerDto() {
		
	}


	public answerDto(int boardno, int groupno, int groupseq, int titletab, String title, String content, String writer,
			Date regdate) {
		this.boardno = boardno;
		this.groupno = groupno;
		this.groupseq = groupseq;
		this.titletab = titletab;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
	}


	public int getBoardno() {
		return boardno;
	}


	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}


	public int getGroupno() {
		return groupno;
	}


	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}


	public int getGroupseq() {
		return groupseq;
	}


	public void setGroupseq(int groupseq) {
		this.groupseq = groupseq;
	}


	public int getTitletab() {
		return titletab;
	}


	public void setTitletab(int titletab) {
		this.titletab = titletab;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	

}
