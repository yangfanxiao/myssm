package com.yitianyike.myssm.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yitianyike.myssm.common.ErrorCode;
import com.yitianyike.myssm.dao.FestivalDao;
import com.yitianyike.myssm.entity.FestivalPojo;
import com.yitianyike.myssm.entity.ResponseResult;
import com.yitianyike.myssm.entity.vo.FestivalVo;
import com.yitianyike.myssm.service.FestivalService;
import com.yitianyike.myssm.utils.DateUtils;
import com.yitianyike.myssm.utils.ExcelUtil;

@Service
public class FestivalServiceImpl implements FestivalService{
	@Autowired
	FestivalDao festivalDao;
	@Override
	public ResponseResult getFestival(FestivalVo vo) {
		// TODO Auto-generated method stub
		ResponseResult responseResult = new ResponseResult();
		Integer pn = vo.getPn();
		Integer ps = vo.getPs();
		if (pn == null || ps== null) {
			vo.setPn(null);
		}else{
			vo.setPn((Integer.valueOf(vo.getPn()).intValue()-1)*vo.getPs());
		}
		List<FestivalPojo> resultlist = festivalDao.getList(vo);
		Map<String, Object>  result = new HashMap<>();
		int total = festivalDao.getTotal(vo);
		result.put("total", total);
		result.put("resultlist", resultlist);
		responseResult.setRespResult(result);
		return responseResult;
	}

	@Override
	public ResponseResult insert(FestivalPojo festivalPojo) {
		// TODO Auto-generated method stub
		ResponseResult responseResult = new ResponseResult();
		Date create_time = DateUtils.getNowDate();
		festivalPojo.setCreate_time(create_time);
		int num = festivalDao.isRepeatedlyFestival(festivalPojo.getFestival_date(), festivalPojo.getFestival_name(),null);
		if (num > 0) {
			return new ResponseResult(ErrorCode.NORMAL_ERRORCODE, "已存在该节日 不能重复创建");
		}
        festivalDao.insert(festivalPojo);
		responseResult.setRespResult(festivalPojo);
		return responseResult;
	}

	@Override
	public ResponseResult delete(Integer id) {
		// TODO Auto-generated method stub
		ResponseResult responseResult = new ResponseResult();
		festivalDao.delete(id);
		return responseResult;
	}

	@Override
	public ResponseResult update(FestivalPojo festivalPojo) {
		// TODO Auto-generated method stub
		Date update_time = DateUtils.getNowDate();
		int num = festivalDao.isRepeatedlyFestival(festivalPojo.getFestival_date(), festivalPojo.getFestival_name(),festivalPojo.getId());
		if (num > 0) {
			return new ResponseResult(ErrorCode.NORMAL_ERRORCODE, "已存在该节日 不能重复");
		}
		festivalPojo.setUpdate_time(update_time);
		ResponseResult responseResult = new ResponseResult();
		int num2 = festivalDao.update(festivalPojo);
		if (num2 == 0) {
			return new ResponseResult(ErrorCode.NORMAL_ERRORCODE, "该节日不存在，刷新后再试");
		}
		return responseResult;
	}

	@Override
	public ResponseResult importFestival(String name,InputStream in,String create_user,Date startDate,Date endDate) {
		
		// TODO Auto-generated method stub
		ResponseResult responseResult = new ResponseResult();
		List<List<Object>> listob = null;
		Date create_time = DateUtils.getNowDate();
		Set<String> dateSet = new HashSet<>();
		List<FestivalPojo> festivalPojos = new ArrayList<>();
		
		try {
			 listob = ExcelUtil.getBankListByExcel(in,name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseResult(ErrorCode.NORMAL_ERRORCODE, "Excel解析出错");
		}
		
		 for (int i = 0; i < listob.size(); i++) {
			 List<Object> ob = listob.get(i);
			 FestivalPojo fPojo = new FestivalPojo();
			 Date festival_date = null;
	    	 if(ob.get(0)!=null){
	    		 festival_date = java.sql.Date.valueOf((String) ob.get(0));
	    		 if (festival_date.before(startDate) || festival_date.after(endDate)) {
	    			 return new ResponseResult(ErrorCode.NORMAL_ERRORCODE, "Excel不能包含选择外的日期节日");
				}
	    		 fPojo.setFestival_date((String) ob.get(0));
	    		 dateSet.add((String) ob.get(0));
	    	 }
	    	 if(ob.get(1)!=null){
	    		 fPojo.setFestival_name(ob.get(1).toString());
	    	 }
	    	 if(ob.get(2)!=null){
	    		 fPojo.setKeyword(ob.get(2).toString()); 
	    	 }
	    	 if(ob.get(3)!=null){
	    		 fPojo.setFestival_url(ob.get(3).toString());
	    	 }
	    	 fPojo.setCreate_user(create_user);
	    	 fPojo.setCreate_time(create_time);
	    	 festivalPojos.add(fPojo);
	     }
		 List<String> dateList =new ArrayList<>();
		 dateList.addAll(dateSet);
		 festivalDao.deleteByDate(dateList);
		 festivalDao.insertList(festivalPojos);
		return responseResult;
	}

	@Override
	public ResponseResult exportFestival(HttpServletResponse response,String year) {
		// TODO Auto-generated method stub
		List<Map<String, String>> dataList = new ArrayList<>();
		Map<String,String> fMap = null;
		FestivalVo festivalVo = new FestivalVo();
		festivalVo.setFestival_date(year);
		List<FestivalPojo> festivalPojos= festivalDao.getList(festivalVo);
		for (FestivalPojo festivalPojo : festivalPojos) {
			fMap = new HashMap<>();
			fMap.put("日期", festivalPojo.getFestival_date());
			fMap.put("节日名称", festivalPojo.getFestival_name());
			fMap.put("关键词", festivalPojo.getKeyword());
			fMap.put("链接", festivalPojo.getFestival_url());
			dataList.add(fMap);
		}
		
		List<String> headData = new ArrayList<>();
		headData.add("日期");
		headData.add("节日名称");
		headData.add("关键词");
		headData.add("链接");
		String fileName = year+"年节日导出表";
		ExcelUtil.export(response, dataList, headData, fileName);
		return new ResponseResult();
	}

}
