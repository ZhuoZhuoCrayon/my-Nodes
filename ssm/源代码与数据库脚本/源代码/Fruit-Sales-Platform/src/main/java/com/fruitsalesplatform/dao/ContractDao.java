package com.fruitsalesplatform.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;

public interface ContractDao extends BaseDao<Contract>{  
    //我们这里可以直接使用继承的BaseDao的增删改查方法  
	//添加新的方法定义
	public int count(Map map);//根据条件统计结果集数量
	public List<ContractVo> findContractList(Map map);//根据条件查询多个结果
	public void insertMiddleTab(MiddleTab middelTab);//插入合同与货物关联信息
	public int deleteMiddleTab(Serializable contractId);//删除合同下所有货物信息
	public String getMaxBarCode();//获取最大编号
}  
