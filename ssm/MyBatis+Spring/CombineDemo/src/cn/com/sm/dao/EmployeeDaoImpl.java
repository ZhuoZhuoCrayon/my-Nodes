package cn.com.sm.dao;

import cn.com.sm.po.Employee;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class EmployeeDaoImpl extends SqlSessionDaoSupport implements EmployeeDao{

    @Override
    public List<Employee> selectAll() throws Exception {
        SqlSession sqlSession = this.getSqlSession();
        List<Employee> employees = sqlSession.selectList("EmployeesMapper.selectAll");
        return employees;
    }
}
