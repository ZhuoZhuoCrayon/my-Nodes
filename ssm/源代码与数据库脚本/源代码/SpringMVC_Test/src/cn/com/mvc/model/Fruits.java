package cn.com.mvc.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cn.com.mvc.validator.group.FruitsGroup1;
import cn.com.mvc.validator.group.FruitsGroup2;

public class Fruits {
	private int id;//id主键
	@Size(min=1,max=20,message="{fruits.name.length.error}",groups={FruitsGroup1.class})  
    private String name; //水果名
    private double price; //价格
    @NotEmpty(message="{fruits.producing_area.isEmpty}",groups={FruitsGroup2.class})  
    private String producing_area; //产地
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
	public String getProducing_area() {
		return producing_area;
	}
	public void setProducing_area(String producing_area) {
		this.producing_area = producing_area;
	}
}
