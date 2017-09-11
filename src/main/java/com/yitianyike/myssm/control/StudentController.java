package com.yitianyike.myssm.control;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yitianyike.myssm.entity.ResponseResult;
import com.yitianyike.myssm.entity.StudentPojo;
import com.yitianyike.myssm.entity.vo.SchoolVo;
import com.yitianyike.myssm.entity.vo.StudentVo;
import com.yitianyike.myssm.service.StudentService;
import com.yitianyike.myssm.service.impl.StudentServiceImpl;
import com.yitianyike.myssm.utils.StringUtils;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Autowired
	StudentService studentServiceImpl;
	
	//http://127.0.0.1:8080/rms/test/querySchools?name=nan&times=1990,1989
	  @RequestMapping(value = "/getSchoolByStudentName",method ={RequestMethod.POST})
	  @ResponseBody
	  public Object getSchoolByStudentName(HttpServletRequest request,String name) throws Exception{
		  List<StudentVo> list = new ArrayList<>();
		  System.out.println("thread[" + Thread.currentThread().getName()+"]getSchoolByStudentName[" + "aaaaaa" + "]");
		  StudentVo studentVo = studentServiceImpl.getSchoolByStudentName(name).get(0); 
		  studentVo.setAges(new Integer[] {19,23,24});
		  list.add(studentVo);
//		  return studentServiceImpl.getSchoolByStudentName(name);
		  return list;
	  }

	  @RequestMapping(value = "/getStudentByStudentAge",method ={RequestMethod.GET})
	  @ResponseBody
	  public List<StudentPojo> getStudentByStudentAge(HttpServletRequest request,StudentVo student) throws Exception{
		  
//		  System.out.println(student.getAges().toString());
		  return studentServiceImpl.getStudentByAge(student.getAges());
	  }

	  

	  
}
