package cn.com.sm.mapper;

import cn.com.sm.po.Customer;

import java.util.List;

public interface CustomersMapper {

    List<Customer> findAll();
    List<Customer> findById(String id);
    void insert(Customer employee);
    void update(Customer employee);
    void delete(String id);
}
