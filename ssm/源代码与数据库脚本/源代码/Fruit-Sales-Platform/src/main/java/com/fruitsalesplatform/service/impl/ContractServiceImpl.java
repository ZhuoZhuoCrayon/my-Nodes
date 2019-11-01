package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fruitsalesplatform.dao.ContractDao;
import com.fruitsalesplatform.entity.*;
import com.fruitsalesplatform.service.ContractService;

@Service  //为了包扫描的时候这个Service被扫描到  
public class ContractServiceImpl implements ContractService{  
    
    @Autowired  
    ContractDao contractDao;

	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	public List<ContractVo> findContractList(Map map) {
		return contractDao.findContractList(map);
	}

	public void insert(Contract contract) {
		contractDao.insert(contract);
	}

	public void insertMiddleTab(MiddleTab middelTab) {
		contractDao.insertMiddleTab(middelTab);
	}

	public void update(Contract contract) {
		contractDao.update(contract);
	}

	public void deleteById(Serializable contractId) {
		//1.删除合同基本信息
		contractDao.deleteById(contractId);
		//2.删除中间表以合同id为外键的所有货物关联信息
		contractDao.deleteMiddleTab(contractId);
	}

	public void deleteMiddleTab(Serializable contractId) {
		contractDao.deleteMiddleTab(contractId);
	}
	public int count(Map map) {
		return contractDao.count(map);
	}
	public String getMaxBarCode() {
		return contractDao.getMaxBarCode();
	}
	public void insert(Contract contract, String[] commoditiesIdArrays,
			String[] priceArrays) {
		contractDao.insert(contract);
		//保存中间表信息
		for (int i = 0; i < commoditiesIdArrays.length; i++) {
			MiddleTab middleTab = new MiddleTab();
			middleTab.setMiddleId(UUID.randomUUID().toString());//中间表的ID
			middleTab.setContractId(contract.getContractId());//关联的合同ID
			middleTab.setFruitId(commoditiesIdArrays[i]);//关联的货物ID
			int number = Integer.parseInt(priceArrays[i].equals("")?"0":priceArrays[i]);
			middleTab.setNumber(number);//货物数量
			this.insertMiddleTab(middleTab);
		}
	}
    
}
