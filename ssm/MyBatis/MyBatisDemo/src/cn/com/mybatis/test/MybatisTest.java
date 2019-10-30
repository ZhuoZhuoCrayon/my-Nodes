package cn.com.mybatis.test;

import cn.com.mybatis.datasource.DataConnection;
import cn.com.mybatis.po.Emp;
import cn.com.mybatis.po.SimpleEmp;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    public DataConnection dataConnection = new DataConnection();
    @Test
    public void testSelect() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        List<Emp> empList = sqlSession.selectList("EmpMapper.selectAll");

        for(Emp emp:empList){
            System.out.println(emp.toString());
        }
    }
    @Test
    public void findById() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        Emp emp = sqlSession.selectOne("EmpMapper.findById",7876);
        System.out.println(emp.toString());
    }

    @Test
    public void findByName() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        List<Emp> empList = sqlSession.selectList("EmpMapper.findByName","J");
        for(Emp emp:empList){
            System.out.println(emp.toString());
        }
    }

    @Test
    public void getSimpleInfo() throws IOException{
        SqlSession sqlSession  = dataConnection.getSqlSession();
        List<SimpleEmp> simpleEmps = sqlSession.selectList("EmpMapper.getSimpleInfo");

        for (SimpleEmp simpleEmp:simpleEmps){
            System.out.println(simpleEmp.toString());
        }
    }

    @Test
    public void insertEmp() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        Emp emp = new Emp();
        emp.setEname("CXX");
        emp.setEmpno(4836);
        emp.setJob("SALEMANS");
        emp.setMgb(7900);
        emp.setHiredate(new Date());
        emp.setSal(0);
        emp.setComm(null);
        emp.setDeptno(10);

        sqlSession.insert("EmpMapper.insertEmp",emp);
        //sqlSession.delete("EmpMapper.deleteById",4836);
        List<Emp> emps = sqlSession.selectList("EmpMapper.findByName","CXX");

        for(Emp emp1:emps){
            System.out.println(emp1.toString());
        }
    }

    @Test
    public void updateEmp() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        Emp emp = new Emp();
        emp.setEmpno(4836);
        emp.setJob("STUDENT");
        emp.setComm(100000);

        sqlSession.update("EmpMapper.updateEmp",emp);
        //sqlSession.delete("EmpMapper.deleteById",4836);
        Emp emp1 = sqlSession.selectOne("EmpMapper.findById",4836);
        System.out.println(emp1.toString());
    }
}
