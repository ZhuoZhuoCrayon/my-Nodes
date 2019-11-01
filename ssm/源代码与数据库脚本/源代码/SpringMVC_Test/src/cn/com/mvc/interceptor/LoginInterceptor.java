package cn.com.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    String uri=request.getRequestURI();  
	    //判断当前请求地址是否是登录地址  
	    if(!(uri.contains("Login")||uri.contains("login"))){  
	        //非登录请求  
	        if(request.getSession().getAttribute("user")!=null){  
	            //说明已经登录过，放行 
	            return true;
	        }else{  
	            //没有登录,跳转到登录界面  
	            response.sendRedirect(request.getContextPath()+"/user/toLogin.action");  
	        }  
	    }else{  
	        //登录请求，直接放行  
	    	return true; 
	    }  
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}
}
