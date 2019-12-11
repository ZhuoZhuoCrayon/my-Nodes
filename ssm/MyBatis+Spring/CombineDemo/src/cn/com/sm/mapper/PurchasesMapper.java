package cn.com.sm.mapper;

import cn.com.sm.po.Purchase;
import org.apache.shiro.crypto.hash.Hash;

import java.util.HashMap;
import java.util.List;

public interface PurchasesMapper {
    List<Purchase> findAll() throws Exception;
    List<Purchase> findById(Integer id) throws Exception;
    HashMap<String,Object> trade(Purchase purchase) throws Exception;
    void insert(Purchase purchase) throws Exception;
    void update(Purchase purchase) throws Exception;
    void delete(Integer id) throws Exception;
}
