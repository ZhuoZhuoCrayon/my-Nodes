package cn.com.sm.controller.company_sys;

import cn.com.sm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public HashMap<String,String> getUser(){
        HashMap<String,String> result = new HashMap<>();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        result.put("username",username);
        result.put("role",(String)userService.getRoles(username).toArray()[0]);
        return result;
    }
}
