package com.fruitsalesplatform.entity;

import java.util.List;

public class Contract {
	private String contractId;
	private String barCode;
	private int type;
	private String createTime;
	private Retailer retailer;
	private List<CommoditiesVo> commoditiesList;
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Retailer getRetailer() {
		return retailer;
	}
	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}
	public List<CommoditiesVo> getCommoditiesList() {
		return commoditiesList;
	}
	public void setCommoditiesList(List<CommoditiesVo> commoditiesList) {
		this.commoditiesList = commoditiesList;
	}
}
