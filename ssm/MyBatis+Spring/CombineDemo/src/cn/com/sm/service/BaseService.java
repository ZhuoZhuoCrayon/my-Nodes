package cn.com.sm.service;

import cn.com.sm.po.Result;

import java.util.List;

public interface BaseService<T> {

    /**
     * 获取表单
     * @return
     */
    List<T> findAll ();

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
    Result insert(T t);

    /**
     * 更新bug：如果撞id，会导致修改另外一行
     * 所以是需要两个参数的，原id和修改的元组
     */
    /**
     * 更新
     * @param t
     */
    Result update(T t);

    /**
     * 批量删除
     * @param ids
     */
    Result delete(String...ids);

    /**
     * 格式检查
     * @param t
     * @return 格式错误信息
     */
    String checkFormat(T t);
}
