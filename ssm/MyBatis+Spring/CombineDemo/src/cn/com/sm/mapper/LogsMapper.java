package cn.com.sm.mapper;

import cn.com.sm.po.Log;

import java.util.List;

public interface LogsMapper {
    List<Log> findAll() throws Exception;
    List<Log> findById(Integer id) throws Exception;
    void insert(Log log) throws Exception;
    void update(Log log) throws Exception;
    void delete(Integer id) throws Exception;
}
