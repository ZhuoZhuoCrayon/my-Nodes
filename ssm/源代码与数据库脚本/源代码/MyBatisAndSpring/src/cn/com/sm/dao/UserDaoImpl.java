package cn.com.sm.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import cn.com.sm.po.User;
public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{  
    public User findUserById(int id) throws Exception {  
        //继承SqlSessionDaoSupport类，通过this.getSqlSession()得到sqlSession  
        SqlSession sqlSession=this.getSqlSession();  
        User user=sqlSession.selectOne("test.findUserById",id);  
        return user;  
    }  
}  
