package cn.com.sm.service;

import cn.com.sm.po.Result;
import cn.com.sm.po.company_sys.User;

import java.util.List;
import java.util.Set;

public interface UserService extends BaseService<User>{
    User findByName(String username);
    List<User> findById(Long id);
    Set<String> getRoles(String username);
    Set<String> getPermissions(String username);
    Result delete(Long... ids);
    Result changePassword(Long id,String newPassword);
}
