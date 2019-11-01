package cn.com.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.mvc.model.Fruits;
import cn.com.mvc.model.ListQryModel;
import cn.com.mvc.model.MapQryModel;
import cn.com.mvc.model.UserAndProductQryModel;
import cn.com.mvc.service.FruitsService;
import cn.com.mvc.service.FruitsServiceImpl;
import cn.com.mvc.validator.group.FruitsGroup1;

@Controller
@RequestMapping("query")
public class FindControllerTest {
	
	private FruitsService fruitsService = new FruitsServiceImpl();
	
	@RequestMapping("queryAllFruits")
    public String queryAllFruits(Model model,Fruits fruits,int type){
		List<Fruits> findList=fruitsService.queryFruitsList();
		model.addAttribute("fruitsList", findList);
		return "/fruits/FruitsList"+type;
	}
	
	@RequestMapping("queryFruitsByCondition")
    public String queryFruitsByCondition(Model model,@Validated(value=FruitsGroup1.class) Fruits fruits,
    		BindingResult bindingResult){
		//Fruits fruits=request.getParameter("fruits");
		List<ObjectError> allErrors = null;
		//获取校验错误信息  
        if(bindingResult.hasErrors()){  
            allErrors=bindingResult.getAllErrors();  
            for(ObjectError objectError:allErrors){  
                //输出错误信息  
                System.out.println(objectError.getDefaultMessage());  
            }  
        }  
		List<Fruits> findList=null;
		if(fruits==null||
				(fruits.getName()==null&&fruits.getProducing_area()==null)){
			//如果fruits或查询条件为空，默认查询所有
			findList=fruitsService.queryFruitsList();
		}else{
			//如果fruits查询条件不为空，按条件查询
			findList=fruitsService.queryFruitsByCondition(fruits);
		}
    	//将model数据传到页面
        model.addAttribute("fruitsList", findList);
        //response.sendRedirect("home.action");
        //将错误传到页面  
        if(allErrors!=null){
        	model.addAttribute("allErrors",allErrors); 
        }
    	return "/fruits/findFruits";
    }
	
	@RequestMapping("queryUserFruitsByCondition")
    public String queryUserFruitsByCondition(Model model,UserAndProductQryModel ufruits){
		String name=ufruits.getUserFruits().getName();//获取搜索信息
		System.out.println("name="+name);
		//下面处理逻辑省略
		return "/fruits/findFruits";
	}
	
	@RequestMapping("fruitsArrayTest")
    public void FruitsArray(Model model,int[] fids){
		for (int i = 0; i < fids.length; i++) {
			System.out.println("fids["+i+"]="+fids[i]);
		}
	}
	
	@RequestMapping("fruitsListTest")
    public void FruitsList(Model model,ListQryModel listQryModel){
		List<Fruits> fruitList=listQryModel.getFruitList();
		for (int i = 0; i < fruitList.size(); i++) {
			System.out.println("fruitList["+i+"].name="+fruitList.get(i).getName());
		}
	}
	
	@RequestMapping("fruitsMapTest")
    public void FruitsMap(Model model,MapQryModel MapQryModel){
		Map<String,Object> fruitMap=MapQryModel.getFruitMap();
		for(String key:fruitMap.keySet()){
			System.out.println("fruitMap["+key+"]="+fruitMap.get(key));
		}
	}
}
