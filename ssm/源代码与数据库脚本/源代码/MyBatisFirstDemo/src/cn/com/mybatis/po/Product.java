package cn.com.mybatis.po;

import java.util.List;

public class Product {
    //商品id
    private int pid;
    //商品名称
    private String pname;
    //给商品点赞的用户
    private List<User> users;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
		
}
