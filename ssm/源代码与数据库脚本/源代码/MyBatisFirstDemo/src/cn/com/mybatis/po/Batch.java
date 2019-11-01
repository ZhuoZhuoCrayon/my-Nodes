package cn.com.mybatis.po;

import java.util.Date;
import java.util.List;
public class Batch {
	private int batch_id;
	private int cus_id;  
	private String number; 
	private Date createtime;
	private String note;
	private List<BatchDetail> batchDetials;
	public int getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<BatchDetail> getBatchDetials() {
		return batchDetials;
	}
	public void setBatchDetials(List<BatchDetail> batchDetials) {
		this.batchDetials = batchDetials;
	}
	
}
