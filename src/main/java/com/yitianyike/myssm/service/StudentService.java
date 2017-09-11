package com.yitianyike.myssm.service;

import java.util.List;


import com.yitianyike.myssm.entity.StudentPojo;
import com.yitianyike.myssm.entity.vo.StudentVo;

/**
 * @author Administrator
 *增加这一层的目的是使代码整洁 当一个表有大量操作语句时  可以在这快速找到需要的
 *而不用去impl实现类查找
 */
public interface StudentService {
	List<StudentVo> getSchoolByStudentName(String name);
	
	List<StudentPojo> getStudentByAge(Integer[] ages);
}
