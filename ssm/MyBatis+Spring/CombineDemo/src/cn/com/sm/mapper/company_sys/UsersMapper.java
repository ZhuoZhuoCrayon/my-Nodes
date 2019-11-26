package cn.com.sm.mapper.company_sys;

import cn.com.sm.po.company_sys.User;

import java.util.List;
import java.util.Set;

public interface UsersMapper {
    User findByName(String username) throws Exception;
    List<User> findById(Long id) throws Exception;
    Set<String> getRoles(String username) throws Exception;
    Set<String> getPermissions(String username) throws Exception;
    void insert(User user) throws Exception;
    void update(User user) throws Exception;
    void delete(Long id) throws Exception;
}
