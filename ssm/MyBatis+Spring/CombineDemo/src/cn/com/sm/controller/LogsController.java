package cn.com.sm.controller;

import cn.com.sm.po.Log;
import cn.com.sm.po.Result;
import cn.com.sm.service.impl.LogsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    private LogsServiceImpl logsService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    public List<Log> findAll(){
        return logsService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public List<Log> findById(@RequestParam(value = "id",required = false)Integer id){
        return logsService.findById(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestBody Log log){
        return logsService.insert(log);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Log log){
        return logsService.update(log);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody Integer ...ids){
        //待解：无法正常将报错信息反馈到客户端
        //通过检查id的存在情况，返回删除信息
        return logsService.delete(ids);
    }
}