package cn.com.sm.controller;

import cn.com.sm.po.Result;
import cn.com.sm.po.Supplier;
import cn.com.sm.service.impl.SuppliersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SuppliersController {

    @Autowired
    private SuppliersServiceImpl suppliersService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    public List<Supplier> findAll(){
        return suppliersService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public List<Supplier> findById(@RequestParam(value = "id",required = false)String id){
        return suppliersService.findById(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestBody Supplier supplier){
        return suppliersService.insert(supplier);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Supplier supplier){
        return suppliersService.update(supplier);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody String ...ids){
        //待解：无法正常将报错信息反馈到客户端
        //通过检查id的存在情况，返回删除信息
        return suppliersService.delete(ids);
    }
}