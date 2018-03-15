package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.SyncProductDAO;
import com.freesheep.biz.model.SyncProductBO;
import com.freesheep.biz.service.SyncProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SyncProductServiceImpl implements SyncProductService {

    @Resource
    SyncProductDAO syncProductDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SyncProductBO record) {
        return 0;
    }

    @Override
    public int insertSelective(SyncProductBO record) {
        return syncProductDAO.insertSelective(record);
    }

    @Override
    public SyncProductBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SyncProductBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SyncProductBO record) {
        return 0;
    }
}
