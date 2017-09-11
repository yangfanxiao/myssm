package com.yitianyike.myssm.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yitianyike.myssm.entity.TMSColumnPojo;

public interface RefreshDao {
	int insertTMS(@Param(value="column_name")String column_name,@Param(value ="column_code")String column_code);
	int insertColumn(List<TMSColumnPojo> list);
	int updateColumnHistoryID();
	
	int insertSport(List<String> list);
	int updateSportHistoryID();
	List<String> getFootboll();
	List<String> getNBA();
}
