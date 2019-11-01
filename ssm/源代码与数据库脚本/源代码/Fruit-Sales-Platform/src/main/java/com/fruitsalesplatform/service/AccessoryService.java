package com.fruitsalesplatform.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fruitsalesplatform.entity.Accessory;

public interface AccessoryService {    
    public Accessory get(Serializable id);//只查询一个，常用于修改  
    public List<Accessory> find(Map map);//根据条件查询多个结果
    public void insert(Accessory accessory);//插入，用实体作为参数  
    public void update(Accessory accessory);//修改，用实体作为参数  
    public void deleteById(Serializable id);//按id删除，删除一条；支持整数型和字符串类型ID  
    public void delete(Serializable[] ids);//批量删除；支持整数型和字符串类型ID  
    public int deleteByFruitId(String fruitId);//根据条件统计结果集数量
}  
