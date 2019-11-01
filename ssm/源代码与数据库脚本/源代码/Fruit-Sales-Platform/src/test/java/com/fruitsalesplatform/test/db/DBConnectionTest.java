package com.fruitsalesplatform.test.db;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fruitsalesplatform.test.entity.User;

public class DBConnectionTest {
	//mybatis配置文件  
    private String resource="beans.xml";   
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession = null;

    public SqlSession getSqlSession() throws Exception {
    	//获取Spring类加载配置对象
    	ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(resource);
    	//从配置对象中创建会话工厂，并注入mybatis配置文件的信息  
    	sqlSessionFactory = (SqlSessionFactory) context.getBean("sessionFactory");
	    sqlSession=sqlSessionFactory.openSession();
	    return sqlSession;
	}
    
    @Test
    public void TestSelect()throws Exception{
    	sqlSession = getSqlSession();
    	User user = sqlSession.selectOne("test.findUserByName", "张三");
    	System.out.println("取出的用户信息：");
    	System.out.println("账号："+user.getUsername());
    	System.out.println("密码："+user.getPassword());
    	System.out.println("姓名："+user.getName());
    	System.out.println("电话："+user.getTelphone());
    }
}
