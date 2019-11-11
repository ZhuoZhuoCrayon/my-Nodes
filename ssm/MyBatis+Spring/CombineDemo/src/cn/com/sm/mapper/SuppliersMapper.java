package cn.com.sm.mapper;

import cn.com.sm.po.Supplier;

import java.util.List;

public interface SuppliersMapper {
    List<Supplier> findAll() throws Exception;
    List<Supplier> findById(String id) throws Exception;
    void insert(Supplier supplier) throws Exception;
    void update(Supplier supplier) throws Exception;
    void delete(String id) throws Exception;
}
