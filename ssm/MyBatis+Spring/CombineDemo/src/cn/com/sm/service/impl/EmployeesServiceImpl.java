package cn.com.sm.service.impl;

import cn.com.sm.mapper.EmployeesMapper;
import cn.com.sm.po.Employee;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements BaseService<Employee> {

    @Autowired
    private EmployeesMapper employeesMapper;
    @Override
    public List<Employee> findAll() throws Exception {
        return employeesMapper.findAll();
    }

    @Override
    public List<Employee> findById(String id) {
        return null;
    }

    @Override
    public void insert(Employee employee) {

    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(String... ids) {

    }
}
