package cn.com.mybatis.test;

import cn.com.mybatis.datasource.DataConnection;
import cn.com.mybatis.po.*;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MybatisTest {
    public DataConnection dataConnection = new DataConnection();
    @Test
    public void testSelect() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        List<Emp> empList = sqlSession.selectList("EmpMapper.selectAll");
        sqlSession.close();
        for(Emp emp:empList){
            System.out.println(emp.toString());
        }
    }
    @Test
    public void findById() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        Emp emp = sqlSession.selectOne("EmpMapper.findById",7876);
        sqlSession.close();
        System.out.println(emp.toString());
    }

    @Test
    public void findByName() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        List<Emp> empList = sqlSession.selectList("EmpMapper.findByName","J");
        sqlSession.close();
        for(Emp emp:empList){
            System.out.println(emp.toString());
        }

    }

    @Test
    public void getSimpleInfo() throws IOException{
        SqlSession sqlSession  = dataConnection.getSqlSession();
        List<SimpleEmp> simpleEmps = sqlSession.selectList("EmpMapper.getSimpleInfo");
        sqlSession.close();
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
        sqlSession.close();
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
        sqlSession.close();
        System.out.println(emp1.toString());
    }
    @Test
    public void deleteById() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        sqlSession.delete("EmpMapper.deleteById",4836);
        sqlSession.close();
        testSelect();
    }

    @Test
    public void getEmpInfo() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        List<EmpInfo> empInfos = sqlSession.selectList("CombineMapper.getEmpInfo");
        sqlSession.close();
        for(EmpInfo empInfo:empInfos){
            System.out.println(empInfo.toString());
        }
    }

    @Test
    public void getDeptInfo() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        DeptInfo deptInfo = sqlSession.selectOne("CombineMapper.getDeptInfo",7839);
        sqlSession.close();
        System.out.println(deptInfo.toString());
    }

    @Test
    public void getDeptMember() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        DeptMember deptMember = sqlSession.selectOne("CombineMapper.getDeptMember",10);
        sqlSession.close();
        for(Emp emp:deptMember.getEmpList()){
            System.out.println(emp.toString());
        }
    }

    @Test
    /**
     * @do: 测试嵌套类查询
     * @Result: collection标签的column是作为查询主键，支持下一查询
     *
     * @do: 测试HashMap返回
     * @Result: HashMap返回<String,Object>键值对，支持映射多种查询结果，value数据类型可互异
     */
    public void getUserDeptById() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        HashMap<String,Object>  userDept = (HashMap<String, Object>) sqlSession.
                                            selectOne("CombineMapper.getUserDeptById",7839);
        sqlSession.close();
        for(Emp emp:(List<Emp>)userDept.get("empList")){
            System.out.println(emp);
        }
        System.out.println(userDept.get("empno"));
    }
}
