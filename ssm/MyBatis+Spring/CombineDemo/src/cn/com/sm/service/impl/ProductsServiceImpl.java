package cn.com.sm.service.impl;

import cn.com.sm.mapper.ProductsMapper;
import cn.com.sm.po.Product;
import cn.com.sm.po.Result;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsServiceImpl implements BaseService<Product> {
    @Autowired
    private ProductsMapper productsMapper;
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
            productsMapper.insert(product);
            return new Result(true,
                    "insert [" + product.getPid() +
                            "] in products successfully");
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
            productsMapper.update(product);
            return new Result(true,
                    "update [" + product.getPid() +
                            "] in products successfully");
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
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,
                    "error from delete on products\n" +
                            e.getMessage());
        }
    }

    @Override
    public String checkFormat(Product product) {
        return null;
    }
}
