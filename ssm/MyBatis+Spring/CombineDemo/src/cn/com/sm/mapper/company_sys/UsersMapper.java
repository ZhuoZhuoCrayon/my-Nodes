package cn.com.sm.mapper.company_sys;

import cn.com.sm.po.company_sys.User;

import java.util.List;

public interface UsersMapper {
    User findByName(String username) throws Exception;
    List<User> findById(Long id) throws Exception;
    void insert(User user) throws Exception;
    void update(User user) throws Exception;
    void delete(Long id) throws Exception;
}
