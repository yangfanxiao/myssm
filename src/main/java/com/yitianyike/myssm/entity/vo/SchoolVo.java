package com.yitianyike.myssm.entity.vo;

import java.io.Serializable;
import java.util.List;
public class SchoolVo implements Serializable{
	private static final long serialVersionUID = 1L;
	String name;
	int creat_time;
	List<Integer> times;
	int type = 211;
	
	String location = "jiangxi";
	
	
	public int getType() {
		
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Integer> getTimes() {
		return times;
	}
	public void setTimes(List<Integer> times) {
		this.times = times;
	}
	public SchoolVo() {
		super();
	}
	public SchoolVo(String name, int creat_time) {
		super();
		this.name = name;
		this.creat_time = creat_time;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreat_time() {
		return creat_time;
	}
	public void setCreat_time(int creat_time) {
		this.creat_time = creat_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
