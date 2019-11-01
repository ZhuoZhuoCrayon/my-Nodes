package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.dao.CommoditiesDao;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.service.CommoditiesService;

@Service  //为了包扫描的时候这个Service被扫描到  
public class CommoditiesServiceImpl implements CommoditiesService{  
    
    @Autowired  
    CommoditiesDao commoditiesDao;

	public Commodities get(Serializable id) {
		return commoditiesDao.get(id);
	}
	public List<Commodities> find(Map map) {
		return commoditiesDao.find(map);
	} 
	public void insert(Commodities commodities) {
		commoditiesDao.insert(commodities);
	}
	public void update(Commodities commodities) {
		commoditiesDao.update(commodities);
	}
	public void deleteById(Serializable id) {
		commoditiesDao.deleteById(id);
	}
	public void delete(Serializable[] ids) {
		commoditiesDao.delete(ids);
	}
	public int count(Map map) {
		return commoditiesDao.count(map);
	}
}
