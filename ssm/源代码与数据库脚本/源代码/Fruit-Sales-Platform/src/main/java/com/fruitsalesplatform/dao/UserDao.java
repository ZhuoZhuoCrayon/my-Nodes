package com.fruitsalesplatform.dao;

import com.fruitsalesplatform.entity.User;

public interface UserDao extends BaseDao<User>{  
    //我们这里可以直接使用继承的BaseDao的增删改查方法  
	//以后还可以在添加新的方法定义
}  
