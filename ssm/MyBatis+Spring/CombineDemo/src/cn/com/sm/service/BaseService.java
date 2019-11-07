package cn.com.sm.service;

import java.util.List;

public interface BaseService<T> {

    /**
     * 获取表单
     * @return
     */
    List<T> findAll() throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    List<T> findById(String id);

    /**
     * 插入数据
     * @param t
     */
    void insert(T t);

    /**
     * 更新
     * @param t
     */
    void update(T t);

    /**
     * 批量删除
     * @param ids
     */
    void delete(String...ids);
}
