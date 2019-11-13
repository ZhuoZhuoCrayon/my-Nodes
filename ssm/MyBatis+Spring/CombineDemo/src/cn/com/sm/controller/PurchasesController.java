package cn.com.sm.controller;

import cn.com.sm.po.Purchase;
import cn.com.sm.po.Result;
import cn.com.sm.service.impl.PurchasesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {

    @Autowired
    private PurchasesServiceImpl purchasesService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    public List<Purchase> findAll(){
        return purchasesService.findAll();
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public List<Purchase> findById(@RequestParam(value = "id",required = false)Integer id){
        return purchasesService.findById(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestBody Purchase purchase){
        return purchasesService.insert(purchase);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Purchase purchase){
        return purchasesService.update(purchase);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody Integer ...ids){
        //待解：无法正常将报错信息反馈到客户端
        //通过检查id的存在情况，返回删除信息
        return purchasesService.delete(ids);
    }
}