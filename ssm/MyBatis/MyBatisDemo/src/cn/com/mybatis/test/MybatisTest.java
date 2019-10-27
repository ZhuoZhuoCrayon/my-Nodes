package cn.com.mybatis.test;

import cn.com.mybatis.datasource.DataConnection;
import cn.com.mybatis.po.Emp;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MybatisTest {
    public DataConnection dataConnection = new DataConnection();
    @Test
    public void TestSelect() throws IOException{
        SqlSession sqlSession = dataConnection.getSqlSession();
        List<Emp> empList = sqlSession.selectList("EmpMapper.selectAll");

        for(Emp emp:empList){
            System.out.println(emp.toString());
        }
    }
}
