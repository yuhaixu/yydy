package com.freesheep.biz.service.impl;

import com.freesheep.biz.dao.StDeviceStatusDAO;
import com.freesheep.biz.model.StDeviceStatusBO;
import com.freesheep.biz.service.StDeviceStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StDeviceStatusServiceImpl implements StDeviceStatusService {

    @Resource
    StDeviceStatusDAO statusDAO;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(StDeviceStatusBO record) {
        return 0;
    }

    @Override
    public int insertSelective(StDeviceStatusBO record) {
        return 0;
    }

    @Override
    public StDeviceStatusBO selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(StDeviceStatusBO record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(StDeviceStatusBO record) {
        return 0;
    }
}
