package com.yitianyike.myssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yitianyike.myssm.common.Base;
import com.yitianyike.myssm.dao.RefreshDao;
import com.yitianyike.myssm.entity.ResponseResult;
import com.yitianyike.myssm.entity.TMSColumnPojo;
import com.yitianyike.myssm.service.RefreshService;
import com.yitianyike.myssm.utils.HttpClientUtils;
import com.yitianyike.myssm.utils.StringUtils;
@Service
public class RefreshServiceImpl implements RefreshService{

	@Autowired
	RefreshDao refreshDao;
	@Override
	public ResponseResult refreshTMS() {
		// TODO Auto-generated method stub
			List<TMSColumnPojo> list = tmsGetDatas();
			refreshDao.updateColumnHistoryID();
			refreshDao.insertColumn(list);
		return new ResponseResult();
	}
	
	@Override
	public ResponseResult refreshSport(List<String> aList) {
		// TODO Auto-generated method stub
			
			refreshDao.updateSportHistoryID();
			refreshDao.insertSport(aList);
		return new ResponseResult();
	}
	
	public List<TMSColumnPojo> tmsGetDatas() {
		List<TMSColumnPojo> list = new ArrayList<>();
		System.out.println(Base.TMS_URL + "?pagenum=1&pagesize=1000&id=" + (new Date().getTime()));
		// json获取栏目
		String tmsAnchor = HttpClientUtils
				.executeByGET(Base.TMS_URL + "?pagenum=1&pagesize=1000&id=" + (new Date().getTime()));
		JSONObject object = JSON.parseObject(tmsAnchor);
		JSONArray array = object.getJSONArray("result");
		for (int i = 0; i < array.size(); i++) {
			JSONObject resultObject = array.getJSONObject(i);
			TMSColumnPojo tPojo = new TMSColumnPojo();
			tPojo.setColumn_code(resultObject.getString("id"));
			String name = resultObject.getString("name");
			if (StringUtils.isNotEmpty(name)) {
				tPojo.setColumn_name(name);
				list.add(tPojo);
			}
		}
		return list;
	}

	@Override
	public List<String> getSport() {
		// TODO Auto-generated method stub
		List<String> aList = new ArrayList<>();
		aList.addAll(refreshDao.getFootboll());
		aList.addAll(refreshDao.getNBA());
		return aList;
	}
	
}
