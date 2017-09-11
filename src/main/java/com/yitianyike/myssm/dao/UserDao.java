package com.yitianyike.myssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yitianyike.myssm.entity.UserPojo;

public interface UserDao {
	List<String> getPassWord(@Param(value = "name")String name);
	void insertUser(UserPojo user);
}
