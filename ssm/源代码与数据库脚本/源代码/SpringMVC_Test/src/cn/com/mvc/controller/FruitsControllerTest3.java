package cn.com.mvc.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;

//注解的Handler类  
//使用@Controller来标识它是一个控制器  
@Controller
public class FruitsControllerTest3{

	private FruitsService fruitsService = new FruitsServiceImpl();
	
	//商品查询列表  
    //@RequestMapping实现 对queryFruitsList方法和url进行映射，一个方法对应一个url  
    //一般建议将url和方法写成一样  
    @RequestMapping(value="/queryFruitsList")
	public ModelAndView  queryFruitsList() throws Exception {
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
    
    //下面还可以定义增删改的URL映射方法
    @RequestMapping(value="/queryFruit",method={RequestMethod.GET})  
    public String queryFruitById(Model model,@RequestParam(value="fruit_id") Integer fruitId)throws Exception{  
              
        //调用service获取水果商品列表 
    	Fruits fruit=fruitsService.queryFruitById(fruitId);   
              
        //通过形参中的model将model数据传到页面  
        //相当于modelAndView.addObject方法  
        model.addAttribute("fruit", fruit);  
              
        return "/fruits/fruitsDetail";  
    }  
    
    @RequestMapping(value="/queryFruit/{id}",method={RequestMethod.GET})  
    public @ResponseBody Fruits getFruitById(Model model,@PathVariable("id")Integer fruitId)
    		throws Exception{  
        //调用service获取水果商品列表 
    	Fruits fruit=fruitsService.queryFruitById(fruitId);   
        return fruit;  
    }  
    
    @RequestMapping(value="/addFruit",method={RequestMethod.POST})  
    public String addFruit(Model model,Fruits fruit)
    		throws Exception{   
         //具体添加逻辑  
    	return "...";
    } 
    
    @RequestMapping(value="/deleteFruit/{id}",method={RequestMethod.DELETE})  
    public String deleteFruitById(Model model,@PathVariable("id")Integer fruitId)
    		throws Exception{  
        //具体删除逻辑
    	return "...";
    } 
    
    @RequestMapping(value="/editFruit",method={RequestMethod.PUT})  
    public String editFruitById(Model model,Fruits fruit)
    		throws Exception{  
        //具体修改逻辑 
        return "...";  
    } 
}