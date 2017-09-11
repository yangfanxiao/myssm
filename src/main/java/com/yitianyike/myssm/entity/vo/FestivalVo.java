package com.yitianyike.myssm.entity.vo;

import com.yitianyike.myssm.entity.FestivalPojo;


public class FestivalVo extends FestivalPojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 开始的时间
	 */
	private String date_start;
	
	/**
	 * 结束
	 */
	private String date_end;
	
	private Integer pn;//pn  当前页  ps 每页条数
	private Integer ps;
	public Integer getPn() {
		return pn;
	}
	public void setPn(Integer pn) {
		this.pn = pn;
	}
	public Integer getPs() {
		return ps;
	}
	public void setPs(Integer ps) {
		this.ps = ps;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	
}
