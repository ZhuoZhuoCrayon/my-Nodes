package cn.com.sm.mapper;

import cn.com.sm.po.User;

public interface UserQueryMapper {
	//根据Id查询用户信息  
	  public User findUserById(int id) throws Exception;  
}
