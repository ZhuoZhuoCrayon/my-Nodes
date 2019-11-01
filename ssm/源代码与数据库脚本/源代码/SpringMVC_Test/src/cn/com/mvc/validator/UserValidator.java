package cn.com.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cn.com.mvc.model.User;

public class UserValidator implements Validator {  
   
    public boolean supports(Class<?> clazz) {
       return User.class.equals(clazz);  
    }  
   
    public void validate(Object obj, Errors errors) {
    	ValidationUtils.rejectIfEmpty(errors, "username", "Username.is.empty", "用户名不能为空");  
        User user = (User) obj;  
        if (null == user.getPassword() || "".equals(user.getPassword())){
     	   //指定验证失败的字段名 ，错误码，默认错误信息
     	   errors.rejectValue("password", "Password.is.empty","密码不能为空");  
        }else if(user.getPassword().length()<6){
     	   //指定验证失败的字段名 ，错误码，默认错误信息
     	   errors.rejectValue("password", "length.too.short", "密码长度不得小于6位."); 
        }  
    }  
}  