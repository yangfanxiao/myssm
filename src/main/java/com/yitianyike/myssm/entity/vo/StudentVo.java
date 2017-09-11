package com.yitianyike.myssm.entity.vo;



import com.yitianyike.myssm.entity.StudentPojo;


public class StudentVo extends StudentPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 需要查询的年龄
	 */
	Integer[] ages;
	
	/**
	 * 学校创建时间
	 */
	int creat_time;
	/**
	 * 学校类型
	 */
	int type;


	public Integer[] getAges() {
		return ages;
	}


	public void setAges(Integer[] ages) {
		this.ages = ages;
	}


	public int getCreat_time() {
		return creat_time;
	}


	public void setCreat_time(int creat_time) {
		this.creat_time = creat_time;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}

}
