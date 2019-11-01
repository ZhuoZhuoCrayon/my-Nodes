package cn.com.sm.dao;

import cn.com.sm.po.User;
//用户管理的Dao接口  
public interface UserDao{  
  //根据Id查询用户信息  
  public User findUserById(int id) throws Exception;  
}  