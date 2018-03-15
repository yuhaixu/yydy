package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.IntoWarehouseOrderDAO;
import com.freesheep.biz.model.IntoWarehouseOrderBO;
import com.freesheep.biz.service.IntoWarehouseOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class IntoWarehouseOrderServiceImpl implements IntoWarehouseOrderService {

    @Resource
    IntoWarehouseOrderDAO intoWarehouseOrderDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(IntoWarehouseOrderBO record) {
        return 0;
    }

    @Override
    public long insertSelective(IntoWarehouseOrderBO record) {
        int low = intoWarehouseOrderDAO.insertSelective(record);
        if (low < 1) return 0;
        return record.getId();
    }

    @Override
    public IntoWarehouseOrderBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(IntoWarehouseOrderBO record) {
        return intoWarehouseOrderDAO.updateByIdSelective(record);
    }

    @Override
    public int updateByPrimaryKey(IntoWarehouseOrderBO record) {
        return 0;
    }

    @Override
    public IntoWarehouseOrderBO selectIntoWarehouseOrderByNum(String systemNum) {
        return intoWarehouseOrderDAO.selectIntoWarehouseOrderByNum(systemNum);
    }
}
