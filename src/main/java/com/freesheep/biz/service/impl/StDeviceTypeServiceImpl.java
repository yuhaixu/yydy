package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StDeviceTypeDAO;
import com.freesheep.biz.model.StDeviceTypeBO;
import com.freesheep.biz.service.StDeviceTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StDeviceTypeServiceImpl implements StDeviceTypeService {

    @Resource
    StDeviceTypeDAO dao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(StDeviceTypeBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StDeviceTypeBO record) {
        return dao.insertSelective(record);
    }

    @Override
    public StDeviceTypeBO selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StDeviceTypeBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StDeviceTypeBO record) {
        return 0;
    }
}
