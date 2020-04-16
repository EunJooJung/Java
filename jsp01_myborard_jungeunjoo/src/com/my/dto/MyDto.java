package com.my.dto;

import java.util.Date;	//시분 초까지 다 잡히기 때문에 사용

public class MyDto {
	
	private int myno;
	private String myname;
	private String mytitle;
	private String mycontent;
	private Date mydate;
	
	public MyDto() {
		
	}
	
	
	
	public MyDto(int myno, String myname, String mytitle, String mycontent, Date mydate) {
		this.myno = myno;
		this.myname = myname;
		this.mytitle = mytitle;
		this.mycontent = mycontent;
		this.mydate = mydate;
		
	}

	//INSERT : MYNAME , MYTITLE, MYCONTENT
	public MyDto(String myname, String mytitle, String mycontent) {
		super();
		this.myname = myname;
		this.mytitle = mytitle;
		this.mycontent = mycontent;
	}
	
	// UPDATE : MYNO, MYTITLE, MYCONTENT
	public MyDto(int myno, String mytitle, String mycontent) {
		super();
		this.myno = myno;
		this.mytitle = mytitle;
		this.mycontent = mycontent;
	}

	public int getMyno() {
		return myno;
	}

	public void setMyno(int myno) {
		this.myno = myno;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public String getMytitle() {
		return mytitle;
	}

	public void setMytitle(String mytitle) {
		this.mytitle = mytitle;
	}

	public String getMycontent() {
		return mycontent;
	}

	public void setMycontent(String mycontent) {
		this.mycontent = mycontent;
	}

	public Date getMydate() {
		return mydate;
	}

	public void setMydate(Date mydate) {
		this.mydate = mydate;
	}
	
	

}
