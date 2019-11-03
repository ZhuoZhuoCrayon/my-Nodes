package cn.com.sm.test;

import cn.com.sm.dao.EmployeeDao;
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
        EmployeeDao employeeDao = (EmployeeDao) applicationContext.getBean("EmployeeDao");
        List<Employee> employeeList = employeeDao.selectAll();

        for(Employee employee:employeeList){
            System.out.println(employee.toString());
        }
    }
}
