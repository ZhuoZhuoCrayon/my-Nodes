package cn.com.sm.controller;

import cn.com.sm.po.Purchase;
import cn.com.sm.po.Result;
import cn.com.sm.service.impl.PurchasesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {

    @Autowired
    private PurchasesServiceImpl purchasesService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    public List<Purchase> findAll(){
        try {
            return purchasesService.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public List<Purchase> findById(@RequestParam(value = "id",required = false)Integer id){
        try {
            return purchasesService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestBody Purchase purchase){
        try{
            purchasesService.insert(purchase);
            return new Result(true,"insert successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"error from insert");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Purchase purchase){
        try{
            purchasesService.update(purchase);
            return new Result(true,"update successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"error from update");
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody Integer ...ids){
        //待解：无法正常将报错信息反馈到客户端
        //通过检查id的存在情况，返回删除信息
        try{
            purchasesService.delete(ids);
            return new Result(true,"delete successfully");
        }catch (Exception e){
            return new Result(false,"error from delete");
        }
    }
}