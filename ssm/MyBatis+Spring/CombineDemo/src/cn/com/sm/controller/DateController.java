package cn.com.sm.controller;

import cn.com.sm.service.impl.TimeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateController {
    @Autowired
    private TimeServiceImpl timeService;
    @RequestMapping(value = "/date",method = RequestMethod.GET)
    public Integer getRemainingTime(){
        return timeService.getRemainingTime();
    }
}
