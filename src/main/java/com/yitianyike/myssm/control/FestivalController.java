package com.yitianyike.myssm.control;



import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yitianyike.myssm.common.ErrorCode;
import com.yitianyike.myssm.entity.FestivalPojo;
import com.yitianyike.myssm.entity.ResponseResult;
import com.yitianyike.myssm.entity.vo.FestivalVo;
import com.yitianyike.myssm.service.FestivalService;
import com.yitianyike.myssm.utils.StringUtils;


@Controller
@RequestMapping(value = "festival")
public class FestivalController {

    @Autowired
    private FestivalService festivalServiceImpl;
    
	
	@RequestMapping(value = "getList",method ={RequestMethod.POST})
	@ResponseBody
	public  ResponseResult getList(HttpServletRequest request,FestivalVo festivalVo){
		try{
			return festivalServiceImpl.getFestival(festivalVo);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseResult(ErrorCode.NORMAL_ERRORCODE,"获取失败");
		}
	}
	
	@RequestMapping(value = "create",method ={RequestMethod.POST})
	@ResponseBody
	public  ResponseResult create(HttpServletRequest request,FestivalPojo festivalPojo){
		try{
			if(StringUtils.isEmpty(festivalPojo.getFestival_date()) || StringUtils.isEmpty(festivalPojo.getFestival_name())
					|| StringUtils.isEmpty(festivalPojo.getKeyword()) || StringUtils.isEmpty(festivalPojo.getFestival_url())){
				return new ResponseResult(ErrorCode.PARAM_ERRORCODE,"参数不对");
			}
			festivalPojo.setCreate_user("user");
		    return festivalServiceImpl.insert(festivalPojo);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseResult(ErrorCode.NORMAL_ERRORCODE,"创建失败");
		}
	}
	
	@RequestMapping(value = "delete",method ={RequestMethod.POST})
	@ResponseBody
	public  ResponseResult delete(HttpServletRequest request,int id){
		try{
		    return festivalServiceImpl.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseResult(ErrorCode.NORMAL_ERRORCODE,"删除失败");
		}
	}
	
	
	@RequestMapping(value = "update",method ={RequestMethod.POST})
	@ResponseBody
	public  ResponseResult update(HttpServletRequest request,FestivalPojo fPojo){
		try{
			if(StringUtils.isEmpty(fPojo.getFestival_date()) || StringUtils.isEmpty(fPojo.getFestival_name())
					|| StringUtils.isEmpty(fPojo.getKeyword()) || StringUtils.isEmpty(fPojo.getId())){
				return new ResponseResult(ErrorCode.PARAM_ERRORCODE,"参数不对");
			}
			fPojo.setUpdate_user("user");
		    return festivalServiceImpl.update(fPojo);				
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseResult(ErrorCode.SQL_ERRORCODE,"编辑失败");
		}
	}
	
	@RequestMapping(value = "importFestival",method ={RequestMethod.POST})
	@ResponseBody
	public  ResponseResult importFestival(HttpServletRequest request,String year,MultipartFile file){
		try{
			if(StringUtils.isEmpty(year)){
				return new ResponseResult(ErrorCode.PARAM_ERRORCODE,"参数不对");
			}
			InputStream in = file.getInputStream();
			String name = file.getOriginalFilename();
			Date startDate = null;
			Date endDate = null;
			try {
				 startDate = java.sql.Date.valueOf(year + "-01-01");
				 endDate = java.sql.Date.valueOf(year + "-12-31");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseResult(ErrorCode.PARAM_ERRORCODE,"年份输入不正确");
			}
			
		    return festivalServiceImpl.importFestival(name, in, "user",startDate,endDate);					
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseResult(ErrorCode.PARAM_ERRORCODE,"导入失败，请检查格式是否正确");
		}
	}
	
	
	@RequestMapping(value = "exportFestival",method ={RequestMethod.GET})
	@ResponseBody
	public  ResponseResult exportFestival(HttpServletResponse response,String year){
		try{
			if(StringUtils.isEmpty(year)){
				return new ResponseResult(ErrorCode.PARAM_ERRORCODE,"参数不对");
			}
		    return festivalServiceImpl.exportFestival(response, year);				
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseResult(ErrorCode.PARAM_ERRORCODE,"导出失败");
		}
	}
	  @RequestMapping(value = "/comeInFestival",method ={RequestMethod.GET})
	  public String comeInFestival(HttpServletRequest request,Model model) throws Exception{
		  
//		  System.out.println(student.getAges().toString());
		  return "upload";
	  }
}
