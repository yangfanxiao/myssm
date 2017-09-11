package com.yitianyike.myssm.entity;

import java.io.Serializable;
import java.util.Date;

import com.yitianyike.myssm.utils.DateUtils;

public class FestivalPojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String festival_date;
	private String festival_name;
	private String keyword;
	private String festival_url;
	private String create_user;
	private Date create_time;
	private String update_user;
	private Date update_time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getFestival_date() {
		return festival_date;
	}

	public void setFestival_date(String festival_date) {
		this.festival_date = festival_date;
	}

	public String getFestival_name() {
		return festival_name;
	}

	public void setFestival_name(String festival_name) {
		this.festival_name = festival_name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFestival_url() {
		return festival_url;
	}

	public void setFestival_url(String festival_url) {
		this.festival_url = festival_url;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public String getCreate_time() {
		return DateUtils.getFormatDateAndTime(create_time);
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public String getUpdate_time() {
		return DateUtils.getFormatDateAndTime(update_time);
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
