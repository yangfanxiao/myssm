package com.yitianyike.myssm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yitianyike.myssm.entity.FestivalPojo;
import com.yitianyike.myssm.entity.vo.FestivalVo;

public interface FestivalDao {
	public List<FestivalPojo> getList(FestivalVo festivalVo);
	public int getTotal(FestivalVo festivalVo);
	
	public int insert(FestivalPojo festivalPojo);
	/**
	 * 批量导入
	 * @param: @param festivalPojos
	 * @param: @return      
	 * @return: int  
	 */
	public int insertList(List<FestivalPojo> festivalPojos);
	
	public int delete(@Param("id")int id);
	
	/**
	 * 删除日期集合内的数据
	 * @param: @param festival_dates
	 * @param: @return      
	 * @return: int  
	 */
	public int deleteByDate(List<String> dateSet);
	
	public int update(FestivalPojo festivalPojo);
	
	/**
	 * 节日是否重复 
	 * @param parent_code
	 * @return
	 */
	public int isRepeatedlyFestival(@Param("festival_date") String festival_date,@Param("festival_name")String festival_name,@Param("id")Integer id);
}
