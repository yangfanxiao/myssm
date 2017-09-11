package com.yitianyike.myssm.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yitianyike.myssm.dao.StudentDao;
import com.yitianyike.myssm.entity.StudentPojo;
import com.yitianyike.myssm.entity.vo.StudentVo;
import com.yitianyike.myssm.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public List<StudentVo> getSchoolByStudentName(String name) {
		// TODO Auto-generated method stub
		
		return studentDao.findSchoolByStudentName(name);
	}

	@Override
	public List<StudentPojo> getStudentByAge(Integer[] ages) {
		// TODO Auto-generated method stub
		return studentDao.getStudentByAge(ages);
	}

}
