package com.bike.dto;

public class BikeDto {

	private String addr_gu;
	private int content_id;
	private String content_nm;
	private String new_addr;
	private int crable_count;
	private double longtitude;
	private double latitude;
	
	public BikeDto() {
		
	}

	public BikeDto(String addr_gu, int content_id, String content_nm, String new_addr, int crable_count,
			double longtitude, double latitude) {
		super();
		this.addr_gu = addr_gu;
		this.content_id = content_id;
		this.content_nm = content_nm;
		this.new_addr = new_addr;
		this.crable_count = crable_count;
		this.longtitude = longtitude;
		this.latitude = latitude;
	}

	public String getAddr_gu() {
		return addr_gu;
	}

	public void setAddr_gu(String addr_gu) {
		this.addr_gu = addr_gu;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getContent_nm() {
		return content_nm;
	}

	public void setContent_nm(String content_nm) {
		this.content_nm = content_nm;
	}

	public String getNew_addr() {
		return new_addr;
	}

	public void setNew_addr(String new_addr) {
		this.new_addr = new_addr;
	}

	public int getCrable_count() {
		return crable_count;
	}

	public void setCrable_count(int crable_count) {
		this.crable_count = crable_count;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	
	
	
}
