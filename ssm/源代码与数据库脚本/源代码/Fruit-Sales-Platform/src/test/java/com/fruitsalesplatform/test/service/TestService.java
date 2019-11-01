package com.fruitsalesplatform.test.service;

import java.util.List;

import com.fruitsalesplatform.test.entity.User;

public interface TestService {
	public List<User> findUserByName(User user); 
}
