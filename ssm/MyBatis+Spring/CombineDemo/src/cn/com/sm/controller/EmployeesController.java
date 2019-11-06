package cn.com.sm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Employees")
public class EmployeesController {

    @RequestMapping("/index")
    public String test(){
        return "index";
    }
}
