package cn.com.sm.mapper;

import cn.com.sm.po.Customer;

import java.util.List;

public interface CustomersMapper {

    List<Customer> findAll() throws Exception;
    List<Customer> findById(String id) throws Exception;
    void insert(Customer employee) throws Exception;
    void update(Customer employee) throws Exception;
    void delete(String id) throws Exception;
}
