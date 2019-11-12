package cn.com.sm.controller;

import cn.com.sm.po.Employee;
import cn.com.sm.po.Result;
import cn.com.sm.service.impl.EmployeesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 标志该方法的返回值（更正，原文是return type，看起来应该是返回值）
 * 应该被直接写回到HTTP响应体中去（而不会被被放置到Model中或被解释为一个视图名）
 */
@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private  EmployeesServiceImpl employeesService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    public List<Employee> findAll(){
        return employeesService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public List<Employee> findById(@RequestParam(value = "id",required = false)String id){
        return employeesService.findById(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestBody Employee employee){
        return employeesService.insert(employee);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Employee employee){
        return employeesService.update(employee);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody String ...ids){
        //待解：无法正常将报错信息反馈到客户端
        //通过检查id的存在情况，返回删除信息
        return employeesService.delete(ids);
    }
}
