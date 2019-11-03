package cn.com.sm.dao;

import cn.com.sm.po.Employee;

import java.util.List;

public interface EmployeeDao {
    public List<Employee> selectAll() throws Exception;
}
