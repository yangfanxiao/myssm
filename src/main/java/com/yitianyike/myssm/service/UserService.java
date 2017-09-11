package com.yitianyike.myssm.service;

import java.util.List;

import com.yitianyike.myssm.entity.UserPojo;


public interface UserService {
	List<String> getPassWord(String name);
	void insertUser(UserPojo user);
}
