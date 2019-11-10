package cn.com.sm.service.impl;

import cn.com.sm.mapper.CustomersMapper;
import cn.com.sm.po.Customer;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomersServiceImpl implements BaseService<Customer> {

    @Autowired
    private CustomersMapper customersMapper;

    @Override
    public List<Customer> findAll() {
        return customersMapper.findAll();
    }

    @Override
    public List<Customer> findById(String id) {
        return customersMapper.findById(id);
    }

    @Override
    public void insert(Customer customer) {
        customersMapper.insert(customer);
    }

    @Override
    public void update(Customer customer) {
        customersMapper.update(customer);
    }

    @Override
    public void delete(String... ids) {
        for(String id:ids){
            customersMapper.delete(id);
        }
    }
}
