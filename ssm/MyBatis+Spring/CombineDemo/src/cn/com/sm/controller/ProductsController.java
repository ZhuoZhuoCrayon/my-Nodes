package cn.com.sm.controller;

import cn.com.sm.po.Product;
import cn.com.sm.po.Result;
import cn.com.sm.service.impl.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsServiceImpl productsService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    public List<Product> findAll(){
        return productsService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public List<Product> findById(@RequestParam(value = "id",required = false)String id){
        return productsService.findById(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestBody Product product){
        return productsService.insert(product);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Product product){
        return productsService.update(product);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody String ...ids){
        //待解：无法正常将报错信息反馈到客户端
        //通过检查id的存在情况，返回删除信息
        return productsService.delete(ids);
    }
}