package cn.com.sm.mapper;

import cn.com.sm.po.MonthlySale;
import cn.com.sm.po.Product;

import java.util.List;

public interface ProductsMapper {
    List<Product> findAll() throws Exception;
    List<Product> findById(String id) throws Exception;
    List<MonthlySale> reportMonthlySale(String id) throws Exception;
    void insert(Product product) throws Exception;
    void update(Product product) throws Exception;
    void delete(String id) throws Exception;
}
