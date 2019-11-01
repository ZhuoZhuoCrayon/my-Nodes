package cn.com.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;

public class FruitsControllerTest implements Controller{

	private FruitsService fruitsService = new FruitsServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//模拟Service获取水果商品列表  
        List<Fruits> fruitsList = fruitsService.queryFruitsList();  
  
        //返回ModelAndView  
        ModelAndView modelAndView =  new ModelAndView();  
        //相当 于request的setAttribut，在jsp页面中通过fruitsList取数据  
        modelAndView.addObject("fruitsList", fruitsList);  
          
        //指定视图  
        modelAndView.setViewName("/WEB-INF/jsp/fruits/fruitsList.jsp");  

        return modelAndView;  
	}

}