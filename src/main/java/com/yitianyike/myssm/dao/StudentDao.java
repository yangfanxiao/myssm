package com.yitianyike.myssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yitianyike.myssm.entity.StudentPojo;
import com.yitianyike.myssm.entity.vo.SchoolVo;
import com.yitianyike.myssm.entity.vo.StudentVo;

public interface StudentDao{
	public void insert(SchoolVo entity);
	public List<SchoolVo> findAll();
	public String getSchoolName();
	public List<StudentVo> findSchoolByStudentName(@Param(value = "name")  String name);
	public void delete(String name);
	public  List<StudentPojo> getStudentByAge(Integer[] ages);
	
}
