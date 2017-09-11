package com.yitianyike.myssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yitianyike.myssm.entity.vo.SchoolVo;

public interface SchoolDao{
	public int insert(SchoolVo entity);
	public List<SchoolVo> findAll();
	public String getSchoolName();
	public List<SchoolVo> findSchoolByName(@Param(value = "name")  String name);
	public List<SchoolVo> querySchoolVO(SchoolVo schoolVo);
	public List<SchoolVo> querySchool(SchoolVo schoolVo);
	public void delete(String name);
}
