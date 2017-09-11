package com.yitianyike.myssm.service;

import java.util.List;

import com.yitianyike.myssm.entity.ResponseResult;


public interface RefreshService {
	public ResponseResult refreshTMS();
	public ResponseResult refreshSport(List<String> aList);
	public List<String> getSport();
}
