package cn.com.sm.service.impl;

import cn.com.sm.mapper.PurchasesMapper;
import cn.com.sm.po.Purchase;
import cn.com.sm.po.Result;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasesServiceImpl implements BaseService<Purchase> {
    @Autowired
    private PurchasesMapper purchasesMapper;
    @Override
    public List<Purchase> findAll(){
        try{
            return purchasesMapper.findAll();
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Purchase> findById(String id){
        return new ArrayList<>();
    }

    //重构findById--由于id类型改变
    public List<Purchase> findById(Integer id){
        try{
            return purchasesMapper.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Result insert(Purchase purchase){
        try{
            purchasesMapper.insert(purchase);
            return new Result(true,
                    "insert [" + purchase.getPurid() +
                            "] in purchases successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"insert[" +
                    purchase.getPurid() + "] in purchases Failed\n" + e.getMessage());
        }

    }

    @Override
    public Result update(Purchase purchase){
        //purchasesMapper.update(purchase);
        try{
            purchasesMapper.update(purchase);
            return new Result(true,
                    "update [" + purchase.getPid() +
                            "] in purchases successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update[" +
                    purchase.getPid() + "] in purchases Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result delete(String... ids){
        return null;
    }

    @Override
    public String checkFormat(Purchase purchase) {
        return null;
    }


    //重构delete--由于id类型改变
    public Result delete(Integer ...ids) throws Exception{
        try {
            for (Integer id : ids) {
                if (purchasesMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in purchases");
                }
            }
            String idStr = "";
            for(Integer id : ids){
                purchasesMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in purchases successfully");
        }catch (Exception e){
            return new Result(false,
                    "error from delete on purchases\n" +
                            e.getMessage());
        }
    }
}
