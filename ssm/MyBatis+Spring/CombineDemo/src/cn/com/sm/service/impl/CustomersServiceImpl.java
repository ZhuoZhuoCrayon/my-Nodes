package cn.com.sm.service.impl;

import cn.com.sm.mapper.CustomersMapper;
import cn.com.sm.po.Customer;
import cn.com.sm.po.Result;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomersServiceImpl implements BaseService<Customer> {

    @Autowired
    private CustomersMapper customersMapper;


    @Override
    public List<Customer> findAll() {
        try {
            return customersMapper.findAll();
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Customer> findById(String id){
        try {
            return customersMapper.findById(id);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public Result insert(Customer customer){
        try {
            String formatInfo = checkFormat(customer);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(customer.getCid()).size()!=0){
                return new Result(false,
                        "cid:[" + customer.getCid() + "]existed");
            }else{
                customersMapper.insert(customer);
                return new Result(true,
                        "insert [" + customer.getCid() +
                        "] in customers successfully");
            }

        }catch (Exception e){
            //System.out.println(e.toString());
            e.printStackTrace();
            return new Result(false,"insert[" +
                    customer.getCid() + "] in customers Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result update(Customer customer){
        try{
            String formatInfo = checkFormat(customer);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(customer.getCid()).size()==0){
                return new Result(false,
                        "cid:[" + customer.getCid() +
                                "] not existed");
            }else{
                customersMapper.update(customer);
                return new Result(true,
                        "update [" + customer.getCid() +
                                "] in customers successfully");
            }
        }catch (Exception e){
            //System.out.println(e.toString());
            e.printStackTrace();
            return new Result(false,"update[" +
                    customer.getCid() + "] in customers Failed\n" + e.getMessage());
        }

    }

    @Override
    public Result delete(String... ids){
        try {
            for (String id : ids) {
                if (customersMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in customers");
                }
            }
            String idStr = "";
            for(String id : ids){
                customersMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in customers successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,
                    "error from delete on customer\n" +
                    e.getMessage());
        }
    }

    @Override
    public String checkFormat(Customer customer) {
        if(customer.getCid()==null||customer.getCid().length()>4){
            return "cid is null or length over 4";
        }else if(customer.getCname()!=null&&customer.getCname().length()>15){
            return "cname length over 15";
        }else if(customer.getCity()!=null&&customer.getCity().length()>15){
            return "city length over 15";
        }else if(customer.getVisits_made()!=null&&
                customer.getVisits_made().toString().length()>5){
            return "visits_made over limited";
        }
        return "pass";
    }
}
