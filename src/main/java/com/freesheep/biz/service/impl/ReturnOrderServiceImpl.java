package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.ReturnOrderDAO;
import com.freesheep.biz.model.ReturnOrderBO;
import com.freesheep.biz.service.ReturnOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {

    @Resource
    ReturnOrderDAO returnOrderDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ReturnOrderBO record) {
        return 0;
    }

    @Override
    public long insertSelective(ReturnOrderBO record) {
        int low = returnOrderDAO.insertSelective(record);
        if (low < 1) return 0;
        return record.getId();
    }

    @Override
    public ReturnOrderBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ReturnOrderBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ReturnOrderBO record) {
        return 0;
    }
}
