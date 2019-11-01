package com.fruitsalesplatform.dao;

import java.util.Map;

import com.fruitsalesplatform.entity.Commodities;

public interface CommoditiesDao extends BaseDao<Commodities>{  
    //我们这里可以直接使用继承的BaseDao的增删改查方法  
	//添加新的方法定义
	public int count(Map map);//根据条件统计结果集数量
}  
