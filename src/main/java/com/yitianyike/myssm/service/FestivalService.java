package com.yitianyike.myssm.service;

import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;

import com.yitianyike.myssm.entity.FestivalPojo;
import com.yitianyike.myssm.entity.ResponseResult;
import com.yitianyike.myssm.entity.vo.FestivalVo;


public interface FestivalService {
	public ResponseResult getFestival(FestivalVo festivalVo);
	
	public ResponseResult insert(FestivalPojo festivalPojo);
	
	public ResponseResult delete(@Param("id")Integer id);
	
	public ResponseResult update(FestivalPojo festivalPojo);
	
	public ResponseResult importFestival(String name,InputStream in,String create_user,Date startDate,Date endDate);
	public ResponseResult exportFestival(HttpServletResponse response,String year);
}
