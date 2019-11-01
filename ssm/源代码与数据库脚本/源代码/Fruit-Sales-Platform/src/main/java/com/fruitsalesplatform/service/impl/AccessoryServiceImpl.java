package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.dao.AccessoryDao;
import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.service.AccessoryService;

@Service  //为了包扫描的时候这个Service被扫描到  
public class AccessoryServiceImpl implements AccessoryService{  
    
    @Autowired  
    AccessoryDao accessoryDao;

	public Accessory get(Serializable id) {
		return accessoryDao.get(id);
	}
	public List<Accessory> find(Map map) {
		return accessoryDao.find(map);
	} 
	public void insert(Accessory accessory) {
		accessoryDao.insert(accessory);
	}
	public void update(Accessory accessory) {
		accessoryDao.update(accessory);
	}
	public void deleteById(Serializable id) {
		accessoryDao.deleteById(id);
	}
	public void delete(Serializable[] ids) {
		accessoryDao.delete(ids);
	}
	public int deleteByFruitId(String fruitId) {
		return accessoryDao.deleteByFruitId(fruitId);
	}
}
