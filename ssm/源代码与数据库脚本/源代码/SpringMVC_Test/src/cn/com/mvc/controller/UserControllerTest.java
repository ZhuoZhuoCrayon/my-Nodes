package cn.com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.mvc.exception.UserException;
import cn.com.mvc.model.User;

@Controller
@RequestMapping("user")
public class UserControllerTest {

   /* @InitBinder
    public void initBinder(DataBinder binder) {
       binder.setValidator(new UserValidator());
    }*/
    
    @RequestMapping("toLogin")
    public String toLoginPage() {
       //跳转至登录界面
       return "/user/login";
    }
 
    @RequestMapping("login")
    public String login(Model model,HttpServletRequest request, 
    		@Valid User user, BindingResult result) throws UserException, IOException {
    	
       //查询用户是为黑名单用户
       boolean isBlackUser = checkBlackList(user);
       //判断商品是否为空,根据id没有查到商品，提示用户商品信息并不存在  
       if(isBlackUser){  
           throw new UserException("user.not.have.power");  
       }  
    	
       //登录检测
       List<ObjectError> allErrors = null;
       if (result.hasErrors()){
    	   allErrors=result.getAllErrors();  
           for(ObjectError objectError:allErrors){  
               //输出错误信息  
               System.out.println("code="+objectError.getCode()+
            		   "  DefaultMessage="+objectError.getDefaultMessage());  
               //或将错误传到页面  
               model.addAttribute("allErrors",allErrors); 
           } 
    	   return "/user/login";
       }else{
    	   //检测账号密码，成功即登录成功
    	   boolean flag = checkUser(user);
    	   if(flag){
    		   //将用户信息放入session
    		   request.getSession().setAttribute("user", user);
    	   }else{
    		   model.addAttribute("errorMsg","账号或密码错误！"); 
    		   return "/user/login";
    	   }
       }
       return "/user/loginSuccess";
    }

	private boolean checkUser(User user) {
		if(user.getUsername().equals("zhangsan")
				&&user.getPassword().equals("qwe123")){
			return true;
		}
		return false;
	}

	private boolean checkBlackList(User user) {
		String blackArray [] = {"jack","tom","jean"};
		for (int i = 0; i < blackArray.length; i++) {
			if(user.getUsername().equals(blackArray[i])){
				return true;
			}
		}
		return false;
	}
   
}
