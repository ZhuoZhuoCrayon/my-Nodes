package cn.com.mybatis.po;

import java.util.Date;

public class FinacialProduct {
    private int id;
    private String name;
    private double price;
    private String detail;
    private String imgpath;
    private Date invattime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public Date getInvattime() {
		return invattime;
	}
	public void setInvattime(Date invattime) {
		this.invattime = invattime;
	}
    
}
