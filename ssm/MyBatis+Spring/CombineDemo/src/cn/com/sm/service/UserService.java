package cn.com.sm.service;

import cn.com.sm.po.Result;
import cn.com.sm.po.company_sys.User;

import java.util.List;

public interface UserService extends BaseService<User>{
    User findByName(String username);
    List<User> findById(Long id);
    Result delete(Long... ids);
    Result changePassword(Long id,String newPassword);
}
