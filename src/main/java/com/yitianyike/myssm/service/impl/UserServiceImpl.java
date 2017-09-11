package com.yitianyike.myssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yitianyike.myssm.dao.UserDao;
import com.yitianyike.myssm.entity.UserPojo;
import com.yitianyike.myssm.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public List<String> getPassWord(String name) {
		// TODO Auto-generated method stub
		return userDao.getPassWord(name);
	}

	@Override
	public void insertUser(UserPojo user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
	}

}
