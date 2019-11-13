package cn.com.sm.service.impl;

import cn.com.sm.mapper.SuppliersMapper;
import cn.com.sm.po.Result;
import cn.com.sm.po.Supplier;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuppliersServiceImpl implements BaseService<Supplier> {
    @Autowired
    private SuppliersMapper suppliersMapper;
    @Override
    public List<Supplier> findAll() {
        try{
            return suppliersMapper.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Supplier> findById(String id){
        try{
            return suppliersMapper.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Result insert(Supplier supplier) {
        try{
            String formatInfo = checkFormat(supplier);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(supplier.getSid()).size()!=0){
                return new Result(false,
                        "sid:[" + supplier.getSid() + "]existed");
            }else{
                suppliersMapper.insert(supplier);
                return new Result(true,
                        "insert [" + supplier.getSid() +
                                "] in suppliers successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"insert[" +
                    supplier.getSid() + "] in suppliers Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result update(Supplier supplier){
        try{
            String formatInfo = checkFormat(supplier);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(supplier.getSid()).size()==0){
                return new Result(false,
                        "sid:[" + supplier.getSid() + "] not existed");
            }else {
                suppliersMapper.update(supplier);
                return new Result(true,
                        "update [" + supplier.getSid() +
                                "] in suppliers successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update[" +
                    supplier.getSid() + "] in suppliers Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result delete(String... ids){
        try {
            for (String id : ids) {
                if (suppliersMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in suppliers");
                }
            }
            String idStr = "";
            for(String id : ids){
                suppliersMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in suppliers successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,
                    "error from delete on suppliers\n" +
                            e.getMessage());
        }
    }

    @Override
    public String checkFormat(Supplier supplier) {
        if(supplier.getSid()==null||supplier.getSid().length()>2){
            return "sid is null or too long";
        }else if(supplier.getSname()==null||supplier.getSname().length()>15){
            return "sname is null or too long";
        }else if(supplier.getCity()!=null&&supplier.getCity().length()>15){
            return "city too long";
        }else if(supplier.getTelephone_no()!=null&&supplier.getCity().length()>10){
            return "telephone_no too long";
        }
        return "pass";
    }
}
