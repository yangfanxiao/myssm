package com.yitianyike.myssm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yitianyike.myssm.dao.SchoolDao;
import com.yitianyike.myssm.entity.vo.SchoolVo;

@Service
public class SchoolServiceImpl{

	@Autowired
	private SchoolDao schoolDao;
	
	public String getSchoolName() {
		return  schoolDao.getSchoolName();
	}

	/**
	 * @param school_name
	 * @return
	 */
	public List<SchoolVo> findByName(String name) {
		List<SchoolVo> schoolVo = schoolDao.findSchoolByName(name);
		return schoolVo;
	}
	
	public List<SchoolVo> querySchoolVO(SchoolVo schoolVo) throws Exception{
		return schoolDao.querySchoolVO(schoolVo);
	}
	
	public List<SchoolVo> querySchool(SchoolVo schoolVo) throws Exception{
		return schoolDao.querySchool(schoolVo);
	}

	public void insert(SchoolVo entity) {
		// TODO Auto-generated method stub
	int	i =  schoolDao.insert(entity);
	System.out.println("insert: " + i);
	}

	public void delete(String name) {
		// TODO Auto-generated method stub
		schoolDao.delete(name);
	
	}
	
	public List<SchoolVo> findAll() {
		// TODO Auto-generated method stub
		return schoolDao.findAll();
	}
}
