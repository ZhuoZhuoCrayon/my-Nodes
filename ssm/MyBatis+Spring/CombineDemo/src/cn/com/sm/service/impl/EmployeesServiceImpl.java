package cn.com.sm.service.impl;

import cn.com.sm.mapper.EmployeesMapper;
import cn.com.sm.po.Employee;
import cn.com.sm.po.Result;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesServiceImpl implements BaseService<Employee> {

    @Autowired
    private EmployeesMapper employeesMapper;
    @Override
    public List<Employee> findAll(){
        try{
            return employeesMapper.findAll();
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Employee> findById(String id){
        try{
            return employeesMapper.findById(id);
        }catch (Exception e){
            System.out.println(e.toString());
            return new ArrayList<>();
        }

    }

    @Override
    public Result insert(Employee employee)
    {
        try{
            employeesMapper.insert(employee);
            return new Result(true,
                    "insert [" + employee.getEid() +
                            "] in employees successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"insert[" +
                    employee.getEid() + "] in employees Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result update(Employee employee){
        try{
            employeesMapper.update(employee);
            return new Result(true,
                    "update [" + employee.getEid() +
                            "] in employees successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"update[" +
                    employee.getEid() + "] in employees Failed\n" + e.getMessage());
        }
    }

    @Override
    public Result delete(String... ids){
        try {
            for (String id : ids) {
                if (employeesMapper.findById(id).size() == 0){
                    return new Result(false,
                            id + "not existed in employees");
                }
            }
            String idStr = "";
            for(String id : ids){
                employeesMapper.delete(id);
                idStr += "[" + id + "]";
            }
            return new Result(true,
                    "delete" + idStr +
                            "in employees successfully");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,
                    "error from delete on employees\n" +
                            e.getMessage());
        }
    }

    @Override
    public String checkFormat(Employee employee) {
        return null;
    }
}
