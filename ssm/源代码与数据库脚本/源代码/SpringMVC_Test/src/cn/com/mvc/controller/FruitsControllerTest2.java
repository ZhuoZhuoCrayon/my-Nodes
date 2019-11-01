package cn.com.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;

public class FruitsControllerTest2 implements HttpRequestHandler{

	private FruitsService fruitsService = new FruitsServiceImpl();
	
	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//模拟Service获取水果商品列表  
        List<Fruits> fruitsList = fruitsService.queryFruitsList();  
        //将fruitsList转换为json串
        String jsonInfo=convertListToJson(fruitsList);
        //设置返回格式
        response.setCharacterEncoding("utf-8");  
        response.setContentType("application/json;charset=utf-8");  
        //写出json串
        response.getWriter().write(jsonInfo);  
        //设置模型数据  
        //request.setAttribute("fruitsList",fruitsList);  
        //设置转发视图  
        //request.getRequestDispatcher("/WEB-INF/jsp/fruits/fruitsList.jsp").forward(request, response);  
	}

	private String convertListToJson(List<Fruits> fruitsList) {
 	    StringBuilder builder=new StringBuilder();
 	    builder.append('[');
 	    for(Fruits fruits:fruitsList){
 		   builder.append('{');
 		   builder.append("\"name\":\"").append(fruits.getName()).append("\",");
 		   builder.append("\"price\":\"").append(fruits.getPrice()).append("\",");
 		   builder.append("\"producing_area\":\"").append(fruits.getProducing_area()).append("\"");
 		   builder.append("},");
 	    }
 	    builder.deleteCharAt(builder.length()-1);
 	    builder.append(']');
		return builder.toString();
	}
}