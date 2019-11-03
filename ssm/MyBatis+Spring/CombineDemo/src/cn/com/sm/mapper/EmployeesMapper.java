package cn.com.sm.mapper;


import cn.com.sm.po.Employee;

import java.util.List;

public interface EmployeesMapper {
    public List<Employee> selectAll() throws Exception;
}
