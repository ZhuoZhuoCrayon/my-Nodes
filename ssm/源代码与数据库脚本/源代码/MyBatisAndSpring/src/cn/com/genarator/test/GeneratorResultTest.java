package cn.com.genarator.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.sm.mapper.UserMapper;
import cn.com.sm.po.User;
import cn.com.sm.po.UserExample;

public class GeneratorResultTest {
	private static ApplicationContext applicationContext;  
    private static UserMapper userMapper;  
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws ParseException {
    	applicationContext =
    			new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");  
    	userMapper=(UserMapper)applicationContext.getBean("userMapper");
    	
    	//1.测试插入操作
    	User user1 = new User();
    	user1.setUsername("李磊磊");
    	user1.setPassword("123qwe");
    	user1.setGender("男");
    	user1.setBirthday(sdf.parse("1992-01-01"));
    	user1.setProvince("云南");
    	user1.setCity("大理");
    	user1.setEmail("lileilei@126.com");
    	userMapper.insert(user1);
    	System.out.println("1.插入了名为："+user1.getUsername()+"的用户");
    	
    	//2.测试查询操作(自定义查询)
    	UserExample userExample=new UserExample();  
        //通过Criteria构造查询条件  
    	UserExample.Criteria criteria=userExample.createCriteria();  
    	//查询条件1：username equal '李磊磊'
        criteria.andUsernameEqualTo("李磊磊");
        //查询条件2：gender <> '女'
        criteria.andGenderNotEqualTo("女");
        //查询条件3：brithday between '1990-01-01' and '1994-01-01'
        criteria.andBirthdayBetween(sdf.parse("1990-01-01"), sdf.parse("1994-01-01"));
        //查询条件4：email is not null
        criteria.andEmailIsNotNull();
        //可能返回多条记录  
        List<User> list=userMapper.selectByExample(userExample);  
        for (int i = 0; i < list.size(); i++) {  
            User uItem=list.get(i);  
            System.out.println(uItem.getId()+":"+uItem.getUsername());  
        } 
        
        //3.测试查询操作(主键id查询)
        User user2=userMapper.selectByPrimaryKey(1);  
        System.out.println("3.主键查询出id为1的用户，名为"+user2.getUsername());  
        
        //4.测试修改操作(对所有字段进行更新)
        //对所有字段进行更新，需要先查询出来再更新  
        User user3 = userMapper.selectByPrimaryKey(1);      
        user3.setEmail("zhangsan@126.com");      
        userMapper.updateByPrimaryKey(user3);  
        System.out.println("4.更新id为"+user3.getId()+"的用户的所有信息");
         
        //5.测试修改操作(对个别字段进行更新)
        //如果传入字段不空为才更新，在批量更新中使用此方法，不需要先查询再更新  
        User user4 = new User();
        //只修改用户的Email信息
        user4.setId(1);
        user4.setEmail("zhangsan@126.com");  
        userMapper.updateByPrimaryKeySelective(user4); 
        System.out.println("5.更新id为"+user4.getId()+"的用户Email为:"
				+user4.getEmail());
        
        //6.测试删除操作
        int deleteId = 5;
        userMapper.deleteByPrimaryKey(deleteId);
        User user5=userMapper.selectByPrimaryKey(deleteId);
        if(user5==null){
        	System.out.println("6.删除id为"+deleteId+"的用户成功，删除成功");
        }
	}
}
