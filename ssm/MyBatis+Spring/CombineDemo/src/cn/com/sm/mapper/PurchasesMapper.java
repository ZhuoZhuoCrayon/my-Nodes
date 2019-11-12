package cn.com.sm.mapper;

import cn.com.sm.po.Purchase;

import java.util.List;

public interface PurchasesMapper {
    List<Purchase> findAll() throws Exception;
    List<Purchase> findById(Integer id) throws Exception;
    void insert(Purchase purchase) throws Exception;
    void update(Purchase purchase) throws Exception;
    void delete(Integer id) throws Exception;
}
