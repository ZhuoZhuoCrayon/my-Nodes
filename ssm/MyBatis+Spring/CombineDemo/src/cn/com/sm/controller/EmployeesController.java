package cn.com.sm.controller;

import cn.com.sm.po.Employee;
import cn.com.sm.service.impl.EmployeesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
    @Autowired
    private  EmployeesServiceImpl employeesService;


    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<Employee> findAll() throws Exception {
        return employeesService.findAll();
    }
}
