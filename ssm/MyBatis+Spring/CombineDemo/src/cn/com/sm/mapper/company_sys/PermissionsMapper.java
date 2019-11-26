package cn.com.sm.mapper.company_sys;



import cn.com.sm.po.company_sys.Permission;

import java.util.List;

public interface PermissionsMapper {
    List<Permission> findAll() throws Exception;
    List<Permission> findById(Long id) throws Exception;
    void insert(Permission permission) throws Exception;
    void update(Permission permission) throws Exception;
    void delete(Long id) throws Exception;
}
