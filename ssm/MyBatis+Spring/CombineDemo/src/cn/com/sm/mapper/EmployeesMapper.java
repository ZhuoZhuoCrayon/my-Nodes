package cn.com.sm.mapper;


import cn.com.sm.po.Employee;

import java.util.List;

public interface EmployeesMapper {
    List<Employee> findAll();
    List<Employee> findById(String id);
    void insert(Employee employee);
    void update(Employee employee);
    void delete(String id);
}