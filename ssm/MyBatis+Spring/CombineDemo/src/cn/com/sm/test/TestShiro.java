package cn.com.sm.test;

import cn.com.sm.po.company_sys.User;
import cn.com.sm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring/*.xml"})
public class TestShiro {

    @Autowired
    private UserService userService;

    @Test
    public void changePasswordTest(){
        userService.changePassword(1L,"admin");
        userService.changePassword(2L,"user");
    }

    @Test
    public void initUserTest(){
        /*userService.update(new User(1L,"admin",null,null));
        userService.update(new User(2L,"user",null,null));*/
        //userService.insert(new User(3L,"ylx","ylx",null));

    }
}
