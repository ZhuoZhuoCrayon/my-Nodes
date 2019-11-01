package com.fruitsalesplatform.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fruitsalesplatform.dao.UserDao;
import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.UserService;

@Service  //为了包扫描的时候这个Service被扫描到  
public class UserServiceImpl implements UserService{  
    
    @Autowired  
    UserDao userDao;

	public User get(Serializable id) {
		return userDao.get(id);
	}
	public List<User> find(Map map) {
		return userDao.find(map);
	} 
	public void insert(User user) {
		userDao.insert(user);
	}
	public void update(User user) {
		userDao.update(user);
	}
	public void deleteById(Serializable id) {
		userDao.deleteById(id);
	}
	public void delete(Serializable[] ids) {
		userDao.delete(ids);
	}
}
