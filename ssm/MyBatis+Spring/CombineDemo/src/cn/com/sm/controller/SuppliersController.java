package cn.com.sm.controller;

import cn.com.sm.po.Result;
import cn.com.sm.po.Supplier;
import cn.com.sm.service.impl.SuppliersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SuppliersController {

    @Autowired
    private SuppliersServiceImpl suppliersService;

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json")
    public List<Supplier> findAll(){
        try {
            return suppliersService.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public List<Supplier> findById(@RequestParam(value = "id",required = false)String id){
        try {
            return suppliersService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insert(@RequestBody Supplier supplier){
        try{
            suppliersService.insert(supplier);
            return new Result(true,"insert successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"error from insert");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody Supplier supplier){
        try{
            suppliersService.update(supplier);
            return new Result(true,"update successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"error from update");
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody String ...ids){
        //待解：无法正常将报错信息反馈到客户端
        //通过检查id的存在情况，返回删除信息
        try{
            suppliersService.delete(ids);
            return new Result(true,"delete successfully");
        }catch (Exception e){
            return new Result(false,"error from delete");
        }
    }
}