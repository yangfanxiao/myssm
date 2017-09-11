package com.yitianyike.myssm.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yitianyike.myssm.common.DynamicDataSource;
import com.yitianyike.myssm.entity.vo.SchoolVo;
import com.yitianyike.myssm.service.impl.SchoolServiceImpl;


@RequestMapping(value = "/test")
@Controller
public class TestController {
	private static Logger logger = Logger.getLogger(TestController.class);  
	@Autowired
	SchoolServiceImpl schoolServiceImpl;
	 
	  @RequestMapping(value = "/insert", method ={RequestMethod.POST})
	  @ResponseBody
	  public String insert(HttpServletRequest request){
		  String name = request.getHeader("name");
		  int creat_time = Integer.parseInt(request.getHeader("creat_time"));
		  SchoolVo entity = new SchoolVo(name,creat_time);
		  schoolServiceImpl.insert(entity);
		  return "aaaa";
	  }
	  
	  
	  @RequestMapping(value = "/delete", method ={RequestMethod.GET})
	  @ResponseBody
	  public void delete(HttpServletRequest request){
		  String name = request.getHeader("name");
		  if(name != null){
			  schoolServiceImpl.delete(name);
		  }
	  }
	  @RequestMapping("/findAll")
	  @ResponseBody
	  public List<SchoolVo> findAll(){
		  DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_A);
		  return schoolServiceImpl.findAll();
	  }
	  
	  @RequestMapping(value="/findByName",method={RequestMethod.POST})
	  @ResponseBody
	  public List<SchoolVo> findByName(HttpServletRequest request){
//		  String name = request.getHeader("name");
		  String name = request.getParameter("name");
//		return new SchoolVo(name,1878);
		  return schoolServiceImpl.findByName(name);
	  }
	  
	  
	  @RequestMapping(value = "/findBySysIds", method ={RequestMethod.POST})
	  @ResponseBody
	  public List<SchoolVo> findBySysIds(HttpServletRequest request){
//		  StringBuffer aaa = new StringBuffer() ;
		  String jbstring ="";
		  try {
			jbstring= IOUtils.toString(request.getInputStream(), "utf-8");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		  String name = request.getHeader("name");
		  System.out.println("aaa:" + name + " " + jbstring);
		  return schoolServiceImpl.findByName("2");
	  }
	  
	  @RequestMapping(value = "/query",method ={RequestMethod.POST})
	  @ResponseBody
	  public List<SchoolVo> query(SchoolVo schoolVo) throws Exception{
		  SchoolVo school = new SchoolVo(schoolVo.getName(), schoolVo.getCreat_time());
		  return schoolServiceImpl.querySchoolVO(school);
	  }
	  
	  //http://127.0.0.1:8080/myssm/test/querySchools?name=nan&times=1990,1989
	  @RequestMapping(value = "/querySchools",method ={RequestMethod.GET})
	  @ResponseBody
	  public List<SchoolVo> querySchools(SchoolVo schoolVo) throws Exception{
		  SchoolVo school = new SchoolVo();
		  school.setName(schoolVo.getName());
		  school.setTimes(schoolVo.getTimes());
		  return schoolServiceImpl.querySchool(school);
	  }
	  
	  @RequestMapping(value = "/upLoad",method ={RequestMethod.POST})
	  @ResponseBody
	  public String upLoad(HttpServletRequest request){
		  DiskFileItemFactory factory = new DiskFileItemFactory();
		  
		  ServletFileUpload upload = new ServletFileUpload(factory);
		  
		  try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				// 3.3判断是否是上传组件
				if (!item.isFormField()) {
					// 3.4 将文件真正上传
					IOUtils.copy(item.getInputStream(), new FileOutputStream(
							"f:/upload/a.txt"));
				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return "aa";
	  }
	  
	  
}
