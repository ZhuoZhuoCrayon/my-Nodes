package com.fruitsalesplatform.test.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fruitsalesplatform.test.dao.TestDao;
import com.fruitsalesplatform.test.entity.User;

@Repository //为了包扫描的时候这个Dao被扫描到  
public class TestDaoImpl implements TestDao{
	
	@Autowired //注入sqlSessionFactory
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession=null;
	
	private SqlSession getSqlSession(){
		if(sqlSession==null){
			sqlSession=sqlSessionFactory.openSession();
		}
		return sqlSession;
	}
	
	public List<User> findUserByName(User user) {
		List<User> uList = getSqlSession().selectList("test.findUserByName", "%"+user.getName()+"%");  
		return uList;
	}
}
