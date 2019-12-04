package cn.com.sm.service.impl;

import cn.com.sm.mapper.ProductsMapper;
import cn.com.sm.po.Log;
import cn.com.sm.po.Product;
import cn.com.sm.po.Result;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductsServiceImpl implements BaseService<Product> {
    @Autowired
    private ProductsMapper productsMapper;
    @Autowired
    private LogsServiceImpl logsService;
    @Override
    public List<Product> findAll(){
        try{
            return productsMapper.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Product> findById(String id){
        try{
            return productsMapper.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Result insert(Product product){
        try{
            String formatInfo = checkFormat(product);
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(findById(product.getPid()).size()!=0){
                return new Result(false,
                        "pid:[" + product.getPid() + "] existed");
            }else{
                productsMapper.insert(product);
                return new Result(true,
                        "insert [" + product.getPid() +
                                "] in products successfully");
            }
        }catch (Exception e){
            //System.out.println(e.toString());
            e.printStackTrace();
            return new Result(false,"insert[" +
                    product.getPid() + "] in products Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result update(Product product){
        try{
            String formatInfo = checkFormat(product);
            List<Product> products = findById(product.getPid());
            if(!formatInfo.equals("pass")){
                return new Result(false,formatInfo);
            }else if(products.size()==0){
                return new Result(false,
                        "pid:[" + product.getPid() + "] not existed");
            }else{
                int oldQoh = products.get(0).getQoh();
                productsMapper.update(product);
                //更新了qoh
                if(oldQoh!=product.getQoh()){
                    Log log = new Log(null,"system",new Date(),
                            "products","update",product.getPid());
                    logsService.insert(log);
                }
                return new Result(true,
                        "update [" + product.getPid() +
                                "] in products successfully");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update[" +
                    product.getPid() + "] in products Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result delete(String... ids){
        try {
            for (String id : ids) {
                if (productsMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in products");
                }
            }
            String idStr = "";
            for(String id : ids){
                productsMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in products successfully");
        }catch(DataIntegrityViolationException dataDependency){
            return new Result(false,
                    "cannot delete  a parent row:" +
                            "FOREIGN KEY('pid') REFERENCES products('pid')");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,
                    "error from delete on products\n" +
                            e.getMessage());
        }
    }

    @Override
    public String checkFormat(Product product) {
        if(product.getPid()==null||product.getPid().length()>4){
            return "pid is null or too long";
        }else if(product.getPname()==null||product.getPname().length()>15){
            return "pname is null or too long";
        }else if(product.getQoh()==null){
            return "qoh is null";
        }else if(product.getSid()==null||product.getSid().length()>2){
            return "sid is null or too long";
        }
        return "pass";
    }
}
