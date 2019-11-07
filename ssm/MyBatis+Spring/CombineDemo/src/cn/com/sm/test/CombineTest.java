package cn.com.sm.test;

import cn.com.sm.mapper.EmployeesMapper;
import cn.com.sm.po.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class CombineTest {
    private ApplicationContext applicationContext;

    @Before
    public void setup() throws Exception{
        applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext.xml");
    }

    @Test
    public void testSelectAll() throws Exception{
        EmployeesMapper employeesMapper = (EmployeesMapper)applicationContext.
                getBean("employeesMapper");
        List<Employee> employeeList = employeesMapper.findAll();
        for(Employee employee:employeeList){
            System.out.println(employee.toString());
        }
    }
}
