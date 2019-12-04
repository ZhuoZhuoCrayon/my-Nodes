package cn.com.sm.service.impl;

import cn.com.sm.mapper.CustomersMapper;
import cn.com.sm.mapper.LogsMapper;
import cn.com.sm.mapper.ProductsMapper;
import cn.com.sm.mapper.PurchasesMapper;
import cn.com.sm.po.*;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasesServiceImpl implements BaseService<Purchase> {
    @Autowired
    private PurchasesMapper purchasesMapper;
    @Autowired
    private ProductsServiceImpl productsService;
    @Autowired
    private CustomersServiceImpl customersService;
    @Autowired
    private LogsServiceImpl logsService;

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
            String formatInfo = checkFormat(purchase);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(purchase.getPurid()).size()!=0){
                return new Result(false,
                        "purid:[" + purchase.getPurid() + "] existed");
            }else{

                List<Product> products = productsService.findById(purchase.getPid());
                List<Customer> customers = customersService.findById(purchase.getCid());
                //商品不存在
                if(products.size()==0){
                    return new Result(false,
                            "product:[" + purchase.getPurid() + "] not existed");
                }
                if(customers.size()==0){
                    return new Result(false,
                            "customer:[" + purchase.getCid() + "] not existed");
                }
                Product product = products.get(0);
                Customer customer = customers.get(0);
                String resMessage = "";

                int oldQoh = product.getQoh();
                //库存小于购买量
                if(product.getQoh()<purchase.getQty()){
                    return new Result(false,
                            "Qty[" + purchase.getQty() + "]" +
                            "> Qoh[" + product.getQoh() + "]");
                }

                //计算/校对价格
                purchase.setTotal_price(product.getOriginal_price() *
                        (1-product.getDiscnt_rate()) * purchase.getQty());
                //更新库存
                product.setQoh(product.getQoh()-purchase.getQty());

                //更新custom表的visits_made&klast_visit_time;
                customer.setVisits_made(customer.getVisits_made() + 1);
                customer.setLast_visit_time(purchase.getPtime());

                if(product.getQoh()<product.getQoh_threshold()){
                    //打印一条消息，指示产品的当前qoh
                    resMessage += "The product qoh is " + product.getQoh() + "\n\n";
                    //qoh低于qoh_threshold,将qoh设置为2 * old_qoh
                    product.setQoh(2 * oldQoh);
                    resMessage +=  "The product has been increased by " +
                                    (oldQoh + purchase.getQty()) + "\n\n";
                }


                productsService.update(product);
                customersService.update(customer);
                purchasesMapper.insert(purchase);

                //插入log
                Log log_pur = new Log(null,purchase.getEid(),purchase.getPtime(),
                        "purchases","insert",purchase.getPurid().toString());
                logsService.insert(log_pur);


                return new Result(true,
                         resMessage +
                                 "insert [" + purchase.getPurid() + "] in purchases successfully");
            }

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
            String formatInfo = checkFormat(purchase);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(purchase.getPurid()).size()==0){
                return new Result(false,
                        "purid:[" + purchase.getPurid() + "] not existed");
            }else{
                purchasesMapper.update(purchase);
                return new Result(true,
                        "update [" + purchase.getPurid() +
                                "] in purchases successfully");
            }

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
        if(purchase.getPurid()==null||purchase.getPurid().toString().length()>11){
            return "purid is null or too long";
        }else if(purchase.getCid()==null||purchase.getCid().length()>4){
            return "cid is null or too long";
        }else if(purchase.getEid()==null||purchase.getEid().length()>3){
            return "eid is null or too long";
        }else if(purchase.getPid()==null||purchase.getPid().length()>4){
            return "pid is null or too long";
        }
        return "pass";
    }


    //重构delete--由于id类型改变
    public Result delete(Integer ...ids){
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
