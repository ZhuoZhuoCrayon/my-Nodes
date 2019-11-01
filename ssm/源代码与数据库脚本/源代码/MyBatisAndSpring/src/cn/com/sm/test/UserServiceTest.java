package cn.com.sm.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.sm.dao.UserDao;
import cn.com.sm.po.User;

public class UserServiceTest {
	private ApplicationContext applicationContext;  
    
	//在执行测试方法之前首先获Spring配置文件对象
    //注解Before是在执行本类所有测试方法之前先调用这个方法  
    @Before  
    public void setup() throws Exception{  
        applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");  
    }  
      
    @Test  
    public void testFindUserById() throws Exception{  
    	//通过配置资源对象获取userDao对象
        UserDao userDao=(UserDao)applicationContext.getBean("userDao");  
        //调用UserDao的方法  
        User user=userDao.findUserById(1);  
        //输出用户信息  
        System.out.println(user.getId()+":"+user.getUsername());  
    }  
}  