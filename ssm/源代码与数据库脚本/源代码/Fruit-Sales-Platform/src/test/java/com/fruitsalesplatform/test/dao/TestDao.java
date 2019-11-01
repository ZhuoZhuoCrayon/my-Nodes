package com.fruitsalesplatform.test.dao;

import java.util.List;

import com.fruitsalesplatform.test.entity.User;

public interface TestDao {
	public List<User> findUserByName(User user);   
}
