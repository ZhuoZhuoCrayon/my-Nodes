package com.fruitsalesplatform.dao.impl;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.dao.ContractDao;
import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;

@Repository //为了包扫描的时候这个Dao被扫描到 
public class ContractDaoImpl  extends BaseDaoImpl<Contract> implements ContractDao{
    public ContractDaoImpl(){  
        //设置命名空间  
        super.setNs("com.fruitslaesplatform.mapper.ContractMapper");  
    }
    public int count(Map map) {
		return this.getSqlSession().selectOne(this.getNs() + ".count", map);
	}
	public List<ContractVo> findContractList(Map map) {
		return this.getSqlSession().selectList(this.getNs() + ".findContractList", map);
	}
	public void insertMiddleTab(MiddleTab middelTab) {
		this.getSqlSession().insert(this.getNs() + ".insertMiddleTab", middelTab);
	}
	public int deleteMiddleTab(Serializable contractId) {
		return this.getSqlSession().delete(this.getNs() + ".deleteMiddleTab", contractId);
	}
	public String getMaxBarCode() {
		return this.getSqlSession().selectOne(this.getNs() + ".getMaxBarCode");
	}
}
