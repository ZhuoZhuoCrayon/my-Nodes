package cn.com.sm.mapper;


import cn.com.sm.po.Employee;

import java.util.List;

public interface EmployeesMapper {
    List<Employee> findAll() throws Exception;
    List<Employee> findById(String id) throws Exception;
    void insert(Employee employee) throws Exception;
    void update(Employee employee) throws Exception;
    void delete(String id) throws Exception;
}