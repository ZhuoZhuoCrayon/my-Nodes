package cn.com.mybatis.mapper;

import cn.com.mybatis.po.Customer;
public interface CustomerMapper {
	//根据Id查询用户信息  
    public Customer findCustomerById(int id) throws Exception;  
      
    //添加用户信息  
    public void insertCustomer(Customer customer) throws Exception;  
      
    //删除用户信息  
    public void deleteCustomer(int id) throws Exception;  
      
    //修改用户信息  
    public void updateCustomerAcNo(Customer customer) throws Exception;  
}
