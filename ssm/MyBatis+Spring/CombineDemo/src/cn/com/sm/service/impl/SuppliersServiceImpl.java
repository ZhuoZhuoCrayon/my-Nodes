package cn.com.sm.service.impl;

import cn.com.sm.mapper.SuppliersMapper;
import cn.com.sm.po.Supplier;
import cn.com.sm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuppliersServiceImpl implements BaseService<Supplier> {
    @Autowired
    private SuppliersMapper suppliersMapper;
    @Override
    public List<Supplier> findAll() throws Exception {
        return suppliersMapper.findAll();
    }

    @Override
    public List<Supplier> findById(String id) throws Exception {
        return suppliersMapper.findById(id);
    }

    @Override
    public void insert(Supplier supplier) throws Exception {
        suppliersMapper.insert(supplier);
    }

    @Override
    public void update(Supplier supplier) throws Exception {
        suppliersMapper.update(supplier);
    }

    @Override
    public void delete(String... ids) throws Exception {
        for(String id:ids){
            suppliersMapper.delete(id);
        }
    }
}
