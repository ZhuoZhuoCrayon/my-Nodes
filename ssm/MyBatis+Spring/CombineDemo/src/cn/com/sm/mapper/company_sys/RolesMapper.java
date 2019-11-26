package cn.com.sm.mapper.company_sys;


import cn.com.sm.po.company_sys.Role;

import java.util.List;

public interface RolesMapper {
    List<Role> findAll() throws Exception;
    List<Role> findById(Long id) throws Exception;
    void insert(Role role) throws Exception;
    void update(Role role) throws Exception;
    void delete(Long id) throws Exception;
}
